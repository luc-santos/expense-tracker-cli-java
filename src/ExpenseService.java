import java.time.LocalDate;
import java.util.List;

public class ExpenseService {
    private final List<Expense> expenses;
    private final ExpenseRepository repository;

    public ExpenseService() {
        this.repository = new ExpenseRepository();
        this.expenses = repository.loadExpenses();
    }

    public void createExpense(String description, double amount, String category, String date) {
        int id = getNextId();

        Expense expense = new Expense(id, description, amount, category, date);

        expenses.add(expense);
        repository.saveExpenses(expenses);

        System.out.println("Expense created successfully!");
    }

    public void listExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses found.");
            return;
        }

        for (Expense expense : expenses) {
            System.out.println(expense);
        }
    }

    public void markExpenseAsPaid(int id) {
        Expense expense = findExpenseById(id);

        if (expense == null) {
            System.out.println("Expense not found.");
            return;
        }

        if (expense.isPaid()) {
            System.out.println("Expense is already paid.");
            return;
        }

        expense.markAsPaid();
        repository.saveExpenses(expenses);

        System.out.println("Expense marked as paid!");
    }

    public void filterByCategory(String category) {
        boolean found = false;

        for (Expense expense : expenses) {
            if (expense.getCategory().equalsIgnoreCase(category)) {
                System.out.println(expense);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No expenses found for this category.");
        }
    }

    public void filterByDate(String date) {
        LocalDate targetDate = LocalDate.parse(date);
        boolean found = false;

        for (Expense expense : expenses) {
            if (expense.getDate().equals(targetDate)) {
                System.out.println(expense);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No expenses found for this date.");
        }
    }

    public void showTotal() {
        double total = 0;

        for (Expense expense : expenses) {
            total += expense.getAmount();
        }

        System.out.println("Total: R$" + String.format("%.2f", total));
    }

    public void showTotalByCategory(String category) {
        double total = 0;
        boolean found = false;

        for (Expense expense : expenses) {
            if (expense.getCategory().equalsIgnoreCase(category)) {
                total += expense.getAmount();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No expenses found for this category.");
            return;
        }

        System.out.println("Total for " + category + ": R$" + String.format("%.2f", total));
    }

    public void listPaidExpenses() {
        listByPaidStatus(true);
    }

    public void listPendingExpenses() {
        listByPaidStatus(false);
    }

    public boolean hasNoExpenses() {
        return expenses.isEmpty();
    }

    private void listByPaidStatus(boolean paid) {
        boolean found = false;

        for (Expense expense : expenses) {
            if (expense.isPaid() == paid) {
                System.out.println(expense);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No expenses found.");
        }
    }

    private Expense findExpenseById(int id) {
        for (Expense expense : expenses) {
            if (expense.getId() == id) {
                return expense;
            }
        }

        return null;
    }

    public void deletePaidExpenses() {
    boolean removed = expenses.removeIf(expense -> expense.isPaid());

    if (!removed) {
        System.out.println("No paid expenses to delete.");
        return;
    }

    repository.saveExpenses(expenses);
    System.out.println("Paid expenses deleted successfully!");
}

    private int getNextId() {
        int maxId = 0;

        for (Expense expense : expenses) {
            if (expense.getId() > maxId) {
                maxId = expense.getId();
            }
        }

        return maxId + 1;
    }
}
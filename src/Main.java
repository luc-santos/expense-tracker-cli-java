import java.util.Scanner;
import java.time.format.DateTimeParseException;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ExpenseService expenseService = new ExpenseService();

    public static void main(String[] args) {
        while (true) {
            showMenu();

            String option = scanner.nextLine();

            if (option.equals("1")) {
                System.out.print("Enter description: ");
                String description = scanner.nextLine();

                System.out.print("Enter amount (XX.XX): ");
                double amount;

                try {
                    amount = Double.parseDouble(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid amount.");
                    continue;
                }

                System.out.print("Enter category: ");
                String category = scanner.nextLine();

                System.out.print("Enter date (YYYY-MM-DD): ");
                String date = scanner.nextLine();

                try {
                    expenseService.createExpense(description, amount, category, date);
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date format. Use YYYY-MM-DD.");
                }

            } else if (option.equals("2")) {
                expenseService.listExpenses();

            } else if (option.equals("3")) {
                expenseService.listPendingExpenses();

            } else if (option.equals("4")) {
                expenseService.listPaidExpenses();

            } else if (option.equals("5")) {
                System.out.print("Enter category: ");
                String category = scanner.nextLine();
                expenseService.filterByCategory(category);

            } else if (option.equals("6")) {
                System.out.print("Enter the date (YYYY-MM-DD): ");
                String date = scanner.nextLine();

                try {
                    expenseService.filterByDate(date);
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date format. Use YYYY-MM-DD.");
                }

            } else if (option.equals("7")) {
                expenseService.showTotal();

            } else if (option.equals("8")) {
                System.out.print("Enter category: ");
                String category = scanner.nextLine();
                expenseService.showTotalByCategory(category);

            } else if (option.equals("9")) {
                System.out.print("Enter expense ID: ");

                try {
                    int id = Integer.parseInt(scanner.nextLine());
                    expenseService.markExpenseAsPaid(id);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid ID.");
                }

            } else if (option.equals("10")) {
                System.out.print("Are you sure you want to delete all paid expenses? (y/n): ");
                String confirmation = scanner.nextLine();

                if (confirmation.equalsIgnoreCase("y")) {
                    expenseService.deletePaidExpenses();
                } else {
                    System.out.println("Operation cancelled.");
                }

            } else if (option.equals("0")) {
                System.out.println("Exiting...");
                break;

            } else {
                System.out.println("Invalid option.");
            }
        }
    }

    public static void showMenu() {
        System.out.println("\n=== EXPENSE CLI ===");
        System.out.println("1 - Add expense");
        System.out.println("2 - List all expenses");
        System.out.println("3 - List pending expenses");
        System.out.println("4 - List paid expenses");
        System.out.println("5 - Filter by category");
        System.out.println("6 - Filter by date");
        System.out.println("7 - Show total");
        System.out.println("8 - Show total by category");
        System.out.println("9 - Mark expense as paid");
        System.out.println("10 - Delete all paid expenses");
        System.out.println("0 - Exit");
    }
}
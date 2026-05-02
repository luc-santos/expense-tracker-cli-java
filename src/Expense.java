import java.time.LocalDate;

public class Expense {
    private final int id;
    private final String description;
    private final double amount;
    private final String category;
    private final LocalDate date;
    private boolean paid;

    public Expense(int id, String description, double amount, 
    String category, String date){
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.date = LocalDate.parse(date);
        this.paid = false;
    }

    public void markAsPaid() {
        if (this.paid) {
            return;
        }

        this.paid = true;
    }

    public int getId(){
        return id;
    }

    public String getDescription(){
        return description;
    }

    public double getAmount(){
        return amount;
    }

    public String getCategory(){
        return category;
    }

    public LocalDate getDate(){
        return date;
    }

    public boolean isPaid() {
        return paid;
    }

    @Override
    public String toString() {
        String icon = amount > 100 ? "⚠️" : "💸";
        String status = paid ? "✅ PAID" : "⏳ PENDING";

        return "[" + id + "] " + icon + " R$" + String.format("%.2f", amount)
                + " | " + description
                + " | " + category
                + " | " + date
                + " | " + status;
    }
}
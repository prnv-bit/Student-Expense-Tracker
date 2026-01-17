public class Expense {
    private String date;
    private double amount;
    private String category;
    private String description;

    public Expense(String date, double amount, String category, String description) {
        this.date = date;
        this.amount = amount;
        this.category = category;
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return date + " | ₹" + amount + " | " + category + " | " + description;
    }

    public String toFileString() {
        return date + "," + amount + "," + category + "," + description;
    }
    public String getDate() {
    return date;
    }

    public String getDescription() {
        return description;
    }

}

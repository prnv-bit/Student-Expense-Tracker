import java.util.*;

public class ExpenseManager {
    private List<Expense> expenses;

    public ExpenseManager() {
        expenses = FileHandler.loadExpenses();
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
        FileHandler.saveExpense(expense);
    }

    public void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded.");
            return;
        }
        for (Expense e : expenses) {
            System.out.println(e);
        }
    }

    public void totalSpending() {
        double total = 0;
        for (Expense e : expenses) {
            total += e.getAmount();
        }
        System.out.println("Total Spending: ₹" + total);
    }

    public void filterByCategory(String category) {
        boolean found = false;
        for (Expense e : expenses) {
            if (e.getCategory().equalsIgnoreCase(category)) {
                System.out.println(e);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No expenses found in this category.");
        }
    }
}

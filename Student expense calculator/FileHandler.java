import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static final String FILE_NAME = "expenses.txt";

    public static void saveExpense(Expense expense) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            bw.write(expense.toFileString());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error saving expense.");
        }
    }

   public static List<Expense> loadExpenses() {
    List<Expense> expenses = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");

            // SAFETY CHECK
            if (data.length == 4) {
                expenses.add(new Expense(
                        data[0],
                        Double.parseDouble(data[1]),
                        data[2],
                        data[3]
                ));
            }
        }
    } catch (IOException e) {
        // File may not exist initially
    }
    return expenses;
}

}

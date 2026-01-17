import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class Main {

    public static void main(String[] args) {
        ExpenseManager manager = new ExpenseManager();

        JFrame frame = new JFrame("Student Expense Tracker");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // ===== Title =====
        JLabel title = new JLabel("Student Expense Tracker", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(title, BorderLayout.NORTH);

        // ===== Buttons Panel =====
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));

        JButton addBtn = new JButton("Add Expense");
        JButton viewBtn = new JButton("View Expenses");
        JButton totalBtn = new JButton("Total Spending");
        JButton filterBtn = new JButton("Filter by Category");
        JButton exitBtn = new JButton("Exit");

        panel.add(addBtn);
        panel.add(viewBtn);
        panel.add(totalBtn);
        panel.add(filterBtn);
        panel.add(exitBtn);

        frame.add(panel, BorderLayout.CENTER);

        // ===== Button Actions =====

        // Add Expense
        addBtn.addActionListener(e -> {
            JTextField date = new JTextField();
            JTextField amount = new JTextField();
            JTextField category = new JTextField();
            JTextField desc = new JTextField();

            Object[] inputs = {
                    "Date (DD-MM-YYYY):", date,
                    "Amount:", amount,
                    "Category:", category,
                    "Description:", desc
            };

            int result = JOptionPane.showConfirmDialog(
                    frame, inputs, "Add Expense", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                try {
                    Expense expense = new Expense(
                            date.getText(),
                            Double.parseDouble(amount.getText()),
                            category.getText(),
                            desc.getText()
                    );
                    manager.addExpense(expense);
                    JOptionPane.showMessageDialog(frame, "Expense added successfully!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input!");
                }
            }
        });

        viewBtn.addActionListener(e -> {

            String[] columns = {"Date", "Amount", "Category", "Description"};
            DefaultTableModel model = new DefaultTableModel(columns, 0);

            for (Expense exp : FileHandler.loadExpenses()) {
                Object[] row = {
                    exp.getDate(),
                    exp.getAmount(),
                    exp.getCategory(),
                    exp.getDescription()
                };
                model.addRow(row);
            }

            JTable table = new JTable(model);
            table.setEnabled(false); // read-only

            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setPreferredSize(new Dimension(450, 200));

            JOptionPane.showMessageDialog(
                frame,
                scrollPane,
                "All Expenses",
                JOptionPane.INFORMATION_MESSAGE
            );
        });


        // Total Spending
        totalBtn.addActionListener(e -> {
            double total = 0;
            for (Expense exp : FileHandler.loadExpenses()) {
                total += exp.getAmount();
            }
            JOptionPane.showMessageDialog(frame, "Total Spending: ₹" + total);
        });

        // Filter by Category
        filterBtn.addActionListener(e -> {
            String cat = JOptionPane.showInputDialog(frame, "Enter Category:");
            JTextArea area = new JTextArea(10, 30);
            area.setEditable(false);

            boolean found = false;
            for (Expense exp : FileHandler.loadExpenses()) {
                if (exp.getCategory().equalsIgnoreCase(cat)) {
                    area.append(exp.toString() + "\n");
                    found = true;
                }
            }

            if (!found) {
                area.setText("No expenses found for this category.");
            }

            JOptionPane.showMessageDialog(frame, new JScrollPane(area),
                    "Filtered Expenses", JOptionPane.INFORMATION_MESSAGE);
        });

        // Exit
        exitBtn.addActionListener(e -> System.exit(0));

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

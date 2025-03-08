import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import java.util.Random;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;

public class Transaction extends JFrame implements ActionListener {
    JTextField orderIdTextField;
    JTextArea orderItemsTextArea;
    JTextField payBillTextField;
    double totalBill = 0;

    public void viewTransactions() {
        JFrame f = new JFrame();
        f.setTitle("View Transactions");
        f.setLocationRelativeTo(null);
        //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Call the openEmployee method of the Employee class when the window is closed
                new Employee().openEmployee();
            }
        });


        JPanel mainPanel = new JPanel(new BorderLayout());
        f.getContentPane().add(mainPanel, BorderLayout.CENTER);

        try {
            ResConn resConn = new ResConn();
            String query = "SELECT TransactionID, OrderID, Timestamp, Amount FROM transactions";
            PreparedStatement statement = resConn.c.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            // Get ResultSet metadata to determine the number of columns
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Create arrays for data and column names
            Object[][] data = new Object[100][columnCount];
            String[] columnNames = new String[columnCount];

            // Populate column names
            for (int i = 0; i < columnCount; i++) {
                columnNames[i] = metaData.getColumnLabel(i + 1);
            }

            // Populate data from ResultSet
            int rowCount = 0;
            while (resultSet.next()) {
                for (int i = 0; i < columnCount; i++) {
                    data[rowCount][i] = resultSet.getObject(i + 1);
                }
                rowCount++;
            }

            // Resize data array to fit the actual number of rows
            Object[][] trimmedData = new Object[rowCount][columnCount];
            System.arraycopy(data, 0, trimmedData, 0, rowCount);

            // Add table to main panel
            addTable(mainPanel, trimmedData, columnNames);

            // Close resources
            resultSet.close();
            statement.close();
            resConn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Pack the frame to adjust its size based on the table's preferred size
        f.pack();
        // Set minimum size to ensure it doesn't become too small
        f.setMinimumSize(f.getSize());
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    // Method to add a table
    private void addTable(JPanel mainPanel, Object[][] data, String[] columnNames) {
        JTable table = new JTable(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make cells non-editable
            }
        };
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        // Set custom renderer for cell background
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();

        // Set the background color of the table scroll pane to black
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBackground(Color.BLACK);

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Set header cell renderer
        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.decode("#F04A00"));
        header.setForeground(Color.WHITE);
    }

    public void payBill() {
        JFrame f = new JFrame();

        f.setTitle("Pay Bill");
        f.setSize(400, 300);
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // Hide instead of dispose
        // Your existing code for creating the item viewer UI...

        // Add a window listener to handle frame close event
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                new Customer().openCustomer();

            }
        });

        JPanel mainPanel = new JPanel(null); // Set layout to null
        mainPanel.setBackground(Color.BLACK); // Set background color of main panel

        // Panel for order ID
        JPanel orderIdPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        orderIdPanel.setBounds(10, 10, 380, 30); // Set bounds for order ID panel
        orderIdPanel.setBackground(Color.BLACK); // Set background color of order ID panel
        JLabel orderIdLabel = new JLabel("Order ID:");
        orderIdLabel.setForeground(Color.WHITE); // Set foreground color of order ID label
        orderIdTextField = new JTextField(10);
        orderIdPanel.add(orderIdLabel);
        orderIdPanel.add(orderIdTextField);
        JButton okButton = new JButton("OK");
        okButton.setBackground(new Color(0xF04A00));
        okButton.setForeground(Color.white);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayOrderItems();
            }
        });
        orderIdPanel.add(okButton); // Add OK button
        mainPanel.add(orderIdPanel);

        // Panel for order items
        JPanel orderItemsPanel = new JPanel(new BorderLayout());
        orderItemsPanel.setBounds(10, 50, 380, 150); // Set bounds for order items panel
        orderItemsPanel.setBackground(Color.BLACK); // Set background color of order items panel
        JLabel orderItemsLabel = new JLabel("Ordered Items:");
        orderItemsLabel.setForeground(Color.WHITE); // Set foreground color of order items label
        orderItemsTextArea = new JTextArea();
        orderItemsTextArea.setEditable(false);
        // orderItemsTextArea.setForeground(Color.WHITE); // Set foreground color of
        // text area
        JScrollPane orderItemsScrollPane = new JScrollPane(orderItemsTextArea);
        orderItemsPanel.add(orderItemsLabel, BorderLayout.NORTH);
        orderItemsPanel.add(orderItemsScrollPane, BorderLayout.CENTER);
        mainPanel.add(orderItemsPanel);

        // Panel for pay bill
        JPanel payBillPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        payBillPanel.setBounds(10, 210, 380, 30); // Set bounds for pay bill panel
        payBillPanel.setBackground(Color.BLACK); // Set background color of pay bill panel
        JLabel payBillLabel = new JLabel("Pay Bill:");
        payBillLabel.setForeground(Color.WHITE); // Set foreground color of pay bill label
        payBillTextField = new JTextField(10);
        payBillPanel.add(payBillLabel);
        payBillPanel.add(payBillTextField);
        JButton payButton = new JButton("Pay");
        payButton.setBackground(new Color(0xF04A00));
        payButton.setForeground(Color.white);

        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call the checkPayment method to verify payment and display message accordingly
                try {
                    Random ran=new Random();
                    int tranID=ran.nextInt(10000);
                    int customerID=0;
                    double totalAmount=0.0;
                    ResConn resConn = new ResConn();
                    String query2="SELECT CustomerID,totalAmount FROM orders where OrderID=?";
                    String query = "INSERT INTO transactions (TransactionID,OrderID, CustomerID, Timestamp, Amount) VALUES (?,?, ?, NOW(), ?)";
                    int OrderID = Integer.parseInt(orderIdTextField.getText());
                    PreparedStatement ps1 = resConn.c.prepareStatement(query2);
                    ps1.setInt(1, OrderID); // Assuming OrderID is a variable holding the order ID
                    ResultSet rs=ps1.executeQuery();
                    if(rs.next()){
                        customerID=rs.getInt("CustomerID");
                        totalAmount=rs.getDouble("totalAmount");
                    }
                    ps1.close();

                    PreparedStatement ps = resConn.c.prepareStatement(query);
                    ps.setInt(1, tranID);
                    ps.setInt(2, OrderID); // Assuming OrderID is a variable holding the order ID
                    ps.setInt(3, customerID); // Assuming customerID is a variable holding the customer ID
                    ps.setDouble(4, totalAmount); // Assuming totalAmount is a variable holding the total amount
                    ps.executeUpdate();
                    ps.close();
                    resConn.close();
                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                    throw new RuntimeException(ex);
                }


                if (checkPayment()) {
                    JOptionPane.showMessageDialog(Transaction.this, "Your bill is successfully paid. ");
                    clearFields();

                } else {
                    JOptionPane.showMessageDialog(Transaction.this, "Insufficient amount paid. Please pay the total bill amount.");
                    clearPayBillText();
                }
            }
        });
        payBillPanel.add(payButton);
        mainPanel.add(payBillPanel);

        f.add(mainPanel);
        //f.setLayout(null);
        f.setVisible(true);
    }
    private void clearFields() {
        orderIdTextField.setText("");
        orderItemsTextArea.setText("");
        payBillTextField.setText("");
    }
    private void clearPayBillText(){
        payBillTextField.setText("");
    }

    void displayOrderItems() {
        String orderId = orderIdTextField.getText();
        // Perform operations to retrieve order items based on order ID
        // For demonstration, let's assume order items are fetched from a database
        String orderItems = fetchOrderItemsFromDB(orderId);
        orderItemsTextArea.setText(orderItems);
    }


    // Dummy method to fetch order items from a database
    String fetchOrderItemsFromDB(String orderId) {
        StringBuilder orderDetails = new StringBuilder();
        try {
            ResConn resConn = new ResConn();
            // Execute SQL query to fetch order items and details from the database
            String query = "SELECT mi.Name, oi.Quantity, mi.Price, (oi.Quantity * mi.Price) AS SubTotal " +
                    "FROM menuitems mi " +
                    "INNER JOIN orderitems oi ON oi.MenuItemID= mi.MenuItemID " +"INNER JOIN orders o using(OrderID)"+
                    "WHERE oi.OrderID = ?";
            PreparedStatement ps = resConn.c.prepareStatement(query);
            ps.setString(1, orderId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String itemName = rs.getString("Name");
                int itemQuantity = rs.getInt("Quantity");
                double itemPrice = rs.getDouble("Price");
                double subTotal = rs.getDouble("SubTotal");
                orderDetails.append(itemName).append(" (Quantity: ").append(itemQuantity).append(") - $").append(itemPrice)
                        .append(" - Sub-Total: ").append(subTotal).append("\n");
                totalBill += subTotal;
            }
            orderDetails.append("\nTotal Order Amount: ").append(totalBill);
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database errors
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return orderDetails.toString();
    }

    boolean checkPayment() {
        try {
            double paidAmount = Double.parseDouble(payBillTextField.getText());
            if (paidAmount < totalBill) {
                return false;
            } else {
                return true;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input for paid amount.");
            return false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

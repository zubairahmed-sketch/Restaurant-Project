import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;



public class Orders extends JFrame implements ActionListener {
    JFrame f;
    JTextField orderIdTextField;
    JComboBox<String> categoryComboBox;
    JList<String> itemList;
    JLabel priceLabel;
    JSpinner quantitySpinner;
    JButton addButton;
    JTextArea orderTextArea;
    JButton placeOrderButton;
    JButton deleteButton;
    int OrderID=0;
    int orderitemId=0;

    public void viewOrders() {
        JFrame frame = new JFrame("View Orders");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                JFrame frame = (JFrame) e.getWindow();
                frame.dispose();

                Employee employee = new Employee();
                employee.openEmployee();
            }
        });

        try {
            ResConn resConn = new ResConn();

            String query = "SELECT " +
                    "o.OrderID, " +
                    "c.Name AS CustomerName, " +
                    "o.totalAmount, " +
                    "o.OrderDate " +
                    "FROM " +
                    "Orders o " +
                    "JOIN " +
                    "Customers c ON o.CustomerID = c.CustomerID " +
                    "GROUP BY " +
                    "o.OrderID";

            ResultSet resultSet = resConn.s.executeQuery(query);
            Vector<Vector<Object>> data = new Vector<>();
            Vector<String> columnNames = new Vector<>();
            columnNames.add("Order ID");
            columnNames.add("Customer Name");
            columnNames.add("Total Amount");
            columnNames.add("Order Date");

            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                row.add(resultSet.getString("OrderID"));
                row.add(resultSet.getString("CustomerName"));
                row.add(resultSet.getDouble("totalAmount"));
                row.add(resultSet.getDate("OrderDate"));
                data.add(row);
            }

            if (data.isEmpty()) {
                System.out.println("No data found in the ResultSet."); // Log message if no data is retrieved
            }

            DefaultTableModel model = new DefaultTableModel(data, columnNames);
            JPanel mainPanel = new JPanel(new BorderLayout());
            addTable(mainPanel, model);
            frame.getContentPane().add(mainPanel);

            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            resultSet.close();
            resConn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void addTable(JPanel mainPanel, DefaultTableModel model) {
        JTable table = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component component = super.prepareRenderer(renderer, row, column);
                int rendererWidth = component.getPreferredSize().width;
                TableColumn tableColumn = getColumnModel().getColumn(column);
                tableColumn.setPreferredWidth(
                        Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
                return component;
            }
        };
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBackground(Color.BLACK);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.decode("#F04A00"));
        header.setForeground(Color.WHITE);
    }



    // Existing code...
    public void placeOrder() {
        f = new JFrame("Restaurant Order");
        f.setSize(600, 350);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(null);
        f.getContentPane().setBackground(Color.BLACK);
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

        JPanel mainPanel = new JPanel(null);
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setBounds(0, 0, 580, 380);

        categoryComboBox = new JComboBox<>();
        categoryComboBox.addActionListener(e -> updateItemList());
        categoryComboBox.setBounds(20, 20, 150, 25);
        mainPanel.add(categoryComboBox);

        itemList = new JList<>();
        itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane itemListScrollPane = new JScrollPane(itemList);
        itemListScrollPane.setBounds(20, 60, 150, 100);
        mainPanel.add(itemListScrollPane);

        priceLabel = new JLabel("Quantity: ");
        priceLabel.setBounds(20, 170, 150, 25);
        priceLabel.setFont(new Font("Arial", Font.BOLD, 18));
        priceLabel.setForeground(Color.WHITE);
        mainPanel.add(priceLabel);

        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 100, 1);
        quantitySpinner = new JSpinner(spinnerModel);
        quantitySpinner.setBounds(20, 200, 150, 25);
        mainPanel.add(quantitySpinner);

        addButton = new JButton("Add to Order");
        addButton.addActionListener(e -> {
            try {
                addToOrderButton();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Failed to add item to order.");
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Class not found: " + ex.getMessage());
            }
        });
        addButton.setFont(new Font("Ink Free", Font.BOLD, 19));
        addButton.setBackground(new Color(0xF04A00));
        addButton.setForeground(Color.WHITE);
        addButton.setBounds(20, 240, 150, 25);
        mainPanel.add(addButton);

        orderTextArea = new JTextArea();
        JScrollPane orderScrollPane = new JScrollPane(orderTextArea);
        orderScrollPane.setBounds(200, 20, 350, 200);
        mainPanel.add(orderScrollPane);

        placeOrderButton = new JButton("Place Order");
        placeOrderButton.addActionListener(e -> placeOrderButtonAction());
        placeOrderButton.setFont(new Font("Ink Free", Font.BOLD, 20));
        placeOrderButton.setBackground(new Color(0xF04A00));
        placeOrderButton.setForeground(Color.WHITE);
        placeOrderButton.setBounds(400, 240, 150, 25);
        mainPanel.add(placeOrderButton);

        deleteButton = new JButton("Delete Item");
        deleteButton.addActionListener(e -> deleteLastItemButton());
        deleteButton.setFont(new Font("Ink Free", Font.BOLD, 20));
        deleteButton.setBackground(new Color(0xF04A00));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setBounds(200, 240, 150, 25);
        mainPanel.add(deleteButton);

        mainPanel.setPreferredSize(new Dimension(580, 380));
        f.add(mainPanel);
        f.setVisible(true);

        updateCategoryComboBox();
    }

    void addToOrderButton() throws SQLException, ClassNotFoundException {
        String selectedItem = itemList.getSelectedValue();
        if (selectedItem != null) {
            int quantity = (int) quantitySpinner.getValue();
            double price = Double.parseDouble(getPrice(selectedItem));
            double subTotal = quantity * price;
            String orderLine = selectedItem + " - Quantity: " + quantity + ", Price: " + price + ", Sub-Total: "+ subTotal+ "\n";
            orderTextArea.append(orderLine);
        } else {
            JOptionPane.showMessageDialog(null, "Please select an item.");
        }
    }

    String getPrice(String itemName) throws SQLException, ClassNotFoundException{
        String price = "0.00";
        String query = "SELECT Price FROM menuitems WHERE Name = ?";
        ResConn resConn = new ResConn();
        PreparedStatement p = resConn.c.prepareStatement(query);
        p.setString(1, itemName);
        ResultSet rs = p.executeQuery();
        if (rs.next()) {
            price = String.valueOf(rs.getDouble("Price"));
        }
        p.close();
        resConn.close();
        return price;
    }
    public int getCustomerID(String email) throws SQLException, ClassNotFoundException {
        int id=0;
        ResConn resConn=new ResConn();
        String query="SELECT CustomerID FROM customers where Email= ?";
        PreparedStatement ps=resConn.c.prepareStatement(query);
        ps.setString(1,email);
        ResultSet rs=ps.executeQuery();
        if(rs.next()){
            id=rs.getInt("CustomerID");
        }
        ps.close();
        resConn.close();
        return id;
    }
    /*public void setCustomerEmail(String email) {
        this.customerEmail = email;
    }*/

    void placeOrderButtonAction() {
        String orderText = orderTextArea.getText();
        String[] orderLines = orderText.split("\n");
        double totalAmount = 0.0;

        try {
            ResConn resConn = new ResConn();
            Random r = new Random();
            OrderID = r.nextInt(10000);
            int customerID = getCustomerID(RestaurantLogin.email); // Store the result in a variable
            String insertOrderQuery = "INSERT INTO orders (OrderID, totalAmount, CustomerID, OrderDate) VALUES (?,?,?, CURRENT_DATE)";
            PreparedStatement psInsertOrder = resConn.c.prepareStatement(insertOrderQuery, Statement.RETURN_GENERATED_KEYS);
            psInsertOrder.setInt(1, OrderID);
            psInsertOrder.setDouble(2, totalAmount);
            psInsertOrder.setInt(3, customerID); // Use the variable here
            psInsertOrder.executeUpdate();

            String insertOrderItemQuery = "INSERT INTO orderitems (OrderID,OrderItemID, MenuItemID, Subtotal, Quantity) VALUES (?, ?, ?, ?,?)";
            PreparedStatement psInsertOrderItem = resConn.c.prepareStatement(insertOrderItemQuery);
            orderitemId=r.nextInt(10000);
            for (String line : orderLines) {
                String[] parts = line.split(" - Quantity: ");
                String itemName = parts[0];
                int quantity = Integer.parseInt(parts[1].split(", Price: ")[0]);
                double price = Double.parseDouble(parts[1].split(", Price: ")[1].split(",")[0]); // Modified to extract numerical value
                double subtotal = quantity * price;
                totalAmount += subtotal;

                String menuItemQuery = "SELECT MenuItemID FROM menuitems WHERE Name = ?";
                PreparedStatement psMenuItem = resConn.c.prepareStatement(menuItemQuery);
                psMenuItem.setString(1, itemName);
                ResultSet rsMenuItem = psMenuItem.executeQuery();
                int menuItemId = 0;
                if (rsMenuItem.next()) {
                    menuItemId = rsMenuItem.getInt("MenuItemID");
                }
                rsMenuItem.close();
                psMenuItem.close();

                psInsertOrderItem.setInt(1, OrderID);
                psInsertOrderItem.setInt(2, orderitemId);
                psInsertOrderItem.setInt(3, menuItemId);
                psInsertOrderItem.setDouble(4, subtotal);
                psInsertOrderItem.setInt(5, quantity);
                psInsertOrderItem.executeUpdate();
                orderitemId++;
            }
            psInsertOrderItem.close();


            String updateTotalAmountQuery = "UPDATE orders SET totalAmount = ? WHERE OrderID = ?";
            PreparedStatement psUpdateTotalAmount = resConn.c.prepareStatement(updateTotalAmountQuery);
            psUpdateTotalAmount.setDouble(1, totalAmount);
            psUpdateTotalAmount.setInt(2, OrderID);
            psUpdateTotalAmount.executeUpdate();
            psUpdateTotalAmount.close();
            resConn.close();

            JOptionPane.showMessageDialog(null, "Order Placed:\n" + orderText + "\nYour Customer ID :" + customerID + "\nYour Order ID :" + OrderID + "\nTotal Amount :" + totalAmount);
            orderTextArea.setText("");
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to place order. Please try again.");
        }
    }

    void deleteLastItemButton() {
        String currentText = orderTextArea.getText();
        int lastIndex = currentText.lastIndexOf('\n');
        if (lastIndex >= 0) {
            orderTextArea.setText(currentText.substring(0, lastIndex));
        }
    }

    void updateItemList() {
        String selectedCategory = (String) categoryComboBox.getSelectedItem();
        DefaultListModel<String> itemListModel = new DefaultListModel<>();
        try {
            ResConn resConn = new ResConn();
            int selectedCategoryID = 0;
            String queryID = "SELECT CategoryID FROM menucategories WHERE Name = ?";
            PreparedStatement p = resConn.c.prepareStatement(queryID);
            p.setString(1, selectedCategory);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                selectedCategoryID = rs.getInt("CategoryID");
            }
            p.close();

            String query = "SELECT Name FROM menuItems WHERE CategoryID = ?";
            PreparedStatement statement = resConn.c.prepareStatement(query);
            statement.setInt(1, selectedCategoryID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String itemName = resultSet.getString("Name");
                itemListModel.addElement(itemName);
            }
            itemList.setModel(itemListModel);
            statement.close();
            resConn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateCategoryComboBox() {
        try {
            ResConn resConn = new ResConn();
            String query = "SELECT DISTINCT Name FROM menucategories";
            PreparedStatement statement = resConn.c.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String category = resultSet.getString("Name");
                categoryComboBox.addItem(category);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void cancelOrder() {
        JFrame f = new JFrame();
        f.setTitle("Cancel Order");
        f.setSize(400, 200);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setLayout(null);

        f.getContentPane().setBackground(Color.BLACK);

        JLabel orderIdLabel = new JLabel("Order ID:");
        orderIdLabel.setForeground(Color.WHITE);
        orderIdLabel.setBounds(43, 30, 100, 25);
        orderIdLabel.setFont(new Font("Ink Free", Font.BOLD, 20));
        add(orderIdLabel);

        orderIdTextField = new JTextField();
        orderIdTextField.setBounds(150, 30, 190, 25);
        add(orderIdTextField);

        JButton backButton = new JButton("Back");
        backButton.setBounds(50, 100, 100, 30);
        backButton.setFont(new Font("Ink Free", Font.BOLD, 18));
        backButton.setBackground(new Color(0xF04A00));
        backButton.setForeground(Color.white);
        add(backButton);

        JButton confirmButton = new JButton("Confirm");
        confirmButton.setBounds(210, 100, 104, 30);
        confirmButton.setFont(new Font("Ink Free", Font.BOLD, 18));
        confirmButton.setBackground(new Color(0xF04A00));
        confirmButton.setForeground(Color.white);
        add(confirmButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add action for back button
                ((JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource())).dispose();
                new Customer().openCustomer();
            }
        });

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int orderId = Integer.parseInt(orderIdTextField.getText());
                // Add logic to fetch order details by orderId and display in JOptionPane
                String orderDetails = null;
                try {
                    orderDetails = getOrderDetails(orderId);

                    ResConn resConn = new ResConn();
                    String deleteOrderItemsQuery = "DELETE FROM orderitems WHERE OrderID = ?";
                    PreparedStatement psOrderItems = resConn.c.prepareStatement(deleteOrderItemsQuery);
                    psOrderItems.setInt(1, orderId);
                    psOrderItems.executeUpdate();

                    // Delete from orders table
                    String deleteOrdersQuery = "DELETE FROM orders WHERE OrderID = ?";
                    PreparedStatement psOrders = resConn.c.prepareStatement(deleteOrdersQuery);
                    psOrders.setInt(1, orderId);
                    psOrders.executeUpdate();

                    psOrderItems.close();
                    psOrders.close();
                    resConn.close();

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                JOptionPane.showMessageDialog(null, orderDetails + "\nYour order canceled successfully.");
                orderIdTextField.setText("");
            }
        });
        f.add(orderIdLabel);
        f.add(orderIdTextField);
        f.add(confirmButton);
        f.add(backButton);
        f.setVisible(true);

    }

    private String getOrderDetails(int orderId) throws SQLException, ClassNotFoundException {
        ResConn resConn = new ResConn();
        String query ="SELECT " +
                "o.OrderID, " +
                "o.OrderDate, " +
                "o.CustomerID, " +
                "SUM(oi.Quantity * mi.Price) AS totalAmount, " +
                "GROUP_CONCAT(CONCAT(mi.Name, ' (', oi.Quantity, ')') SEPARATOR ', ') AS itemsWithQuantity, " +
                "GROUP_CONCAT(oi.Quantity * mi.Price SEPARATOR ', ') AS subtotalForEachItem, " +
                "SUM(oi.Quantity * mi.Price) AS orderSubtotal " +
                "FROM " +
                "orders o " +
                "INNER JOIN " +
                "orderitems oi ON o.OrderID = oi.OrderID " +
                "INNER JOIN " +
                "menuitems mi ON oi.MenuItemID = mi.MenuItemID " +
                "WHERE " +
                "o.OrderID = ? " +
                "GROUP BY " +
                "o.OrderID;";


        PreparedStatement preparedStatement = resConn.c.prepareStatement(query);
        preparedStatement.setInt(1, orderId); // Set the orderId
        ResultSet resultSet = preparedStatement.executeQuery();

        StringBuilder resultBuilder = new StringBuilder();

        // Process the ResultSet
        if (resultSet.next()) {
            resultBuilder.append("Customer ID: ").append(resultSet.getString("CustomerID")).append("\n");
            resultBuilder.append("Order ID: ").append(resultSet.getString("OrderID")).append("\n");
            resultBuilder.append("Order Date: ").append(resultSet.getString("OrderDate")).append("\n");
            resultBuilder.append("Total Amount: ").append(resultSet.getDouble("totalAmount")).append("\n");
            resultBuilder.append("Items with Quantity: ").append(resultSet.getString("itemsWithQuantity")).append("\n");
            resultBuilder.append("Subtotal for Each Item: ").append(resultSet.getString("subtotalForEachItem")).append("\n");
            resultBuilder.append("Order Subtotal: ").append(resultSet.getDouble("orderSubtotal")).append("\n");
        }
        // Close resources
        resultSet.close();
        preparedStatement.close();
        resConn.close();

        return resultBuilder.toString();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.*;
import java.util.Random;
import java.util.SimpleTimeZone;


public class MenuItems extends JFrame implements ActionListener {
    JLabel updateMenuLabel, categoryLabel, nameLabel, priceLabel;
    JTextField nameTextField, priceTextField;
    JComboBox<String> categoryTextField;
    JButton addButton, cancelButton;
    JLabel viewMenuButton, addItemButton, updatePriceButton;
    JLabel label;
    int menuItemID=5421;

    JButton button1, button2, button3;

    private JFrame frame; // Declare frame as a class member variable
    JTextField menuItemIdTextField;
    JTextField newPriceTextField;
    public String status;

    public MenuItems() {
    }

    public MenuItems(JFrame frame) {
        this.frame = frame;
    }
    public MenuItems(String status) {
        this.status = status;
    }

    public void viewMenu(){
        button1 = new JButton("GO!");
        button1.setBounds(320, 125, 80, 30);
        button1.setFont(new Font("Ink Free", Font.BOLD, 20));
        button1.setBackground(new Color(0xF04A00));
        button1.setForeground(Color.white);

        button2 = new JButton("GO!");
        button2.setBounds(320, 220, 80, 30);
        button2.setFont(new Font("Ink Free", Font.BOLD, 20));
        button2.setBackground(new Color(0xF04A00));
        button2.setForeground(Color.white);

        button3 = new JButton("GO!");
        button3.setBounds(320, 320, 80, 30);
        button3.setFont(new Font("Ink Free", Font.BOLD, 20));
        button3.setBackground(new Color(0xF04A00));
        button3.setForeground(Color.white);

        label = new JLabel("SELECT OPTION!");
        label.setBounds(140, 40, 300, 45);
        label.setHorizontalTextPosition(JLabel.LEFT);
        label.setFont(new Font("Showcard Gothic", Font.BOLD, 35));
        label.setForeground(Color.white);

        viewMenuButton = new JLabel("View Menu");
        viewMenuButton.setBounds(140, 125, 150, 20);
        viewMenuButton.setFont(new Font("Ink Free", Font.BOLD, 22));
        viewMenuButton.setForeground(Color.white);

        addItemButton = new JLabel("Add New Item");
        addItemButton.setBounds(140, 220, 180, 20);
        addItemButton.setFont(new Font("Ink Free", Font.BOLD, 22));
        addItemButton.setForeground(Color.white);

        updatePriceButton = new JLabel("Update Price");
        updatePriceButton.setBounds(140, 320, 160, 20);
        updatePriceButton.setFont(new Font("Ink Free", Font.BOLD, 22));
        updatePriceButton.setForeground(Color.white);

        frame = new JFrame(); // Initialize frame here
        frame.setTitle("Menu Portal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setSize(556, 442);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setLocationRelativeTo(null);

        frame.add(viewMenuButton);
        frame.add(addItemButton);
        frame.add(updatePriceButton);
        frame.add(label);
        frame.add(button1);
        frame.add(button2);
        frame.add(button3);
        frame.setLocationRelativeTo(null);
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // Hide instead of dispose
        // Your existing code for creating the item viewer UI...

        // Add a window listener to handle frame close event
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                // Show the customer portal when the item viewer is closed
                new Employee().openEmployee();
            }
        });
        frame.setVisible(true);
    }

    public void addMenuItem(){
        // Code for adding new menu item
        JFrame frame2=new JFrame();
        frame2.setTitle("Add Item");
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setSize(550, 400); // Increased size of the frame
        frame2.getContentPane().setBackground(Color.BLACK); // Setting background color to black
        frame2.setLayout(null); // Using null layout

        // Creating labels
        updateMenuLabel = new JLabel("ADD NEW ITEM");
        //menuIdLabel = new JLabel("MenuItem ID:");
        categoryLabel = new JLabel("Category:");
        nameLabel = new JLabel("Name:");
        priceLabel = new JLabel("Price:");

        // Setting label colors
        updateMenuLabel.setForeground(Color.WHITE);
        //menuIdLabel.setForeground(Color.WHITE);
        categoryLabel.setForeground(Color.WHITE);
        nameLabel.setForeground(Color.WHITE);
        priceLabel.setForeground(Color.WHITE);

        String[] categories={"Beverages","Chicky meals","Burgers","Snacks"};
        // Creating text fields
        //menuIdTextField = new JTextField();
        categoryTextField = new JComboBox(categories);
        nameTextField = new JTextField();
        priceTextField = new JTextField();

        // Creating buttons
        addButton = new JButton("Add to Menu");
        addButton.setFont(new Font("Arial", Font.PLAIN, 15));
        addButton.setBackground(new Color(0xF04A00));
        addButton.setForeground(Color.white);

        cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Arial", Font.PLAIN, 15));
        cancelButton.setBackground(new Color(0xF04A00));
        cancelButton.setForeground(Color.white);

        // Adding ActionListener to the cancel button to close the frame
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource())).dispose();
                viewMenu(); // Close the frame
            }
        });

        // Setting positions and sizes of components
        updateMenuLabel.setBounds(165, 30, 220, 35);
        updateMenuLabel.setFont(new Font("Showcard Gothic", Font.BOLD, 29));

        /*menuIdLabel.setBounds(90, 80, 120, 30);
        menuIdLabel.setFont(new Font("Arial", Font.PLAIN, 19));
        menuIdTextField.setBounds(220, 80, 200, 23);*/

        categoryLabel.setBounds(90, 95, 100, 30);
        categoryLabel.setFont(new Font("Arial", Font.PLAIN, 19));
        categoryTextField.setBounds(220, 95, 200, 23);

        nameLabel.setBounds(90, 142, 150, 30);
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 19));
        nameTextField.setBounds(220, 142, 200, 23);

        priceLabel.setBounds(90, 189, 80, 30);
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 19));
        priceTextField.setBounds(220, 189, 200, 23);

        addButton.setBounds(105, 260, 120, 30);
        cancelButton.setBounds(280, 260, 120, 30);

        // Adding components to the frame
        frame2.add(updateMenuLabel);
       // frame2.add(menuIdLabel);
        //frame2.add(menuIdTextField);
        frame2.add(categoryLabel);
        frame2.add(categoryTextField);
        frame2.add(nameLabel);
        frame2.add(nameTextField);
        frame2.add(priceLabel);
        frame2.add(priceTextField);
        frame2.add(addButton);
        frame2.add(cancelButton);
        frame2.setLocationRelativeTo(null);
        cancelButton.addActionListener(this);
        addButton.addActionListener(this);
        frame2.setVisible(true);
    }



    public void itemViewer(final String s) {
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // Hide instead of dispose
        // Your existing code for creating the item viewer UI...

        // Add a window listener to handle frame close event
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                if (s.equals("employee")) {
                    viewMenu(); // Open the viewMenu frame if the parameter is "employee"
                    frame.dispose();
                } else {
                    new Customer().openCustomer(); // Open the openCustomer frame for other cases
                    frame.dispose();
                }
                //frame.dispose(); // Dispose of the current frame when closing
            }
        });
        try {
            ResConn resConn = new ResConn(); // Establish database connection

            String query = "SELECT c.Name AS Category, m.Name AS Item, m.Price " +
                    "FROM menuitems m JOIN menucategories c ON m.CategoryID = c.CategoryID " +
                    "ORDER BY c.Name, m.Name";

            PreparedStatement preparedStatement = resConn.c.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<String> categories = new ArrayList<>();
            List<Object[][]> categoryData = new ArrayList<>();

            while (resultSet.next()) {
                String categoryName = resultSet.getString("Category");
                String itemName = resultSet.getString("Item");
                double price = resultSet.getDouble("Price");

                int index = categories.indexOf(categoryName);
                if (index == -1) {
                    categories.add(categoryName);
                    Object[][] data = new Object[1][2];
                    data[0][0] = itemName;
                    data[0][1] = price;
                    categoryData.add(data);
                } else {
                    Object[][] data = categoryData.get(index);
                    int length = data.length;
                    Object[][] newData = new Object[length + 1][2];
                    System.arraycopy(data, 0, newData, 0, length);
                    newData[length][0] = itemName;
                    newData[length][1] = price;
                    categoryData.set(index, newData);
                }
            }

            JPanel mainPanel = new JPanel(new GridLayout(0, 1));

            for (int i = 0; i < categories.size(); i++) {
                addCategoryPanel(mainPanel, categories.get(i), categoryData.get(i));
            }

            frame.setContentPane(mainPanel);
            frame.revalidate();
            frame.setVisible(true);
            resultSet.close();
            preparedStatement.close();
            resConn.close();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to retrieve menu items.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addCategoryPanel(JPanel mainPanel, String categoryName, Object[][] data) {
        JPanel categoryPanel = new JPanel(new BorderLayout());

        JLabel categoryLabel = new JLabel(categoryName, SwingConstants.CENTER);
        categoryLabel.setFont(categoryLabel.getFont().deriveFont(Font.BOLD));
        categoryLabel.setForeground(Color.WHITE);
        categoryLabel.setBackground(Color.BLACK);
        categoryLabel.setOpaque(true);
        categoryPanel.add(categoryLabel, BorderLayout.NORTH);

        JTable table = new JTable(data, new String[]{"Item", "Price"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        table.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBackground(Color.BLACK);
        categoryPanel.add(scrollPane, BorderLayout.CENTER);

        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.decode("#F04A00"));
        header.setForeground(Color.WHITE);

        mainPanel.add(categoryPanel);
    }




    public void updatePrice(){
        // Code for updating price
            JFrame f1=new JFrame();
            f1.setTitle("Update Price Portal");
            f1.setSize(400, 300);
            f1.setDefaultCloseOperation(EXIT_ON_CLOSE);
            //f1.setLayout(null);
            f1.setLocationRelativeTo(null);

            JPanel mainPanel = new JPanel(null); // Set layout to null
            mainPanel.setBackground(Color.BLACK); // Set background color of main panel

            // JLabel for Update Price heading
            JLabel updatePriceLabel = new JLabel("Update Price");
            updatePriceLabel.setForeground(Color.WHITE); // Set foreground color of label
            updatePriceLabel.setBounds(100, 18, 190, 25); // Set bounds
            updatePriceLabel.setFont(new Font("Showcard Gothic", Font.BOLD, 25));
            mainPanel.add(updatePriceLabel);

            // JLabel and JTextField for Menu Item ID
            JLabel menuItemIdLabel = new JLabel("MenuItem Name:");
            menuItemIdLabel.setForeground(Color.WHITE); // Set foreground color of label
            menuItemIdLabel.setBounds(40, 70, 140, 20); // Set bounds
            menuItemIdLabel.setFont(new Font("Ink Free", Font.BOLD, 16));
            mainPanel.add(menuItemIdLabel);

            menuItemIdTextField = new JTextField();
            menuItemIdTextField.setBounds(170, 70, 150, 20); // Set bounds
            mainPanel.add(menuItemIdTextField);

            // JLabel and JTextField for New Price
            JLabel newPriceLabel = new JLabel("New Price:");
            newPriceLabel.setForeground(Color.WHITE); // Set foreground color of label
            newPriceLabel.setBounds(40, 120, 100, 20); // Set bounds
            newPriceLabel.setFont(new Font("Ink Free", Font.BOLD, 18));
            mainPanel.add(newPriceLabel);

            newPriceTextField = new JTextField();
            newPriceTextField.setBounds(170, 120, 150, 20); // Set bounds
            mainPanel.add(newPriceTextField);

            // JButton for Update
            JButton updateButton = new JButton("Update");
            updateButton.setBounds(70, 180, 100, 30); // Set bounds
            updateButton.setFont(new Font("Ink Free", Font.BOLD, 18));
            updateButton.setBackground(new Color(0xF04A00));
            updateButton.setForeground(Color.white);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ResConn resConn = new ResConn();
                    String menuName = menuItemIdTextField.getText();
                    int price = Integer.parseInt(newPriceTextField.getText());

                    // Query to retrieve the menu item ID
                    String query = "SELECT menuitemID FROM menuitems WHERE Name = ?";
                    PreparedStatement ps = resConn.c.prepareStatement(query);
                    ps.setString(1, menuName);
                    ResultSet rs = ps.executeQuery();

                    // Check if the menu item was found
                    if (rs.next()) {
                        int menuID = rs.getInt("menuitemID");

                        // SQL update query to update the price
                        String updPrice = "UPDATE menuitems SET Price = ? WHERE menuitemID = ?";
                        PreparedStatement psUpdate = resConn.c.prepareStatement(updPrice);
                        psUpdate.setInt(1, price);
                        psUpdate.setInt(2, menuID);
                        // Execute the update query
                        psUpdate.executeUpdate();
                        // Close prepared statement and result set
                        psUpdate.close();
                    } else {
                        // Display message in a JOptionPane dialog
                        JOptionPane.showMessageDialog(null, "Menu item not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    // Close prepared statement and result set
                    ps.close();
                    resConn.close();
                    ((JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource())).dispose();
                    new RestaurantLogin().priceUpdated();


                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        mainPanel.add(updateButton);

            // JButton for Cancel
            JButton cancelButton = new JButton("Cancel");
            cancelButton.setBounds(200, 180, 100, 30); // Set bounds
            cancelButton.setFont(new Font("Ink Free", Font.BOLD, 18));
            cancelButton.setBackground(new Color(0xF04A00));
            cancelButton.setForeground(Color.white);

            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                     // Close the frame
                    ((JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource())).dispose();
                    new MenuItems().viewMenu();
                }
            });
            mainPanel.add(cancelButton);
            f1.add(mainPanel);
            f1.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button1){
            String emp="employee";
            itemViewer(emp);
        } else if (e.getSource()==button2) {
            ((JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource())).dispose();
            addMenuItem();
        } else if (e.getSource()==button3) {
            ((JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource())).dispose();
            updatePrice();
        } else if (e.getSource()==addButton) {
            try {
                ResConn resConn=new ResConn();
                String categoryName= (String) categoryTextField.getSelectedItem();
                String name=nameTextField.getText();
                int categoryID=0;
                int price= Integer.parseInt(priceTextField.getText());
                String getID="SELECT CategoryID from menucategories where Name = ?";
                PreparedStatement p=resConn.c.prepareStatement(getID);
                p.setString(1,categoryName);
                ResultSet rs=p.executeQuery();
                if(rs.next()) {
                    categoryID = rs.getInt("CategoryID");
                }
                p.close();

                String query = "INSERT INTO menuitems(menuitemID,CategoryID,Name,Price) Values(?,?,?,?)";
                PreparedStatement preparedStatement = resConn.c.prepareStatement(query);
                preparedStatement.setInt(1, menuItemID);
                preparedStatement.setInt(2, categoryID);
                preparedStatement.setString(3, name);
                preparedStatement.setInt(4, price);
                preparedStatement.executeUpdate();
                preparedStatement.close();
                resConn.close();
                menuItemID++;

                ((JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource())).dispose();
                new RestaurantLogin().itemAdded();

            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        }
    }
}

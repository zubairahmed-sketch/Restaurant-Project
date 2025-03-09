import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;



public class Customer extends JFrame implements ActionListener{

    private RestaurantLogin restaurantLogin;
    private JFrame f1;
    private ImageIcon image, image2, image3, image4, image5;
    private JButton b, b1, b2, b3, b4, b5;
    private JLabel l, l2, l3, l4, l5, l6;
    private JComboBox cb;
    private JTextField t3, t4, t6;
    private PasswordFieldWithShowButton p;
    int id=0;

    public Customer() {
    }
    public Customer(RestaurantLogin restaurantLogin) {
        this.restaurantLogin = restaurantLogin;
    }

    public void signUp() {
        ImageIcon image = new ImageIcon("signup.png");

        JLabel l = new JLabel("Name");
        l.setBounds(40, 90, 70, 40);
        l.setFont(new Font("Ink Free", Font.BOLD, 20));
        l.setForeground(Color.white);

        JLabel l2 = new JLabel("Email");
        l2.setBounds(40, 140, 100, 40);
        l2.setFont(new Font("Ink Free", Font.BOLD, 20));
        l2.setForeground(Color.white);

        JLabel l3 = new JLabel("Password");
        l3.setBounds(40, 190, 100, 40);
        l3.setFont(new Font("Ink Free", Font.BOLD, 20));
        l3.setForeground(Color.white);

        JLabel l4 = new JLabel("Phone");
        l4.setBounds(40, 240, 100, 40);
        l4.setFont(new Font("Ink Free", Font.BOLD, 20));
        l4.setForeground(Color.white);

        JLabel l5 = new JLabel("Role");
        l5.setBounds(40, 290, 100, 40);
        l5.setFont(new Font("Ink Free", Font.BOLD, 20));
        l5.setForeground(Color.white);

        JLabel l6 = new JLabel();
        l6.setIcon(image);
        l6.setBounds(410, 101, image.getIconWidth(), image.getIconHeight());

        t3 = new JTextField();
        t3.setBounds(137, 100, 200, 20);

        t4 = new JTextField();
        t4.setBounds(137, 150, 200, 20);

        p = new PasswordFieldWithShowButton();
        p.setBounds(137, 200, 200, 20);

        t6 = new JTextField();
        t6.setBounds(137, 250, 200, 20);

        String[] role = {"Customer", "Employee"};
        cb = new JComboBox(role);
        cb.setBounds(137, 300, 200, 20);
        cb.setFont(new Font("Ink Free", Font.BOLD, 15));

        b4 = new JButton("Create");
        b4.setBounds(125, 365, 90, 30);
        b4.setFont(new Font("Ink Free", Font.BOLD, 17));
        b4.setBackground(new Color(0xF04A00));
        b4.setForeground(Color.white);

        b5 = new JButton("Back");
        b5.setBounds(255, 365, 80, 30);
        b5.setFont(new Font("Ink Free", Font.BOLD, 17));
        b5.setBackground(new Color(0xF04A00));
        b5.setForeground(Color.white);

        JFrame f = new JFrame();
        f.setTitle("Sign Up");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().setBackground(Color.BLACK);
        f.setLayout(null);
        f.setResizable(false);
        f.setSize(736, 552);

        f.add(l);
        f.add(l2);
        f.add(l3);
        f.add(l4);
        f.add(l5);

        f.add(t3);
        f.add(t4);
        f.add(p);
        f.add(t6);
        f.add(cb);
        f.add(b4);
        f.add(b5);
        f.add(l6);

        b4.addActionListener(this);
        b5.addActionListener(this);
        f.setLocationRelativeTo(null);
        f.setVisible(true);

    }

    public void openCustomer(){
        image = new ImageIcon("customer.png");
        image2 = new ImageIcon("placeOrder.png");
        image3 = new ImageIcon("viewmenu.png");
        image4 = new ImageIcon("cancelord.png");
        image5 = new ImageIcon("transaction.png");

        l = new JLabel();
        l.setIcon(image);
        l.setVerticalAlignment(JLabel.CENTER);
        l.setHorizontalAlignment(JLabel.CENTER);
        l.setBounds(200, 100, 400, 350);

        l2 = new JLabel();
        l2.setIcon(image2);
        l2.setBounds(115, 74, 100, 100);

        l3 = new JLabel();
        l3.setIcon(image3);
        l3.setBounds(110, 350, 100, 100);

        l4 = new JLabel();
        l4.setIcon(image4);
        l4.setBounds(580, 70, 100, 100);

        l5 = new JLabel();
        l5.setIcon(image5);
        l5.setBounds(580, 350, 100, 100);

        l6 = new JLabel("Customer Portal");
        l6.setBounds(270, 50, 250, 35);
        l6.setHorizontalTextPosition(JLabel.LEFT);
        l6.setFont(new Font("Showcard Gothic", Font.BOLD, 25));
        l6.setForeground(Color.white);

        b = new JButton("Place Order");
        b.setBounds(110, 185, 110, 30);
        b.setFont(new Font("Arial", Font.BOLD, 12));
        b.setBackground(new Color(0xF04A00));
        b.setForeground(Color.white);

        b1 = new JButton("View Menu");
        b1.setBounds(115, 470, 110, 30);
        b1.setFont(new Font("Arial", Font.BOLD, 12));
        b1.setBackground(new Color(0xF04A00));
        b1.setForeground(Color.white);

        b2 = new JButton("Cancel Order");
        b2.setBounds(580, 180, 110, 30);
        b2.setFont(new Font("Arial", Font.BOLD, 12));
        b2.setBackground(new Color(0xF04A00));
        b2.setForeground(Color.white);

        b3 = new JButton("Pay Bill");
        b3.setBounds(580, 460, 110, 30);
        b3.setFont(new Font("Arial", Font.BOLD, 12));
        b3.setBackground(new Color(0xF04A00));
        b3.setForeground(Color.white);

        f1 = new JFrame();
        f1.setTitle("Customer Portal");
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.getContentPane().setBackground(Color.BLACK);
        f1.setLayout(null);
        f1.setResizable(false);
        f1.setSize(800, 600);
        f1.add(l);
        f1.add(l2);
        f1.add(l3);
        f1.add(l4);
        f1.add(l5);
        f1.add(b);
        f1.add(b1);
        f1.add(b2);
        f1.add(b3);
        f1.add(l6);
        /*b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });*/
        b.addActionListener(this);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        f1.setLocationRelativeTo(null);
        f1.setVisible(true);
    }
    public void viewCustomers() {
        JFrame f = new JFrame();
        f.setTitle("Customers Info");
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // Hide instead of dispose
        // Your existing code for creating the item viewer UI...

        // Add a window listener to handle frame close event
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                //((JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource())).dispose();
                new Employee().openEmployee();

            }
        });

        // Fetch customer data from the database
        Object[][] data = getCustomerData();

        // Column names for the tables
        String[] columnNames = { "Customer Name", "CustomerID", "Phone", "Email" };

        // Create a panel for the table
        JPanel mainPanel = new JPanel(new BorderLayout());
        f.getContentPane().add(mainPanel, BorderLayout.CENTER);

        addTable(mainPanel, data, columnNames);

        // Pack the frame to adjust its size based on the table's preferred size
        f.pack();
        // Set minimum size to ensure it doesn't become too small
        f.setMaximumSize(f.getSize());
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    // Method to fetch customer data from the database
    private Object[][] getCustomerData() {
        ArrayList<Object[]> dataList = new ArrayList<>();
        try {
            //

            // Query to fetch customer data
            String query = "SELECT Name, CustomerID, Phone, Email FROM customers";
            // Execute query
            ResConn resConn=new ResConn();
            ResultSet rs = resConn.s.executeQuery(query);

            // Process result set
            while (rs.next()) {
                String name = rs.getString("Name");
                String customerId = rs.getString("CustomerID");
                String phone = rs.getString("Phone");
                String email = rs.getString("Email");

                // Add customer data to the list
                dataList.add(new Object[]{name, customerId, phone, email});
            }

            // Close result set, statement, and connection
            rs.close();
            resConn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to fetch customer data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Convert ArrayList to 2D array
        Object[][] data = new Object[dataList.size()][4];
        for (int i = 0; i < dataList.size(); i++) {
            data[i] = dataList.get(i);
        }
        return data;
    }
    private void addTable(JPanel mainPanel, Object[][] data, String[] columnNames) {
        JTable table = new JTable(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make cells non-editable
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

        // Set custom renderer for cell background
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        table.getColumnModel().getColumn(0).setCellRenderer(cellRenderer); // Customer Name column
        table.getColumnModel().getColumn(1).setCellRenderer(cellRenderer); // CustomerID column
        table.getColumnModel().getColumn(2).setCellRenderer(cellRenderer); // Phone column
        table.getColumnModel().getColumn(3).setCellRenderer(cellRenderer); // Email column

        // Hide the remaining column
        TableColumnModel columnModel = table.getColumnModel();
        int columnCount = columnModel.getColumnCount();
        if (columnCount > 4) {
            TableColumn hiddenColumn = columnModel.getColumn(4);
            hiddenColumn.setMinWidth(0);
            hiddenColumn.setMaxWidth(0);
            hiddenColumn.setWidth(0);
            hiddenColumn.setPreferredWidth(0);
        }

        // Set the background color of the table scroll pane to black
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBackground(Color.BLACK);

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Set header cell renderer
        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.decode("#F04A00"));
        header.setForeground(Color.WHITE);
        System.out.print("HII");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Random random = new Random();
        if (e.getSource() == b4) {
            ResConn resConn1 = null;
            try {
                // Open a connection to the database
                resConn1 = new ResConn();
                //resConn1.c.setAutoCommit(false);

                String name = t3.getText();
                String email=t4.getText();
                String pass = new String(p.getPassword());
                String phone = t6.getText();
                String status = (String) cb.getSelectedItem();
                String query1 = "";
                String query2 = "";

                // Construct the SQL query based on the selected role
                if (status.equals("Customer")) {
                    query1 = "INSERT INTO customers (CustomerID,Name, Email,Phone ) VALUES (?,?, ?, ?)";
                    query2 = "INSERT INTO customeraccounts (Email, Password) VALUES (?, ?)";
                } else {
                    query1 = "INSERT INTO employees (EmployeeID,Name, Email, ContactInfo) VALUES (?,?, ?, ?)";
                    query2 = "INSERT INTO employeeaccounts (Email, Password) VALUES (?, ?)";
                }

                PreparedStatement preparedStatement = resConn1.c.prepareStatement(query2);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, pass);
                preparedStatement.executeUpdate();
                preparedStatement.close();

                this.id=random.nextInt(10000);
                PreparedStatement preparedStatement1 = resConn1.c.prepareStatement(query1);
                preparedStatement1.setInt(1, id);
                preparedStatement1.setString(2, name);
                preparedStatement1.setString(3, email);
                preparedStatement1.setString(4, phone);
                preparedStatement1.executeUpdate();
                preparedStatement1.close();

                resConn1.close();
                ((JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource())).dispose();
                restaurantLogin.accountCreated();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to create account: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to create account: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == b5) {
            ((JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource())).dispose();
            restaurantLogin.viewLogin(); // Call viewLogin() method of the restaurantLogin instance
        } else if (e.getSource() == b) {
            ((JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource())).dispose();
            new Orders().placeOrder();
        } else if (e.getSource() == b2) {
            ((JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource())).dispose();
            new Orders().cancelOrder();
        } else if (e.getSource() == b1) {
            MenuItems menuItems = new MenuItems(f1);
            String cus="customer";
            menuItems.itemViewer(cus);
        } else if (e.getSource()==b3) {
            ((JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource())).dispose();
            new Transaction().payBill();
        }
    }

}

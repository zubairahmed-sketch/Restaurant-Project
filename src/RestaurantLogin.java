import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
public class RestaurantLogin extends JFrame implements ActionListener {
    ImageIcon image, image2, icon;
    JLabel l, l2, l3, l4, l5;
    JButton b, b2, b3,b6;
    JRadioButton empButton, cusButton;
    ButtonGroup group;
    JTextField t;
    JPasswordField p;

    public void viewLogin() {
        image = new ImageIcon("img.png");
        image2 = new ImageIcon("img_1.png");

        l = new JLabel("LOGIN");
        l.setBounds(500, 100, 150, 50);
        l.setHorizontalTextPosition(JLabel.LEFT);
        l.setFont(new Font("My Boli", Font.BOLD, 30));
        l.setForeground(Color.white);

        l2 = new JLabel();
        l2.setIcon(image);
        l2.setBounds(20, -20, 700, 550);

        b = new JButton("SIGN IN");
        b.setBounds(430, 370, 80, 30);
        b.setFont(new Font("Arial", Font.PLAIN, 12));
        b.setBackground(new Color(0xF04A00));
        b.setForeground(Color.white);

        b2 = new JButton("<html><u>Underlined Button</u></html>");
        b2.setText("SIGN UP");
        b2.setBounds(550, 370, 90, 30);
        b2.setFont(new Font("Arial", Font.PLAIN, 12));
        b2.setBackground(Color.black);
        b2.setForeground(Color.white);

        empButton = new JRadioButton("Employee");
        empButton.setBounds(430, 328, 120, 20);
        empButton.setFont(new Font("Ink Free", Font.BOLD, 20));
        empButton.setBackground(Color.BLACK);
        empButton.setForeground(Color.white);

        cusButton = new JRadioButton("Customer");
        cusButton.setBounds(550, 328, 120, 20);
        cusButton.setFont(new Font("Ink Free", Font.BOLD, 20));
        cusButton.setBackground(Color.BLACK);
        cusButton.setForeground(Color.white);

        group = new ButtonGroup();
        group.add(empButton);
        group.add(cusButton);

        l3 = new JLabel();
        l3.setIcon(image2);
        l3.setBounds(8, 15, 150, 80);

        l4 = new JLabel("EMAIL");
        l4.setBounds(430, 180, 70, 40);
        l4.setFont(new Font("Arial", Font.PLAIN, 15));
        l4.setForeground(Color.white);

        l5 = new JLabel("PASSWORD");
        l5.setBounds(430, 250, 100, 40);
        l5.setFont(new Font("Arial", Font.PLAIN, 15));
        l5.setForeground(Color.white);

        t = new JTextField();
        t.setBounds(430, 220, 200, 20);

       // t2 = new JTextField();
        p=new JPasswordField();
        p.setBounds(430, 290, 200, 20);

        setTitle("Restaurant Management System Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(736, 552);
        getContentPane().setBackground(Color.BLACK);
        setLayout(null);

        icon = new ImageIcon("logo.jpg");
        setIconImage(icon.getImage());

        add(l);
        add(l2);
        add(l3);
        add(b);
        add(b2);
        add(l4);
        add(l5);
        add(t);
        add(p);
        add(empButton);
        add(cusButton);



        b.addActionListener(this);
        b2.addActionListener(this);
        empButton.addActionListener(this);
        cusButton.addActionListener(this);
        final RestaurantLogin loginInstance = this; // Declare final instance
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e1) {
                ((JFrame) SwingUtilities.getWindowAncestor((Component) e1.getSource())).dispose();
                Customer customer = new Customer(loginInstance); // Pass the current RestaurantLogin instance
                customer.signUp();
            }
        });
        setVisible(true);
    }



    public void invalidLogin() {
        //JOptionPane.showMessageDialog(this, "INVALID LOGIN!", "Invalid Login", JOptionPane.ERROR_MESSAGE);
        JLabel l = new JLabel("INVALID LOGIN!");
        l.setBounds(105, 80, 250, 30);
        l.setHorizontalTextPosition(JLabel.LEFT);
        l.setFont(new Font("My Boli", Font.BOLD, 30));
        l.setForeground(Color.white);

        b3 = new JButton("Try Again");
        b3.setBounds(160, 130, 110, 30);
        b3.setFont(new Font("Arial", Font.BOLD, 12));
        b3.setBackground(new Color(0xF04A00));
        b3.setForeground(Color.white);

        JFrame f = new JFrame();
        f.setTitle("Invalid Login");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().setBackground(Color.BLACK);
        f.setLayout(null);
        f.setResizable(false);
        f.setSize(450, 250);


        f.add(l);
        f.add(b3);
        b3.addActionListener(this);
        f.setVisible(true);
    }

    public void accountCreated() {
        //JOptionPane.showMessageDialog(this, "INVALID LOGIN!", "Invalid Login", JOptionPane.ERROR_MESSAGE);
        JLabel l = new JLabel("Account Created Successfully!");
        l.setBounds(105, 80, 250, 30);
        l.setHorizontalTextPosition(JLabel.LEFT);
        l.setFont(new Font("My Boli", Font.BOLD, 18));
        l.setForeground(Color.white);

        b6 = new JButton("Ok");
        b6.setBounds(160, 130, 130, 50);
        b6.setFont(new Font("Arial", Font.BOLD, 20));
        b6.setBackground(new Color(0xF04A00));
        b6.setForeground(Color.white);

        JFrame f = new JFrame();
        f.setTitle("Account Created");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().setBackground(Color.BLACK);
        f.setLayout(null);
        f.setResizable(false);
        f.setSize(450, 250);
        f.setVisible(true);

        f.add(l);
        f.add(b6);
        b6.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == b) {

            try {
                ResConn resConn = new ResConn();
                String email = t.getText();
                String pass = Arrays.toString(p.getPassword());
                String status = "";

                if (empButton.isSelected()) {
                    status = "employee";
                } else if (cusButton.isSelected()) {
                    status = "customer";
                }

                String query1 = "SELECT Email, Password FROM " + (status.equals("employee") ? "employeeaccounts" : "customeraccounts") + " WHERE Email=? AND Password=?";
                PreparedStatement preparedStatement = resConn.c.prepareStatement(query1);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, pass);
                ResultSet rs = preparedStatement.executeQuery();

                if (rs.next()) {
                    if (rs.getString(1).equals(email) && rs.getString(2).equals(pass)) {
                        setVisible(false);
                        if (status.equals("customer")) {
                            new Customer().openCustomer();
                        } else if (status.equals("employee")) {
                            new Employee().openEmployee();
                        }
                    } else {
                        //this.setVisible(false);
                        ((JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource())).dispose();
                        invalidLogin();
                        t.setText("");
                        p.setText("");


                    }
                } else {
                    //this.setVisible(false);
                    ((JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource())).dispose();
                    invalidLogin();
                    t.setText("");
                    p.setText("");

                }

                rs.close();
                preparedStatement.close();
                resConn.close();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }else if (e.getSource() == b3 || e.getSource() == b6) {
            // Try Again or Ok button action
            ((JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource())).dispose();
            viewLogin();
        }
    }
        public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RestaurantLogin().viewLogin();
        });
    }
}


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class Employee extends JFrame implements ActionListener {

    JFrame f;
    ImageIcon empimage, image2, image3, image4, image5;

    JLabel l, l2, l3, l4, l5, l6;
    JButton b, b1, b2, b3;

    public void openEmployee(){

           // MenuItems menuItems = new MenuItems("employee");

            empimage = new ImageIcon("employee.png");
            image2 = new ImageIcon("viewCus.jpg");
            image3 = new ImageIcon("vieword.jpg");
            image4 = new ImageIcon("transaction.png");
            image5 = new ImageIcon("menu.jpg");

            JLabel l = new JLabel();
            l.setIcon(empimage);
            l.setVerticalAlignment(JLabel.CENTER);
            l.setHorizontalAlignment(JLabel.CENTER);
            l.setBounds(200, 100, 400, 350);

            l2 = new JLabel();
            l2.setIcon(image2);
            l2.setBounds(115, 70, 100, 100);

            l3 = new JLabel();
            l3.setIcon(image3);
            l3.setBounds(110, 350, 100, 100);

            l4 = new JLabel();
            l4.setIcon(image4);
            l4.setBounds(580, 70, 100, 100);

            l5 = new JLabel();
            l5.setIcon(image5);
            l5.setBounds(580, 350, 100, 100);

            l6 = new JLabel("Employee Portal");
            l6.setBounds(270, 50, 250, 35);
            l6.setHorizontalTextPosition(JLabel.LEFT);
            l6.setFont(new Font("Showcard Gothic", Font.BOLD, 25));
            l6.setForeground(Color.white);

            b = new JButton("View Customers");
            b.setBounds(105, 185, 135, 30);
            b.setFont(new Font("Arial", Font.BOLD, 12));
            b.setBackground(new Color(0xF04A00));
            b.setForeground(Color.white);

            b1 = new JButton("View Orders");
            b1.setBounds(110, 470, 110, 30);
            b1.setFont(new Font("Arial", Font.BOLD, 12));
            b1.setBackground(new Color(0xF04A00));
            b1.setForeground(Color.white);

            b2 = new JButton("View Transactions");
            b2.setBounds(575, 180, 140, 30);
            b2.setFont(new Font("Arial", Font.BOLD, 12));
            b2.setBackground(new Color(0xF04A00));
            b2.setForeground(Color.white);

            b3 = new JButton("View Menu");
            b3.setBounds(580, 470, 110, 30);
            b3.setFont(new Font("Arial", Font.BOLD, 12));
            b3.setBackground(new Color(0xF04A00));
            b3.setForeground(Color.white);

            f = new JFrame();
            f.setTitle("Employee Portal");
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.getContentPane().setBackground(Color.BLACK);
            f.setResizable(false);
            f.setLayout(null);
            f.setSize(800, 600);

            f.add(l);
            f.add(l2);
            f.add(l3);
            f.add(l4);
            f.add(l5);
            f.add(b);
            f.add(b1);
            f.add(b2);
            f.add(b3);
            f.add(l6);
            b.addActionListener(this);
            b1.addActionListener(this);
            b2.addActionListener(this);
            b3.addActionListener(this);
            f.setLocationRelativeTo(null);
            f.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b){
                ((JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource())).dispose();
                new Customer().viewCustomers();
        }else if (e.getSource()==b1) {
                ((JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource())).dispose();
                new Orders().viewOrders();
        } else if (e.getSource()==b2) {
                ((JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource())).dispose();
                new Transaction().viewTransactions();
        } else if (e.getSource()==b3) {
                ((JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource())).dispose();
                new MenuItems((JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource())).viewMenu();
        }

    }
}


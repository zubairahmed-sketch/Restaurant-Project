
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class Node {
    String customerName;
    String order;
    int orderID;
    int customerID;
    String customerEmail;
    int totalAmount;
    LocalDate orderDate;
    String customerPhone;
    String customerAddress;
    Node next;


    public void display(){

        System.out.println("\tCustomer Name *===* "+customerName);
        System.out.println( "\tCustomerID *===* "+customerID);
        System.out.println("\tCustomer Email Address *===* "+customerEmail);
        System.out.println( "\tCustomer Phone Number *===* "+customerPhone);
        System.out.println( "\tCustomer Address *===* "+customerAddress);
        System.out.println();
    }
    public void canceledDisplay() {
        System.out.println("\tBelow customer's order is Cancelled");
        System.out.println("\tCustomer Name *===* "+customerName);
        System.out.println( "\tCustomer ID *===* "+customerID);
        System.out.println( "\tOrder *===* "+order);
        System.out.println( "\tOrderID *===* "+orderID);
        System.out.println( "\tTotal Amount *===* "+totalAmount);
        System.out.println("\tCustomer Email Address *===* "+customerEmail);
        System.out.println( "\tCustomer Phone Number *===* "+customerPhone);
        System.out.println( "\tCustomer Address *===* "+customerAddress);
        System.out.println("Order is placed on : "+orderDate);
        System.out.println();

    }



}


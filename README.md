# 🍽️ Restaurant Management System – Java GUI & MySQL

This **Restaurant Management System** is a Java Swing desktop application with MySQL backend designed to manage restaurant operations efficiently. It includes role-based login for **Customers** and **Employees**, offering features like order placement, menu management, billing, and transaction history.

---

## 👥 Roles & Portals

### 🔐 Login & Signup
- Role-based login (Customer / Employee)
- Secure signup and login process

---

### 🧑‍🍳 Customer Portal Features
- **View Menu** – Browse available food and drink items
- **Place Order** – Select items and place an order
- **Pay Bill** – View and pay bill for placed order
- **Cancel Order** – Cancel a placed order before it's processed

---

### 👨‍💼 Employee Portal Features
- **View Customers** – View all registered customer details
- **View Orders** – Check pending and completed customer orders
- **View Transactions** – Track completed payments
- **View Menu** – See all current items on the menu
- **Add New Item to Menu** – Insert a new food or drink item
- **Update Price of Item** – Modify price of any existing menu item

---

## 🛠️ Technologies Used

- **Java (Swing)** – For building the graphical user interface
- **MySQL** – For backend relational data storage
- **JDBC** – For connecting Java to MySQL
- **VS-Code / Eclipse** – IDE used for development

---

## 📸 Screenshots


- ![Login Page](screenshots/LoginPage.png)
- ![SignUp Page](screenshots/SignUpPage.png)
- ![Customer Portal](screenshots/CustomerPortal.png)
- ![Place Order](screenshots/PlaceOrder.png)
- ![Cancel Order](screenshots/CancelOrder.png)
- ![Pay Bill](screenshots/PayBill.png)
- ![Employee Portal](screenshots/EmployeePortal.png)
- ![Menu Portal](screenshots/MenuPortal.png)
- ![Add Menu Item](screenshots/AddNewItem.png)
- ![Update Item Price](screenshots/UpdatePrice.png)


---

## 📦 How to Run the Project

1. **Clone the Repository**
     ```bash
     git clone https://github.com/zubairahmed-sketch/Restaurant-Project.git


2. **Set Up MySQL**

    Import the provided SQL schema file (restaurant.sql) into your MySQL server.

    Create tables for users, menu, orders, and transactions.
  

3. **Configure Database in Java Code**
   
    String url = "jdbc:mysql://localhost:3306/restaurant";
  
    String username = "root";
  
    String password = "your_password";


4. **Run the Project**

    Open the project in VS-Code or Eclipse

    Locate and run the main class (e.g., RestaurantLogin.java)
   
  
---

🔮 Future Improvements

   Generate printable PDF invoices

   Add admin role for deeper control

   Include image thumbnails in menu

   Role-based UI themes
    

---

📄 License

   This project is open-source and available for educational use.


---

🙌 Developed By
   [Zubair Ahmed]
   
   Contributions are welcome. Fork, star ⭐, and share!

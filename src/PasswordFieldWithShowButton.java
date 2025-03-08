import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PasswordFieldWithShowButton extends JPasswordField {
    JToggleButton showToggleButton;

    public PasswordFieldWithShowButton() {
        initialize();
    }

    public void initialize() {
        setLayout(new BorderLayout());
        showToggleButton = new JToggleButton(createImageIcon("eye.png")); // Adjust the path as per your image location
        showToggleButton.setPreferredSize(new Dimension(25, 25));
        showToggleButton.setFocusPainted(false);
        showToggleButton.setContentAreaFilled(false);
        showToggleButton.setBorderPainted(false);
        showToggleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JToggleButton toggleButton = (JToggleButton) e.getSource();
                if (toggleButton.isSelected()) {
                    setEchoChar((char) 0); // Show password
                } else {
                    setEchoChar('\u2022'); // Hide password
                }
            }
        });
        add(showToggleButton, BorderLayout.EAST);
    }

    // Method to create ImageIcon from file path
    ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

}
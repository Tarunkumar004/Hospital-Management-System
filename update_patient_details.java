package minihospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class update_patient_details extends JFrame {

    update_patient_details() {

        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 940, 490);
        panel.setBackground(new Color(90, 156, 163));
        panel.setLayout(null);
        add(panel);

        // Icon Image
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/updated.png"));
        Image image = imageIcon.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel label = new JLabel(imageIcon1);
        label.setBounds(500, 60, 300, 300);
        panel.add(label);

        JLabel label1 = new JLabel("Update Patient Details");
        label1.setBounds(124, 11, 300, 25);
        label1.setFont(new Font("Tahoma", Font.BOLD, 20));
        label1.setForeground(Color.white);
        panel.add(label1);

        JLabel label2 = new JLabel("Name :");
        label2.setBounds(25, 88, 100, 14);
        label2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label2.setForeground(Color.white);
        panel.add(label2);

        Choice choice = new Choice();
        choice.setBounds(248, 85, 140, 25);
        panel.add(choice);

        // Load Patient Names into Choice Dropdown
        try {
            DatabaseConnector c = new DatabaseConnector();
            ResultSet rs = c.statement.executeQuery("SELECT Name FROM patient_info");
            while (rs.next()) {
                choice.add(rs.getString("Name"));
            }
            rs.close();
            c.statement.close();
            c.connection.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error loading patient names: " + e.getMessage());
        }

        JLabel label3 = new JLabel("Room Number :");
        label3.setBounds(25, 129, 150, 14);
        label3.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label3.setForeground(Color.white);
        panel.add(label3);

        JTextField textFieldR = new JTextField();
        textFieldR.setBounds(248, 129, 140, 20);
        panel.add(textFieldR);

        JLabel label4 = new JLabel("In-Time  :");
        label4.setBounds(25, 174, 100, 14);
        label4.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label4.setForeground(Color.white);
        panel.add(label4);

        JTextField textFieldINTime = new JTextField();
        textFieldINTime.setBounds(248, 174, 140, 20);
        panel.add(textFieldINTime);

        JLabel label5 = new JLabel("Amount Paid (Rs) :");
        label5.setBounds(25, 216, 150, 14);
        label5.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label5.setForeground(Color.white);
        panel.add(label5);

        JTextField textFieldAmount = new JTextField();
        textFieldAmount.setBounds(248, 216, 140, 20);
        panel.add(textFieldAmount);

        JLabel label6 = new JLabel("Pending Amount (Rs) :");
        label6.setBounds(25, 261, 180, 14);
        label6.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label6.setForeground(Color.white);
        panel.add(label6);

        JTextField textFieldPending = new JTextField();
        textFieldPending.setBounds(248, 261, 140, 20);
        textFieldPending.setEditable(false);
        panel.add(textFieldPending);

        JButton check = new JButton("CHECK");
        check.setBounds(281, 378, 89, 23);
        check.setBackground(Color.black);
        check.setForeground(Color.white);
        panel.add(check);

        // Check Button Logic
        check.addActionListener(e -> {
            String name = choice.getSelectedItem();
            try {
                DatabaseConnector c = new DatabaseConnector();
                ResultSet rs = c.statement.executeQuery("SELECT * FROM patient_info WHERE Name='" + name + "'");
                if (rs.next()) {
                    textFieldR.setText(rs.getString("Room_Number"));
                    textFieldINTime.setText(rs.getString("Time"));
                    textFieldAmount.setText(rs.getString("Deposite"));
                }

                String roomNumber = textFieldR.getText();
                if (!roomNumber.isEmpty()) {
                    ResultSet rs1 = c.statement.executeQuery("SELECT Price FROM room WHERE room_no='" + roomNumber + "'");
                    if (rs1.next()) {
                        int roomPrice = rs1.getInt("Price");
                        int paid = textFieldAmount.getText().isEmpty() ? 0 : Integer.parseInt(textFieldAmount.getText());
                        int pending = Math.max(roomPrice - paid, 0);
                        textFieldPending.setText(String.valueOf(pending));
                    }
                    rs1.close();
                }
                rs.close();
                c.statement.close();
                c.connection.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error fetching patient details: " + ex.getMessage());
            }
        });

        // Update Pending Amount Live
        textFieldAmount.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    String roomNumber = textFieldR.getText();
                    if (!roomNumber.isEmpty()) {
                        DatabaseConnector c = new DatabaseConnector();
                        ResultSet rs = c.statement.executeQuery("SELECT Price FROM room WHERE room_no='" + roomNumber + "'");
                        if (rs.next()) {
                            int roomPrice = rs.getInt("Price");
                            int paid = textFieldAmount.getText().isEmpty() ? 0 : Integer.parseInt(textFieldAmount.getText());
                            int pending = Math.max(roomPrice - paid, 0);
                            textFieldPending.setText(String.valueOf(pending));
                        }
                        rs.close();
                        c.statement.close();
                        c.connection.close();
                    }
                } catch (Exception ex) {
                    textFieldPending.setText("0");
                }
            }
        });

        JButton update = new JButton("UPDATE");
        update.setBounds(56, 378, 89, 23);
        update.setBackground(Color.black);
        update.setForeground(Color.white);
        panel.add(update);

        // Update Button Logic
        update.addActionListener(e -> {
            try {
                DatabaseConnector c = new DatabaseConnector();
                String name = choice.getSelectedItem();
                String room = textFieldR.getText();
                String time = textFieldINTime.getText();
                String amount = textFieldAmount.getText();

                if (room.isEmpty() || time.isEmpty() || amount.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all fields before updating.");
                    return;
                }

                c.statement.executeUpdate("UPDATE patient_info SET Room_Number='" + room + "', Time='" + time + "', Deposite='" + amount + "' WHERE Name='" + name + "'");
                JOptionPane.showMessageDialog(null, "Updated Successfully!");
                c.statement.close();
                c.connection.close();
                setVisible(false);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Update Failed: " + ex.getMessage());
            }
        });

        JButton back = new JButton("BACK");
        back.setBounds(168, 378, 89, 23);
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        panel.add(back);
        back.addActionListener(e -> setVisible(false));

        setUndecorated(true);
        setSize(950, 500);
        setLayout(null);
        setLocation(200, 150);
        setVisible(true);
    }

    public static void main(String[] args) {
        new update_patient_details();
    }
}

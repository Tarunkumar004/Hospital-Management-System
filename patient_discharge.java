package minihospital;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

import javax.swing.*;

public class patient_discharge extends JFrame {

    patient_discharge() {

        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 790, 390);
        panel.setBackground(new Color(90, 156, 163));
        panel.setLayout(null);
        add(panel);

        JLabel label = new JLabel("CHECK-OUT");
        label.setBounds(100, 20, 150, 20);
        label.setFont(new Font("Tahoma", Font.BOLD, 20));
        label.setBackground(Color.white);
        panel.add(label);

        JLabel label2 = new JLabel("Customer Id");
        label2.setBounds(30, 80, 150, 20);
        label2.setFont(new Font("Tahoma", Font.BOLD, 14));
        label2.setBackground(Color.white);
        panel.add(label2);

        Choice choice = new Choice();
        choice.setBounds(200, 80, 150, 25);
        panel.add(choice);

        try {
            DatabaseConnector c = new DatabaseConnector();
            ResultSet resultSet = c.statement.executeQuery("select * from patient_info");
            while (resultSet.next()) {
                choice.add(resultSet.getString("Number"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel label3 = new JLabel("Room Number");
        label3.setBounds(30, 130, 150, 20);
        label3.setFont(new Font("Tahoma", Font.BOLD, 14));
        label3.setBackground(Color.white);
        panel.add(label3);

        JLabel RNo = new JLabel();
        RNo.setBounds(200, 130, 150, 20);
        RNo.setFont(new Font("Tahoma", Font.BOLD, 14));
        RNo.setBackground(Color.white);
        panel.add(RNo);

        JLabel label4 = new JLabel("In Time");
        label4.setBounds(30, 180, 150, 20);
        label4.setFont(new Font("Tahoma", Font.BOLD, 14));
        label4.setBackground(Color.white);
        panel.add(label4);

        JLabel INTime = new JLabel();
        INTime.setBounds(200, 180, 250, 20);
        INTime.setFont(new Font("Tahoma", Font.BOLD, 14));
        INTime.setBackground(Color.white);
        panel.add(INTime);

        JLabel label5 = new JLabel("Out Time");
        label5.setBounds(30, 230, 150, 20);
        label5.setFont(new Font("Tahoma", Font.BOLD, 14));
        label5.setBackground(Color.white);
        panel.add(label5);

        Date date = new Date();
        JLabel OUTTime = new JLabel("" + date);
        OUTTime.setBounds(200, 230, 250, 20);
        OUTTime.setFont(new Font("Tahoma", Font.BOLD, 14));
        OUTTime.setBackground(Color.white);
        panel.add(OUTTime);

        JButton discharge = new JButton("Discharge");
        discharge.setBounds(30, 300, 120, 20);
        discharge.setBackground(Color.black);
        discharge.setForeground(Color.white);
        panel.add(discharge);
        discharge.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                DatabaseConnector c = new DatabaseConnector();
                try {
                    String selectedId = choice.getSelectedItem();
                    String roomNumber = RNo.getText();  // Fetch Room Number from RNo label

                    // Check if Room Number is valid
                    if (roomNumber.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No Room Number assigned. Please check patient details.");
                        return;
                    }

                    // Remove patient from patient_info table
                    c.statement.executeUpdate("delete from patient_info where Number = '" + selectedId + "'");

                    // Update room availability
                    c.statement.executeUpdate("update room set Availability = 'Available' where room_no = '" + roomNumber + "'");

                    JOptionPane.showMessageDialog(null, "Patient Discharged Successfully");
                    setVisible(false);
                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });

        JButton check = new JButton("Check");
        check.setBounds(170, 300, 120, 20);
        check.setBackground(Color.black);
        check.setForeground(Color.white);
        panel.add(check);
        check.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                DatabaseConnector c = new DatabaseConnector();
                try {
                    String selectedId = choice.getSelectedItem();
                    ResultSet resultSet = c.statement.executeQuery(
                            "select * from patient_info where Number = '" + selectedId + "'");
                    if (resultSet.next()) {
                        RNo.setText(resultSet.getString("Room_Number"));
                        INTime.setText(resultSet.getString("Time"));
                    } else {
                        JOptionPane.showMessageDialog(null, "Patient not found.");
                    }
                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });

        JButton back = new JButton("Back");
        back.setBounds(300, 300, 120, 20);
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        panel.add(back);
        back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        setUndecorated(true);
        setSize(800, 400);
        setLayout(null);
        setLocation(300, 230);
        setVisible(true);

    }

    public static void main(String[] args) {
        new patient_discharge();
    }
}
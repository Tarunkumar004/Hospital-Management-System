package minihospital;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.*;

public class Employee_info extends JFrame {

    Employee_info() {

        JPanel panel = new JPanel();
        panel.setBounds(4, 4, 990, 590);
        panel.setBackground(new Color(109, 164, 170));
        panel.setLayout(null);
        add(panel);

        JTable table = new JTable();
        table.setBounds(10, 34, 980, 450);
        table.setBackground(new Color(109, 164, 170));
        table.setFont(new Font("Tahoma", Font.BOLD, 12));
        panel.add(table);

        try {
            DatabaseConnector c = new DatabaseConnector();
            ResultSet resultSet = c.statement.executeQuery("select * from emp_info");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel label1 = new JLabel("Name");
        label1.setBounds(41, 9, 70, 20);
        label1.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(label1);

        JLabel label2 = new JLabel("Age");
        label2.setBounds(200, 9, 70, 20);
        label2.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(label2);

        JLabel label3 = new JLabel("Phone Number");
        label3.setBounds(350, 9, 150, 20);
        label3.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(label3);

        JLabel label4 = new JLabel("Salary");
        label4.setBounds(550, 9, 150, 20);
        label4.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(label4);

        JLabel label5 = new JLabel("Gmail");
        label5.setBounds(710, 9, 150, 20);
        label5.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(label5);

        JLabel label6 = new JLabel("Aadhar Number");
        label6.setBounds(850, 9, 150, 20);
        label6.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(label6);

        JButton button = new JButton("BACK");
        button.setBounds(450, 500, 120, 30);
        button.setBackground(Color.black);
        button.setForeground(Color.white);
        panel.add(button);
        button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });


        setUndecorated(true);
        setSize(1000, 600);
        setLocation(170, 50);
        setLayout(null);
        setVisible(true);

    }

    public static void main(String[] args) {
        new Employee_info();
    }
}

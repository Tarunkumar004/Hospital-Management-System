package minihospital;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.*;

public class Ambulance extends JFrame {

    
    Ambulance(){

                JPanel panel = new JPanel();
        panel.setBounds(5, 5, 890, 590);
        panel.setBackground(new Color(90, 156, 163));
        panel.setLayout(null);
        add(panel);

        JTable table = new JTable();
        table.setBounds(10, 40, 900, 450);
        table.setBackground(new Color(90, 156, 163));
        panel.add(table);

        try {
            // Establish database connection
            DatabaseConnector c = new DatabaseConnector();
            ResultSet resultSet = c.statement.executeQuery("select * from ambulance");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel label1 = new JLabel("Name");
        label1.setBounds(70, 11, 100, 14);
        label1.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(label1);

        JLabel label2 = new JLabel("Gender");
        label2.setBounds(220, 11, 100, 14);
        label2.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(label2);

        JLabel label3 = new JLabel("Car_name");
        label3.setBounds(410, 11, 100, 14);
        label3.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(label3);

        JLabel label4 = new JLabel("Available");
        label4.setBounds(600, 11, 100, 14);
        label4.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(label4);

        JLabel label5 = new JLabel("Location");
        label5.setBounds(770, 11, 100, 14);
        label5.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(label5);



        JButton button = new JButton("BACK");
        button.setBounds(390, 510, 120, 30);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        panel.add(button);
        button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }

        });

        setUndecorated(true);
        setSize(900, 600);
        setLayout(null);
        setLocation(250, 50);
        setVisible(true);
    }


    
    public static void main(String[] args) {
        new Ambulance();
    }
    }


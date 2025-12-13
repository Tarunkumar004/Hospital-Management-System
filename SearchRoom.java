package minihospital;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.*;

public class SearchRoom extends JFrame {

    Choice choice;

    SearchRoom() {

        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 690, 490);
        panel.setLayout(null);
        panel.setBackground(new Color(90, 156, 163));
        add(panel);

        JLabel For = new JLabel("Search For Room");
        For.setBounds(280, 11, 186, 31);
        For.setForeground(Color.white);
        For.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(For);

        JLabel status = new JLabel("Status");
        status.setBounds(70, 70, 80, 20);
        status.setForeground(Color.white);
        status.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(status);

        choice = new Choice();
        choice.setBounds(170, 70, 120, 20);
        choice.add("Available");
        choice.add("Occupied");
        panel.add(choice);

        JTable table = new JTable();
        table.setBounds(0, 187, 700, 210);
        table.setBackground(new Color(90, 156, 163));
        table.setForeground(Color.white);
        panel.add(table);

        try {
            DatabaseConnector c = new DatabaseConnector();
            String q = "select * from room";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel Roomno = new JLabel("Room Number");
        Roomno.setBounds(23, 162, 150, 20);
        Roomno.setForeground(Color.white);
        Roomno.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(Roomno);

        JLabel available = new JLabel("Availability");
        available.setBounds(220, 162, 150, 20);
        available.setForeground(Color.white);
        available.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(available);

        JLabel price = new JLabel("Price");
        price.setBounds(410, 162, 150, 20);
        price.setForeground(Color.white);
        price.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(price);

        JLabel Bed = new JLabel("Bed Type");
        Bed.setBounds(550, 162, 150, 20);
        Bed.setForeground(Color.white);
        Bed.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(Bed);

        JButton Search = new JButton("SEARCH");
        Search.setBounds(200, 420, 120, 20);
        Search.setBackground(Color.BLACK);
        Search.setForeground(Color.WHITE);
        panel.add(Search);
        Search.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String q = "select * from room where Availability = '" + choice.getSelectedItem() + "'";
                try {
                    DatabaseConnector c = new DatabaseConnector();
                    ResultSet resultSet = c.statement.executeQuery(q);
                    table.setModel(DbUtils.resultSetToTableModel(resultSet));

                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });

        JButton Back = new JButton("BACK");
        Back.setBounds(380, 420, 120, 20);
        Back.setBackground(Color.BLACK);
        Back.setForeground(Color.WHITE);
        panel.add(Back);
        Back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        setSize(700, 500);
        setLayout(null);
        setLocation(350, 150);
        setVisible(true);
    }

    public static void main(String[] args) {
        new SearchRoom();
    }

}

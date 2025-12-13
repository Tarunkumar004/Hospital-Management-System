package minihospital;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame implements ActionListener{

    JTextField textField;

    JButton b1,b2;

    JPasswordField jPasswordField;
    Login() {

        super("Login Page");

        JLabel namelabel = new JLabel("Username");
        namelabel.setBounds(40,20,200,30);
        namelabel.setFont(new Font("Tahoma",Font.BOLD,20));
        namelabel.setForeground(Color.black);
        add(namelabel);


        JLabel password = new JLabel("Password");
        password.setBounds(40,70,200,30);
        password.setFont(new Font("Tahoma",Font.BOLD,20));
        password.setForeground(Color.BLACK);
        add(password);

        textField = new JTextField();
        textField.setBounds(180,20,150,30);
        textField.setFont(new Font("Tahoma",Font.PLAIN,15));
        textField.setBackground(Color.white);
        add(textField);

        jPasswordField = new JPasswordField();
        jPasswordField.setBounds(180,70,150,30);
        jPasswordField.setFont(new Font("Tahoma",Font.PLAIN,15));
        jPasswordField.setBackground(Color.white);
        add(jPasswordField);

        ImageIcon imageIcon  = new ImageIcon(ClassLoader.getSystemResource("icon/Se.gif"));
        Image i1 = imageIcon.getImage().getScaledInstance(380,450,Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(i1);
        JLabel label = new JLabel(imageIcon1);
        label.setBounds(410,-20,400,300);
        add(label);

        b1 = new JButton("LOGIN");
        b1.setBounds(40,160,120,30);
        b1.setFont(new Font("serif",Font.BOLD,15));
        b1.setBackground(Color.black);
        b1.setForeground(Color.white);
        b1.addActionListener(this);
        add(b1);

        b2 = new JButton("CANCEL");
        b2.setBounds(210,160,120,30);
        b2.setFont(new Font("serif",Font.BOLD,15));
        b2.setBackground(Color.black);
        b2.setForeground(Color.white);
        b2.addActionListener(this);
        add(b2);

        getContentPane().setBackground(new Color(109,164,170));
        setSize(800,300);
        setLayout(null);
        setLocation(300,200);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()==b1){
            try{
               DatabaseConnector c = new DatabaseConnector();
               String username = textField.getText();
               String password = jPasswordField.getText();

               String q = "select * from admin where username = '"+username+"' and  password = '"+password+"'";
               ResultSet resultSet = c.statement.executeQuery(q);
               if(resultSet.next()){
                new Reception();
                setVisible(false);
               }else{
                JOptionPane.showMessageDialog(null, "Invalid Username and Password");
               }

            }catch(Exception E){
                E.printStackTrace();
            }
        }else{
            System.exit(40);
        }
    }
    public static void main(String[] args) {
        new Login();
    }
}

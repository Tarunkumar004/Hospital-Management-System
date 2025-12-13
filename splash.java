package minihospital;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class splash extends JFrame implements Runnable {

    Thread t;

    splash() {

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/Sr.png"));
        Image i2 = i1.getImage().getScaledInstance(1000, 700, Image.SCALE_DEFAULT);

        ImageIcon i3 = new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        
        add(img);

        t = new Thread(this);
        t.start();
        setVisible(true);

        int x = 1;
        for (int i = 2; i <= 600; i += 4, x += 1) {
            setLocation(500 - ((i + x) / 2), 350 - (i / 2));
            setSize(i + 3 * x, i + x / 2);

            try {
                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
                // TODO: handle exception
            }
        }

        setVisible(true);

    }

    public void run() {
        try {
            Thread.sleep(5000);
            setVisible(false);
            new Login();
            // next class
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new splash();
    }
}
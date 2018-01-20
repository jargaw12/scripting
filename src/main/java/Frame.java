import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    Panel panel;
    public Frame() throws HeadlessException {
        super("Scripting");
        setMinimumSize(new Dimension(600,300));
        setLocationRelativeTo(null);
        try {
            //XD
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        panel=new Panel();
        setJMenuBar(new Menu(panel.getTable()));
        setContentPane(panel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
}

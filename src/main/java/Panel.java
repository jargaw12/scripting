import javax.swing.*;
import java.awt.event.ActionEvent;


public class Panel extends JTabbedPane  {
    private Table table;

    public Panel() {
        table=new Table();
        add("Dane i modyfikacja",new TabData(table));
        add("Skrypt 1",new TabScripting(s1, table));
        add("Skrypt 2",new TabScripting(s2, table));
    }

    Action s1 = new AbstractAction("Uruchom skrypt 1") {
        public void actionPerformed(ActionEvent e) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    //TODO todododdooosilnik skryptowy 1

                }
            });
        }
    };

    Action s2 = new AbstractAction("Uruchom skrypt 2") {
        public void actionPerformed(ActionEvent e) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    //TODO tudoduuu silnik skryptowy 2
                }
            });
        }
    };


    public Table getTable() {
        return table;
    }
}

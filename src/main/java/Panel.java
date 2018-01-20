import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;


public class Panel extends JTabbedPane  {
    private Table table;
    ScriptEngineManager managerScript;
    ScriptEngine engineJRuby;
    ScriptEngine engineJS;
    TabScripting tab1;
    TabScripting tab2;

    public Panel() {
        table=new Table();
        tab1 = new TabScripting(s1, table);
        tab2 = new TabScripting(s2, table);
        add("Dane i modyfikacja",new TabData(table));
        add("Skrypt 1",new TabScripting(s1, table));
        add("Skrypt 2",new TabScripting(s2, table));
        managerScript =    new ScriptEngineManager();
        engineJRuby=managerScript.getEngineByName("jruby");
        engineJS = managerScript.getEngineByName("nashorn");

    }

    Action s1 = new AbstractAction("Uruchom skrypt 1") {
        public void actionPerformed(ActionEvent e) {

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    String skrypt = tab1.getArea1().getText();
                    InputStream in = this.getClass().getResourceAsStream(skrypt);
                    Reader reader = new InputStreamReader(in);
                    try {
                        engineJS.eval(reader);
                    } catch (ScriptException e1) {
                        e1.printStackTrace();
                    }

                }
            });
        }
    };

    Action s2 = new AbstractAction("Uruchom skrypt 2") {
        public void actionPerformed(ActionEvent e) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    String skrypt = tab1.getArea1().getText();
                    InputStream in = this.getClass().getResourceAsStream(skrypt);
                    Reader reader = new InputStreamReader(in);
                    try {
                        engineJRuby.eval(reader);
                    } catch (ScriptException e1) {
                        e1.printStackTrace();
                    }
                }
            });
        }
    };


    public Table getTable() {
        return table;
    }
}

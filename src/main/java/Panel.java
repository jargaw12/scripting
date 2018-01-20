import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.nio.charset.StandardCharsets;


public class Panel extends JTabbedPane {
    ScriptEngineManager managerScript;
    ScriptEngine engineJRuby;
    ScriptEngine engineJS;
    TabScripting tab1;
    TabScripting tab2;
    String skrypt;
    Reader reader;
    InputStream in;

    Action s1 = new AbstractAction("Uruchom skrypt 1") {
        public void actionPerformed(ActionEvent e) {

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    skrypt = tab1.area1.getText().toString();


                    try {
                        in = new ByteArrayInputStream(skrypt.getBytes(StandardCharsets.UTF_8.name()));
                    } catch (UnsupportedEncodingException e1) {
                        e1.printStackTrace();
                    }
                    reader = new InputStreamReader(in);
                    try {
                        engineJRuby.eval(reader);
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

                    skrypt = tab2.area1.getText().toString();


                    try {
                        in = new ByteArrayInputStream(skrypt.getBytes(StandardCharsets.UTF_8.name()));
                    } catch (UnsupportedEncodingException e1) {
                        e1.printStackTrace();
                    }
                    reader = new InputStreamReader(in);
                    try {
                        engineJRuby.eval(reader);
                    } catch (ScriptException e1) {
                        e1.printStackTrace();
                    }
                }
            });
        }
    };
    private Table table;

    public Panel() {
        table = new Table();
        tab1 = new TabScripting(s1, table);
        tab2 = new TabScripting(s2, table);
        add("Dane i modyfikacja", new TabData(table));
        add("Skrypt 1", tab1);
        add("Skrypt 2", tab2);
        managerScript = new ScriptEngineManager();
        engineJRuby = managerScript.getEngineByName("jruby");
        engineJS = managerScript.getEngineByName("nashorn");

    }

    public Table getTable() {
        return table;
    }
}

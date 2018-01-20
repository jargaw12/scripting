import javax.script.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.*;

public class Panel extends JTabbedPane {
    ScriptEngineManager managerScript;
    ScriptEngine engineJRuby;
    ScriptEngine engineJS;
    TabScripting tab1;
    TabScripting tab2;
    String skrypt;
    Reader reader;
    InputStream in;

    Action s1 = new AbstractAction("Uruchom skrypt Nashorn") {
        public void actionPerformed(ActionEvent e) {

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    tab1.area2.setText("");
                    StringWriter writer = new StringWriter();
                    engineJS.getContext().setWriter(writer);
                    skrypt = tab1.area1.getText().toString();
                    try {
                        Bindings scope = engineJS.createBindings();
                        scope.put("products", table.getModel().getProducts());
                       Object result =  engineJS.eval(skrypt,scope);

                        tab1.area2.setText(writer.toString());
                        table.getModel().fireTableDataChanged();
                    } catch (ScriptException e1) {
                        e1.printStackTrace();
                    }
                }
            });
        }
    };

    Action s2 = new AbstractAction("Uruchom skrypt Ruby") {
        public void actionPerformed(ActionEvent e) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    tab2.area2.setText("");
                    StringWriter writer = new StringWriter();
                    engineJRuby.getContext().setWriter(writer);
                    skrypt = tab2.area1.getText().toString();
                    try {
                        Bindings scope = engineJRuby.createBindings();
                        scope.put("products", table.getModel().getProducts());
                        Object result = engineJRuby.eval(skrypt,scope);
                        tab2.area2.setText(writer.toString());
                        table.getModel().fireTableDataChanged();
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
        managerScript.getEngineFactories();
        engineJRuby = managerScript.getEngineByName("jruby");
        engineJS = managerScript.getEngineByName("nashorn");

    }

    public Table getTable() {
        return table;
    }
}

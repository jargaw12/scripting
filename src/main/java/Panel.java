import jdk.nashorn.api.scripting.NashornException;
import org.jruby.Ruby;
import org.jruby.RubyException;
import org.jruby.RubyModule;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.StringWriter;

import static jdk.nashorn.api.scripting.NashornException.getScriptStackString;

public class Panel extends JTabbedPane {
    ScriptEngineManager managerScript;
    ScriptEngine engineJRuby;
    ScriptEngine engineJS;
    TabScripting tab1;
    TabScripting tab2;
    private Table table;
    Action s1 = new AbstractAction("Uruchom skrypt Nashorn") {
        public void actionPerformed(ActionEvent e) {

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    tab1.area2.setText("");
                    StringWriter writer = new StringWriter();
                    StringWriter err = new StringWriter();
                    String in = tab1.area1.getText().toString();
                    engineJS.getContext().setWriter(writer);
                    engineJS.getContext().setErrorWriter(err);

                    try {
                        Bindings scope = engineJS.createBindings();
                        scope.put("products", table.getModel().getProducts());
                        Object result = engineJS.eval(in, scope);

                        tab1.area2.setText(writer.toString());

                        table.getModel().fireTableDataChanged();
                    } catch (ScriptException e1) {
                        if (e1.getCause() instanceof NashornException) {
                            String jsStackTrace = getScriptStackString(e1.getCause());
                            tab1.area2.setText(jsStackTrace);
                        }
                        //e1.printStackTrace();
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
                    StringWriter err = new StringWriter();
                    String in = tab2.area1.getText().toString();
                    engineJRuby.getContext().setWriter(writer);

                    try {
                        Bindings scope = engineJRuby.createBindings();
                        scope.put("products", table.getModel().getProducts());
                        Object result = engineJRuby.eval(in, scope);
                        tab2.area2.setText(writer.toString());
                        table.getModel().fireTableDataChanged();
                    } catch (ScriptException e1) {
                        engineJRuby.getContext().setErrorWriter(err);
                        if (false) {
                            //String jsStackTrace = RubyException.getScriptStackString(e1.getCause());
                            tab2.area2.setText("");
                        }
                         e1.printStackTrace();
                    }
                }
            });
        }
    };

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

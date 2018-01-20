import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.*;

public class TabScripting extends JPanel {
    private Table table;
    JButton exec;

    public TabScripting(Action a, Table tab) {
        this.table=tab;
        setLayout(new GridBagLayout());
        GridBagConstraints c=new GridBagConstraints();
        TextArea area1=new TextArea();
        TextArea area2=new TextArea();
        exec=new JButton(a);
        exec.setEnabled(false);
        table.getModel().addTableModelListener(new TableModelListener() {
            public void tableChanged(TableModelEvent e) {
                if (!exec.isEnabled() && table.getModel().getRowCount()>0){
                    exec.setEnabled(true);
                }
            }
        });
        area2.setEditable(false);
        c.fill=GridBagConstraints.BOTH;
        c.weightx=1;
        c.gridx=0;
        c.gridy=0;
        add(area1,c);
        c.fill=GridBagConstraints.HORIZONTAL;
        c.gridy=1;
        add(exec,c);
        c.fill=GridBagConstraints.BOTH;
        c.gridy=2;
        add(area2,c);
    }
}

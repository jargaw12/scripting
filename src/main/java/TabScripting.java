import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.*;

public class TabScripting extends JPanel {
    private Table table;
    JButton exec;

    public TextArea getArea1() {
        return area1;
    }

    public void setArea1(TextArea area1) {
        this.area1 = area1;
    }

    public TextArea getArea2() {
        return area2;
    }

    public void setArea2(TextArea area2) {
        this.area2 = area2;
    }

    TextArea area1;
    TextArea area2;

    public TabScripting(Action a, Table tab) {
        this.table=tab;
        setLayout(new GridBagLayout());
        GridBagConstraints c=new GridBagConstraints();
        area1=new TextArea();
        area2=new TextArea();
        exec=new JButton(a);
        exec.setEnabled(true);
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

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TabData extends JPanel {
    JScrollPane scrollPane;
    JButton addBtn;
    Table table;

    public TabData(Table table1) {
        addBtn =new JButton(addRow);
        addBtn.setEnabled(false);
        setLayout(new BorderLayout());
        table=table1;
        table.getModel().addTableModelListener(new TableModelListener() {
            public void tableChanged(TableModelEvent e) {
                if (!addBtn.isEnabled() && table.getModel().getRowCount()>0){
                    addBtn.setEnabled(true);
                }
            }
        });
        scrollPane = new JScrollPane(table.getTable());
        add(scrollPane,BorderLayout.CENTER);
        add(addBtn,BorderLayout.SOUTH);
    }

    Action addRow = new AbstractAction("Dodaj") {
        public void actionPerformed(ActionEvent e) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    table.getModel().addRow(new Object[]{null, null, null,null,null});
                }
            });
        }
    };
}

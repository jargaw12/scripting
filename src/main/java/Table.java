import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class Table {
    private DefaultTableModel model;
    private JTable table;

    public Table() {
        model = new DefaultTableModel();
        table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(600, 300));
        model.addColumn("Koszyk");
        model.addColumn("Cena");
        model.addColumn("Srednia");
        model.addColumn("XD");
        model.addColumn("XDD");
    }

    public void read(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        String row = scanner.nextLine();
        String[] tokens = row.split(";");
        while ((scanner.hasNextLine())) {
            row = scanner.nextLine();
            tokens = row.split(";");
            model.addRow(new Object[]{tokens[0], tokens[1], tokens[2], tokens[3], tokens[4]});
        }
    }

    public void save(File f) {
        Writer output = null;
        try {
            File file = f;
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            output = new BufferedWriter(new FileWriter(file, true));
            for (int i = 0; i < model.getRowCount(); i++) {
                output.append("\r\n" + model.getValueAt(i, 0) + ";" + model.getValueAt(i, 1) + ";" + model.getValueAt(i, 2) + ";" + model.getValueAt(i, 3) + ";" + model.getValueAt(i, 4) + ";" + model.getValueAt(i, 5));
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            assert output != null;
            output.close();
        } catch (IOException ec) {
            ec.printStackTrace();
        }

    }

    public DefaultTableModel getModel() {
        return model;
    }

    public JTable getTable() {
        return table;
    }
}

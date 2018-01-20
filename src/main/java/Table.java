import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class Table {
    private ProductDataModel model;
    private JTable table;

    public Table() {
        model = new ProductDataModel();
        table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(600, 300));
    }

    public void read(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        String row ;
        String[] tokens;
        while ((scanner.hasNextLine())) {
            row = scanner.nextLine();
            tokens = row.split(";");
            model.addElement(new Product(tokens[0],Double.parseDouble(tokens[1]),Integer.parseInt(tokens[2])));
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
                output.append("\r\n" + model.getValueAt(i, 0) + ";" + model.getValueAt(i, 1) + ";" + model.getValueAt(i, 2));
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

    public ProductDataModel getModel() {
        return model;
    }

    public JTable getTable() {
        return table;
    }
}

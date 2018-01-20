import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Menu extends JMenuBar {
    private JMenu plik;
    private JMenuItem open;
    private JMenuItem save;
    private Table table;

    public Menu(Table table) {
        this.table=table;
        plik = new JMenu("Plik");
        open=new JMenuItem(otworz);
        save=new JMenuItem(zapisz);
        save.setEnabled(false);
        JMenuItem s=new JMenuItem();
        plik.add(open);
        plik.add(save);
        add(plik);
    }

    Action otworz = new AbstractAction("Otwórz") {
        public void actionPerformed(ActionEvent e) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    JFileChooser chooser = new JFileChooser();
                    chooser.setCurrentDirectory(new File("."));
                    chooser.setMultiSelectionEnabled(false);
                    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    int result = chooser.showOpenDialog(null);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        File selectedFile;
                        selectedFile = chooser.getSelectedFile();
                        try {
                            table.read(selectedFile);
                        } catch (FileNotFoundException e1) {
                            e1.printStackTrace();
                        }
                        save.setEnabled(true);
                    }
                }
            });
        }
    };

    Action zapisz = new AbstractAction("Zapisz") {
        public void actionPerformed(ActionEvent e) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    JFileChooser chooser = new JFileChooser();
                    chooser.setCurrentDirectory(new File("."));
                    int result = chooser.showSaveDialog(null);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        File file = chooser.getSelectedFile();
                        table.save(file);

                    }
                }
            });
        }
    };
}

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ProductDataModel extends AbstractTableModel {
    private ArrayList<Product> products;


    public ProductDataModel() {
        products = new ArrayList<>();
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    @Override
    public int getRowCount() {
        return products.size();
    }

    @Override
    public int getColumnCount() {
        return Product.getFieldsCount();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return products.get(rowIndex).getColumnValue(columnIndex);
    }

    @Override
    public String getColumnName(int column) {
        return Product.getColumnName(column);
    }

    public void addElement(Product product){
        products.add(product);
        fireTableDataChanged();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex==0){
            products.get(rowIndex).setName(aValue.toString());
        }
        else if (columnIndex==1){
            products.get(rowIndex).setPrice((double)aValue);
        }
        else {
            products.get(rowIndex).setQuantity((double)aValue);
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }

}

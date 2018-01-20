public class Product {
    private String name;
    private double price;
    private double quantity;

    public Product(String name, double price, double quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Object getColumnValue(int columnIndex) {
        Object res = null;
        try {
            res = getClass().getDeclaredFields()[columnIndex].get(this);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return res;
    }


    public static int getFieldsCount() {
        return Product.class.getDeclaredFields().length;
    }

    public static String getColumnName(int column) {
        return Product.class.getDeclaredFields()[column].getName();
    }

    @Override
    public String toString() {
        return this.getName();
    }
}

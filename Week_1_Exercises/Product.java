public class Product 
{
    int productId;
    String productName;
    int quantity;
    double price;

    public Product(int id, String name, int qty, double prc) {
        this.productId = id;
        this.productName = name;
        this.quantity = qty;
        this.price = prc;
    }

    
}

import java.util.HashMap;

public class Inventory {
    private HashMap<Integer, Product> products = new HashMap<>();

    public void addProduct(Product p) {
        products.put(p.productId, p);
    }

    public void updateProduct(Product p) {
        if (products.containsKey(p.productId)) {
            products.put(p.productId, p);
        }
    }

    public void deleteProduct(int productId) {
        products.remove(productId);
    }

    public Product getProduct(int productId) {
        return products.get(productId);
    }
}

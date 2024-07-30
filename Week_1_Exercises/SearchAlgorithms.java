public class SearchAlgorithms {
    // Linear Search
    public static Product linearSearch(Product[] products, int id) {
        for (Product p : products) {
            if (p.productId == id) return p;
        }
        return null;
    }

    
    
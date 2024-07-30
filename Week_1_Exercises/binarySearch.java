public static binarySearch(Product[] products, int id) {
        int left = 0, right = products.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (products[mid].productId == id) return products[mid];
            if (products[mid].productId < id) left = mid + 1;
            else right = mid - 1;
        }
        return null;
    }


package cooperativeMgmt;

public class Product {
    private String productId;

    public Product(String  productId){
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

    @Override
    public String toString() {
        return "productId" + productId;
    }
}

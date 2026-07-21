package service.product;

import model.Product;
import service.GenericService;

public interface ProductService extends GenericService<Product> {
    Product findBelowSpecified(int quantity);
}

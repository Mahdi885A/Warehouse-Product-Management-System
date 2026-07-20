package repository.product;

import model.Product;
import repository.GenericRepository;

public interface ProductRepository extends GenericRepository<Product> {

    Product findBelowSpecified(int quantity);
}

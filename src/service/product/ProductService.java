package service.product;

import model.Product;
import service.GenericService;

import java.util.Optional;

public interface ProductService extends GenericService<Product> {
    Product findBelowSpecified(int quantity);

    Long totalNumberOfProduct();

    double getPriceAvg();

    Optional<Product> getMostExpensiveProduct();
}

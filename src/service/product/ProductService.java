package service.product;

import model.Product;
import service.GenericService;

import java.util.Optional;

public interface ProductService extends GenericService<Product> {
    Product findBelowSpecified(int quantity);

    public Long totalNumberOfProduct();

    public double getPriceAvg();

    public Optional<Product> getMostExpensiveProduct();
}

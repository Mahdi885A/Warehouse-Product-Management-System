package service.product.impl;

import exception.InvalidDataException;
import exception.ProductNotFoundException;
import model.Product;
import repository.product.impl.ProductRepositoryImpl;
import service.product.ProductService;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {
    private ProductRepositoryImpl productRepository = new ProductRepositoryImpl();

    @Override
    public Long creat(Product product) {
        if(product.getName() == null || product.getName().isEmpty()){
            throw new InvalidDataException("Invalid name's product...");
        }
        if (product.getPrice()<0){
            throw new InvalidDataException("Invalid price's product...");
        }
        if(product.getQuantity()<0){
            throw new InvalidDataException("Invalid quantity...");
        }
        return productRepository.save(product);

    }

    @Override
    public boolean update(Product product ,Long id) {
        if (productRepository.findById(id).isEmpty()){
            throw new ProductNotFoundException("Product not fond, Invalid ID...");
        }
        if(product.getName() == null || product.getName().isEmpty()){
            throw new InvalidDataException("Invalid name's product...");
        }
        if (product.getPrice()<0){
            throw new InvalidDataException("Invalid price's product...");
        }
        if(product.getQuantity()<0){
            throw new InvalidDataException("Invalid quantity...");
        }
        return productRepository.update(product,id);
    }

    @Override
    public boolean delete(Long id) {
        if (productRepository.findById(id).isEmpty()){
            throw new ProductNotFoundException("Product not fond, Invalid ID...");
        }
        return productRepository.delete(id);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findBelowSpecified(int quantity) {
        if (quantity<= 0){
            throw new InvalidDataException("The Quantity must not be 0 or negative...");
        }
        return productRepository.findBelowSpecified(quantity);
    }

    @Override
    public Long totalNumberOfProduct() {
       return productRepository.findAll().stream().count();
    }

    @Override
    public double getPriceAvg() {
        return productRepository.findAll().stream().mapToDouble(Product :: getPrice).average().orElse(0.0);
    }

    @Override
    public Optional<Product> getMostExpensiveProduct() {
        return productRepository.findAll().stream().max(Comparator.comparingDouble(Product :: getPrice));
    }
}

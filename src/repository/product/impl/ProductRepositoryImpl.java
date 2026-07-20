package repository.product.impl;

import exception.DatabaseRepositoryException;
import model.Product;
import repository.product.ProductRepository;
import util.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepositoryImpl implements ProductRepository {


    @Override
    public Long save(Product product) {
        String sql = "insert into product(id,name,price,quantity) values (?,?,?,?)";
        try(Connection connection = DatabaseConfig.getConnection()){

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1,product.getId());
            ps.setString(2,product.getName());
            ps.setDouble(3,product.getPrice());
            ps.setInt(4,product.getQuantity());
            try(ResultSet rs = ps.executeQuery()){
                return product.getId();
            }
            catch (SQLException e){
                throw new DatabaseRepositoryException("Product ID not returned!");
            }

        }catch (SQLException e){
            throw new DatabaseRepositoryException("The product save failed...");
        }
    }

    @Override
    public boolean update(Product product, Long id) {
        String sql = "update product set id = ? ,name= ? ,price= ? ,quantity= ? where id = ? ";
        try(Connection connection = DatabaseConfig.getConnection()){
         PreparedStatement ps = connection.prepareStatement(sql);
         ps.setLong(1,product.getId());
         ps.setString(2,product.getName());
         ps.setDouble(3,product.getPrice());
         ps.setInt(4,product.getQuantity());
         ps.setLong(5,id);

         return (ps.executeUpdate()>0);

        }catch (SQLException e){
            throw new DatabaseRepositoryException("The product update failed...");
        }
    }

    @Override
    public boolean delete(Long id) {
        String sql = "delete product where id = ?";
        try (Connection connection = DatabaseConfig.getConnection()){
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1,id);
            return (ps.executeUpdate()>0);
        }catch (SQLException e){
            throw new DatabaseRepositoryException("The product delete failed");
        }
    }

    @Override
    public Optional<Product> findById(Long id) {
        String sql = "select * from product where id = ? ";
        try(Connection connection =DatabaseConfig.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(
                            new Product(
                                    rs.getLong(1),
                                    rs.getString(2),
                                    rs.getDouble(3),
                                    rs.getInt(4)

                            )
                    );
                }
                return Optional.empty();
            }

        }catch (SQLException e){
            throw new DatabaseRepositoryException("Finding By ID From product Table Failed!");
        }
    }

    @Override
    public List<Product> findAll() {
        String sql = "select * from product order by id";

        try(Connection connection = DatabaseConfig.getConnection()){
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Product> products =new ArrayList<>();
            while (rs.next()){
                products.add(
                        new Product(
                                rs.getLong(1),
                                rs.getString(2),
                                rs.getDouble(3),
                                rs.getInt(4))
                );
            }
            return products;
        } catch (SQLException e) {
            throw new DatabaseRepositoryException("Finding All From product Table Failed...");
        }
    }

    @Override
    public Product findBelowSpecified(int quantity) {
        String sql = "select * from product where quantity <?";
        try(Connection connection = DatabaseConfig.getConnection()){
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,quantity);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                return new Product(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getInt(4)
                );
            }
        }catch (SQLException e){
            throw new DatabaseRepositoryException("The find product failed... ");
        }
        return null;
    }
}

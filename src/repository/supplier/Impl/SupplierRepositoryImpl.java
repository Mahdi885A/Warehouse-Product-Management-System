package repository.supplier.Impl;

import exception.DatabaseRepositoryException;
import model.Product;
import model.Supplier;
import repository.supplier.SupplierRepository;
import util.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SupplierRepositoryImpl implements SupplierRepository {
    @Override
    public Long save(Supplier supplier) {
        String sql = "insert into supplier(id,company_name,phone) values (?,?,?)";
        try (Connection connection = DatabaseConfig.getConnection()) {

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, supplier.getId());
            ps.setString(2, supplier.getCompanyName());
            ps.setString(3, supplier.getPhone());
            ps.executeUpdate();
            return supplier.getId();
        } catch (SQLException e) {
            throw new DatabaseRepositoryException("The supplier save failed..."+ e.getMessage());
        }
    }

    @Override
    public boolean update(Supplier supplier, Long id) {
        String sql = "update supplier set company_name =?,phone =? where id = ?";
        try (Connection connection = DatabaseConfig.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, supplier.getCompanyName());
            ps.setString(2, supplier.getPhone());
            ps.setLong(3, id );
            return (ps.executeUpdate() > 0);
        } catch (SQLException e) {
            throw new DatabaseRepositoryException("The supplier Update failed");
        }
    }

    @Override
    public boolean delete(Long id) {
        String sql = "delete from supplier where id = ?";
        try (Connection connection = DatabaseConfig.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            return (ps.executeUpdate() > 0);
        } catch (SQLException e) {
            throw new DatabaseRepositoryException("The product delete failed");
        }
    }

        @Override
        public Optional<Supplier> findById (Long id){
            String sql = "select * from product where id = ? ";
            try (Connection connection = DatabaseConfig.getConnection()) {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setLong(1, id);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return Optional.of(
                                new Supplier(
                                        rs.getLong(1),
                                        rs.getString(2),
                                        rs.getString(3)
                                )
                        );
                    }
                    return Optional.empty();
                }

            } catch (SQLException e) {
                throw new DatabaseRepositoryException("Finding By ID From product Table Failed!");
            }
        }



    @Override
    public List<Supplier> findAll() {
        String sql = "select * from supplier";

        try(Connection connection = DatabaseConfig.getConnection()){
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Supplier> suppliers =new ArrayList<>();
            while (rs.next()){
                suppliers.add(
                        new Supplier(
                                rs.getLong(1),
                                rs.getString(2),
                                rs.getString(3)
                        )
                );
            }
            return suppliers;
        } catch (SQLException e) {
            throw new DatabaseRepositoryException("Finding All From supplier Table Failed...");
        }

    }
}

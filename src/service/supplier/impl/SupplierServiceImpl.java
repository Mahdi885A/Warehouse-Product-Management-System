package service.supplier.impl;

import exception.InvalidDataException;
import exception.SupplierNotFondException;
import model.Supplier;
import repository.supplier.Impl.SupplierRepositoryImpl;
import repository.supplier.SupplierRepository;
import service.supplier.SupplierService;

import java.util.List;
import java.util.Optional;

public class SupplierServiceImpl implements SupplierService {
    private SupplierRepositoryImpl supplierRepository;

    @Override
    public Long creat(Supplier supplier) {
        if (supplier.getId() <= 0) {
            throw new InvalidDataException("The ID must not be 0 or negative...");
        }
        if (supplier.getCompanyName() == null || supplier.getCompanyName().isEmpty()) {
            throw new InvalidDataException("Invalid name company... ");
        }
        if (supplier.getPhone() == null || supplier.getPhone().isEmpty()) {
            throw new InvalidDataException("Invalid Phone number...");
        }
        return supplierRepository.save(supplier);
    }

    @Override
    public boolean update(Supplier supplier, Long id) {
        if (supplierRepository.findById(id).isEmpty()) {
            throw new SupplierNotFondException("Supplier not found...");
        }
        if (supplier.getCompanyName() == null || supplier.getCompanyName().isEmpty()) {
            throw new InvalidDataException("Invalid name company... ");
        }
        if (supplier.getPhone() == null || supplier.getPhone().isEmpty()) {
            throw new InvalidDataException("Invalid Phone number...");
        }
        return supplierRepository.update(supplier, id);
    }

    @Override
    public boolean delete(Long id) {
        if (supplierRepository.findById(id).isEmpty()){
            throw new SupplierNotFondException("Supplier not fond...");
        }
        return supplierRepository.delete(id);
    }

    @Override
    public Optional<Supplier> findById(Long id) {
        return supplierRepository.findById(id);
    }

    @Override
    public List<Supplier> findAll() {
        return supplierRepository.findAll();
    }
}

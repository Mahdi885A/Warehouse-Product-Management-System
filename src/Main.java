import model.Product;
import model.Supplier;
import repository.product.impl.ProductRepositoryImpl;
import repository.supplier.Impl.SupplierRepositoryImpl;
import service.product.impl.ProductServiceImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ProductServiceImpl pRI = new ProductServiceImpl();
        SupplierRepositoryImpl sRI = new SupplierRepositoryImpl();
        Scanner scan = new Scanner(System.in);
        int menu;
        do {
            System.out.println("""
                    1. Add Product
                    2. Show All Products
                    3. Update Product
                    4. Delete Product
                    5. Add Supplier
                    6. Show All Suppliers
                    7. Update Supplier
                    8. Delete Supplier
                    9. Reports
                    10. Thread Simulation
                    11. Exit
                    """);
            System.out.print("Enter number: ");
            menu = scan.nextInt();

            switch (menu){
                case 1:
                    System.out.println("====ADD PRODUCT====");

                    System.out.print("Enter ID: ");
                    Long id = scan.nextLong();
                    scan.nextLine();
                    System.out.print("Enter name: ");
                    String nameProduct = scan.next();
                    scan.nextLine();
                    System.out.print("Enter price: ");
                    double priceProduct = scan.nextDouble();
                    scan.nextLine();
                    System.out.print("Enter quantity: ");
                    int quantityProduct = scan.nextInt();
                    scan.nextLine();

                    Product product = new Product(id,nameProduct,priceProduct,quantityProduct);
                    pRI.creat(product);
                break;

                case 2:
                    System.out.println("===SHOW ALL PRODUCT===");
                    System.out.println(pRI.findAll());
                break;

                case 3:
                    System.out.println("===UPDATE PRODUCT===");
                    System.out.print("Enter ID: ");
                    id = scan.nextLong();
                    scan.nextLine();
                    System.out.print("Enter name: ");
                    nameProduct = scan.next();
                    scan.nextLine();
                    System.out.print("Enter price: ");
                    priceProduct = scan.nextDouble();
                    scan.nextLine();
                    System.out.print("Enter quantity: ");
                    quantityProduct = scan.nextInt();
                    scan.nextLine();
                    Product product1 = new Product(nameProduct,priceProduct,quantityProduct);

                    pRI.update(product1,id);
                break;

                case 4:
                    System.out.println("===DELETE PRODUCT===");
                    System.out.print("Enter ID: ");
                    id = scan.nextLong();
                    pRI.delete(id);
                break;

                case 5:
                    System.out.println("====ADD SUPPLIER====");

                    System.out.print("Enter ID: ");
                    Long idSupplier = scan.nextLong();
                    scan.nextLine();
                    System.out.print("Enter name company: ");
                    String nameCompany = scan.next();
                    scan.nextLine();
                    System.out.print("Enter Phone number: ");
                    String phoneNumber = scan.next();
                    scan.nextLine();

                    Supplier supplier = new Supplier(idSupplier,nameCompany,phoneNumber);
                    sRI.save(supplier);
                break;

                case 6:
                    System.out.println("===SHOW ALL SUPPLIER===");
                    sRI.findAll();

                break;

                case 7:
                    System.out.println("===UPDATE SUPPLIER===");
                    System.out.print("Enter ID: ");
                    idSupplier =scan.nextLong();
                    scan.nextLine();
                    System.out.print("Enter company name: ");
                    nameCompany = scan.next();
                    scan.nextLine();
                    System.out.print("Enter phone number: ");
                    phoneNumber = scan.next();
                    scan.nextLine();
                    Supplier supplier1 = new Supplier(nameCompany,phoneNumber);
                    sRI.update(supplier1,idSupplier);
                break;

                case 8 :
                    System.out.println("===DELETE SUPPLIER===");
                    System.out.print("Enter ID: ");
                    idSupplier =scan.nextLong();
                    sRI.delete(idSupplier);
                break;
                case 9:
                    System.out.println("Most expensive product");
                    System.out.println(pRI.getMostExpensiveProduct());
                    System.out.println("Average product price");
                    System.out.println(pRI.getPriceAvg());
                    System.out.println("Most expensive product");
                    System.out.println(pRI.totalNumberOfProduct());
                break;
                case 10:
                    System.out.println("Under construction....");
                break;

                case 11:
                    System.out.println("GOOD BYE!");
                break;
                default:
                    System.out.println("Invalid number! ");
                break;
            }

        }while (menu!=11);

    }
}
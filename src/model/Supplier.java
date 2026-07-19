package model;

import java.util.Objects;

public class Supplier {
        private Long id;
        private String companyName;
        private String phone;

    public Supplier(String companyName, String phone) {
        this.companyName = companyName;
        this.phone = phone;
    }

    public Supplier(Long id, String companyName, String phone) {
        this.id = id;
        this.companyName = companyName;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Supplier supplier = (Supplier) object;
        return Objects.equals(id, supplier.id) && Objects.equals(companyName, supplier.companyName) && Objects.equals(phone, supplier.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, companyName, phone);
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}

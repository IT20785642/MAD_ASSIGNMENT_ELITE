package org.nirushan.carddetails.model;

public class Customer {
    private int customerId;
    private String customerName;
    private String customerPhone;
    private String customerDesc;
    private String customerAddress;
    private String customerCreated;
    private String customerTotal;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerDesc() {
        return customerDesc;
    }

    public void setCustomerDesc(String customerDesc) {
        this.customerDesc = customerDesc;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerCreated() {
        return customerCreated;
    }

    public void setCustomerCreated(String customerCreated) {
        this.customerCreated = customerCreated;
    }

    public String getCustomerTotal() {
        return customerTotal;
    }

    public void setCustomerTotal(String customerTotal) {
        this.customerTotal = customerTotal;
    }
}

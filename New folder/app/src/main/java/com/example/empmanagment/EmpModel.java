package com.example.empmanagment;

import android.widget.EditText;

public class EmpModel {
    private String Name;
    private String  Email;
    private String  Department;
    private String  Branch;
    private String  NIC;
    private String  Contact;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getBranch() {
        return Branch;
    }

    public void setBranch(String branch) {
        Branch = branch;
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public EmpModel(String name, String email, String department, String branch, String NIC, String contact) {
        this.Name = name;
        this.Email = email;
        this.Department = department;
        this.Branch = branch;
        this.NIC = NIC;
        this.Contact = contact;
    }
}

package model;

import java.time.LocalDate;

public class Staff {
    private int id;
    private String name;
    private LocalDate birth;
    private String address;
    private String phone;
    private String mail;
    private Department department;

    public Staff(int id, String name, LocalDate birth, String address, String phone, String mail, Department department) {
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.address = address;
        this.phone = phone;
        this.mail = mail;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}

package model;

import java.io.Serializable;

public class Address implements Serializable {
    private int id;
    private int pinCode;
    private String city;
    private int studentId;

    public Address(int id, int pinCode, String city, int studentId) {
        this.id = id;
        this.pinCode = pinCode;
        this.city = city;
        this.studentId = studentId;
    }

    public int getStudentId() { return studentId; }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", pinCode=" + pinCode +
                ", city='" + city + '\'' +
                ", studentId=" + studentId +
                '}';
    }
}


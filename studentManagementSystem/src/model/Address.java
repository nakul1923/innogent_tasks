package model;

public class Address {
    private int id;
    private String pinCode;
    private String city;
    private int studentId;

    public Address(int id, String pinCode, String city, int studentId) {
        this.id = id;
        this.pinCode = pinCode;
        this.city = city;
        this.studentId = studentId;
    }

    public int getId() { return id; }
    public String getPinCode() { return pinCode; }
    public String getCity() { return city; }
    public int getStudentId() { return studentId; }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", pinCode='" + pinCode + '\'' +
                ", city='" + city + '\'' +
                ", studentId=" + studentId +
                '}';
    }
}

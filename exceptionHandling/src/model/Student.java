package model;

import java.io.Serializable;

public class Student implements Serializable {
    private int id;
    private String name;
    private int classId;
    private double marks;
    private String gender;
    private int age;

    public Student(int id, String name, int classId, double marks, String gender, int age) {
        this.id = id;
        this.name = name;
        this.classId = classId;
        this.marks = marks;
        this.gender = gender;
        this.age = age;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getClassId() { return classId; }
    public double getMarks() { return marks; }
    public String getGender() { return gender; }
    public int getAge() { return age; }

    public String getResult() {
        return marks >= 50 ? "Pass" : "Fail";
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", classId=" + classId +
                ", marks=" + marks +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                '}';
    }
}


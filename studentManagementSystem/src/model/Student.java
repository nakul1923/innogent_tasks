package model;

public class Student {
    private int id;
    private String name;
    private int classId;
    private double marks;
    private String gender;
    private int age;
    private boolean passed;
    private int rank;

    public Student(int id, String name, int classId, double marks, String gender, int age) {
        this.id = id;
        this.name = name;
        this.classId = classId;
        this.marks = marks;
        this.gender = gender;
        this.age = age;
        this.passed = marks >= 50;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getClassId() { return classId; }
    public double getMarks() { return marks; }
    public String getGender() { return gender; }
    public int getAge() { return age; }
    public boolean isPassed() { return passed; }
    public int getRank() { return rank; }

    public void setRank(int rank) { this.rank = rank; }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", classId=" + classId +
                ", marks=" + marks +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", passed=" + passed +
                ", rank=" + rank +
                '}';
    }
}
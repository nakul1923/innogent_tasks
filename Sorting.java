import java.util.*;


class SortBySalary implements Comparator<Employee>{

    @Override
    public int compare(Employee e1,Employee e2){

        return Double.compare(e2.getSalary(), e1.getSalary());
    }
}

class Employee implements Comparable<Employee>{

    private int id;
    private String name;
    private String department;
    private double salary;

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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                '}';
    }

    public int compareTo(Employee e){

        int departComp = this.department.compareTo(e.department);
        if(departComp!=0) return departComp;

        int nameComp = this.name.compareTo(e.name);
        if(nameComp!=0) return nameComp;

        return Double.compare(this.salary, e.salary);
    }
}

public class Sorting {

    public static void main(String[] args) {

        List<Employee> list = new ArrayList<Employee>();

        list.add(new Employee(101, "nakul", "IT", 40000));
        list.add(new Employee(102, "shrey", "HR", 30000));
        list.add(new Employee(103, "nakul", "IT", 20000));
        list.add(new Employee(104, "Abhi", "MM", 60000));
        list.add(new Employee(105, "Raj", "HR", 45000));


        Collections.sort(list);

        Iterator itr = list.iterator();

        while (itr.hasNext()) {

            System.out.println(itr.next());
        }

        System.out.println("/n/n");

        Collections.sort(list, new SortBySalary());

        Iterator itr1 = list.iterator();

        while (itr1.hasNext()) {

            System.out.println(itr1.next());
        }
    }
}
package service;

import model.Address;
import model.Classroom;
import model.Student;

import java.util.*;
import java.util.stream.Collectors;

public class StudentManagement {

    List<Classroom> classes = new ArrayList<Classroom>();
    List<Student> students = new ArrayList<Student>();
    List<Address> addresses = new ArrayList<Address>();

    public void addStudent(Student s){

        if(s.getAge()>20){

            System.out.println(s.getName() + " not addded! age is greater than 20");
            return;
        }

        students.add(s);

    }

    public void addClassroom(Classroom c){

        classes.add(c);
    }

    public void addAddress(Address a){

        addresses.add(a);
    }

    public void assignRanks(){

        students.sort((s1,s2) -> Double.compare(s2.getMarks(),s1.getMarks()));
        for(int i = 0; i< students.size();i++){

            students.get(i).setRank(i+1);
        }
    }

    private List<Student> filterByAge(List<Student> list, Integer age){

        if(age == null) return list;

        return list.stream().
                filter(s->s.getAge() == age).collect(Collectors.toList());
    }

    private List<Student> filterByGender(List<Student> list, String gender){

        if(gender == null) return list;

        return list.stream().
                filter(s->s.getGender().equals(gender)).collect(Collectors.toList());
    }

    private List<Student> filterByClass(List<Student> list, String className){

        if(className == null) return list;

        Optional<Classroom> c = classes.stream().
                filter(c1-> c1.getName().equalsIgnoreCase(className)).
                findFirst();

        if(c.isEmpty()) return new ArrayList<>();

        int classId = c.get().getId();

        return list.stream().filter(s->s.getClassId() == classId).collect(Collectors.toList());
    }

    private List<Student> filterByCity(List<Student> list, String city) {
        if (city == null) return list;
        Set<Integer> ids = addresses.stream()
                .filter(a -> a.getCity().equalsIgnoreCase(city))
                .map(Address::getStudentId)
                .collect(Collectors.toSet());
        return list.stream().filter(s -> ids.contains(s.getId())).collect(Collectors.toList());
    }

    private List<Student> filterByPincode(List<Student> list, String pin) {
        if (pin == null) return list;
        Set<Integer> ids = addresses.stream()
                .filter(a -> a.getPinCode().equals(pin))
                .map(Address::getStudentId)
                .collect(Collectors.toSet());
        return list.stream().filter(s -> ids.contains(s.getId())).collect(Collectors.toList());
    }

    public List<Student> findStudentsByCity(String city, String gender, Integer age, String className) {
        List<Student> result = filterByCity(students, city);
        result = filterByGender(result, gender);
        result = filterByAge(result, age);
        result = filterByClass(result, className);
        return result;
    }

    public List<Student> findStudentsByPincode(String pin, String gender, Integer age, String className) {
        List<Student> result = filterByPincode(students, pin);
        result = filterByGender(result, gender);
        result = filterByAge(result, age);
        result = filterByClass(result, className);
        return result;
    }

    public List<Student> getPassedStudents(String gender, Integer age, String className, String city, String pin) {
        List<Student> result = students.stream().filter(Student::isPassed).collect(Collectors.toList());
        result = filterByGender(result, gender);
        result = filterByAge(result, age);
        result = filterByClass(result, className);
        result = filterByCity(result, city);
        result = filterByPincode(result, pin);
        return result;
    }

    public List<Student> getFailedStudents(String gender, Integer age, String className, String city, String pin) {
        List<Student> result = students.stream().filter(s -> !s.isPassed()).collect(Collectors.toList());
        result = filterByGender(result, gender);
        result = filterByAge(result, age);
        result = filterByClass(result, className);
        result = filterByCity(result, city);
        result = filterByPincode(result, pin);
        return result;
    }

    public void deleteStudent(int id) {
        students.removeIf(s -> s.getId() == id);
        addresses.removeIf(a -> a.getStudentId() == id);

        Set<Integer> remainingClassIds = students.stream().map(Student::getClassId).collect(Collectors.toSet());
        classes.removeIf(c -> !remainingClassIds.contains(c.getId()));
    }

    public List<Student> getPaginated(List<Student> list, int start, int end, Comparator<Student> sorter) {
        if (sorter != null) list.sort(sorter);
        int fromIndex = Math.max(0, start - 1);
        int toIndex = Math.min(list.size(), end);
        if (fromIndex >= list.size()) return new ArrayList<>();
        return list.subList(fromIndex, toIndex);
    }
}

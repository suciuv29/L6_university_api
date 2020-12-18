package lab3.model;

import java.util.ArrayList;
import java.util.List;

public class Student extends Person{

    private long studentId;
    private int totalCredits;
    private List<Course> enrolledCourses;


    /**
     * Constructor with parameters
     * */
    public Student(String firstName, String lastName, long studentId) {
        super(firstName, lastName);
        this.studentId = studentId;
        this.totalCredits = 0;
        this.enrolledCourses = new ArrayList<>();
    }


    /**
     * Getters and setters
     * */
    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public int getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(int totalCredits) {
        this.totalCredits = totalCredits;
    }

    public List<Course> getEnrolledCourses() {
        return this.enrolledCourses;
    }

    public void setEnrolledCourses(List<Course> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }


    @Override
    public String toString() {
        return  studentId + ",  " +
                "full name= " + this.getFirstName() + " " + this.getLastName() +
                ", totalCredits= " + totalCredits;
    }
    public boolean compareTo(Student other) {
        if (this.studentId ==  other.getStudentId())
            return true;
        return false;
    }
}


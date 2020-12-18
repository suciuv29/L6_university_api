package lab3.model;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends Person{

    private List<Course> courses;

    private long teacherId;

    /**
     * Constructor with parameters
     * */
    public Teacher(String firstName, String lastName, long teacherId) {
        super(firstName, lastName);
        this.courses = new ArrayList<Course>();
        this.teacherId = teacherId;
    }

    /**
     * Getters and setters
     * */
    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        return   teacherId + "  " +
                "Full Name=" + this.getFirstName() + " " + this.getLastName();
    }
}


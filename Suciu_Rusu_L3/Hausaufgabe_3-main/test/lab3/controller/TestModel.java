package lab3.controller;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Scanner;
import lab3.model.Course;
import lab3.model.Student;
import lab3.model.Teacher;


public class TestModel {

    Student stud1 = new Student("Vlad", "Suciu", 234);
    Teacher prof1 = new Teacher("Vlad", "Chis",236);
    List<Student> list1 = new ArrayList<Student>();
    Course c1 = new Course("Prezentari", prof1, 75, list1 , 3, 17);

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void student_test() {
        assertTrue(stud1.getFirstName() == "Vlad");
        assertTrue(stud1.getLastName() == "Suciu");
        assertTrue(stud1.getStudentId() == 234);
    }

    @Test
    void teacher_test(){
        assertTrue(prof1.getFirstName() == "Vlad");
        assertTrue(prof1.getLastName() == "Chis");
        assertTrue(prof1.getTeacherId() == 236);
    }

    @Test
    void course_test(){
        assertTrue(c1.getCourseId()==17);
        assertTrue(c1.getName()=="Prezentari");
        assertTrue(c1.getTeacher()==prof1);
        assertTrue(c1.getStudentsEnrolled()==list1);
        assertTrue(c1.getMaxEnrollment()==75);
        assertTrue(c1.getCredits()==3);
    }

}
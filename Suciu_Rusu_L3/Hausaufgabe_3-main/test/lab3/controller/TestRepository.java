package lab3.controller;

import lab3.model.Course;
import lab3.model.Student;
import lab3.model.Teacher;
import lab3.repository.RepoCourses;
import lab3.repository.RepoStudents;
import lab3.repository.RepoTeachers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TestClassRepositoryTest {
    public static RepoCourses courserepository;
    public static RepoTeachers teacherRepository;
    public static RepoStudents studentrepository;

    Student stud1 = new Student("Vlad", "Suciu", 234);
    Student stud2 = new Student("Cristi", "Rusu", 36487);

    Teacher prof1 = new Teacher("Doamna", "Doctor",1203);
    Teacher prof2 = new Teacher("Duamna Doctor ", "Madalina Adam",376);

    Course c1;

    @BeforeEach
    void setUp() {

        //declarare pentru teste
        List<Course> cursuri_prof_1=new ArrayList<>();
        List<Student> studenti_curs_1=new ArrayList<>();
        List<Course> cursuri_elev_1=new ArrayList<>();

        studenti_curs_1.add(stud1);
        studenti_curs_1.add(stud2);

        c1 = new Course("Prezentari", prof1, 75, studenti_curs_1, 3, 17);

        cursuri_elev_1.add(c1);
        cursuri_prof_1.add(c1);

        prof1.setCourses(cursuri_prof_1);

        stud1.setEnrolledCourses(cursuri_elev_1);
        stud1.setEnrolledCourses(cursuri_elev_1);

        List<Course> allcursuri=new ArrayList<>();
        List<Student> allstudents=new ArrayList<>();
        List<Teacher> allteachers=new ArrayList<>();

        allcursuri.add(c1);
        allstudents.add(stud1);
        allstudents.add(stud2);
        allteachers.add(prof1);
        allteachers.add(prof2);


        courserepository=new RepoCourses(allcursuri);
        teacherRepository=new RepoTeachers(allteachers);
        studentrepository=new RepoStudents(allstudents);
    }

    @AfterEach
    void tearDown() {
    }
    @Test
    void TestRepo()
    {
        assertTrue(studentrepository.findOne((long)234)==stud1);
        assertTrue(teacherRepository.findOne((long)376)==prof2);
        assertTrue(courserepository.findOne((long)17)==c1);
    }
}
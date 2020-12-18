package lab3.controller;

import lab3.model.Course;
import lab3.model.Student;
import lab3.model.Teacher;
import lab3.repository.RepoCourses;
import lab3.repository.RepoStudents;
import lab3.repository.RepoTeachers;

import java.util.*;
import java.util.stream.Collectors;

public class RegistrationSystem {
    private List<Student> students;
    private List<Teacher> teachers;
    private List<Course> courses;

    private RepoStudents studentRepo;
    private RepoTeachers teacherRepo;
    private RepoCourses courseRepo;
    public RegistrationSystem(List<Student> students, List<Teacher> teachers, List<Course> courses)
    {
        this.students=students;
        this.teachers=teachers;
        this.courses=courses;
        studentRepo=new RepoStudents(this.students);
        teacherRepo=new RepoTeachers(this.teachers);
        courseRepo=new RepoCourses(this.courses);
    }
    public void test() {
        for (Course c: courseRepo.findAll()) {
            System.out.println(c + " \n");
        }
        for (Student s: studentRepo.findAll()) {
            System.out.println(s + " \n");
        }
        for (Teacher t: teacherRepo.findAll()) {
            System.out.println(t + " \n");
        }

    }

    public boolean register(Course course, Student student){

        //verifica daca exista cursul
        if (courseRepo.findOne(course.getCourseId()) == null)
        {
            return false;
        }


        List<Student> courseStudentList =  course.getStudentsEnrolled();

        //verifica daca cursul are locuri libere
        if (course.getStudentsEnrolled().size() == course.getMaxEnrollment()) {
            return false;
        }

        //verifica daca studentul exista
        if ((studentRepo.findOne(student.getStudentId())) == null) {
            return false;
        }

        //verifica daca studentul este inscris deja la curs
        for(Student s: courseStudentList) {
            if (s.compareTo(student))
                return false;
        }

        //verifica daca creditele > 30
        int sumCredits=0;
        List<Course> courseList = student.getEnrolledCourses();
        for(Course c: courseList){
            sumCredits += c.getCredits();
        }
        sumCredits += course.getCredits();
        if(sumCredits > 30)
            return false;


        //adauga studentul la curs
        courseStudentList.add(student);
        course.setStudentsEnrolled(courseStudentList);
        courseRepo.update(course);

        //updateaza numarul total de credite ale unui student
        student.setTotalCredits(sumCredits);

        //updateaza cursurile la care e inregistrat un student
        List<Course> enrolledCourses = student.getEnrolledCourses();
        enrolledCourses.add(course);
        student.setEnrolledCourses(enrolledCourses);

        studentRepo.update(student);

        return true;
    }
    // Refactorizat sa foloseasca stream
    public List<Course> retrieveCoursesWithFreePlaces(){

        List<Course> availableCourses = new ArrayList<Course>();
        //Iterable<Course> courseIterable = courseRepo.findAll();

        availableCourses = ((List<Course>) courseRepo.findAll()).stream().filter(c -> c.getStudentsEnrolled().size() < c.getMaxEnrollment()).collect(Collectors.toList());

        /*for(Course c: courseIterable){
            if (c.getStudentsEnrolled().size() < c.getMaxEnrollment()) {
                availableCourses.add(c);
            }
        }*/
        return availableCourses;
    }

    public List<Student> retrieveStudentsEnrolledForACourse(Course course){
        if (courseRepo.findOne(course.getCourseId()) != null) {
            return course.getStudentsEnrolled();
        }
        return null;
    }

    public void updateStudentsTotalCredits(){

        for(Student s: this.studentRepo.findAll()){
            List<Course> courseList = s.getEnrolledCourses();
            int sum=0;
            for(Course c: courseList){
                sum += c.getCredits();
            }

            s.setTotalCredits(sum);
        }
    }

    public boolean deleteCourseFromTeacher(Teacher teacher, Course course){
        //verifica daca exista cursul
        if (courseRepo.findOne(course.getCourseId()) == null)
        {
            return false;
        }

        //verifica daca exista profesorul
        if (teacherRepo.findOne(teacher.getTeacherId()) == null)
        {
            return false;
        }

        List<Course> courseList = teacher.getCourses();
        for(Course c:courseList){
            if(c.compareTo(course)){
                courseList.remove(c);
                teacher.setCourses(courseList);
                teacherRepo.update(teacher);
                return true;
            }
        }
        return false;

    }

    public List<Course> getAllCourses(){
        List<Course> courses_copie =  new ArrayList<Course>();
        Iterable<Course> iterable = courseRepo.findAll();

        for(Course c: iterable){
            courses_copie.add(c);
        }
        return courses_copie;
    }

    public List<Student> getAllStudents(){
        List<Student> copie_studentList = new ArrayList<>();
        Iterable<Student> iterable_studentList = studentRepo.findAll();

        for(Student s:iterable_studentList){
            copie_studentList.add(s);
        }

        return copie_studentList;
    }

    public List<Teacher> getAllTeacher(){
        List<Teacher> copie_teacherList = new ArrayList<>();
        Iterable<Teacher> iterable_teacherList = teacherRepo.findAll();

        for(Teacher teacher:iterable_teacherList){
            copie_teacherList.add(teacher);
        }

        return copie_teacherList;
    }

    class SortbyCredits implements Comparator<Course>
    {

        @Override
        public int compare(Course o1, Course o2) {
            return o2.getCredits()-o1.getCredits();
        }
    }

    //sorteaza cursurile dupa numarul de credite in ordine descrescatoare
    public List<Course> SortCourse(){
        List<Course> copie_courses = new ArrayList<>();
        Iterable<Course> iterable_courseList=courseRepo.findAll();

        for(Course course:iterable_courseList){
            copie_courses.add(course);
        }

        Collections.sort(copie_courses,new SortbyCredits());
        return copie_courses;
    }


    //filtreaza studentii dupa un anumit numar de credite
    public List<Student> filterStudents(int nrCredits){
        List<Student> copie_studenti = new ArrayList<>();
        Iterable<Student> iterable_studentList=studentRepo.findAll();

        for(Student student:iterable_studentList){
            if (student.getTotalCredits() < nrCredits)
            copie_studenti.add(student);
        }

        return copie_studenti;
    }




    public Student findOneStudent(long id){
        return this.studentRepo.findOne(id);
    }

    public Course findOneCourse(long id){
        return this.courseRepo.findOne(id);
    }

    public Teacher findOneTeacher(long id){
        return this.teacherRepo.findOne(id);
    }

}


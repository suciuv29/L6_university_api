package lab3.controller;

import lab3.model.Course;
import lab3.model.Student;
import lab3.model.Teacher;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleView {
    public void printMenu() {
        System.out.println("1. Inscriere student la curs.");
        System.out.println("2. Afisare cursuri disponibile si numarul de locuri.");
        System.out.println("3. Afisare studenti inrolati la un anumit curs.");
        System.out.println("4. Stergere curs.");
        System.out.println("5. Update.");
        System.out.println("6. Sorteaza cursurile dupa numarul de credite.");
        System.out.println("7. Filtreaza studentii dupa numarul de credite.");
        System.out.println("0. Iesire.");
    }

    public void menu(RegistrationSystem controller) throws Exception{

        Scanner in = new Scanner(System.in);

        boolean stay = true;
        while(stay){
            printMenu();
            System.out.print("Alegeti o optiune: ");
            int key = in.nextInt();
            if (key > 7 || key < 0) throw new Exception ("Optiunea este gresita!");
            switch (key){

                case 0:
                    stay = false;
                    break;

                case 1:
                    /**Afisare studenti*/
                    System.out.println();
                    for(Student s: controller.getAllStudents()){
                        System.out.println(s);
                    }
                    /**Alegere id student*/
                    System.out.println("\nIntroduceti id-ul studentului pe care doriti sa-l inrolati.");
                    long stud_id = in.nextLong();

                    /**Salvare student*/
                    Student givenStudent = controller.findOneStudent(stud_id);

                    /**Afisare cursuri*/
                    System.out.println();
                    for(Course c: controller.getAllCourses()){
                        System.out.println(c);
                    }
                    /**Alegere id curs*/
                    System.out.println("\nIntroduceti id-ul cursului la care doriti sa-l inrolati pe " + givenStudent.getFirstName());
                    long course_id = in.nextLong();

                    /**Salvare curs*/
                    Course givenCourse = controller.findOneCourse(course_id);
                    if(controller.register(givenCourse, givenStudent)){
                        System.out.println("Studentul a fost inrolat!");
                    }
                    else{
                        System.out.println("Eroare!");
                    }
                    System.out.println();
                    break;

                case 2:
                    for (Course c: controller.retrieveCoursesWithFreePlaces()){
                        System.out.println(c);
                    }
                    System.out.println();
                    break;

                case 3:
                    /**Afisare cursuri*/
                    System.out.println();
                    for(Course c: controller.getAllCourses()){
                        System.out.println(c);
                    }
                    /**Alegere id curs*/
                    System.out.println("\nIntroduceti id-ul cursului:");
                    int opt = in.nextInt();

                    /**Cautare curs*/
                    Course checkStudentsCourse = null;
                    for(Course c:controller.getAllCourses()){
                        if(opt==c.getCourseId()){
                            checkStudentsCourse = c;
                        }
                    }
                    assert checkStudentsCourse != null;
                    for(Student s: controller.retrieveStudentsEnrolledForACourse(checkStudentsCourse)){
                        System.out.println(s);
                    }
                    System.out.println();

                    break;

                case 4:
                    /**Preluare profesori*/
                    System.out.println();
                    for(Teacher t: controller.getAllTeacher()){
                        System.out.println(t);
                    }
                    /**Alegere id profesor*/
                    System.out.println("\nIntroduceti id-ul profesorului dorit:");
                    long teacher_id = in.nextLong();

                    /**Salvare studenti*/
                    Teacher givenTeacher = controller.findOneTeacher(teacher_id);

                    /**afisarea cursurilor pe care le preda profesorul*/
                    List<Course> courseList = givenTeacher.getCourses();
                    for(Course c : courseList){
                        System.out.println(c.getCourseId() + "  " + c.getName());
                    }

                    /**Alegere id curs*/
                    System.out.println("Alegeti id-ul cursului pe care doriti sa il stergeti:");
                    long courseTeacher_id = in.nextLong();

                    /**Salvare curs*/
                    Course choosenCourse = controller.findOneCourse(courseTeacher_id);


                    if(controller.deleteCourseFromTeacher(givenTeacher, choosenCourse)){
                        System.out.println("Cursul profesorului " + givenTeacher.getFirstName() + " a fost sters!");
                    }
                    else{
                        System.out.println("Eroare!");
                    }
                    System.out.println();
                    break;
                case 5:

                    System.out.println("Totul este updatat!");
                    break;
                case 6:
                    System.out.println();
                    List<Course> courseListsorted = controller.SortCourse();
                    for (Course c : courseListsorted){
                        System.out.println(c.getCourseId() + " " + c.getName() + " " + c.getCredits());
                    }
                    System.out.println();
                    break;

                case 7:
                    System.out.println();
                    System.out.println("\nIntroduceti numarul de credite");
                    int nrCredits = in.nextInt();
                    List<Student> sudentsfiltered = controller.filterStudents(nrCredits);
                    for (Student s : sudentsfiltered){
                        System.out.println(s.getStudentId() + " " + s.getFirstName() + " " + s.getLastName());
                    }
                    System.out.println();
                    break;
            }

        }
    }

    public void consoleApp() throws Exception {
        //CREATING STUDENT
        Student stud1 = new Student("Vlad", "Suciu", 234);
        Student stud2 = new Student("Cristi", "Rusu", 36487);
        Student stud3 = new Student("Cristiano", "Rolando", 235);
        Student stud4 = new Student("Leo", "Nessi", 927);
        Student stud5 = new Student("Moe", "Lester", 317);

        //CREATING TEACHERS
        Teacher prof1 = new Teacher("Doamna", "Doctor",1203);
        Teacher prof2 = new Teacher("Duamna Doctor ", "Madalina Adam",376);
        Teacher prof3 = new Teacher("Vlad", "Chis",236);
        Teacher prof4 = new Teacher("Daryus", "Tzakavei",7846);

        //CREATING LISTS OF STUDENTS FOR COURSES
        List<Student> list1 = new ArrayList<Student>();
        list1.add(stud1);
        list1.add(stud2);
        List<Student> list2 = new ArrayList<Student>();
        list2.add(stud4);
        list2.add(stud5);
        List<Student> list3 = new ArrayList<Student>();
        list3.add(stud3);
        list3.add(stud2);
        list3.add(stud5);

        //CREATING COURSES
        Course c1 = new Course("Prezentari", prof1, 75, list1, 3, 17);
        Course c2 = new Course("Bisnita", prof1, 50, list2, 5, 12);
        Course c3 = new Course("Samsarime", prof3, 100, list3, 10, 56);

        //CREATING LISTS OF COURSES
        List<Course> lc1 =  new ArrayList<>();
        lc1.add(c2);
        lc1.add(c3);
        List<Course> lc2 =  new ArrayList<>();
        lc2.add(c1);
        List<Course> lc3 =  new ArrayList<>();
        lc3.add(c3);
        List<Course> lc4 =  new ArrayList<>();
        lc4.add(c1);
        lc4.add(c2);
        List<Course> lc5 =  new ArrayList<>();
        lc5.add(c2);

        //ADDING COURSES TO STUDENTS
        stud1.setEnrolledCourses(lc2);
        stud2.setEnrolledCourses(lc4);
        stud3.setEnrolledCourses(lc3);
        stud4.setEnrolledCourses(lc5);
        stud5.setEnrolledCourses(lc1);

        prof3.setCourses(lc1);
        prof1.setCourses(lc2);
        prof4.setCourses(lc2);
        prof2.setCourses(lc2);

        List<Course> allCourses =  new ArrayList<Course>();
        allCourses.add(c1);
        allCourses.add(c2);
        allCourses.add(c3);

        List<Teacher> allTeachers =  new ArrayList<Teacher>();
        allTeachers.add(prof1);
        allTeachers.add(prof3);

        List<Student> allStudents =  new ArrayList<Student>();
        allStudents.add(stud1);
        allStudents.add(stud2);
        allStudents.add(stud3);
        allStudents.add(stud4);
        allStudents.add(stud5);

        RegistrationSystem controller = new RegistrationSystem(allStudents, allTeachers, allCourses);
        controller.updateStudentsTotalCredits();
        menu(controller);

    }
}

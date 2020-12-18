package lab3.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lab3.controller.RegistrationSystem;
import lab3.model.Course;
import lab3.model.Student;
import lab3.model.Teacher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * clasa care implementeaza proiectul in grafica
 */
public class GuiController extends Application {

    public TextField student_name;
    public TextField profesor_name;
    public static RegistrationSystem controller;
    public ListView cursuri;
    public static Stage studentStage = null;
    public static Student student;
    public static List <Long> CourseIds;
    public static Stage profesorStage = null;
    public static Teacher teacher;

    public static void main(String[] args) throws Exception {
        controller = generateController();
        launch(args);
    }

    /**
     * @param primaryStage
     * @throws IOException
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("My app");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void open_student(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("student-login.fxml"));
        Stage loginStage = new Stage();
        loginStage.setTitle("Login");
        loginStage.setScene(new Scene(root));
        if (studentStage != null) {
            studentStage.close();
            studentStage = null;
        }
        studentStage = loginStage;
        student = null;
        loginStage.show();
    }

    public void open_profesor(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("profesor-login.fxml"));
        Stage loginStage = new Stage();
        loginStage.setTitle("Login");
        loginStage.setScene(new Scene(root));
        if (profesorStage != null) {
            profesorStage.close();
            profesorStage = null;
        }
        profesorStage = loginStage;
        teacher = null;
        loginStage.show();
    }

    /**
     * metoda care actualizeaza creditele
     */
    public void updateCursuri() {
        cursuri = (ListView) studentStage.getScene().lookup("#cursuri");
        cursuri.getItems().clear();
        List <Long> ids = new ArrayList<>();
        for (Course c : controller.retrieveCoursesWithFreePlaces()) {
            if (!c.getStudentsEnrolled().contains(student)) {
                cursuri.getItems().add(c.getName());
                ids.add(c.getCourseId());
            }
        }
        CourseIds = ids;
        Label totalCredite = (Label) studentStage.getScene().lookup("#totalCredite");
        totalCredite.setText(Integer.toString(student.getTotalCredits()));
    }

    /**
     * metoda pentru autdentificarea studentului
     * @param mouseEvent
     * @throws IOException
     */
    public void login_student(MouseEvent mouseEvent) throws IOException {
        List<Student> students = controller.getAllStudents();
        Student foundStudent = null;
        for (Student s : students) {
            if ((s.getFirstName() + " " + s.getLastName()).equals(student_name.getText())) {
                foundStudent = s;
            }
        }
        if (foundStudent == null) {
            new Alert(Alert.AlertType.ERROR, "No student found with that name!").showAndWait();
        }
        else {
            student = foundStudent;
            Parent root = FXMLLoader.load(getClass().getResource("student.fxml"));
            Stage theStage = new Stage();
            theStage.setTitle("Student");
            theStage.setScene(new Scene(root));

            if (studentStage != null) {
                studentStage.close();
                studentStage = null;
            }
            studentStage = theStage;
            updateCursuri();
            theStage.show();
        }
    }

    public void profesor_observer() {
        if (profesorStage != null) {
            update_profesor_list();
        }
    }

    /**
     * actualizeaza lista de studenti inscrisi la curs
     */
    public void update_profesor_list() {
        ListView studList = (ListView) profesorStage.getScene().lookup("#studenti");
        studList.getItems().clear();
        List <Long> ids = new ArrayList<>();
        for (Course c: teacher.getCourses()) {
            for (Student s: c.getStudentsEnrolled()) {
                if (!ids.contains(s.getStudentId())) {
                    ids.add(s.getStudentId());
                    studList.getItems().add(s.getFirstName() + " " + s.getLastName());
                }
            }
        }
    }

    /**
     * metoda pentru autdentificarea profesorului
     * @param mouseEvent
     * @throws IOException
     */
    public void login_profesor(MouseEvent mouseEvent) throws IOException {
        List<Teacher> teachers = controller.getAllTeacher();
        Teacher foundTeacher = null;
        for (Teacher t : teachers) {
            if ((t.getFirstName() + " " + t.getLastName()).equals(profesor_name.getText())) {
                foundTeacher = t;
            }
        }
        if (foundTeacher == null) {
            new Alert(Alert.AlertType.ERROR, "No teacher found with that name!").showAndWait();
        }
        else {
            teacher = foundTeacher;
            Parent root = FXMLLoader.load(getClass().getResource("profesor.fxml"));
            Stage theStage = new Stage();
            theStage.setTitle("Profesor");
            theStage.setScene(new Scene(root));

            if (profesorStage != null) {
                profesorStage.close();
                profesorStage = null;
            }
            profesorStage = theStage;
            update_profesor_list();
            theStage.show();
        }
    }

    public static RegistrationSystem generateController() throws Exception {
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
        return controller;

    }

    /**
     * inscrie studentul la cursul dorit
     * @param mouseEvent
     */
    public void inscriere(MouseEvent mouseEvent) {
        if (cursuri.getSelectionModel().getSelectedIndex() >= 0) {
            int Index = cursuri.getSelectionModel().getSelectedIndex();
            Course c = controller.findOneCourse(CourseIds.get(Index));
            controller.register(c, student);
            profesor_observer();
            updateCursuri();
        }
    }
}

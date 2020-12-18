package lab3.repository;

import lab3.model.Student;

import java.util.List;

public class RepoStudents implements ICrudRepository<Student>{
    List<Student> students;

    public RepoStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public Student findOne(Long id) {
        for(Student s: students)
        {
            if(s.getStudentId()==id)
                return s;
        }
        return null;
    }

    @Override
    public Iterable<Student> findAll() {
        //List<Student> studentList = this.students;
        return students;
    }

    @Override
    public Student save(Student entity) {
        if (this.findOne(entity.getStudentId()) != null)
            return null;
        students.add(entity);
        return entity;
    }

    @Override
    public Student delete(Student entity) {
        if (this.findOne(entity.getStudentId()) != null)
            return null;
        students.remove(entity);
        return entity;
    }

    @Override
    public Student update(Student entity) {
        Student student = this.findOne(entity.getStudentId());
        if (student == null)
            return null;
        students.remove(student);
        students.add(entity);
        return entity;
    }
}


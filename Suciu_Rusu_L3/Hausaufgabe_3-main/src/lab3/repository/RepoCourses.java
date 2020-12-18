package lab3.repository;

import lab3.model.Course;

import java.util.List;

public class RepoCourses implements ICrudRepository<Course>{
    List<Course> courses;

    public RepoCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public Course findOne(Long id) {
        for(Course c: courses) {
            if (c.getCourseId() == id)
                return c;
        }
        return  null;
    }

    @Override
    public Iterable<Course> findAll() {
        List<Course> courseList = this.courses;
        return courseList;
    }

    @Override
    public Course save(Course entity) {
        if (this.findOne(entity.getCourseId()) != null)
            return null;
        courses.add(entity);
        return entity;
    }


    @Override
    public Course delete(Course entity) {
        if (this.findOne(entity.getCourseId()) != null)
            return null;
        courses.remove(entity);
        return null;
    }

    @Override
    public Course update(Course entity) {
        Course course;
        if (( course=this.findOne(entity.getCourseId()) ) == null)
            return null;
        courses.remove(course);
        courses.add(entity);
        return entity;
    }
}

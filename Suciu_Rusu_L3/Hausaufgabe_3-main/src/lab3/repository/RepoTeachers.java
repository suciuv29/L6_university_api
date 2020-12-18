package lab3.repository;

import lab3.model.Teacher;

import java.util.List;

public class RepoTeachers implements ICrudRepository<Teacher> {
    List<Teacher> teachers;

    public RepoTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    @Override
    public Teacher findOne(Long id) {
        for(Teacher t: teachers)
        {
            if(t.getTeacherId()==id)
                return t;
        }
        return null;
    }

    @Override
    public Iterable<Teacher> findAll() {
        return teachers;
    }

    @Override
    public Teacher save(Teacher entity) {
        if (this.findOne(entity.getTeacherId()) != null)
            return null;
        teachers.add(entity);
        return entity;
    }

    @Override
    public Teacher delete(Teacher entity) {
        if (this.findOne(entity.getTeacherId()) != null)
            return null;
        teachers.remove(entity);
        return entity;
    }

    @Override
    public Teacher update(Teacher entity) {
        Teacher teacher = this.findOne(entity.getTeacherId());
        if (teacher == null)
            return null;
        teachers.remove(teacher);
        teachers.add(entity);
        return entity;
    }
}


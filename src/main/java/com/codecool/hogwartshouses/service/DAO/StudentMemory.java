package com.codecool.hogwartshouses.service.DAO;

import com.codecool.hogwartshouses.model.Student;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public class StudentMemory implements StudentDAO {

    private final Set<Student> students;

    public StudentMemory(Set<Student> students) {
        this.students = students;
    }

    @Override
    public Optional<Student> get(long id) {
        return students.stream()
                .filter(student -> student.getId() == id)
                .findFirst();
    }

    @Override
    public Set<Student> getAll() {
        return students;
    }

    @Override
    public void save(Student student) {
        students.add(student);
    }

    @Override
    public Optional<Student> update(Student student, long id) {
        boolean idsMatch = student.getId() == id;
        if (idsMatch) {
            boolean deleted = delete(id);
            if (deleted) students.add(student);
        }
        return get(student.getId());
    }

    @Override
    public boolean delete(long id) {
        return students.removeIf(student -> student.getId() == id);
    }
}

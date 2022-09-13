package com.codecool.hogwartshouses.service;

import com.codecool.hogwartshouses.model.Room;
import com.codecool.hogwartshouses.model.Student;
import com.codecool.hogwartshouses.service.DAO.StudentDAO;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class StudentService {
    private final StudentDAO studentDAO;

    public StudentService(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public Student saveStudent(Student Student) {
        studentDAO.save(Student);
        return Student;
    }

    public Set<Student> getStudents() {
        return studentDAO.getAll();
    }

    public Optional<Student> findStudentById(long id) {
        return studentDAO.get(id);
    }

    public Optional<Student> updateStudent(Student student, long id) {
        return studentDAO.update(student, id);
    }

    public boolean deleteRoom(long id) {
        return studentDAO.delete(id);
    }
}

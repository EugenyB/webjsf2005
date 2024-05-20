package ua.mk.berkut.webjsf2005.beans;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import ua.mk.berkut.webjsf2005.dao.StudentDao;
import ua.mk.berkut.webjsf2005.data.Student;
import ua.mk.berkut.webjsf2005.data.StudentDto;

import java.util.List;

@Named
@RequestScoped
public class StudentBean {

    @EJB
    private StudentDao studentDao;

    @Getter
    private Student student = new Student();

    public List<Student> getStudents() {
        return studentDao.findAll();
    }

    public List<StudentDto> getStudentDtos() {
        return studentDao.findAllWithFaculties();
    }

    public String sayHello() {
        return "hello";
    }

    public void addStudent() {
        studentDao.save(student);
        student = new Student();
    }

    public void delete(int id) {
        studentDao.delete(id);
    }
}

package ua.mk.berkut.webjsf2005.dao;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import ua.mk.berkut.webjsf2005.data.Student;
import ua.mk.berkut.webjsf2005.data.StudentDto;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class StudentDao {
    @Resource(name = "jdbc/pgdemo")
    DataSource ds;

    public List<Student> findAll() {
        try (Connection connection = ds.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("select * from student")) {
            List<Student> students = new ArrayList<>();
            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"));
                students.add(student);
            }
            return students;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<StudentDto> findAllWithFaculties() {
        try (Connection connection = ds.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("select student.id, student.name as sname, age, faculty_id, faculty.id, faculty.name as fname" +
                     " from student join faculty on faculty.id = student.faculty_id")) {
            List<StudentDto> students = new ArrayList<>();
            while (rs.next()) {
                StudentDto student = new StudentDto(
                        rs.getInt("id"),
                        rs.getString("sname"),
                        rs.getInt("age"),
                        rs.getString("fname"));
                students.add(student);
            }
            return students;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(Student student) {
        try (Connection connection = ds.getConnection();
        PreparedStatement ps = connection.prepareStatement("insert into student (name, age) VALUES (?, ?)")){
            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge());
            ps.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        try (Connection connection = ds.getConnection();
             PreparedStatement ps = connection.prepareStatement("delete from student where id = ?")) {
             ps.setInt(1, id);
             ps.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
}

// CRUD
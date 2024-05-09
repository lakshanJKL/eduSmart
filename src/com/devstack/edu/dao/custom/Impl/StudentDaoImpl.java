package com.devstack.edu.dao.custom.Impl;

import com.devstack.edu.dao.CrudUtill;
import com.devstack.edu.dao.custom.StudentDao;
import com.devstack.edu.db.DBConnection;
import com.devstack.edu.entity.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    public static int selectedStudentId;

    @Override
    public boolean save(Student student) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO student(student_name,email,dob,address,status,user_email) VALUES (?,?,?,?,?,?)";
        return CrudUtill.execute(query,student.getStName(),student.getStEmail(),java.sql.Date.valueOf(student.getStDob()),student.getStAddress(),student.isStatus(),student.getUserEmail());
    }
    @Override
    public boolean update(Student student) throws SQLException, ClassNotFoundException {
        String query = "UPDATE student SET student_name=?, email=?, dob=?,address=? WHERE student_id=?";
        return CrudUtill.execute(query,student.getStName(),student.getStEmail(),java.sql.Date.valueOf(student.getStDob()),student.getStAddress(),selectedStudentId);
    }
    @Override
    public List<Student> findAll(String searchText) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM student WHERE student_name LIKE ? OR email LIKE ? OR address LIKE ?";
        ResultSet resultSet = CrudUtill.execute(query,searchText,searchText,searchText);

        ArrayList<Student> students = new ArrayList<>();
        while (resultSet.next()){
            students.add(
                    new Student(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            LocalDate.parse(resultSet.getString(4)),
                            resultSet.getString(5),
                            resultSet.getBoolean(6),
                            resultSet.getString(7)
                    )
            );
        }
        return students;
    }

    @Override
    public List<String> autoComplteStudents() throws SQLException, ClassNotFoundException {
        String query = "SELECT email FROM student";
        ResultSet resultSet = CrudUtill.execute(query);
        ArrayList<String> data = new ArrayList<>();
        while (resultSet.next()){
            data.add(resultSet.getString(1));
        }
        return data;
    }

    @Override
    public long getstId(String email) throws SQLException, ClassNotFoundException {
        String query ="SELECT student_id FROM student WHERE email=?";
        ResultSet rs = CrudUtill.execute(query,email);
        while (rs.next()){
            return rs.getLong(1);
        }
        return 0;
    }

    @Override
    public boolean delete(Integer id) throws SQLException, ClassNotFoundException {
        String query1 = "DELETE FROM student WHERE student_id=?";
        return CrudUtill.execute(query1,id);
    }

}

package com.devstack.edu.bo.custome.Impl;

import com.devstack.edu.bo.custome.StudentBo;
import com.devstack.edu.dao.DaoFactory;
import com.devstack.edu.dao.custom.Impl.StudentDaoImpl;
import com.devstack.edu.dao.custom.StudentDao;
import com.devstack.edu.dto.StudentDto;
import com.devstack.edu.entity.Student;
import com.devstack.edu.util.GlobalVar;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentBoImpl implements StudentBo {
    private StudentDao studentdao = DaoFactory.getDao(DaoFactory.DataType.STUDENT);
    @Override
    public boolean saveStudent(StudentDto studentDto) throws SQLException, ClassNotFoundException, InvocationTargetException {
       return studentdao.save(
                new Student(studentDto.getStid(),studentDto.getStName(),studentDto.getStEmail(),studentDto.getStDob(),studentDto.getStAddress(),studentDto.isStatus(), GlobalVar.userEmail)
        );

    }
    @Override
    public List<StudentDto> findAll(String searchtetxt) throws SQLException, ClassNotFoundException {
        List<Student> studentList = studentdao.findAll(searchtetxt);
        List<StudentDto> studentDtoList = new ArrayList<>();
        for (Student student:studentList){
          studentDtoList.add(
                  new StudentDto(student.getStid(),student.getStName(),student.getStEmail(),student.getStDob(),student.getStAddress(),student.isStatus()));
        }
        return studentDtoList;
    }

    @Override
    public List<String> autoComplteStudents() throws SQLException, ClassNotFoundException {
        return studentdao.autoComplteStudents();
    }

    @Override
    public boolean update(StudentDto studentDto) throws SQLException, ClassNotFoundException {
       return studentdao.update(
                new Student(studentDto.getStid(),studentDto.getStName(),studentDto.getStEmail(),studentDto.getStDob(),studentDto.getStAddress(),studentDto.isStatus(),GlobalVar.userEmail)

        );
    }

    @Override
    public boolean delete(int id) throws SQLException, ClassNotFoundException {
        return studentdao.delete(id);
    }

    @Override
    public long getstId(String email) throws SQLException, ClassNotFoundException {
        return studentdao.getstId(email);
    }
}

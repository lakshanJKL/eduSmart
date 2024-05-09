package com.devstack.edu.dao.custom.Impl;

import com.devstack.edu.dao.CrudUtill;
import com.devstack.edu.dao.custom.RegistrationDao;
import com.devstack.edu.db.DBConnection;
import com.devstack.edu.entity.Registration;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RegistrationDaoImpl implements RegistrationDao {
    @Override
    public boolean save(Registration registration) throws SQLException, ClassNotFoundException, InvocationTargetException {
        String query = "INSERT INTO registration(registration_id,register_date,program_amount,intake_intake_id,student_student_id) VALUES (?,?,?,?,?)";
        return CrudUtill.execute(query,registration.getRegId(),java.sql.Date.valueOf(registration.getRegDate()),registration.getProAmount(),registration.getIntkId(),registration.getStdntId());
    }

    @Override
    public boolean update(Registration registration) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(Long id) throws SQLException, ClassNotFoundException {

        String query = "DELETE FROM registration WHERE registration_id=?";
        return CrudUtill.execute(query,id);
    }

    @Override
    public long findRegId(Registration registration) throws SQLException, ClassNotFoundException {
        String query = "SELECT registration_id FROM registration WHERE intake_intake_id=? AND student_student_id=?";
        ResultSet resultSet = CrudUtill.execute(query,registration.getIntkId(),registration.getStdntId());
        while (resultSet.next()){
            return resultSet.getLong(1);
        }
        return 0;
    }

    @Override
    public List<Registration> findAll(String searchtetxt) throws SQLException, ClassNotFoundException {
        String query = "SELECT r.registration_id,p.program_name,s.student_name,r.register_date FROM registration r " + "INNER JOIN program p ON p.amount = r.program_amount " + "INNER JOIN student s ON s.student_id = r.student_student_id " + "INNER JOIN intake i ON i.intake_id = r.intake_intake_id "+"WHERE registration_id LIKE ? OR program_name Like ? OR student_name Like ?";

        ResultSet resultSet = CrudUtill.execute(query,searchtetxt,searchtetxt,searchtetxt);
        ArrayList<Registration> registrationArrayList = new ArrayList<>();
        while (resultSet.next()){
            registrationArrayList.add(
                  new Registration(resultSet.getLong("registration_id"),resultSet.getDate("register_date").toLocalDate(),resultSet.getString("program_name"),resultSet.getString("student_name"))
            );
        }
        return registrationArrayList;
    }

    @Override
    public String findIntake(long id) throws SQLException, ClassNotFoundException {
        String query = "SELECT i.intake_id, i.intake_name FROM registration r INNER JOIN intake i ON r.intake_intake_id=i.intake_id WHERE registration_id=?";
        ResultSet resultSet = CrudUtill.execute(query,id);
        while (resultSet.next()){
                   return resultSet.getString(1)+"   "+resultSet.getString(2);
        }
        return null;
    }

    @Override
    public String findProgram(long id) throws SQLException, ClassNotFoundException {
        String query = "SELECT p.program_id, p.program_name FROM registration r INNER JOIN program p ON r.program_amount = p.amount WHERE registration_id=?";
        ResultSet resultSet = CrudUtill.execute(query,id);
        while (resultSet.next()){
            return resultSet.getString(1)+"   "+resultSet.getString(2);
        }
        return null;
    }

    @Override
    public String findEmail(long id) throws SQLException, ClassNotFoundException {
        String query = "SELECT s.email FROM registration r INNER JOIN student s ON r.student_student_id = s.student_id WHERE registration_id=?";
        ResultSet resultSet = CrudUtill.execute(query,id);
        while (resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }
}

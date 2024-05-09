package com.devstack.edu.dao.custom.Impl;

import com.devstack.edu.dao.CrudUtill;
import com.devstack.edu.dao.custom.IntakeDao;
import com.devstack.edu.db.DBConnection;
import com.devstack.edu.entity.Intake;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IntakeDaoImpl implements IntakeDao {

    public static long slectedIntakeId;

    @Override
    public boolean save(Intake intake) throws SQLException, ClassNotFoundException, InvocationTargetException {
        String query1 = "INSERT INTO intake(intake_id,intake_name,start_date,program_program_id) VALUES(?,?,?,?)";
        return CrudUtill.execute(query1,intake.getIntkID(),intake.getIntkName(),java.sql.Date.valueOf(intake.getIntkStdate()),intake.getprogramId());
    }

    @Override
    public boolean update(Intake intake) throws SQLException, ClassNotFoundException {
        String updtequery = "UPDATE intake SET intake_name=?,start_date=? WHERE intake_id=?";
        return CrudUtill.execute(updtequery,intake.getIntkName(),java.sql.Date.valueOf(intake.getIntkStdate()),slectedIntakeId);

    }

    @Override
    public boolean delete(Long id) throws SQLException, ClassNotFoundException {
        String dltequery = "DELETE FROM intake WHERE intake_id = ? ";
        return CrudUtill.execute(dltequery,id);
    }


    @Override
    public List<Intake> findAll(String text) throws SQLException, ClassNotFoundException {

        String query = "SELECT p.program_id, p.program_name, i.intake_id,i.intake_name,i.start_date FROM intake i INNER JOIN program p ON i.program_program_id = P.program_id WHERE i.intake_name LIKE ? OR p.program_name LIKE ?";
        ResultSet resultSet = CrudUtill.execute(query,text,text);
        ArrayList<Intake> intakes = new ArrayList<>();
        while (resultSet.next()){
            intakes.add(
                    new Intake(
                          resultSet.getLong(3),
                          resultSet.getString(4),
                          resultSet.getDate(5).toLocalDate(),
                          resultSet.getInt(1)
                    )
            );
        }
        return intakes;
    }

    @Override
    public List<String> loadAllIntakes() throws SQLException, ClassNotFoundException {
        String query = "SELECT intake_id,intake_name FROM intake";
        ResultSet resultSet = CrudUtill.execute(query);
        ArrayList<String> intakelist = new ArrayList<>();
        while (resultSet.next()){
            intakelist.add(resultSet.getLong(1)+"   "+resultSet.getString(2));
        }
        return intakelist;
    }

    @Override
    public int find(String text) throws SQLException, ClassNotFoundException {
//        SELECT p.program_id, p.program_name,p.hours,p.amount,p.trainer_trainer_id,p.user_email,
//                GROUP_CONCAT(pc.header) AS content FROM program p JOIN program_content pc ON p.program_id=pc.program_program_id
//        GROUP BY p.program_id;
        String query = "SELECT program_id FROM program WHERE program_name=?";
        ResultSet rs = CrudUtill.execute(query,text);
        while (rs.next()){
            return rs.getInt(1);
        }
        return 0;
    }

    @Override
    public String findProgramName(long id) throws SQLException, ClassNotFoundException {
        String query = "SELECT program_name FROM program WHERE program_id=?";
        ResultSet rs= CrudUtill.execute(query,id);
        while (rs.next()){
            return rs.getString(1);
        }
        return null;
    }


}

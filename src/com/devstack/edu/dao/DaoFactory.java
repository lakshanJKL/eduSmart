package com.devstack.edu.dao;

import com.devstack.edu.bo.custome.Impl.RegistrationBoImpl;
import com.devstack.edu.dao.custom.Impl.*;
import com.devstack.edu.dao.custom.StudentDao;
import com.devstack.edu.dao.custom.TrainerDao;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class DaoFactory {

  private DaoFactory daoFactory;

  private DaoFactory(){}

  public enum DataType{
      STUDENT,TRAINER,PROGRAM,PROGRAMCONTENT,INTAKE,REGISTRATION,PAYMENT,USER
  }

  public static <T>T getDao(DataType type){
      switch (type){
          case STUDENT:
              return (T) new StudentDaoImpl();
          case TRAINER:
              return (T) new TrainerDaoImpl();
          case PROGRAM:
              return (T) new ProgramDaoImpl();
          case PROGRAMCONTENT:
              return (T) new Program_contentDaoImpl();
          case INTAKE:
              return (T) new IntakeDaoImpl();
          case REGISTRATION:
              return (T) new RegistrationDaoImpl();
          case PAYMENT:
              return (T) new PaymentDaoImpl();
          case USER:
              return (T) new UserDaoImpl();
          default:
              return null;
      }
  }
}

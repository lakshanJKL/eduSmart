package com.devstack.edu.bo.custome;

import com.devstack.edu.bo.custome.Impl.*;
import com.devstack.edu.dao.custom.Impl.UserDaoImpl;

public class BoFactory {
    private BoFactory boFactory;
    private BoFactory(){}

    public enum DataType{
        STUDENT,TRAINER,PROGRAME,PROGRAMCONTENT,INTAKE,REGISTRATION,PAYMENT,USER
    }

    public static <T>T getBo(DataType type){
        switch (type){
            case STUDENT:
                return (T)new StudentBoImpl();
            case TRAINER:
                return (T) new TrainerBoImpl();
            case PROGRAME:
                return (T) new ProgramBoImpl();
            case PROGRAMCONTENT:
                return (T) new ProgramContentBoImpl();
            case INTAKE:
                return (T) new IntakeBoImpl();
            case REGISTRATION:
                return (T) new RegistrationBoImpl();
            case PAYMENT:
                return (T) new PaymentBoImpl();
            case USER:
                return (T) new UserBoImpl();
            default:
                return null;
        }
    }
}

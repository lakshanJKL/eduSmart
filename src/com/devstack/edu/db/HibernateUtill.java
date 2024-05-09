package com.devstack.edu.db;

import com.devstack.edu.model.Student;
import com.devstack.edu.model.Trainer;
import com.devstack.edu.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtill {
    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                .configure( "hibernate.cfg.xml" )
                .build();

        Metadata metadata = new MetadataSources( standardRegistry )
                .addAnnotatedClass(User.class )
                .addAnnotatedClass(Student.class )
                .addAnnotatedClass(User.class )
                .addAnnotatedClass(Trainer.class )
                .getMetadataBuilder()
                .applyImplicitNamingStrategy( ImplicitNamingStrategyJpaCompliantImpl.INSTANCE )
                .build();

        return metadata.getSessionFactoryBuilder()
                .build();
    }

    public static Session openSession(){
        return sessionFactory.openSession();
    }
}

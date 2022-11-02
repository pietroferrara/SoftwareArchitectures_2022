package org.example;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(new Event("1st!", new Date()));
        session.save(new Event("Next", new Date()));
        session.getTransaction().commit();
        session.close();


        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Event> result = entityManager
                .createQuery("from Event", Event.class)
                .getResultList();
        for(Event ev:result) {
            System.out.println( ev.getId() + ") Event (" + ev.getDate()
                    + ") : " + ev.getTitle());
        }
        entityManager.getTransaction().commit();
        entityManager.close();


    }
}
package ru.geekbrains.javahibernate2.repositories;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;
import ru.geekbrains.javahibernate2.models.Buyer;

import java.util.List;

@Component
public class BuyerRepo {
    private SessionFactory factory;

    public void init(){
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public void shutdown() {
        factory.close();
    }

    public void read (long id){
        try(Session session = factory.getCurrentSession()){
            session.beginTransaction();
            Buyer buyer = session.find(Buyer.class,id);
            System.out.println(buyer);
            session.getTransaction().commit();
        }
    }

    public void showAll(){
        try(Session session = factory.getCurrentSession()){
            session.beginTransaction();
            List<Buyer> buyers = session.createQuery("from Buyer ").getResultList();
            for (Buyer p: buyers  ) {
                System.out.println(p + "\n");
            }
            session.getTransaction().commit();
        }
    }

    public void delete(long id){
        try (Session session = factory.getCurrentSession()){
            session.beginTransaction();
            Buyer buyer = session.get(Buyer.class,id);
            session.delete(buyer);
            session.getTransaction().commit();
        }
    }

    public void create(String name){
        try(Session session = factory.getCurrentSession()){
            session.beginTransaction();
            Buyer buyer = new Buyer(name);
            session.save(buyer);
            session.getTransaction().commit();
        }
    }

    public void update(long id, String newName){
        try(Session session = factory.getCurrentSession()){
            session.beginTransaction();
            Buyer buyer = session.get(Buyer.class,id);
            buyer.setName(newName);
            session.getTransaction().commit();
        }
    }

}

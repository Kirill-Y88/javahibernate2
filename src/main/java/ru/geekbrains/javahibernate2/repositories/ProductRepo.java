package ru.geekbrains.javahibernate2.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;
import ru.geekbrains.javahibernate2.models.Buyer;
import ru.geekbrains.javahibernate2.models.Product;

import java.util.List;

@Component
public class ProductRepo {
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
            Product product = session.find(Product.class,id);
            System.out.println(product);
            session.getTransaction().commit();
        }
    }

    public void showAll(){
        try(Session session = factory.getCurrentSession()){
            session.beginTransaction();
            List<Product> products = session.createQuery("from Product ").getResultList();
            for (Product p: products  ) {
                System.out.println(p + "\n");
            }
            session.getTransaction().commit();
        }
    }

    public void delete(long id){
        try (Session session = factory.getCurrentSession()){
            session.beginTransaction();
            Product product = session.get(Product.class,id);
            session.delete(product);
            session.getTransaction().commit();
        }
    }

    public void create(String title){
        try(Session session = factory.getCurrentSession()){
            session.beginTransaction();
            Product product = new Product(title);
            session.save(product);
            session.getTransaction().commit();
        }
    }

    public void update(long id, String newTitle){
        try(Session session = factory.getCurrentSession()){
            session.beginTransaction();
            Product product = session.get(Product.class,id);
            product.setTitle(newTitle);
            session.getTransaction().commit();
        }
    }

}

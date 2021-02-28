package ru.geekbrains.javahibernate2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.geekbrains.javahibernate2.models.Buyer;
import ru.geekbrains.javahibernate2.models.Product;
import ru.geekbrains.javahibernate2.repositories.BuyerRepo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Javahibernate2Application {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigContext.class);
        BuyerRepo buyerRepo = context.getBean("buyerRepo",BuyerRepo.class);

        downloadSQLScript();

        //опробование crud
        buyerRepo.init();
        buyerRepo.create("15A");
        buyerRepo.create("16S");
        buyerRepo.create("17D");
        buyerRepo.create("18F");
        buyerRepo.create("19G");
        buyerRepo.shutdown();


        //опробование вывода спиков товаров по id покупателя и наоборот (пока без сервиса, но не в том проблема)
        System.out.println("+++++++++++++++++++++++++++");
        buy_products(1L);
        System.out.println("+++++++++++++++++++++++++++");


      //  SpringApplication.run(Javahibernate2Application.class, args);

    }

    public static void downloadSQLScript() {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        Session session = null;
        try {
            String sql = Files.lines(Paths.get("full.sql")).collect(Collectors.joining(" "));
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            factory.close();
            if (session != null) {
                session.close();
            }
        }
    }

    public static void buy_products(long id) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        Session session = null;
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            List<Buyer> buyers;
            buyers = session.getNamedQuery("buy_products")
                    .setParameter("id", id)
                    .getResultList();
            session.getTransaction().commit();
            System.out.println("========================================");
            for (Buyer b : buyers) {
                b.toString();
            }
            System.out.println("========================================");

        } finally {
            factory.close();
            if (session != null) {
                session.close();
            }
        }
    }


    public static void buyer_buy_products (long id){
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        Session session = null;
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            List<Product> products;
            products = session.getNamedQuery("buyer_buy_products")
                    .setParameter("id", id)
                    .getResultList();
            session.getTransaction().commit();
            for (Product p : products) {
                p.toString();
            }

        } finally {
            factory.close();
            if (session != null) {
                session.close();
            }
        }

        }






}

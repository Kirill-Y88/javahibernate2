package ru.geekbrains.javahibernate2.models;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
@NamedQueries({
        @NamedQuery( name = "buyer_buy_products", query = "SELECT p FROM Product p JOIN FETCH p.buyers WHERE p.id = :id")
})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @ManyToMany
    @JoinTable(
            name = "orders",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "buyer_id")
    )
    private List<Buyer> buyers;

    public Product(){}

    public Product(String title){
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Buyer> getBuyers() {
        return buyers;
    }

    public void setBuyers(List<Buyer> buyers) {
        this.buyers = buyers;
    }

    @Override
    public String toString() {
        return String.format("Product [id = %d, title = %s]", id, title);
    }
}



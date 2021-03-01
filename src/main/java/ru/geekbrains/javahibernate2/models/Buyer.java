package ru.geekbrains.javahibernate2.models;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "buyers")
@NamedQueries({
        @NamedQuery( name = "buy_products", query = "SELECT b FROM Buyer b JOIN FETCH b.products WHERE b.id = :id")
})
public class Buyer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "orders",
            joinColumns = @JoinColumn(name = "buyer_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    public Buyer(){}

    public Buyer(String name){
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return String.format("Buyer [id = %d, title = %s]", id, name);
    }


}

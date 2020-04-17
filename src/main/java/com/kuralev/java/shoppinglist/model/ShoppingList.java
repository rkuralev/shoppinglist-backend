package com.kuralev.java.shoppinglist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "lists")
@Getter @Setter
public class ShoppingList {
    @Id
    @Column(unique = true)
    @Type(type="uuid-char")
    @JsonIgnore
    private UUID uuid;

    @OneToMany(
            mappedBy = "shoppingList",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    private List<Item> items;
}

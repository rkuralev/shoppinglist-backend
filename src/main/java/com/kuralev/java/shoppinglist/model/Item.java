package com.kuralev.java.shoppinglist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "items")
@Getter @Setter
public class Item {
    @Id
    @GeneratedValue
    @JsonIgnore
    private long internalId;
    private String description;
    private int id;
    private boolean checked;
    private int priority;
    private boolean deleted;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "list_id", nullable = false)
    @JsonIgnore
    private ShoppingList shoppingList;
}

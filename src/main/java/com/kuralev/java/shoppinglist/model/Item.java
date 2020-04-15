package com.kuralev.java.shoppinglist.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Item {
    private String description;
    private int id;
    private boolean checked;
    private int priority;
    private boolean deleted;
}

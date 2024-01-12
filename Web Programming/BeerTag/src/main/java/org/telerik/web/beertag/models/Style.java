package org.telerik.web.beertag.models;

import jakarta.persistence.*;

@Entity
@Table(name = "styles")
public class Style {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "style_id")
    private int id;
    @Column(name = "name")
    private String name;

    public Style() {
    }

    public Style(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

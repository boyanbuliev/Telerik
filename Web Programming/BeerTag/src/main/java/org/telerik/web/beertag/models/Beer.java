package org.telerik.web.beertag.models;

import jakarta.validation.constraints.Positive;

import java.util.Objects;

public class Beer {
    @Positive(message = "Id should be positive")
    private int id;
    private String name;
    private double abv;
    private Style style;
    private User createdBy;

    public Beer() {
    }

    public Beer(int id, String name, double abv, Style style, User user) {
        this.id = id;
        this.name = name;
        this.abv = abv;
        this.style = style;
        this.createdBy = user;
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

    public double getAbv() {
        return abv;
    }

    public void setAbv(double abv) {
        this.abv = abv;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Beer beer)) return false;
        return id == beer.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

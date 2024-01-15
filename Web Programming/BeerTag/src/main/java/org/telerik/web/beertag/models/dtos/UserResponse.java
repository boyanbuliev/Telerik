package org.telerik.web.beertag.models.dtos;

import org.telerik.web.beertag.models.Beer;

import java.util.Set;

public class UserResponse {
   private int id;
   private String username;
   private String firstName;
   private String lastName;
   private String email;
   private boolean admin;
   private Set<Beer> wishList;

    public UserResponse(int id, String username, String firstName, String lastName, String email, boolean admin, Set<Beer> wishList) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.admin = admin;
        this.wishList = wishList;
    }
}

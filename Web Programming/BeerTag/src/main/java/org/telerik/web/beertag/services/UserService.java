package org.telerik.web.beertag.services;

import org.telerik.web.beertag.models.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User getById(int id);

    User getByUsername(String username);

    void addToWishList(int userId, int beerId);

    void deleteFromWishList(int userId, int beerId);
}

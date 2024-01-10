package org.telerik.web.beertag.services;

import org.telerik.web.beertag.models.User;

import java.util.List;

public interface UserService {

    User create(User user);

    List<User> getAll();

    User getById(int id);

    User getByUsername(String username);


}

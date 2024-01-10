package org.telerik.web.beertag.repositories;

import org.telerik.web.beertag.models.User;

import java.util.List;

public interface UserRepository {

    User create(User user);

    List<User> getAll();

    User getById(int id);

    User getByUsername(String username);


}

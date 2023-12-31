package org.telerik.web.beertag.repositories;

import org.springframework.stereotype.Repository;
import org.telerik.web.beertag.exceptions.EntityNotFoundException;
import org.telerik.web.beertag.models.User;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final List<User> users;

    public UserRepositoryImpl() {
        users = new ArrayList<>();
        users.add(new User(1, "pesho", true));
        users.add(new User(2, "gosho", false));
        users.add(new User(3, "ivan", false));
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(users);
    }

    @Override
    public User getById(int id) {
        return getAll().stream()
                .filter(u -> u.getId() == id)
                .findFirst().orElseThrow(() -> new EntityNotFoundException("User", id));
    }

    @Override
    public User getByUsername(String username) {
        return getAll().stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst().orElseThrow(() -> new EntityNotFoundException("User", "username", username));
    }
}

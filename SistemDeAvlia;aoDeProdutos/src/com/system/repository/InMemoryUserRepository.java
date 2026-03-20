package com.system.repository;

import com.system.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryUserRepository implements UserRepository {
    private final List<User> users = new ArrayList<>();
    private long currentId = 1;

    @Override
    public void save(User entity) {
        if (entity.getId() == null) {
            entity.setId(currentId++);
            users.add(entity);
        } else {
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getId().equals(entity.getId())) {
                    users.set(i, entity);
                    return;
                }
            }
            users.add(entity);
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        return users.stream().filter(u -> u.getId().equals(id)).findFirst();
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users);
    }

    @Override
    public void delete(Long id) {
        users.removeIf(u -> u.getId().equals(id));
    }
}

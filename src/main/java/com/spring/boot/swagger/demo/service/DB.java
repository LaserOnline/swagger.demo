package com.spring.boot.swagger.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

@Service
public class DB {
    private final List<Entity> dataStore = new ArrayList<>();
    private final AtomicInteger idCounter = new AtomicInteger();

    public Entity add (String message) {
        Entity entity = new Entity();
        entity.setId(idCounter.getAndIncrement());
        entity.setMessage(message);
        dataStore.add(entity);
        return  entity;
    }

    public Entity updateById(int id, String newMessage) {
        for (Entity entity : dataStore) {
            if (entity.getId() == id) {
                entity.setMessage(newMessage);
                return entity;
            }
        }
        return null;
    }

    public List<Entity> getAll() {
        return dataStore;
    }

    public boolean deleteById(int id) {
        return dataStore.removeIf(entity -> entity.getId() == id);
    }
}

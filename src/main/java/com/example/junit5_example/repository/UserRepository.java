package com.example.junit5_example.repository;


import com.example.junit5_example.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class UserRepository {

    private static  final Logger LOGGER = LogManager.getLogger();
    private Map<Integer, User> userMap = new LinkedHashMap<>();
    private int increUserId = 1;


    public void add(User user) {
        user.setId(increUserId++);
        userMap.put(increUserId,user);
        LOGGER.info("{} successfuly added ", user);
    }

    public Optional<User> getById(int userId) {
        return Optional.ofNullable(userMap.get(userId));

    }

    public List<User> getAll(){
        return new ArrayList<>(userMap.values());
    }
}

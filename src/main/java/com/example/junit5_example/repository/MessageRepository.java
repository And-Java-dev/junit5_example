package com.example.junit5_example.repository;



import com.example.junit5_example.model.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MessageRepository {

    private static final Logger LOGGER = LogManager.getLogger();

    private Map<Integer, Message> messageMap = new LinkedHashMap<>();

    private int increMessage = 1;


    public void add(Message message) {
        message.setId(increMessage++);
        messageMap.put(message.getId(), message);
        LOGGER.info("{} successfuly added ", message);
    }

    public List<Message> getAllByFromId(int fromId){
         return messageMap.values()
                 .stream()
                 .filter(message -> message.getFrom().getId() == fromId)
                 .collect(Collectors.toList());
    }


    public void deleteById(int id) {
        Message message = messageMap.remove(id);
        increMessage--;
        LOGGER.info("{} successfuly deleted ", message);
    }

    public int size(){
        return increMessage -1;
    }
}

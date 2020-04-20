package com.example.junit5_example.repository;


import com.example.junit5_example.model.Message;
import com.example.junit5_example.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("messageRepository test class")
public class MessageRepositoryTest {

    private MessageRepository messageRepository;

    @BeforeEach
    void beforeEache() {
        System.out.println("Before eac method invoked");
        messageRepository = new MessageRepository();
    }

    @Test
    @DisplayName("test getAllByFromId() method")
    void getAllByFromIdTest() {
        User from = getTwoUsersForTest().get(0);
        User to = getTwoUsersForTest().get(1);
        List<Message> messages = getSomeMessagesByToAndFrom(to, from);
        messages.forEach(messageRepository:: add);
        List<Message> messageList = messageRepository.getAllByFromId(from.getId());
        assertAll(
                () -> assertEquals("Hello", messageList.get(0).getText()),
                () -> assertEquals("Hi", messageList.get(1).getText()),
                () -> assertEquals("Aloha", messageList.get(2).getText())
        );
    }

    @RepeatedTest(7)
    void repeatTest() {
        System.out.println("repeat test");
    }
    //test cases of this class will run similar to test case of MessageRepositoryTest class
    @Nested
    @DisplayName("messageRepository add and delete test class")
    class  MessageAddAndDeleteTest{

        @Test
        @DisplayName("test add() method")
        void addTest() {
            List<User> userList = getTwoUsersForTest();
            User from = userList.get(0);
            User to = userList.get(1);
            Message message = new Message();
            message.setSendDate(new Date());
            message.setText("Bonjorno");
            message.setFrom(from);
            message.setTo(to);
            messageRepository.add(message);
            assertEquals(1, message.getId(), "Id of the message must be equal 1");

        }

        @Test
        @DisplayName("test deleteById() method")
        void deleteByIdTest() {
            List<User> userList = getTwoUsersForTest();
            User from = getTwoUsersForTest().get(0);
            User to = getTwoUsersForTest().get(1);

            Message message = Message.builder()
                    .sendDate(new Date())
                    .text("BYE")
                    .from(from)
                    .to(to)
                    .build();

            messageRepository.add(message);
            messageRepository.deleteById(message.getId());
            assertEquals(0, messageRepository.size(), " size must be equal 0");
        }
    }

    static List<Message> getSomeMessagesByToAndFrom(User to, User from) {

        return Arrays.asList(
                Message
                        .builder()
                        .sendDate(new Date())
                        .from(from)
                        .to(to)
                        .text("Hello")
                        .build(),
                Message
                        .builder()
                        .sendDate(new Date())
                        .from(from)
                        .to(to)
                        .text("Hi")
                        .build(),
                Message
                        .builder()
                        .sendDate(new Date())
                        .from(from)
                        .to(to)
                        .text("Aloha")
                        .build()
        );
    }

    static List<User> getTwoUsersForTest() {

        return Arrays.asList(
                User
                        .builder()
                        .name("Sam")
                        .surname("Brenon")
                        .age(11)
                        .build(),

                User
                        .builder()
                        .name("Tom")
                        .surname("Soyer")
                        .age(32)
                        .build()
        );
    }
}

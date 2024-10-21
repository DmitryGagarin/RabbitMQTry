package org.example;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Send {
//    private final static String QUEUE_NAME = "hello";
        private final static String TASK_QUEUE_NAME = "task_queue";


    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
//            for (int i = 0; i < 10; i++) {
//                String message = String.valueOf(i);
//                channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
//                System.out.print("[x] sent: " + message);
//            }
            ArrayList<String> messages = new ArrayList<String>();
            messages.add("Hello");
            messages.add("Hello.");
            messages.add("Hello..");
            messages.add("Hello...");
            messages.add("Hello....");
            for (String message : messages) {
                channel.basicPublish("", TASK_QUEUE_NAME,
                        MessageProperties.PERSISTENT_TEXT_PLAIN,
                        message.getBytes("UTF-8"));
                System.out.println("Sent: " + message);
            }
        }
    }
}
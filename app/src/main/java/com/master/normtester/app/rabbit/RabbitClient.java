package com.master.normtester.app.rabbit;

import android.util.Log;
import com.rabbitmq.client.*;
import com.rabbitmq.client.AMQP.BasicProperties;

import java.io.IOException;

/**
 * Created by lpalonek on 21/04/15.
 */
public enum RabbitClient {
    INSTANCE;

    private static final String SERVER_ADDRESS = "ec2-52-28-63-144.eu-central-1.compute.amazonaws.com";

    private static final String QUEUE_NAME = "test";

    private static final String TAG = "RabbitClient";

    private QueueingConsumer consumer;

    private Channel channel;

    private Connection connection;

    RabbitClient() {
        connection = createConnection();
        channel = createChannelAndConsumer();
    }

    public String call(String message) {
        Log.i(TAG,"Sending message");
        String response = null;
        String corrId = java.util.UUID.randomUUID().toString();

        BasicProperties props = new BasicProperties
                .Builder()
                .correlationId(corrId)
                .replyTo(QUEUE_NAME)
                .deliveryMode(2)
                .build();

        try {
            channel.basicPublish("", QUEUE_NAME, props, message.getBytes());
        } catch (IOException e) {
            Log.e(TAG, "Could not publish message " + e.getMessage());
        }

        while (true) {
            QueueingConsumer.Delivery delivery = null;
            try {
                delivery = consumer.nextDelivery();
            } catch (InterruptedException e) {
                Log.e(TAG, "Could not obtain delivered message " + e.getMessage());
            }
            if (delivery.getProperties().getCorrelationId().equals(corrId)) {
                response = new String(delivery.getBody());
                Log.i(TAG, "Response received");
                break;
            }
        }

        return response;
    }

    public Channel createChannelAndConsumer() {
        if (connection == null) {
            Log.e(TAG, "Connection cannot be null");
            return null;
        } else {
            try {
                channel = connection.createChannel();
                consumer = new QueueingConsumer(channel);
                channel.basicConsume(QUEUE_NAME, true, consumer);
                return channel;
            } catch (IOException e) {
                Log.e(TAG, "Could not instantiate connection!");
                return null;
            }
        }
    }

    public Connection createConnection() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(SERVER_ADDRESS);
        try {
            return factory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void close() {
        try {
            channel.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

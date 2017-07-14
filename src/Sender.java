import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Channel;

import java.util.concurrent.TimeoutException;

public class Sender {
    public static void main(String[] args) throws java.io.IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.89.111");
        factory.setPort(15672);
        factory.setUsername("inhouse");
        factory.setPassword("inhouse");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclareNoWait("beks", false, false, false, null);
        String message = "Su4ka";
        channel.basicPublish("", "beks", null, message.getBytes());
        System.out.println("[x] Sent " + message + "");
        channel.close();
        connection.close();
    }
}

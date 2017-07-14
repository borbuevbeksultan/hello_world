import com.rabbitmq.client.*;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Receiver {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.89.111");
        factory.setPort(15672);
        factory.setUsername("inhouse");
        factory.setPassword("inhouse");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare("beks", false, false, false, null);
        System.out.println("[*] Waiting for messages. CTRL + C");

        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties basicProperties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("[x] Received " + message);
            }
        };
        channel.basicConsume("beks", true, consumer);
    }
}

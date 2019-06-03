package cc.mrbird.febs.web.controller;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.CopyOnWriteArraySet;

@RestController
public class Test2 extends Thread {

    private static CopyOnWriteArraySet<MqttClient> mqttClientSet = new CopyOnWriteArraySet<MqttClient>();


    @GetMapping("dingyue")
    public void dingyue() {
        String HOST = "tcp://192.168.3.13";
        String TOPIC = "0000000000000001/#";
        int qos = 1;
        String clientid = "0000000000000001office";
        try {
            // host为主机名，test为clientid即连接MQTT的客户端ID，一般以客户端唯一标识符表示，MemoryPersistence设置clientid的保存形式，默认为以内存保存
            MqttClient client = new MqttClient(HOST, clientid, new MemoryPersistence());
            // MQTT的连接设置
            MqttConnectOptions options = new MqttConnectOptions();
            // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
            options.setCleanSession(true);
            // 设置超时时间 单位为秒
            options.setConnectionTimeout(10);
            // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
            options.setKeepAliveInterval(20);
            // 设置回调函数
            client.setCallback(new MqttCallback() {

                @Override
                public void connectionLost(Throwable cause) {
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) {
                    System.out.println("message content:" + new String(message.getPayload()));
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    System.out.println("deliveryComplete:" + token.isComplete());
                }
            });
            client.connect(options);
            client.subscribe(TOPIC, qos);
            System.out.println("完成订阅");
            mqttClientSet.add(client);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("chakan")
    public void chakan() {
        for (MqttClient o : mqttClientSet) {
            System.out.println(o.toString());
        }
    }

    @GetMapping("close")
    public void close(String hascode) {
        for (MqttClient o : mqttClientSet) {
            if (hascode.equals(o.toString())){
                try {
                    mqttClientSet.remove(o);
                    o.disconnect();
                } catch (MqttException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Test2 t = new Test2();
        System.out.println(t.getId());
        System.out.println(t.getName());
        System.out.println(t.getThreadGroup());
        System.out.println(t.getState());
        t.start();
        System.out.println(t.getState());
        Test2 t1 = new Test2();
        System.out.println(t1.getId());
        System.out.println(t1.getName());
        System.out.println(t1.getThreadGroup());
        System.out.println(t1.getState());
        t1.start();
        System.out.println(t1.getState());
        Thread thread = new Thread("da");
        System.out.println(thread.getId());
        System.out.println(thread.getName());
        System.out.println(thread.getThreadGroup());
        System.out.println(thread.getState());
        thread.start();
        Dingyue dingyue = new Dingyue();
        Thread t3 = new Thread(dingyue, "sd");
        System.out.println(t3.getId());
        System.out.println(t3.getName());
        System.out.println(t3.getThreadGroup());
        System.out.println(t3.getState());
        t3.start();
    }

//    public static void main(String[] args) {
//        Dingyue dingyue = new Dingyue();
//        Thread t1 = new Thread(dingyue, "d1");
////        Dingyue dingyue2 = new Dingyue();
//        Thread t2 = new Thread(dingyue, "d2");
//        Thread t3 = new Thread(dingyue, "d3");
//        Thread t4 = new Thread(dingyue, "d4");
//        Thread t5 = new Thread(dingyue, "d5");
//        t1.start();
//        t2.start();
//        t3.start();
//        t4.start();
//        t5.start();
//
//
//    }

//    public static void main(String[] args) throws UnknownHostException {
//        InetAddress address =InetAddress.getLocalHost();
//        address.getHostName();//获取计算机名
//        address.getHostAddress();//获取IP地址
//        byte[] bytes = address.getAddress();//获取字节数组形式的IP地址,以点分隔的四部分
//        System.out.println(address.getHostName() + " " + address.getHostAddress());
//
////获取其他主机的InetAddress实例
////        InetAddress address2 =InetAddress.getByName("其他主机名");
//        InetAddress address3 =InetAddress.getByName("192.168.10.9");
//        System.out.println(address3.getHostName() + " " + address3.getHostAddress());
//
//
//    }
    @Override
    public void run(){
        System.out.println("111");
    }


}

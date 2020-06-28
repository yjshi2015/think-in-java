package com.syj.demo.thread._2020_rpc.rpcDemo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2020/6/28.
 * @desc RPC服务端发布者代码实现
 *
 */
public class RpcProvider {

    static ExecutorService pool = Executors.newFixedThreadPool(5);

    public static void exporter(String hostName, int port) throws Exception {
        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress(hostName, port));
        try {
            while (true) {
                /**
                 * 1.作为服务端，监听客户端的TCP连接，接收到新的客户端连接之后，将其封装成Task，由线程池执行
                 * 2.将客户端发送的码流反序列化成对象，反射调用服务实现者，获取执行结果
                 * 3.将执行结果对象反序列化，通过Socket发送给客户端
                 * 4.远程服务调用完成之后，释放Socket等连接资源，防止句柄泄露
                 */
                pool.execute(new ExporterTask(server.accept()));
            }
        } finally {
            server.close();
        }
    }

    private static class ExporterTask implements Runnable {

        Socket client = null;

        public ExporterTask(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            ObjectInputStream inputStream = null;
            ObjectOutputStream outputStream = null;

            try {
                // 获取输入流
                inputStream = new ObjectInputStream(client.getInputStream());
                // 获取对应的接口信息
                String interfaceName = inputStream.readUTF();
                Class<?> service = Class.forName(interfaceName);
                String methodName = inputStream.readUTF();
                Class<?>[] parameterTypes = (Class<?>[]) inputStream.readObject();
                Object[] args = (Object[]) inputStream.readObject();
                // 反射调用provider侧的服务
                Method method = service.getMethod(methodName, parameterTypes);
                Object result = method.invoke(service.newInstance(), args);
                // 将调用结果，通过socket返回给consumer
                outputStream = new ObjectOutputStream(client.getOutputStream());
                outputStream.writeObject(result);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (client != null) {
                    try {
                        client.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }
}

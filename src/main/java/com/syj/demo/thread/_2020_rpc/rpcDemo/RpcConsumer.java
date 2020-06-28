package com.syj.demo.thread._2020_rpc.rpcDemo;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by Administrator on 2020/6/28.
 * @DES RPC客户端本地服务代理
 */
public class RpcConsumer<S> {

    /**
     * 1.将本地的接口调用转换成JDK的动态代理，在动态代理中实现接口的远程调用
     * 2.创建socket客户端，根据指定地址连接远程服务提供者
     * 3.将远程服务所需的接口类、方法名、参数列表等编码后发送给服务提供者
     * 4.同步阻塞等待服务端返回应答，获取应答之后返回
     * @param serviceClass
     * @param addr
     * @return
     */
    public S importer(final Class<?> serviceClass, final InetSocketAddress addr) {
        return (S) Proxy.newProxyInstance(serviceClass.getClassLoader(),
                new Class<?>[]{serviceClass.getInterfaces()[0]},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Socket socket = null;
                        ObjectInputStream inputStream = null;
                        ObjectOutputStream outputStream = null;

                        try {
                            socket = new Socket();
                            socket.connect(addr);
                            outputStream = new ObjectOutputStream(socket.getOutputStream());
                            outputStream.writeUTF(serviceClass.getName());
                            outputStream.writeUTF(method.getName());
                            outputStream.writeObject(method.getParameterTypes());
                            outputStream.writeObject(args);

                            // 同步阻塞，获取返回值
                            inputStream = new ObjectInputStream(socket.getInputStream());
                            return inputStream.readObject();
                        } finally {
                            if (socket != null) {
                                socket.close();
                            }
                            if (outputStream != null) {
                                outputStream.close();
                            }
                            if (inputStream != null) {
                                inputStream.close();
                            }
                        }

                    }
                });
    }
}

package com.tiansuan.jetty;

import java.net.InetSocketAddress;
import java.net.ServerSocket;  
import java.net.Socket;
import java.net.SocketAddress;  

public class Server1 {  
    public static void main(String[] args) throws Exception{  
        ServerSocket server = new ServerSocket();  
        SocketAddress endpoint=new InetSocketAddress("192.168.1.101",9000);
		server.bind(endpoint);
        Socket client = null;  
        boolean f = true;  
        while(f){  
            //等待客户端的连接，如果没有获取连接  
            client = server.accept();  
            System.out.println("与客户端连接成功！"+client.getPort()+"  "+client.getLocalPort());  
            //为每个客户端连接开启一个线程  
            new Thread(new ServerThread(client)).start();  
        }  
        server.close();  
    }  
}
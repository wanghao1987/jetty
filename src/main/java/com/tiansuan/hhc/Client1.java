package com.tiansuan.hhc;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class Client1 {
  public static void main(String args[]) throws Exception {
    // 要连接的服务端IP地址和端口
    String host = "192.168.0.105";
    int port = 5002;
    // 与服务端建立连接
    Socket socket = new Socket();
    SocketAddress endpoint=new InetSocketAddress(host,port);
	socket.connect(endpoint);
    // 建立连接后获得输出流
    OutputStream outputStream = socket.getOutputStream();
    String message = "off1";
    outputStream.write(message.getBytes());
    outputStream.flush();
    //通过shutdownOutput高速服务器已经发送完数据，后续只能接受数据
    //socket.shutdownOutput();
    
    InputStream inputStream = socket.getInputStream();
    byte[] bytes = new byte[1024];
    int len;
    StringBuilder sb = new StringBuilder();
    while ((len = inputStream.read(bytes)) != -1) {
      //注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
      sb.append(new String(bytes, 0, len));
    }
    System.out.println("get message from server: " + sb);
    
    inputStream.close();
    //outputStream.close();
    socket.close();
  }
}

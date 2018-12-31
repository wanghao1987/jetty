package com.tiansuan.jetty;

import java.io.BufferedReader;  
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;  
import java.net.SocketTimeoutException;  
/**
 * 
 * @author wanghao
 * 20181231
 *
 */
public class Client {  
    public static void main(String[] args) throws IOException {  
        //客户端请求与本机在20006端口建立TCP连接   
        Socket client = new Socket("192.168.0.105", 5002);  
        client.setSoTimeout(10000);  
        //获取键盘输入   
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));  
        //获取Socket的输出流，用来发送数据到服务端    
        //PrintWriter out = new PrintWriter(client.getOutputStream());  
        // 建立连接后获得输出流
        OutputStream outputStream = client.getOutputStream();
      
        //获取Socket的输入流，用来接收从服务端发送过来的数据    
        //BufferedReader buf =  new BufferedReader(new InputStreamReader(client.getInputStream())); 
        //BufferedReader buf =  null;
        byte[] b=null;
        
        InputStream inputStream =client.getInputStream();
        b=new byte[20];
        inputStream.read(b);
        System.out.println("第一次连接："+new String(b));
        boolean flag = true;  
        while(flag){  
            System.out.print("输入信息：");  
            String str = input.readLine();  
            //发送数据到服务端    
            //out.print(str); 
            //out.flush();
            outputStream.write(str.getBytes());
            outputStream.flush();
            if("bye".equals(str)){  
                flag = false;  
            }else{  
                try{  
                    
					//从服务器端接收数据有个时间限制（系统自设，也可以自己设置），超过了这个时间，便会抛出该异常  
                	//buf=new BufferedReader(new InputStreamReader(client.getInputStream()));  
                    //String echo = buf.readLine();  
                	 b=new byte[20];
                	inputStream.read(b);
                    System.out.println(new String(b));
                }catch(SocketTimeoutException e){  
                	//e.printStackTrace();
                    System.out.println("Time out, No response");  
                }  
            }  
        }  
        input.close();  
        if(client != null){  
            //如果构造函数建立起了连接，则关闭套接字，如果没有建立起连接，自然不用关闭  
            client.close(); //只关闭socket，其关联的输入输出流也会被关闭  
        }  
    }  
}  


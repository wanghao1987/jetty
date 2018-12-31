package com.tiansuan.hhc;

public class TestHhc {
    public static void main(String[] args) {
     
        Thread client1 = new Thread(new Client("192.168.0.105",5000,"ONE"));
        //Thread client2 = new Thread(new Client(10086,"TWO"));
     
        client1.start();
        //client2.start();
    }
}


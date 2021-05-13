package io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(6666);

            while (true) {
                Socket s = ss.accept(); // 阻塞方法

                System.out.println("connected！");

                DataInputStream inputStream = new DataInputStream(s.getInputStream());

                DataOutputStream outputStream = new DataOutputStream(s.getOutputStream());

                String str = null;
                if ((str = inputStream.readUTF()) != null) {
                    System.out.println(str);

                    System.out.println("from " + s.getInetAddress() + ",port:" + s.getPort());
                }

                outputStream.writeUTF("hello, "+ s.getInetAddress() + ",port:" + s.getPort());

                inputStream.close();
                outputStream.close();
                s.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Exception");
        }
    }
}

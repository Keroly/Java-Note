package io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class ClientServer {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("127.0.0.1",6666);

            OutputStream os = s.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            dos.writeUTF("Hello,Server");

            DataInputStream dis = new DataInputStream(s.getInputStream());
            System.out.println(dis.readUTF());

            dos.flush();
            dos.close();

            dis.close();
            s.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Connection fail!");
        }
    }
}

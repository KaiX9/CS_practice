package sg.edu.nus.iss.app.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {
    public static void main(String[] args) {
        Socket sock = null;
        InputStream is = null;
        OutputStream os = null;

        try {
            // Get server port from java cli first argument
            String portNumber = args [0];
            System.out.println(portNumber);

            // Get the idioms file from second argument
            String idiomFile = args[1];
            System.out.println(idiomFile);

            // Get the idiom result file
            String idiomResultFile = args[2];
            System.out.println(idiomResultFile);

            System.out.printf("Server started at %s\n", portNumber);

            // Instantiate server socket object pass in the server
            ServerSocket server = new ServerSocket(Integer.parseInt(portNumber));
            while (true) {
                sock = server.accept();

                is = sock.getInputStream();
                DataInputStream dis = new DataInputStream(is);

                os = sock.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);

                while (true) {
                    try {
                        String dataFromClient = dis.readUTF();

                        if (dataFromClient.equals("idiom")) {
                            String randomIdiom = Idiom.getRandomIdiom(idiomFile, idiomResultFile);
                            dos.writeUTF("idiom_" + randomIdiom);
                        } else {
                            dos.writeUTF("Invalid Command...");
                        }
                    } catch (EOFException e) {
                        System.out.println("Client disconnected...");
                        sock.close();
                        break;
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Kindly input the correct no. of arguments...");
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                sock.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

package sg.edu.nus.iss.app.Client;

import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.rmi.UnknownHostException;

public class ClientApp {
    public static void main(String[] args) {
        String[] arraySplit = args[0].split(":");
        System.out.println(arraySplit[0]);
        System.out.println(arraySplit[1]);

        try {
            while (true) {
                Socket sock = new Socket(arraySplit[0], Integer.parseInt(arraySplit[1]));
                
                InputStream is = sock.getInputStream();
                DataInputStream dis = new DataInputStream(is);

                OutputStream os = sock.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);

                Console cons = System.console();
                String input = cons.readLine("Send command to the random idiom server: ");
                
                dos.writeUTF(input);
                dos.flush();

                String response = dis.readUTF();
                if (response.contains("idiom")) {
                    String[] idiomArray = response.split("_");
                    System.out.println("Idiom generated: " + idiomArray[1]);
                } else {
                    System.err.println(response);
                }

                is.close();
                os.close();
                sock.close();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}

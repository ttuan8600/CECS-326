/*
 * An echo server listening on port 6007. This server reads from the client
 * and echoes back the result. 
 */

import java.net.*;
import java.io.*;

public class  EchoServer
{
    public static void main(String[] args){
        try (ServerSocket sock = new ServerSocket(6007)) {
            while (true) {
                Socket client = sock.accept();
                BufferedReader bin = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter pout = new PrintWriter(client.getOutputStream(), true);
                // write the data to the socket

                String line;
                while ((line = bin.readLine()) != null){
                    System.out.println("Received: " + line);
                    pout.println(line);
                }
                // close the socket and resume listening for connections
                client.close();
                if(client.isClosed()){
                    System.out.println("Client has closed the connection!");
                    break;
                }
            }
        }catch(IOException ioe){
            System.err.println(ioe);
        }
    }
}

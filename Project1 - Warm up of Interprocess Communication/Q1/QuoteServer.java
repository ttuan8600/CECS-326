/*
 * Quote server listening to port 6017.
 */
 
import java.net.*;
import java.io.*;

public class QuoteServer
{
  public static void main(String[] args){
    try(ServerSocket sock = new ServerSocket(6017)){
      // now listen for connections
      while (true) {
        Socket client = sock.accept();
        PrintWriter pout = new PrintWriter(client.getOutputStream(), true);
        
        // write quote of the day to the socket
        pout.println("If two wrongs don't make a right, try three.\n\t\t\t\t\t- Laurence J. Peter");
        
        // close the socket and resume listening for connections
        client.close();
      }
    }catch(IOException ioe){
      System.err.println(ioe);
    }
  }
}

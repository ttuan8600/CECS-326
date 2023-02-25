/*
 * An echo client. The client enters data to the server, and the
 * server echoes the data back to the client.
 */

import java.net.*;
import java.io.*;

public class EchoClient
{
	public static void main(String[] args){
		try{
			Socket sock = new Socket("localhost", 6007);
			BufferedReader bin = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			PrintWriter pout = new PrintWriter(sock.getOutputStream(), true);
			// write the Date to the socket

			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String line;
			while ((line = in.readLine()) != null) {
				pout.println(line);
				System.out.println(bin.readLine());
			}

			// close the socket
			sock.close();
		}catch(IOException ioe){
			System.err.println(ioe);
		}
	}
}

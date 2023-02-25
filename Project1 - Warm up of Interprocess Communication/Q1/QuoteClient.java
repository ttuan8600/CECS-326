/*
 * Modified DateClient so that it requests a quote
 * from the quote server.
 */
 
import java.net.*;
import java.io.*;

public class QuoteClient
{
	public static void main(String[] args){
		try{
	  		Socket sock = new Socket("localhost", 6017);
			InputStream in = sock.getInputStream();	
			BufferedReader bin = new BufferedReader(new InputStreamReader(in));
			// read the date from the socket

			String line;
			while ((line = bin.readLine()) != null)
				System.out.println(line);

			// close the socket
			sock.close();
		}catch(IOException ioe){
			System.err.println(ioe);
		}
	}
}

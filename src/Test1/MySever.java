package Test1;

import java.net.*;
import java.util.Date;
import java.io.*;
public class MySever {

	public static void main(String[] args) {

		
		 try {
			ServerSocket serversocket = new ServerSocket(2010);
			
			while(true){
			Socket socket= serversocket.accept();
			
			InputStream is=socket.getInputStream();
			
			DataInputStream dis = new DataInputStream(is);
			
			String str = dis.readLine();
			
			System.out.println(str);
			
			OutputStream os = socket.getOutputStream();
			
			PrintWriter pw = new PrintWriter(os);
			
			pw.print("Message form server");
			   
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
		 
      
	}

}

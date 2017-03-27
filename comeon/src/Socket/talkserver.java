package Socket;

import java.io.*;
import java.net.*;

public class talkserver {
	public static void main(String args[]) {
		try {
			ServerSocket server = new ServerSocket(4700);
			Socket socket = server.accept();
			String line;
			BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter os = new PrintWriter(socket.getOutputStream());
			BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Client:" + is.readLine());
			line = sin.readLine();
			while (!line.equals("bye")) {
				os.println(line);
				os.flush();
				System.out.println("Server:" + line);
				System.out.println("Client:" + is.readLine());
				line = sin.readLine();
			}

			is.close();
			os.close();
			socket.close();
			server.close();
		} catch (Exception e) {
			System.out.println("Error" + e);
		}
	}
}

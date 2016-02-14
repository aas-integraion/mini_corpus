import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLServerSocketFactory;

public class Ssl06 extends Thread {

  public static void main(String[] args) throws Exception {
    ServerSocketFactory ssf = SSLServerSocketFactory.getDefault();
    ServerSocket ss = ssf.createServerSocket(9096);

    while (true) {
      new Ssl06(ss.accept()).start();
    }
  }
  private Socket sock;
  public Ssl06(Socket s) {
    sock = s;
  }
  public void run() {
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
      PrintWriter pw = new PrintWriter(sock.getOutputStream());

      String data = br.readLine();
      pw.println("What is she?");
      pw.close();
      sock.close();
    } catch (IOException ioe) {
      // Client disconnected
    }
  }
}











import java.io.*;
import java.net.*;
import javax.net.ssl.*;

public class Ssl01 {

  private static final String HOST = "localhost";

  private static final int PORT = 8080;

  public static void main(String[] args) throws Exception {
    SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();
    Socket s = sf.createSocket(HOST, PORT);
    OutputStream out = s.getOutputStream();
    out.write("\nConnection established.\n\n".getBytes());
    out.flush();
    int theCharacter = 0;
    theCharacter = System.in.read();
    while (theCharacter != '~') // The '~' is an escape character to exit
    {
      out.write(theCharacter);
      out.flush();
      theCharacter = System.in.read();
    }

    out.close();
    s.close();
  }
}
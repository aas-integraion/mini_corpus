import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.net.ssl.SSLSocketFactory;

public class Ssl03 {
  public static void main(String args[]) throws Exception {
    System.setProperty("javax.net.ssl.trustStore", "clienttrust");

    SSLSocketFactory ssf = (SSLSocketFactory) SSLSocketFactory.getDefault();
    Socket s = ssf.createSocket("127.0.0.1", 5432);
    BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
    String x = in.readLine();
    System.out.println(x);
    in.close();

  }
}
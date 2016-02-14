import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

import javax.net.ssl.SSLSocketFactory;

public class Ssl05 {
  public static void main(String args[]) throws Exception {
    FileOutputStream fouts = null;
    System.setProperty("javax.net.ssl.trustStore", "clienttrust");
    SSLSocketFactory ssf = (SSLSocketFactory) SSLSocketFactory.getDefault();
    Socket s = ssf.createSocket("127.0.0.1", 5432);

    OutputStream outs = s.getOutputStream();
    PrintStream out = new PrintStream(outs);
    InputStream ins = s.getInputStream();
    BufferedReader in = new BufferedReader(new InputStreamReader(ins));

    out.println(args[0]);
    fouts = new FileOutputStream("result.html");
//  fouts = new FileOutputStream("result.gif");
    int kk;
    while ((kk = ins.read()) != -1) {
      fouts.write(kk);
    }
    in.close();
    fouts.close();
  }
}
import java.net.*;
import java.io.*;

public class chatclient {
    public static void deleteString(String st) {

        for (int i = 0; i <= st.length(); i++)
            System.out.print('\b');

    }

    public static void main(String args[]) throws Exception {
        Socket sk = new Socket("127.0.0.1", 2000);
        BufferedReader sin = new BufferedReader(new InputStreamReader(sk.getInputStream()));
        PrintStream sout = new PrintStream(sk.getOutputStream());
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String s, message;
        message = "Awaiting for server's reply!";
        System.out.println("CHAT CLIENT Connected");
        System.out.println("Connected: 2000");
        System.out.println("Type End to exit the chat");
        while (true) {
            System.out.print("Client : ");
            s = stdin.readLine();
            sout.println(s);
            System.out.print(message);
            s = sin.readLine();
            deleteString(message);
            System.out.print("\nServer : " + s + "\n");
            if (s.equalsIgnoreCase("END"))
                break;
        }
        sk.close();
        sin.close();
        sout.close();
        stdin.close();
    }
}
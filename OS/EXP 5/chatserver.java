import java.net.*;
import java.io.*;

public class chatserver {
    public static void deleteString(String st) {

        for (int i = 0; i <= st.length(); i++)
            System.out.print('\b');

    }

    public static void main(String args[]) throws Exception {
        ServerSocket ss = new ServerSocket(2000);
        Socket sk = ss.accept();
        BufferedReader cin = new BufferedReader(new InputStreamReader(sk.getInputStream()));
        PrintStream cout = new PrintStream(sk.getOutputStream());
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String s, message;
        message = "Awaiting for clients's reply!";
        System.out.println("CHAT SERVER started ");
        System.out.println("Connected to Port : 2000");
        System.out.println("Type End to leave Chat");
        while (true) {
            System.out.print(message);
            s = cin.readLine();
            if (s.equalsIgnoreCase("END")) {
                cout.println("BYE");
                break;
            }
            deleteString(message);
            System.out.print("\nClient : " + s + "\n");
            System.out.print("Server : ");
            s = stdin.readLine();
            cout.println(s);
        }
        ss.close();
        sk.close();
        cin.close();
        cout.close();
        stdin.close();
    }
}
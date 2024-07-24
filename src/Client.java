import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable{
    Socket socket;
    Scanner in;
    PrintStream  out;
    ChatServer server;


    public Client(Socket socket, ChatServer server){
        this.socket = socket;
        this.server = server;
        //запуск потока
        new Thread(this).start();
    }

        void receive(String message) {
            out.println(message);
        }

    public void run() {
        try {
            //ввод и вывод
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            //средства ввода и вывода
            in = new Scanner(is);
            out = new PrintStream(os);

            //считывание и запись из сети
            out.println("Welcome to the chat, buddy!");
            String input = in.nextLine();
            while (!input.equals("Bye-bye")) {
                server.sendAll(input);
                input = in.nextLine();
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

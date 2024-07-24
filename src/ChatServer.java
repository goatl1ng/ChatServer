import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {
    ArrayList<Client> clients = new ArrayList<>();
    ServerSocket serverSocket;
    ChatServer() throws IOException {
        //создание серверного сокета на порт 1234
        serverSocket = new ServerSocket(1234);

    }
    void sendAll(String message) {
        for (Client client : clients) {
            client.receive(message);
        }
    }
    public void run() {
        while (true) {
            System.out.println("Waiting...");
            //ожидание клиента
            try {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected!");
                //создание клиента на своей стороне
                clients.add(new Client(socket, this));

            } catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) throws IOException {
        new ChatServer().run();
        }
    }
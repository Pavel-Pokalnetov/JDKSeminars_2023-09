package networkchat.server.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private TCPServer server;
    ChatServerCore chatServerCore;
    private volatile boolean isRunning = true;

    public ClientHandler(Socket socket,TCPServer server, ChatServerCore chatServerCore) {
        this.clientSocket = socket;
        this.chatServerCore = chatServerCore;
        this.server = server;
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        //основной поток соединения сервера с клиентом
        String message;
        try {
            while (isRunning) {
                while ((message = in.readLine()) != null) {//ждем получения данных
                    // Обработка полученных данных от клиента
                    chatServerCore.log("Получено сообщение от клиента: " + message);

                    //здесь какие-нибудь действия с сообщением

                    // рассылка сообщения всем через метод бродкаст сервера
                    server.broadcast(message);
                }
            }
        } catch (IOException e) {
            chatServerCore.log("#1");
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();//закрываем сокет
                // Удаление текущего клиента из списка
            } catch (IOException e) {
                chatServerCore.log("#2");
                e.printStackTrace();
            }
            server.removeClient(this);
            isRunning = false;
        }

    }

    public synchronized void sendMessage(String message) {
        out.println(message);
    }

    public void stop() {

    }
}
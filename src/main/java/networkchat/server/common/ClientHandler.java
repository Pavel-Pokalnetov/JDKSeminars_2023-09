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

    public ClientHandler(Socket socket, ChatServerCore chatServerCore) {
        this.clientSocket = socket;
        this.chatServerCore = chatServerCore;
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String message;

        try {
            while (isRunning) {
                while ((message = in.readLine()) != null) {
                    // Обработка полученных данных от клиента
                    chatServerCore.log("Получено сообщение от клиента: " + message);

                    // Для отправки данных в контроллер
                    server.broadcast(message);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
                // Удаление текущего клиента из списка
                server.removeClient(this);
                isRunning=false;
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public synchronized void sendMessage(String message) {
        out.println(message);
    }

    public void stop() {

    }
}
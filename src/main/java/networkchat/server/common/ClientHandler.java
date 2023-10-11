package networkchat.server.common;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private final TCPServer server;
    ChatServerCore chatServerCore;
    private volatile boolean isRunning = true;
    private final String name;

    public ClientHandler(Socket socket, String name, TCPServer server, ChatServerCore chatServerCore) {
        this.clientSocket = socket;
        this.name = name;
        this.chatServerCore = chatServerCore;
        this.server = server;
    }

    @Override
    public void run() {
        //основной поток соединения сервера с клиентом
        String message;
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String inputLine;
            while(isRunning){
            while ((inputLine = in.readLine()) != null) {
                chatServerCore.log(this.name+" >> " + inputLine);
                server.broadcast(inputLine);
            }}
        } catch (
                IOException e) {
            System.out.println("Клиент отключился: " + clientSocket.getInetAddress().getHostAddress());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            server.removeClient(this);
            isRunning = false;
        }

    }

    public synchronized void sendMessage(String message) {
        if (out != null) {
            out.println(message);
            out.flush();
        }
    }

    public synchronized void stop() {
        isRunning = false;
        while(!clientSocket.isClosed()){
            try {
                clientSocket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public String getName() {
        return this.name;
    }
}
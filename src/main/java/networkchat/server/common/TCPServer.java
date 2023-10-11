package networkchat.server.common;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TCPServer implements Runnable {
    private final int port;
    private final List<ClientHandler> clients = Collections.synchronizedList(new ArrayList<>());
    private final ChatServerCore chatServerCore;
    //    ServerSocket serverSocket;
    private boolean isRunning = true;
    private boolean isStoped = true;
    private int clientCounter = 0;
    private ServerSocket srvSocket;


    public TCPServer(int port, ChatServerCore chatServerCore) {
        this.chatServerCore = chatServerCore;
        this.port = port;
    }

    @Override
    public void run() {
        //основной метод потока сервера
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            this.srvSocket = serverSocket;
            isStoped = false;
            chatServerCore.log("Сервер запущен на порту " + port);
            serverSocket.setSoTimeout(3000);

            while (isRunning && !isStoped) {
                try {
                    serverSocket.setSoTimeout(12000);
                    Socket clientSocket = serverSocket.accept();
                    chatServerCore.log("Новое соединение установлено: " + clientSocket.getInetAddress().getHostAddress() + ":" + clientSocket.getPort());
                    //создаем клиента и добавляем в список
                    ClientHandler clientHandler = new ClientHandler(clientSocket, "cl#" + clientCounter++, this, chatServerCore);
                    clients.add(clientHandler);
                    //стартуем клиента в отдельном потоке
                    Thread clientThread = new Thread(clientHandler);
                    clientThread.start();
                    clientHandler.sendMessage("connection " + "cl#" + clientCounter + ":OK\n");
                } catch (SocketTimeoutException e) {
                    //здесь можно чего-нибудь поделать, но мы просто пропустим этот момент
                } catch (SocketException e){
                   break;
                }
            }

        } catch (IOException e) {
            System.out.println("@!");
            e.printStackTrace();
        } finally {
            isRunning = false;
            clientsStop();
            chatServerCore.log("Сервер остановлен");
        }
    }

    private void clientsStop() {
        //TODO не отключает некоторых клиентов
        if (clients.isEmpty()) return;
        for (ClientHandler client : clients) {
            chatServerCore.log("Отключение клиента "+client.getName());
            client.stop();
        }
    }

    public void broadcast(String message) {
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
    }

    public void removeClient(ClientHandler client) {
        chatServerCore.log("Остановка и удаление клиента:  " + clients.indexOf(client));
        clients.remove(client);
        chatServerCore.log("Осталось клиентов: " + clients.size());
    }

    public void stop() {
        if (!isRunning) return;
        if (!isStoped) {
            try {
                srvSocket.close();
            } catch (IOException ignored) {
            }
            isStoped = true;//здесь мы сообщаем потоку что хотим остановиться
            while (isRunning) { //и ждем пока сервер сам остановится
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {
                }
            }
        }
    }

}
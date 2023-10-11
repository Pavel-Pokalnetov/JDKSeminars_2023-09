package networkchat.server.common;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Vector;

public class TCPServer implements Runnable {
    private int port;
    private Vector<ClientHandler> clients = new Vector<>();
    private ChatServerCore chatServerCore;
    ServerSocket serverSocket;
    private boolean isRunning = true;
    private boolean isStoped = true;


    /**
     * @param chatServerCore
     */
    public TCPServer(int port, ChatServerCore chatServerCore) {

        this.chatServerCore = chatServerCore;
        this.port = port;
    }
@Override
    public void run() {
        //основной метод потока сервера
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            isStoped=false;
            chatServerCore.log("Сервер запущен на порту " + port);
            serverSocket.setSoTimeout(3000);
            while (isRunning && !isStoped) {
                try {
                    serverSocket.setSoTimeout(3000);
                    Socket clientSocket = serverSocket.accept();
                    chatServerCore.log("Новое соединение установлено: " + clientSocket.getInetAddress() +":"+clientSocket.getPort());
                    //создаем клиента и добавляем в список
                    ClientHandler clientHandler = new ClientHandler(clientSocket,this,chatServerCore);
                    clients.add(clientHandler);
                    //стартуем клиента в отдельном потоке
                    Thread clientThread = new Thread(clientHandler);
                    clientThread.start();
                } catch (SocketTimeoutException e) {
                    //здесь можно чего-нибудь поделать, но мы просто пропустим этот момент
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            isRunning = false;
            clientsStop();
            chatServerCore.log("Сервер остановлен");
        }
    }

    private void clientsStop() {
        for (ClientHandler client: clients                 ) {
            client.stop();
        }
        clients.clear();
    }

    public void broadcast(String message) {
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
    }

    public void removeClient(ClientHandler client) {
        client.stop();
        clients.remove(client);
    }

    public void stop(){
        if (!isRunning)return;
        if(!isStoped){
            isStoped = true;//здесь мы сообщаем потоку что хотим остановиться
            while(isRunning){ //и ждем пока сервер сам остановится
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
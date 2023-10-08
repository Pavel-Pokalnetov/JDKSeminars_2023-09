package networkchat.client.common;

import networkchat.client.gui.ClientWindow;
import networkchat.share.Log2File;
import networkchat.share.Logger;

public class Controller implements ChatClientCore {

    private ClientWindow window;
    private String serverAddress;
    private int serverPort;
    private String login;
    private String password;

    public Controller() {
        Logger logger = new Log2File("cl");
        this.window = new ClientWindow(this, logger);
        window.setOfflineTheme();
    }

    @Override
    public void sendMessage(String msg) {
        //отправка сообщения
        window.outText(msg);
    }

    @Override
    public void connect(ConnectData connectInfo) {
        serverAddress = connectInfo.address();
        serverPort = connectInfo.port();
        login = connectInfo.login();
        password = connectInfo.password();

        // блок соединения с сервером
        window.outText("Соединяемся с " + serverAddress + ":" + serverPort);

        // авторизуемся
        window.outText("Попытка авторизации: " + login);

        //соединились
        window.setOnlineTheme();
    }

    @Override
    public void disconnect() {
        //отсоединение от сервера
        window.outText("переход в оффлайн");
        window.setOfflineTheme();
    }
}

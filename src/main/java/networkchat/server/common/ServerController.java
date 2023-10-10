package networkchat.server.common;

import networkchat.server.gui.ServerWindow;
import networkchat.share.Log2File;
import networkchat.share.Logger;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

import static javax.swing.JOptionPane.showMessageDialog;

public class ServerController implements ChatServerCore {
    private final Integer port;
    private Logger logger;
    private final ServerWindow window;
    private TCPServer tcpServer;
    Thread threadTCPServer;

    public ServerController() {
        logger = new Log2File("srv");
        ServerConfig serverConfig = getServerConfig();
        this.port = serverConfig.getPort();
        this.window = new ServerWindow(this, logger);
        logger.put("Application start");
        window.setOfflineTheme();
        if (serverConfig.getAutostart()) {
            // здесь код для автозапуска сервера
            serverStart();
        }

    }

    @Override
    public void serverStart() {
        //здесь код запуска потоков сервера
        tcpServer = new TCPServer(port,this);
        threadTCPServer = new Thread(tcpServer);
        threadTCPServer.start();
        System.out.println("1");
        window.outText(String.format("Server started in port: %s",port));
        window.setOnlineTheme();
    }

    @Override
    public void serverStop() {
        //здесь код остановки потоков сервера
        tcpServer.stop();
        window.outText(threadTCPServer.toString());
        window.outText("Server stopped");
        window.setOfflineTheme();
    }

    @Override
    public void serverOff() {
        //здесь код завершения потоков сервера
        logger.put("Application exit");
        System.exit(0);
    }

    @Override
    public void log(String s) {

    }

    @NotNull
    private ServerConfig getServerConfig() {
        //получение настроек сервера из конфигурационного файла
        ServerConfig serverConfig = new ServerConfig();
        try {
            serverConfig.load("./config/server.yml");
        } catch (Exception e) {
            showMessageDialog(null, "Ошибка при загрузке конфига сервера.", "Ошибка", JOptionPane.ERROR_MESSAGE);
            logger.put("Ошибка при получении конфига.");
            System.exit(1);
        }
        return serverConfig;
    }

    public static void main(String[] args) {
        ChatServerCore chatServerCore = new ServerController();

    }
}

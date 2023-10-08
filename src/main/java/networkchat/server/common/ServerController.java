package networkchat.server.common;

import networkchat.server.gui.ServerWindow;
import networkchat.share.Log2File;
import networkchat.share.Logger;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

import static javax.swing.JOptionPane.showMessageDialog;

public class ServerController implements ChatServerCore {
    private final String ip;
    private final Integer port;
    private Logger logger;
    private final ServerWindow window;

    public ServerController() {
        logger = new Log2File("srv");
        ServerConfig serverConfig = getServerConfig();
        this.ip = serverConfig.getIp();
        this.port = serverConfig.getPort();
        this.window = new ServerWindow(this, logger);
        logger.put("Application start");
        if(serverConfig.getAutostart()){
            // здесь код для автозапуска сервера
            serverStart();
            window.hideOnDesktop();
        }else{
            window.setOfflineTheme();

        }

    }

    @Override
    public void serverStart() {
        //здесь код запуска потоков сервера
        window.outText("Server started");
        window.outText(String.format("Socket: %s:%d", ip, port));
        window.setOnlineTheme();
    }

    @Override
    public void serverStop() {
        //здесь код остановки потоков сервера
        window.outText("Server stopped");
        window.setOfflineTheme();
    }

    @Override
    public void serverOff() {
        //здесь код завершения потоков сервера
        logger.put("Application exit");
        System.exit(0);
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
}

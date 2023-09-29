package networkchat.server.common;

import networkchat.server.gui.ServerWindow;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

import static javax.swing.JOptionPane.showMessageDialog;

public class ServerController {
    private final String ip;
    private final Integer port;
    ServerWindow window = new ServerWindow(this);

    public ServerController() {
        ServerConfig serverConfig = getServerConfig();
        this.ip = serverConfig.getIp();
        this.port = serverConfig.getPort();
    }


    public void btStartPressed(){
        //здесь код запуска потоков сервера
        window.out("Server started");
        window.out(String.format("Socket: %s:%d",ip,port));
    }
    public void btStopPressed(){
        //здесь код остановки потоков сервера
        window.out("Server stopped");
    }

    public void btExitPressed(){
        System.exit(0);
    }

    @NotNull
    private static ServerConfig getServerConfig() {
        ServerConfig serverConfig = new ServerConfig();
        try {
            serverConfig.load("./config/server.yml");
        }catch (Exception e) {
            showMessageDialog(null, "Ошибка при загрузке конфига сервера.", "Ошибка", JOptionPane.ERROR_MESSAGE);
            System.out.println("Ошибка при получении конфига.");
            System.exit(1);
        }
        return serverConfig;
    }
}

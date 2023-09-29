package networkchat.client.gui;

import networkchat.client.common.ClientController;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class ClientWindow extends JFrame {
    private final int WINDOW_HEIGHT = 550;
    private final int WINDOW_WIDTH = 480;

    ClientController controller;

    private final JTextField inputIp = new JTextField("ip-address", 10);
    private JTextField inputPort = new JTextField("port");
    private final JTextField inputLogin = new JTextField("login");
    private final JPasswordField inputPassword = new JPasswordField("password");

    private final JTextArea log = new JTextArea("текст лога", 26, 30);
    private final JTextArea users = new JTextArea("пользователи", 26, 10);
    private final JTextArea message = new JTextArea("текст сообщения", 3, 30);

    private final JButton btConnect = new JButton("Connect");
    private final JButton btSend = new JButton("Send");


    public ClientWindow(ClientController controller) {
        super("Chat client");
        this.controller = controller;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - WINDOW_WIDTH) / 2;
        int y = (screenSize.height - WINDOW_HEIGHT) / 2;
        setLocation(x, y);
        setResizable(false);

        // сетап элементов окна
        inputPassword.setEchoChar('*');
        JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JPanel header = new JPanel(new GridLayout(2, 3));
        header.setBackground(Color.lightGray);
        JPanel center = new JPanel(new FlowLayout());
        JPanel footer = new JPanel(new FlowLayout());

        header.add(inputIp);
        header.add(inputPort);
        header.add(Box.createHorizontalStrut(50));
        header.add(inputLogin);
        header.add(inputPassword);
        header.add(btConnect);


        mainPanel.add(header);
//        mainPanel.add(center);
//        mainPanel.add(footer);
        add(mainPanel);
        setVisible(true);
    }




}

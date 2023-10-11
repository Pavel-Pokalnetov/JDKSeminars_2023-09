package networkchat.server.gui;

import networkchat.server.common.ChatServerCore;
import networkchat.server.common.ServerController;
import networkchat.share.Logger;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class ServerWindow extends JFrame implements ServerGUI {
    private final int WINDOW_HEIGHT = 417;
    private final int WINDOW_WIDTH = 280;

    private final Logger logger;

    JButton btStart = new JButton("Start");
    JButton btStop = new JButton("Stop");
    JButton btExit = new JButton("Exit");
    JTextArea log = new JTextArea("", 21, 23);
    ChatServerCore controller;

    public ServerWindow(ServerController controller, Logger logger) throws HeadlessException {
        super("Chat server");
        this.logger = logger;
        this.controller = controller;
        Image iconImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/icon-server.png"))).getImage();
        setIconImage(iconImage);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setAlwaysOnTop(true);

        //ставим окно по центру
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - WINDOW_WIDTH) / 2;
        int y = (screenSize.height - WINDOW_HEIGHT) / 2;

        // Устанавливаем координаты окна
        setLocation(x, y);
        setResizable(false);

        // создание иконки системного трея
        if (SystemTray.isSupported()) {
            SystemTray tray = SystemTray.getSystemTray();
            // Создаем иконку и меню в трее
            TrayIcon trayIcon = getTrayIcon(iconImage);
            try {
                // Добавляем иконку в трей
                tray.add(trayIcon);
            } catch (AWTException e) {
                System.err.println("Не удалось добавить иконку в трей.");
            }
            addWindowStateListener(new WindowStateListener() {
                public void windowStateChanged(WindowEvent e) {
                    if ((e.getNewState() & Frame.ICONIFIED) == Frame.ICONIFIED) {
                        setVisible(false);
                    }
                }
            });
        } else {
            System.err.println("Трей не поддерживается на этой платформе.");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        // панель для кнопок окна
        JPanel pnlFooter = new JPanel();
        pnlFooter.add(btStart);
        pnlFooter.add(btStop);
        pnlFooter.add(btExit);
        // панель для текстового поля
        JPanel pnlText = new JPanel();
        JScrollPane scrollLog = new JScrollPane(log);
        scrollLog.setAutoscrolls(true);
        pnlText.add(scrollLog);

        add(pnlText, BorderLayout.NORTH);
        add(pnlFooter, BorderLayout.SOUTH);

        btStart.addActionListener(e -> controller.serverStart());
        btStop.addActionListener(e -> controller.serverStop());
        btExit.addActionListener(e -> controller.serverOff());

        setVisible(true);
    }

    private TrayIcon getTrayIcon(Image iconImage) {
        PopupMenu popup = getPopupMenu();
        // Создаем объект TrayIcon
        TrayIcon trayIcon = new TrayIcon(iconImage, "Chat server", popup);
        trayIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 2) {
                    setVisible(true);
                    setState(Frame.NORMAL);
                    toFront();
                }
            }
        });
        return trayIcon;
    }



    private PopupMenu getPopupMenu() {
        // всплывающее меню иконки в трее
        PopupMenu popup = new PopupMenu();
        MenuItem showItem = new MenuItem("Показать окно");
        showItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Отображение окна при выборе опции "Показать окно"
                setVisible(true);
            }
        });
        popup.add(showItem);

        MenuItem exitItem = new MenuItem("Выход");
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Выход из программы
                System.exit(0);
            }
        });
        popup.add(exitItem);
        return popup;
    }

    @Override
    public void outText(String text) {
        log.append(text + "\n");
        logger.put(text);
    }

    @Override
    public void setOfflineTheme() {
        btExit.setEnabled(true);
        btStart.setEnabled(true);
        btStop.setEnabled(false);
    }

    @Override
    public void showOnDesktop() {
        setExtendedState(JFrame.ICONIFIED);
    }

    @Override
    public void hideOnDesktop() {
        setExtendedState(JFrame.ICONIFIED);
    }

    @Override
    public void setOnlineTheme() {
        btExit.setEnabled(false);
        btStart.setEnabled(false);
        btStop.setEnabled(true);
    }
}

package networkchat.share;

/**
 * Общие свойства (методы) графических окон
 */
public interface GUI
{
    /**
     * Вы вод текста в поле лога GUI
     * @param text текстовая строка
     */
    void outText(String text);

    /**
     * Устанавливает тему окна режима Online
     */
    void setOnlineTheme();

    /**
     * Устанавливавет тему окна режима Offline
     */
    void setOfflineTheme();

    /**
     * Развернуть (отобразить окно)
     */
    void showOnDesktop();

    /**
     * Свернуть окно в кнопку на панели
     */
    void hideOnDesktop();
}

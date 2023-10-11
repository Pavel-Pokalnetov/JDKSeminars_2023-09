package networkchat.share;
public interface Logger {

    /** Запись текстовых данных в лог
     * @param message -текст
     */
    void put(String message);

    /**
     * Получение текста всего лога
     * @return - текст
     */
    String getLogContents();
}

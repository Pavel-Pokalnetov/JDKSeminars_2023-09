package networkchat.server.common;

public interface ChatServerCore {
    void serverStop();
    void serverStart();
    void serverOff();

    void log(String s);
}

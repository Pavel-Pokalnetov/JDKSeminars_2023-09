package networkchat.client.common;

public interface ChatClientCore {
    void sendMessage(String message);
    void connect(ConnectData connectInfo);
    void disconnect();
}

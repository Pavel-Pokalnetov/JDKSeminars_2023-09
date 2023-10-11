package networkchat.client.gui;

public class InputException extends Exception {
    private final int errorCode;

    public InputException(int errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
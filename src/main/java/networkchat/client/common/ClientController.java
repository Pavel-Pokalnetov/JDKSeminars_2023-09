package networkchat.client.common;

import networkchat.client.gui.ClientWindow;

public class ClientController {

    ClientWindow window;

    public ClientController() {
        this.window = new ClientWindow(this);
    }
}

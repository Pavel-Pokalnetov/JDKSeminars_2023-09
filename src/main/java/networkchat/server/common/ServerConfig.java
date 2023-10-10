package networkchat.server.common;import org.yaml.snakeyaml.Yaml;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

public class ServerConfig {
    private int port;
    private boolean autostart = false;

    public void load(String configFile) throws RuntimeException {
        Yaml yaml = new Yaml();
        try (FileInputStream input = new FileInputStream(configFile)) {
            Map<String, Object> data = (Map<String, Object>) yaml.load(input);

            port = (Integer) data.get("port");
            autostart = (Boolean) data.get("autostart");
//            System.out.println(autostart);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public int getPort() {
        return port;
    }

    public boolean getAutostart(){return autostart;}
}
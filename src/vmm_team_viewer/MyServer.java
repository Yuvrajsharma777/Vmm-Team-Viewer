package vmm_team_viewer;

import com.vmm.JHTTPServer;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.Path;
import java.util.Properties;
import javax.imageio.ImageIO;

public class MyServer extends JHTTPServer {

    InetAddress address = InetAddress.getLocalHost();
    String IP = address.getHostAddress();
    File f;
    String path;

    public MyServer(int portno) throws IOException {
        super(portno);
    }

    @Override
    public Response serve(String uri, String method, Properties header, Properties parms, Properties files) {
        Response res = null;
        System.out.println(uri);
        if (uri.equals("/checkAddress")) {
            System.out.println("Request received");
            String ip = parms.getProperty("ip");
            if (ip.equals(IP)) {
                res = new Response(HTTP_OK, "text/plain", "success");

            } else {
                res = new Response(HTTP_OK, "text/plain", "failed");
            }
        } else if (uri.equals("/getScreen")) {
            try {
                Robot robot = new Robot();

                BufferedImage screenShot = robot
                        .createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
                f = new File("src/uploads/one.jpg");
                ImageIO.write(screenShot, "JPG", f);
                path = f.getPath();
                res = new Response(HTTP_OK, "text/plain", path);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return res;
    }

}

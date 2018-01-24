import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static java.awt.Desktop.getDesktop;

/**
 * Created by Ibbitasam Asim on 6/14/2016.
 */
public class Sample2Controller {
    public  void minedesktop(){
    Desktop desktop = getDesktop();
        try {
            desktop.mail(new URI("mailto:bilalhaider.ali@hotmail.com"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

}

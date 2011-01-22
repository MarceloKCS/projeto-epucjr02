/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package epucjr.engyos.applet;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Rogerio
 */
public class ImagePanel extends JPanel{

    private BufferedImage image;

    public ImagePanel() {
        this.setSize(200, 200);
       try {
          image = ImageIO.read(new File("E:/Downloads/biometria5.png"));
       } catch (IOException ex) {
            ex.printStackTrace();
       }
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, 200, 200, null); // see javadoc for more info on the parameters

        g.setFont(new Font("Arial", Font.BOLD, 12));
        g.drawString("Por Favor Insira a Digital", 0, 210);

    }

}

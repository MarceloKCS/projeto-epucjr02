/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package epucjr.engyos.applet.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Rogerio
 */
public class ImagePanel extends JPanel{

    private BufferedImage image;

    public ImagePanel(URL urlBase) {
        this.setSize(100, 150);
        try {
            //para pegar uma imagem dentro da aplicacao, pode ser ponto ou barra (n sei ao certo)
            //image = ImageIO.read(getClass().getClassLoader().getResource("epucjr/engyos/imagem/biometria5.png"));
            // File relativePath = new File("biometria.png");
            URL url = new URL(urlBase, "images/biometria.png");
            image = ImageIO.read(url);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, 100, 150, null); // see javadoc for more info on the parameters

//        g.setFont(new Font("Arial", Font.BOLD, 12));
//        g.drawString("Por Favor Insira a Digital", 10, 25);

    }

}

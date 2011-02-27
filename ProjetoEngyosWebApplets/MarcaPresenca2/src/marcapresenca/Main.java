/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package marcapresenca;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

/**
 *
 * @author Diego
 */
public class Main extends Applet {

    /**
     * Initialization method that will be called after the applet is loaded
     * into the browser.
     */
    int idReuniao;
    int acao;
    ServletDataManager sdm;
    static int erro = 1;
    Button iniciar;

    String mensagem;

    public void init() {
        bi = new BufferedImage(getSize().width, getSize().height, BufferedImage.TYPE_INT_BGR);
        mensagem = "";

        acao = 0;
        setLayout(null);

        try {
            System.out.println(getParameter("idReuniao"));
            idReuniao = Integer.parseInt(getParameter("idReuniao"));
            //TODO localização do servlet
            URL url = new URL("http://localhost:8080/ProjetoEngyos/AppletController");
            sdm = new ServletDataManager(url);
        } catch(NumberFormatException e) {
            e.printStackTrace();
            cor = Color.red;
            idReuniao = -1;
            acao = 1;
            mensagem = "Erro no servidor!!!";
        } catch(MalformedURLException e) {
            e.printStackTrace();
            cor = Color.red;
            idReuniao = -1;
            acao = 1;
            mensagem = "Erro no servidor!!!";
        }

        iniciar = new Button("Iniciar");
        iniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                acao = 0;
                iniciarLoop();
            }
        });
        iniciar.setEnabled(acao <= 0);



        add(iniciar);
        iniciar.setBounds(32, 100, 128, 32);
    }

    public void iniciarLoop() {
        iniciar.setEnabled(false);
        while(acao <= 0) {
            marcarPresencaDigital();
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                continue;
            }
        }
        iniciar.setEnabled(acao <= 0);
    }

    @Override
    public void start() {
        super.start();
    }

    Color cor;
    BufferedImage bi;
    public void paint(Graphics g) {
        Graphics2D g2d = bi.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                            RenderingHints.VALUE_TEXT_ANTIALIAS_ON );

        g2d.setPaint(new GradientPaint(new Point(0,0), Color.white, new Point(0, this.getSize().height), new Color(150, 210, 230)));
        g2d.fillRect(0, 0, this.getSize().width, this.getSize().height);

        g2d.setFont(new Font("Arial", Font.BOLD, 18));

        g2d.setColor(new Color(0, 64, 0));

        g2d.setColor(cor);
        g2d.drawString(mensagem, 16,64);

        g.drawImage(bi, 0, 0, null);
    }

    public void marcarPresencaDigital() {
        if (acao == 0) {
            String nome = Digital.getNome(idReuniao, sdm);
            if (nome == null) {
                cor = Color.red;
                acao = 1;
                mensagem = "Erro no servidor!!!";
            } else if (nome.length() == 0) {
                cor = Color.red;
                mensagem = "Digital n"+0x00e3+"o identificada !!!";
            } else if (nome.equals("-2")) {
                cor = Color.red;
                mensagem = "Erro no equipamento !!!";
                acao = 2;
            } else if (nome.equals("-1")) {
                mensagem = "Cancelou dispositivo !!!";
                acao = -1;
            } else {
                cor = new Color(0, 64, 0);
                mensagem = "Bem Vindo "+nome+" !!!";
                acao = -1;//TODO teste
            }
        }
        repaint();
    }
}

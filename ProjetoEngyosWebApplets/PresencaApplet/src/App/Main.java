/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package App;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.Point;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;
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

    String resposta;
    TextField senha;
    Button botaoSenha;
    Button botaoDigital;

    public void init() {
        bi = new BufferedImage(getSize().width, getSize().height, BufferedImage.TYPE_INT_BGR);
        resposta = "";
        acao = 0;
        setLayout(null);

        senha = new TextField();
        senha.setBounds(50+24, 16, getSize().width-90, 24);
        senha.setEchoChar('*');
        add(senha);
        senha.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                marcarPresenca();
            }
        });

        botaoSenha = new Button("Marcar Presen"+(char)0x00e7+"a");
        botaoSenha.setBounds(getWidth()/2 + 8, 64, getWidth()/2 - 24, 24);
        botaoSenha.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                marcarPresenca();
            }
        });
        add(botaoSenha);

        botaoDigital = new Button("Digital");
        botaoDigital.setBounds(16, 64, getWidth()/2 - 24, 24);
        botaoDigital.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                marcarPresencaDigital();
            }
        });
        add(botaoDigital);

        try {
//            System.out.println(getParameterInfo()[0][0]);
            System.out.println(getParameter("idReuniao"));
            idReuniao = Integer.parseInt(getParameter("idReuniao"));
            //TODO
//            URL url = new URL(getCodeBase(), "/ProjetoBiometria/ServletControler");
            URL url = new URL("http://localhost:8080/ProjetoBiometria/ServletControler");
            sdm = new ServletDataManager(url);
        } catch(NumberFormatException e) {
            cor = Color.red;
            idReuniao = -1;
            acao = 1;
            resposta = "Erro no servidor!!!";
        } catch(MalformedURLException e) {
            cor = Color.red;
            idReuniao = -1;
            acao = 1;
            resposta = "Erro no servidor!!!";
        }

        // TODO start asynchronous download of heavy resources
    }

    Color cor;
    BufferedImage bi;
    public void paint(Graphics g) {
        Graphics2D g2d = bi.createGraphics();

        g2d.setPaint(new GradientPaint(new Point(0,0), Color.white, new Point(0, this.getSize().height), new Color(150, 210, 230)));
        g2d.fillRect(0, 0, this.getSize().width, this.getSize().height);

        g2d.setFont(new Font("Arial", Font.BOLD, 12));

        g2d.setColor(Color.black);
        g2d.drawString("Senha:", 16, 32);

        g2d.setColor(cor);
        g2d.drawString(resposta, 16,120);

        g.drawImage(bi, 0, 0, null);
    }

    public void marcarPresenca() {
        if (acao == 0) {
            String nome = sdm.marcarPresenca(idReuniao, senha.getText());
            if (nome == null) {
                cor = Color.red;
                acao = 1;
                resposta = "Erro no servidor!!!";
            } else if (nome.length() == 0) {
                cor = Color.red;
                resposta = "Senha Incorreta !!!";
            } else {
                cor = new Color(0, 64, 0);
                resposta = "Bem Vindo "+nome+" !!!";
            }
        }
        senha.setText("");
        repaint();
    }

    public void marcarPresencaDigital() {
        if (acao == 0) {
            String nome = Digital.getNome(idReuniao, sdm);
            if (nome == null) {
                cor = Color.red;
                acao = 1;
                resposta = "Erro no servidor!!!";
            } else if (nome.length() == 0) {
                cor = Color.red;
                resposta = "Digital n"+0x00e3+"o identificada !!!";
            } else if (nome.equals("-2")) {
                cor = Color.red;
                resposta = "Erro no equipamento !!!";
            } else if (nome.equals("-1")) {
                resposta = "";
            }
        }
        senha.setText("");
        repaint();
    }
}

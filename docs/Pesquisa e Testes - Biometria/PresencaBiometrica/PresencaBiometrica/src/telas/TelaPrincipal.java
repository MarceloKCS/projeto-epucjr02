/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package telas;

import telas.layout.LayoutListaReunioes;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LinearGradientPaint;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import presencabiometrica.Controler;
import telas.layout.LayoutPresenca;

/**
 *
 * @author Diego
 */
public class TelaPrincipal extends JFrame {

    Controler controler;

    JPanel principalContainer;
    
    JScrollPane containerListaReunioes;
    JList listaReunioes;
    DefaultListModel listaReunioesModel;
    JLabel titulo;
    JButton iniciarReuniao;

    JLabel reuniaoSelecionada;
    JLabel senhaLabel;
    JPasswordField senha;
    JButton marcarPresenca;
    JButton digital;
    
    JFrame isto;

    public TelaPrincipal(Controler contr, String reuniao) {
        super("Projeto Engyos");
        isto = this;
        this.controler = contr;

        titulo = new JLabel("Titulo");
        titulo.setName("titulo");

        principalContainer = new JPanel(true) {

            @Override
            public void paint(Graphics g) {
                Graphics2D g2d = (Graphics2D)g;

                g2d.setPaint(new GradientPaint(new Point(0,0), Color.white, new Point(0, this.getSize().height), new Color(150, 210, 230)));
                Insets insets = this.getInsets();
                g2d.fillRect(insets.left, insets.top, this.getSize().width - insets.right - insets.left, this.getSize().height - insets.bottom - insets.top);

                super.paint(g);
            }

        };
        principalContainer.setOpaque(false);
//
        iniciarReuniao = new JButton("Iniciar Reuni"+(char)0x00e3+"o");
        iniciarReuniao.setName("iniciarReuniao");
        iniciarReuniao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (listaReunioes.getSelectedIndex() == -1) return;
                boolean ok = controler.selecionarReuniao(listaReunioes.getSelectedValue().toString());
                if (ok) {
                    reuniaoSelecionada.setText(listaReunioes.getSelectedValue().toString());
                    principalContainer.setLayout(new LayoutPresenca());
                } else {
                    JOptionPane.showMessageDialog(isto, "Erro na conex"+(char)0x00e3+"o com servidor.", "Erro", JOptionPane.ERROR_MESSAGE);
                    atualizarPrincipalContainer(null, null);
                }
            }
        });

        listaReunioesModel = new DefaultListModel();
        listaReunioes = new JList(listaReunioesModel);
        containerListaReunioes = new JScrollPane(listaReunioes, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        containerListaReunioes.setName("containerListaReunioes");
//
        reuniaoSelecionada = new JLabel("", JLabel.CENTER);
        reuniaoSelecionada.setFont(new Font("Arial", Font.BOLD, 42));
        reuniaoSelecionada.setName("reuniaoSelecionada");

        senhaLabel = new JLabel("Senha:");
        senhaLabel.setFont(new Font("Arial", Font.BOLD, 16));
        senhaLabel.setName("senhaLabel");

        senha = new JPasswordField();
        senha.setName("senha");
        senha.setFont(new Font("Arial", Font.BOLD, 16));
        senha.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 marcarPresenca();
            }
        });

        marcarPresenca = new JButton("Marcar Presen"+(char)0x00e7+'a');
        marcarPresenca.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                marcarPresenca();
            }
        });
        marcarPresenca.setName("marcarPresenca");

        digital = new JButton("Digital");
        digital.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                verificarDigital();
            }
        });
        digital.setName("digital");
//
        principalContainer.setLayout(new LayoutListaReunioes());
        principalContainer.add(titulo);
        principalContainer.add(containerListaReunioes);
        principalContainer.add(iniciarReuniao);
        principalContainer.add(reuniaoSelecionada);
        principalContainer.add(senhaLabel);
        principalContainer.add(senha);
        principalContainer.add(marcarPresenca);
        principalContainer.add(digital);

        getContentPane().add(principalContainer);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(500, 400));
        setLocationRelativeTo(null);
        if (reuniao != null) {
            boolean ok = controler.selecionarReuniao(reuniao);
            if (ok) {
                reuniaoSelecionada.setText(reuniao);
                principalContainer.setLayout(new LayoutPresenca());
            } else atualizarPrincipalContainer(null,null);
        } else atualizarPrincipalContainer(null,null);
        setVisible(true);
    }

    public void marcarPresenca() {
        String nome = controler.marcarPresenca(new String(senha.getPassword()));
        if (nome == null) JOptionPane.showMessageDialog(isto, "Erro na conex"+(char)0x00e3+"o com servidor.", "Erro", JOptionPane.ERROR_MESSAGE);
        else if (nome.length() == 0) JOptionPane.showMessageDialog(isto, "Senha incorreta!", "Erro", JOptionPane.WARNING_MESSAGE);
        else JOptionPane.showMessageDialog(isto, "Bem Vindo "+nome+" !!!", "Bem Vindo !!!", JOptionPane.PLAIN_MESSAGE);
        senha.setText("");
    }

    public void verificarDigital() {
        String nome = controler.marcarPresencaDigital();
        if(nome.equals("-1")) return;
        else if(nome.equals("-2")) JOptionPane.showMessageDialog(isto, "Erro no dispositivo!\nReconecte o aparelho para corrigir o problema.\nCaso o problema continue, reinstale os drivers.", "Erro", JOptionPane.ERROR_MESSAGE);
        else if(nome == null) JOptionPane.showMessageDialog(isto, "Erro na conex" + (char) 0x00e3 + "o com servidor.", "Erro", JOptionPane.ERROR_MESSAGE);
        else if (nome.length() == 0) JOptionPane.showMessageDialog(isto, "Digital n"+(char)0x00e3+"o reconhecida!", "Erro", JOptionPane.WARNING_MESSAGE);
        else JOptionPane.showMessageDialog(isto, "Bem Vindo "+nome+" !!!", "Bem Vindo !!!", JOptionPane.PLAIN_MESSAGE);
        senha.setText("");
    }
    
    public void atualizarPrincipalContainer(String data0, String data1) {
        if (data0 == null || data1 == null) {
            GregorianCalendar calendar = new GregorianCalendar();
            int d = calendar.get(GregorianCalendar.DAY_OF_MONTH);
            int m = calendar.get(GregorianCalendar.MONTH) + 1;
            int a = calendar.get(GregorianCalendar.YEAR);

            if (d < 10) {
                data0 = "0";
                data1 = "0";
            }
            data0 = "" + d + '/';
            data1 = "" + d + '/';

            if (m < 10) {
                data0 = data0 + '0' + m + '/' + a;
                data1 = data1 + '0' + (m+1) + '/' + a;
            } else if (m < 12) {
                data0 = data0 + m + '/' + a;
                data1 = data1 + (m+1) + '/' + a;
            } else {
                data0 = data0 + m + '/' + a;
                data1 = data1 + "01/" + (a + 1);
            }

        }

        listaReunioesModel.removeAllElements();
        String[] list = controler.getReunioes(data1, data0);
        if(list == null) JOptionPane.showMessageDialog(isto, "Erro na conex" + (char) 0x00e3 + "o com servidor.", "Erro", JOptionPane.ERROR_MESSAGE);
        else for(String e: list) {
            listaReunioesModel.addElement(e);
        }
        listaReunioes.updateUI();
    }
    
}

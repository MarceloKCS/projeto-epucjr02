package com.epucjr.engyos.teste;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.epucjr.engyos.tecnologia.utilitarios.DataSet;
import com.epucjr.engyos.tecnologia.utilitarios.GeradorPDF;
import com.epucjr.engyos.tecnologia.utilitarios.Grafico;

public class TesteGrafico {

        private DataSet dataset0, dataset1;
        private JLabel label0, label1;
        private JFrame tela0, tela1;
        
        public TesteGrafico() {
                super();
                dataset0 = new DataSet(0, DataSet.DATASET_MES);
                dataset1 = new DataSet(1, DataSet.DATASET_MES);
        }
        
        /**
         * Janela teste
         */
        public void initComponentsTest() {
                //Gera os graficos
                Color c = Color.getHSBColor((float)Math.random(), 1f,1f);//gera cor aleatorio
                
                BufferedImage bi0 = gerarGrafico3D(0, c);
                BufferedImage bi1 = gerarGrafico3D(1, null);
                label0 = new JLabel(new ImageIcon(bi0));
                label1 = new JLabel(new ImageIcon(bi1));
                
                try {
                        FileOutputStream fos0 = new FileOutputStream(new File("c:\\arquiv\\test0.pdf"));
                        FileOutputStream fos1 = new FileOutputStream(new File("c:\\arquiv\\test1.pdf"));
                        GeradorPDF.gerarPDF(bi0, fos0);
                        GeradorPDF.gerarPDF(bi1, fos1);
                        fos0.close();
                        fos1.close();
                } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                } catch (IOException e) {
                        e.printStackTrace();
                }
                
                
                //Instancia as janelas
                tela0 = new JFrame("modo 0");
                tela1 = new JFrame("modo 1");
                
                //adiciona o fechar as janelas
                tela0.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                tela1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                
                //adiciona os graficos na janela
                tela0.getContentPane().add(label0);
                tela1.getContentPane().add(label1);
                
                //prepara para exibir
                tela0.pack();
                tela1.pack();
                
                //exibe
                tela0.setVisible(true);
                tela1.setVisible(true);
        }

        /**
         * Adiciona reuniao no DataSet
         * @param r
         */
        public void addReuniao(int mes, Number valor) {
                dataset0.addValor(mes, valor);
                dataset1.addValor(mes, valor);
        }
        
        /**
         * Retorna a imagem do grafico
         * @param modo
         * @param cor
         * @return
         */
        public BufferedImage gerarGrafico3D(int modo, Color cor) {
                if (modo == 0) return Grafico.gerarGrafico3D(null, "Presenca 2010", "mês", "presença", 800, 500, 800, 500, dataset0.getDcd(), 0.75f, cor, modo, true);
                if (modo == 1) return Grafico.gerarGrafico3D(null, "Presenca 2010", "mês", "presença", 800, 500, 800, 500, dataset1.getDcd(), 0.75f, null, modo, true);
                return null;
        }
        
        public static void main(String[] args) {
                //instancia os DataSet
                TesteGrafico tg = new TesteGrafico();
                
                //add as reunioes
                for (int k = 0; k < 12; k++) {          
                        tg.addReuniao(k, (int)(Math.random()*20));
                }
                
                tg.initComponentsTest();
                
        }

}

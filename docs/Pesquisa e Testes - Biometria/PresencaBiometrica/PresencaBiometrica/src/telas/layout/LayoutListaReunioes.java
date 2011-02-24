/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package telas.layout;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;

/**
 *
 * @author Diego
 */
public class LayoutListaReunioes implements LayoutManager {

    public void addLayoutComponent(String name, Component comp) {
    }

    public void removeLayoutComponent(Component comp) {
    }

    public Dimension preferredLayoutSize(Container parent) {
        return new Dimension(0, 0);
    }

    public Dimension minimumLayoutSize(Container parent) {
        return new Dimension(0, 0);
    }

    public void layoutContainer(Container parent) {
        synchronized (parent.getTreeLock()) {
            Insets insets = parent.getInsets();
            int w =  parent.getSize().width - insets.left - insets.right;
            int h = parent.getSize().height - insets.top - insets.bottom;
            int x = insets.left;
            int y = insets.top;

            Component[] components = parent.getComponents();
            for (Component c: components) {
                if (c.getName().equals("titulo")) {
                    c.setBounds(x, insets.top, w, 150);
                    c.setVisible(true);
                } else if (c.getName().equals("containerListaReunioes")) {
                    c.setBounds(x + 64, y + 150, w - 128, h - 230);
                    c.setVisible(true);
                } else if (c.getName().equals("iniciarReuniao")) {
                    c.setBounds((w - c.getPreferredSize().width) / 2, h - 60, c.getPreferredSize().width, 40);
                    c.setVisible(true);
                } else {
                    c.setVisible(false);
                }
            }
        }
    }

}

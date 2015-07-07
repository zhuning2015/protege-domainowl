package org.protege.editor.owl.ning.tab.dialog;

import org.protege.editor.owl.ning.domainOWL.MetaConcept;
import org.protege.editor.owl.ning.tab.DomainOWLPanel;

import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JList;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Dimension;

import java.awt.Color;

public class MetaConceptListCellRenderer extends JPanel
    implements ListCellRenderer
{
    private ImageIcon icon;
    private String name;
    private Color background;
    private Color foreground;

    public Component getListCellRendererComponent(JList list,
                                                  Object value,
                                                  int index,
                                                  boolean isSelected,
                                                  boolean cellHasFocus)
    {
        MetaConcept mc = (MetaConcept) value;
        String iconName = mc.getIconName();
        name = mc.getName();
        iconName = iconName.replace("16", "32");
        icon = new ImageIcon(DomainOWLPanel.getPluginDir() +
                             "resources/icons/" + iconName);
        background = isSelected ? list.getSelectionBackground() :
            list.getBackground();
        foreground = isSelected ? list.getSelectionForeground() :
            list.getForeground();
        return this;
    }

    public void paint(Graphics g)
    {
        int imageWidth = icon.getImage().getWidth(null);
        int imageHeight = icon.getImage().getHeight(null);
        g.setColor(background);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(foreground);
        g.drawImage(icon.getImage(), getWidth() / 2 - imageWidth / 2,
                    32, null);
        g.drawString(name, getWidth() / 2 - name.length() / 2,
                     imageHeight + 40);
    }

    public Dimension getPreferredSize()
    {
        return new Dimension(60,80);
    }
}

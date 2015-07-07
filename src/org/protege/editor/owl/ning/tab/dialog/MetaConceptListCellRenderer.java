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
import java.awt.FontMetrics;

/**
 * The customized list cell renderer for the meta concept list
 * in the domain owl panel for customzing domain concepts
 *
 * @author Zhu Ning
 * @version 0.1.0
 */
public class MetaConceptListCellRenderer extends JPanel
    implements ListCellRenderer
{
    /**
     * The image icon for the list cell
     */
    private ImageIcon icon;

    /**
     * The name of the list cell representing meta concept
     */
    private String name;

    /**
     * The background of the panel for the list cell
     */
    private Color background;

    /**
     * The foreground of the panel for the list cell
     */
    private Color foreground;

    @Override
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

    @Override
    public void paint(Graphics g)
    {
        int imageWidth = icon.getImage().getWidth(null);
        int imageHeight = icon.getImage().getHeight(null);
        g.setColor(background);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(foreground);
        g.drawImage(icon.getImage(), getWidth() / 2 - imageWidth / 2,
                    10, null);
        FontMetrics metrics = g.getFontMetrics(g.getFont());

        g.drawString(name, getWidth() / 2 - metrics.stringWidth(name) / 2,
                     imageHeight + 20);
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(60,80);
    }
}

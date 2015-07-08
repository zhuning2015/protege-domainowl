package org.protege.editor.owl.ning.tab.dialog;

import org.protege.editor.owl.ning.util.NameParser;
import org.protege.editor.owl.ning.tab.DomainOWLPanel;

import javax.swing.JPanel;
import javax.swing.table.TableCellRenderer;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.ImageIcon;

import java.awt.Component;
import java.awt.Graphics;

/**
 * The renderer for the table cell to show icons
 *
 * @author Zhu Ning
 * @version 0.1.0
 */
public class ImageTableCellRenderer extends JPanel implements TableCellRenderer
{
    /**
     * The width of the image in the table cell
     */
    final int ICON_WIDTH = 16;
    /**
     * The height of the image in the table cell
     */
    final int ICON_HEIGHT = 16;

    /**
     * The value in the table cell
     */
    private String cellValue = "";

    public Component getTableCellRendererComponent(JTable table,
                                                   Object value,
                                                   boolean isSelected,
                                                   boolean hasFocus,
                                                   int row,
                                                   int column)
    {
        cellValue = (String) value;
        if (hasFocus)
        {
            setBorder(UIManager.
                      getBorder("Table.focusCellHighlightBorder"));
        }else
        {
            setBorder(null);
        }
        return this;
    }

    public void paint(Graphics g)
    {
        ImageIcon imageIcon =
            new ImageIcon(DomainOWLPanel.getPluginDir() +
                          "resources/icons/" + cellValue);
        g.drawImage(imageIcon.getImage(),
                    (getWidth() - ICON_WIDTH) / 2,
                    (getHeight() - ICON_HEIGHT) / 2,
                    null);
    }

}

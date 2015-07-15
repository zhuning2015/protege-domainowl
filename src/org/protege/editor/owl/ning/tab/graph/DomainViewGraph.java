package org.protege.editor.owl.ning.tab.graph;

import org.protege.editor.owl.ning.domainOWL.DomainConcept;
import org.protege.editor.owl.ning.domainOWL.DomainOntology;
import org.protege.editor.owl.ning.tab.DomainOWLPanel;

import org.jgraph.JGraph;
import org.jgraph.graph.GraphModel;
import org.jgraph.graph.GraphLayoutCache;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.GraphCellEditor;
import org.jgraph.graph.CellView;
import org.jgraph.graph.CellHandle;
import org.jgraph.graph.GraphContext;
import org.jgraph.plaf.basic.BasicGraphUI;

import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D;
import java.awt.Point;

/**
 * The right panel in the domain owl panel for visualizing the domain
 * concepts and relations in the domain ontology
 *
 * @author Zhu Ning
 * @version 0.1.0
 */
public class DomainViewGraph extends JGraph
{
    public DomainViewGraph(GraphModel model, GraphLayoutCache view)
    {
        super(model, view);
    }

    @Override
    public void updateUI()
    {
        setUI(new DomainViewGraphUI());
        invalidate();
    }

    public void addCell(DomainConcept dc, Point pnt)
    {
        DefaultGraphCell cell = new DefaultGraphCell(dc);
        GraphConstants.setBounds(cell.getAttributes(),
                                 new Rectangle2D.Double(20,20,60,60));
        String iconName = dc.getMetaConcept().getIconName();
        iconName = iconName.replace("16", "32");
        ImageIcon imageIcon =
            new ImageIcon(DomainOWLPanel.getPluginDir() +
                          "resources/icons/" + iconName);
        GraphConstants.setIcon(cell.getAttributes(),imageIcon);
        GraphConstants.setOpaque(cell.getAttributes(), true);
        Rectangle2D bounds =
            GraphConstants.getBounds(cell.getAttributes());
        bounds.setRect(pnt.x, pnt.y, bounds.getWidth(),
                       bounds.getHeight());
        GraphConstants.setBounds(cell.getAttributes(), bounds);
        getGraphLayoutCache().insert(cell);
        getGraphLayoutCache().setVisible(cell, true);
        getGraphLayoutCache().update();
    }

}

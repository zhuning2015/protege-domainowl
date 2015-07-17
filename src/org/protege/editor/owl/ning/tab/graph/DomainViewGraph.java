package org.protege.editor.owl.ning.tab.graph;

import org.protege.editor.owl.ning.domainOWL.DomainConcept;
import org.protege.editor.owl.ning.domainOWL.DomainOntology;
import org.protege.editor.owl.ning.tab.DomainOWLPanel;

import org.jgraph.JGraph;
import org.jgraph.graph.GraphModel;
import org.jgraph.graph.GraphLayoutCache;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;

import javax.swing.ImageIcon;

import java.awt.Point;
import java.awt.geom.Rectangle2D;

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
        setPortsVisible(true);
        setMarqueeHandler(new DomainViewMarqueeHandler(this));
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

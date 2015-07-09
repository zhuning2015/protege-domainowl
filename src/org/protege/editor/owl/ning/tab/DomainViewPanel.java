package org.protege.editor.owl.ning.tab;

import org.protege.editor.owl.ning.tab.graph.DomainViewGraphModel;
import org.protege.editor.owl.ning.domainOWL.DomainConcept;

import org.jgraph.JGraph;
import org.jgraph.graph.GraphModel;
import org.jgraph.graph.GraphLayoutCache;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;

import javax.swing.JScrollPane;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.geom.Rectangle2D;

/**
 * The right panel in the domain owl panel for visualizing the domain
 * concepts and relations in the domain ontology
 *
 * @author Zhu Ning
 * @version 0.1.0
 */
public class DomainViewPanel extends JGraph
{
    public DomainViewPanel(GraphModel model, GraphLayoutCache view)
    {
        super(model, view);
    }

    public void addCell(DomainConcept dc)
    {
        DefaultGraphCell cell = new DefaultGraphCell(dc);
        GraphConstants.setBounds(cell.getAttributes(), new Rectangle2D.Double(20,20,40,20));
        GraphConstants.setGradientColor(cell.getAttributes(), Color.orange);
        GraphConstants.setOpaque(cell.getAttributes(), true);
        getGraphLayoutCache().insert(cell);
        getGraphLayoutCache().setVisible(cell, true);
        getGraphLayoutCache().update();
    }
}

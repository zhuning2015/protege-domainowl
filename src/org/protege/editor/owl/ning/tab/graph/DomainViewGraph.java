package org.protege.editor.owl.ning.tab.graph;

import org.protege.editor.owl.ning.domainOWL.DomainConcept;
import org.protege.editor.owl.ning.domainOWL.DomainOntology;
import org.protege.editor.owl.ning.domainOWL.DomainRelation;
import org.protege.editor.owl.ning.tab.DomainOWLPanel;

import org.jgraph.JGraph;
import org.jgraph.graph.DefaultGraphModel;
import org.jgraph.graph.GraphLayoutCache;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.DefaultPort;
import org.jgraph.graph.AbstractCellView;

import javax.swing.ImageIcon;

import java.awt.Point;
import java.awt.geom.Rectangle2D;

import java.io.OutputStream;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;

import java.beans.XMLEncoder;
import java.beans.ExceptionListener;
import java.beans.DefaultPersistenceDelegate;
import java.beans.PersistenceDelegate;
import java.beans.Encoder;
import java.beans.Expression;
import java.beans.XMLDecoder;

/**
 * The right panel in the domain owl panel for visualizing the domain
 * concepts and relations in the domain ontology
 *
 * @author Zhu Ning
 * @version 0.1.2
 */
public class DomainViewGraph extends JGraph
{
    DefaultGraphModel model = null;

    public DomainViewGraph(DefaultGraphModel model,
                           GraphLayoutCache view)
    {
        super(model, view);
        this.model = model;
        setMarqueeHandler(new DomainViewMarqueeHandler(this));
    }

    @Override
    public void updateUI()
    {
        setUI(new DomainViewGraphUI());
        invalidate();
    }

    /**
     * Clears all the cells in the graph
     */
    public void clear()
    {
        model.remove(model.getAll(model));
        refresh();
    }

    /**
     * Add the cell to the graph which consist of the domain concept dc
     * @param dc The domain concept which the created cell represent for
     * @param pnt The location of the cell in the graph
     */
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

    /**
     * Saves the graphLayoutCache into a xml file
     * @param out The output stream for the xml file
     */
    public void save(OutputStream out)
    {
        XMLEncoder encoder = new XMLEncoder(out);
        encoder.setExceptionListener(new ExceptionListener()
        {
            public void exceptionThrown(Exception e)
            {
                e.printStackTrace();
            }
        });

        encoder.setPersistenceDelegate(DefaultGraphModel.class,
                new DefaultPersistenceDelegate(new String[] { "roots",
                                                "attributes" }));
        encoder.setPersistenceDelegate(GraphLayoutCache.class,
           new DefaultPersistenceDelegate(new String[] { "model",
              "factory", "cellViews", "hiddenCellViews", "partial" }));
        encoder.setPersistenceDelegate(DefaultGraphCell.class,
            new DefaultPersistenceDelegate(
                        new String[] { "userObject" }));
        encoder.setPersistenceDelegate(DefaultEdge.class,
            new DefaultPersistenceDelegate(
                        new String[] { "userObject" }));
        encoder.setPersistenceDelegate(DefaultPort.class,
            new DefaultPersistenceDelegate(
                        new String[] { "userObject" }));
        encoder.setPersistenceDelegate(AbstractCellView.class,
            new DefaultPersistenceDelegate(new String[] { "cell",
                                                "attributes" }));
        encoder.setPersistenceDelegate(
            DefaultEdge.DefaultRouting.class,
             new PersistenceDelegate() {
             protected Expression instantiate(
                             Object oldInstance, Encoder out) {
                              return new Expression(oldInstance,
                                           GraphConstants.class,
                                      "getROUTING_SIMPLE", null);
                                        }
             });
        encoder.setPersistenceDelegate(DefaultEdge.LoopRouting.class,
           new PersistenceDelegate() {
            protected Expression instantiate(Object oldInstance,
                                             Encoder out) {
                return new Expression(oldInstance,
                                      GraphConstants.class,
                                      "getROUTING_DEFAULT", null);
            }
        });
        encoder.setPersistenceDelegate(ArrayList.class,
                    encoder.getPersistenceDelegate(List.class));
        encoder.setPersistenceDelegate(DomainConcept.class,
                  new DefaultPersistenceDelegate(new String[]{
                         "name", "outgoingRelations",
                         "incomingRelations", "metaConceptName",
                         "instanceName"}));
        encoder.setPersistenceDelegate(DomainRelation.class,
                  new DefaultPersistenceDelegate(new String[]{
                "name", "srcDcName", "dstDcName", "metaRelationName"}));

        encoder.writeObject(getGraphLayoutCache());
        encoder.close();
    }

    /**
     * Loads the graphLayoutCache into the graph from the xml file
     * @param in The input stream for the xml file
     */
    public void load(InputStream in)
    {
        XMLDecoder dec = new XMLDecoder(in,
                                        null,
                                        null,
                                        DomainViewGraph.class.getClassLoader());
        if (dec != null)
        {
            GraphLayoutCache gle = (GraphLayoutCache) dec.readObject();
            dec.close();
            setGraphLayoutCache(gle);
            gle.update();
            refresh();
        }
    }
}

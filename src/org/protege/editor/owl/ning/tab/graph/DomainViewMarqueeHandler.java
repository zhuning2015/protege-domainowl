package org.protege.editor.owl.ning.tab.graph;

import org.protege.editor.owl.ning.domainOWL.DomainConcept;
import org.protege.editor.owl.ning.domainOWL.MetaConcept;
import org.protege.editor.owl.ning.domainOWL.MetaOntology;
import org.protege.editor.owl.ning.domainOWL.MetaRelation;
import org.protege.editor.owl.ning.domainOWL.DomainRelation;
import org.protege.editor.owl.ning.tab.DomainOWLPanel;

import org.jgraph.JGraph;
import org.jgraph.graph.BasicMarqueeHandler;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.DefaultPort;
import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.Port;

import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import java.util.Iterator;
import java.util.Map;
import java.util.Hashtable;

import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * Customed BasicMarqueeHandler of JGraph to popup right-click menu
 *
 * @author Zhu Ning
 * @version 0.1.1
 */
public class DomainViewMarqueeHandler extends BasicMarqueeHandler
{
    /**
     * The start point of the connecting line when connecting two cells
     */
    private Point2D start;
    /**
     * The end point of the connecting line which is the same as the mouse location
     * when connecting two cells
     */
    private Point2D current;
    /**
     * The prot of the inital cell
     */
    private DefaultPort firstPort;
    /**
     * The graph the handler is for
     */
    private JGraph graph = null;
    /**
     * The meta relation which is the object in the edge
     */
    private MetaRelation edgeMetaObj = null;
    /**
     * The domain concept which is the object in the source cell
     */
    private DomainConcept srcDc = null;
    /**
     * The domain concept which is the object in the destination cell
     */
    private DomainConcept dstDc = null;

    public DomainViewMarqueeHandler(JGraph graph)
    {
        this.graph = graph;
    }

    @Override
    public boolean isForceMarqueeEvent(MouseEvent e)
    {
        if (e.isShiftDown())
        {
            return false;
        }

        if (SwingUtilities.isRightMouseButton(e))
        {
            return true;
        }

        if (start != null)
            return true;

        return super.isForceMarqueeEvent(e);
    }

    @Override
    public void mousePressed(final MouseEvent e)
    {
        DefaultGraphCell cell = getCellAt(e.getPoint());

        if(SwingUtilities.isRightMouseButton(e))
        {
            stopDrawLine();
            if (cell != null)
            {
                JPopupMenu menu = createPopupMenu(e.getPoint(), cell);
                menu.show(graph, e.getX(), e.getY());
            }
        }else
        {
            if (cell != null)
            {
                dstDc = (DomainConcept)graph.getModel().getValue(cell);
                DefaultPort secondPort = null;
                if (cell.getChildCount() > 0)
                    secondPort = (DefaultPort)cell.getChildAt(0);
                if (secondPort == null)
                {
                    secondPort = new DefaultPort();
                    cell.add(secondPort);
                }
                connect(firstPort, secondPort);
            }else
            {

            }
            stopDrawLine();
            super.mousePressed(e);
        }
    }

    /**
     * Clears the flags for drawing connecting lines and refresh the graph
     */
    private void stopDrawLine()
    {
        start = null;
        current = null;
        graph.repaint();
    }

    /**
     * Gets the cell at the position pt in the graph
     * @param The position to find the cell
     * @return The cell at the specified position pt, null if there is not a
     * cell there
     */
    private DefaultGraphCell getCellAt(Point pt)
    {
        Object cellObj = graph.getFirstCellForLocation(pt.getX(),
                                                    pt.getY());
        DefaultGraphCell cell = (DefaultGraphCell)cellObj;
        return cell;
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        if (start != null)
        {
            Graphics g = graph.getGraphics();
            DefaultGraphCell cell = getCellAt(e.getPoint());
            paintConnector(Color.black, graph.getBackground(), g);
            current = graph.snap(e.getPoint());
            paintConnector(graph.getBackground(), Color.black, g);
            if (cell != null)
             {
                 graph.setCursor(new Cursor(Cursor.HAND_CURSOR));
                 e.consume();
             }

        }else
            super.mouseMoved(e);
    }


    /**
     * Creates the pop-up menu when righclicking the cell
     * @param pt The point to show the pop-up menu
     * @param cell The cell clicked
     * @return The popup menu which should be shown when the cell
     * is clicked
     */
    private JPopupMenu createPopupMenu(final Point pt,
                                       final Object cell)
    {
        JPopupMenu menu = new JPopupMenu();
        DomainConcept dc =
            (DomainConcept)graph.getModel().getValue(cell);
        srcDc = dc;
        MetaConcept mc = dc.getMetaConcept();
        Iterator<String> it = mc.outgoingMetaRelationIterator();
        while(it.hasNext())
        {
            String outgoingMRName = it.next();
            MetaOntology mo = MetaOntology.getMetaOntology();
            MetaRelation mr = mo.getMetaRelation(outgoingMRName);
            if (mr.getIsIncluded())
            {
                String iconPath = DomainOWLPanel.getPluginDir() +
                    "resources/icons/edgeEnds/" +
                    mr.getIconName();
                ImageIcon icon = new ImageIcon(iconPath);
                JMenuItem menuItem = new JMenuItem(mr.getName(), icon);
                ActionListener menuItemListener =
                    new CellMenuItemActionListener(mr, cell);
                menuItem.addActionListener(menuItemListener);
                menu.add(menuItem);
            }
        }
        return menu;
    }

    /**
     * Paints the connector when connecting two cells
     * @param fg The foreground color
     * @param bg The backgraoud color
     * @param g  The graphics interface
     */
    private void paintConnector(Color fg, Color bg, Graphics g)
    {
        if (graph.isXorEnabled())
        {
            g.setColor(fg);
            g.setXORMode(bg);
            drawConnectorLine(g);
        }else
        {
            Rectangle dirty =
                new Rectangle((int)start.getX(), (int)start.getY(),
                              1, 1);

            if (current != null)
            {
                dirty.add(current);
            }

            dirty.grow(1, 1);
            graph.repaint(dirty);
        }
    }

    @Override
    public void paint(JGraph graph, Graphics g)
    {
        super.paint(graph, g);
        if (!graph.isXorEnabled())
        {
            g.setColor(Color.black);
            drawConnectorLine(g);
        }
    }

    /**
     * Draw the connecting line between two cells
     * @param g The graphic interface
     */
    private void drawConnectorLine(Graphics g)
    {
        if (firstPort != null && start != null && current != null)
        {
            g.drawLine((int)start.getX(), (int)start.getY(),
                       (int)current.getX(), (int)current.getY());
        }
    }

    /**
     * Connects two cells from their ports with eges
     * @param source The port of the source cell
     * @param target The port of the target cell
     */
    private void connect(Port source, Port target)
    {
        DomainRelation dr =
            DomainRelation.create(edgeMetaObj.getName());
        dr.setSrc(srcDc);
        dr.setDst(dstDc);
        dr.setMetaRelation(edgeMetaObj);
        DefaultEdge edge = createDefaultEdge(dr);
        if (graph.getModel().acceptsSource(edge, source) &&
            graph.getModel().acceptsTarget(edge, target))
        {
            edge.getAttributes().applyMap(createEdgeAttributes());
            graph.getGraphLayoutCache().insertEdge(edge, source,
                                                   target);
        }
        edgeMetaObj = null;
        srcDc = null;
        dstDc = null;
    }

    /**
     * Creates the default edge connecting two cells
     * @param dr The domain relation which dwells in the edge
     * @return The created edge
p     */
    private DefaultEdge createDefaultEdge(DomainRelation dr)
    {
        return new DefaultEdge(dr);
    }

    private Map createEdgeAttributes()
    {
        Map map = new Hashtable();
        String iconName = edgeMetaObj.getIconName();
        if (iconName.contains("Circle"))
        {
            GraphConstants.setLineBegin(map,
                                      GraphConstants.ARROW_CIRCLE);
        }else if (iconName.contains("Technique"))
        {
            GraphConstants.setLineBegin(map,
                                      GraphConstants.ARROW_TECHNICAL);
        }else if (iconName.contains("Classic"))
        {
            GraphConstants.setLineBegin(map,
                                      GraphConstants.ARROW_CLASSIC);
        }else if (iconName.contains("Diamond"))
        {
            GraphConstants.setLineBegin(map,
                                      GraphConstants.ARROW_DIAMOND);
        }else if (iconName.contains("DoubleLine"))
        {
            GraphConstants.setLineBegin(map,
                                      GraphConstants.ARROW_DOUBLELINE);
        }else if (iconName.contains("Line") &&
                  !iconName.contains("DoubleLine"))
        {
            GraphConstants.setLineBegin(map,
                                      GraphConstants.ARROW_LINE);
        }else
        {
            GraphConstants.setLineBegin(map,
                                      GraphConstants.ARROW_SIMPLE);
        }
        if (iconName.contains("Fill"))
                GraphConstants.setEndFill(map, true);
        GraphConstants.setLabelAlongEdge(map, true);
        return map;
    }

    private class CellMenuItemActionListener implements ActionListener
    {
        /**
         * The meta relation which the menu item is corresponding to
         */
        private MetaRelation mr = null;

        /**
         * The current cell where the menu item is popped up
         */
        private Object cell = null;

        public CellMenuItemActionListener(MetaRelation mr,
                                          Object cell)
        {
            this.mr = mr;
            this.cell = cell;
        }

        public void actionPerformed(ActionEvent e)
        {
            DefaultGraphCell graphCell = (DefaultGraphCell) cell;
            DefaultPort defaultPort = null;
            edgeMetaObj = mr;
            if (graphCell != null && graphCell.getChildCount() > 0)
            {
                defaultPort = (DefaultPort)graphCell.getChildAt(0);
            }else
            {
                defaultPort = new DefaultPort();
                graphCell.add(defaultPort);
            }
            Rectangle2D bounds =
                GraphConstants.getBounds(graphCell.getAttributes());
            double x = bounds.getX() + bounds.getWidth()  / 2;
            double y = bounds.getY() + bounds.getHeight() / 2;
            start = new Point2D.Double(x, y);
            firstPort = defaultPort;
        }
    }
}

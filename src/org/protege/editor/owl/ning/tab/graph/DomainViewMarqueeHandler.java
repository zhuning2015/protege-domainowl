package org.protege.editor.owl.ning.tab.graph;

import org.protege.editor.owl.ning.domainOWL.DomainConcept;
import org.protege.editor.owl.ning.domainOWL.MetaConcept;
import org.protege.editor.owl.ning.domainOWL.MetaOntology;
import org.protege.editor.owl.ning.domainOWL.MetaRelation;
import org.protege.editor.owl.ning.tab.DomainOWLPanel;

import org.jgraph.JGraph;
import org.jgraph.graph.PortView;
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

/**
 * Customed BasicMarqueeHandler of JGraph to popup right-click menu
 *
 * @author Zhu Ning
 * @version 0.1.0
 */
public class DomainViewMarqueeHandler extends BasicMarqueeHandler
{
    protected Point2D start, current;
    protected PortView port, firstPort;
    private JGraph graph = null;

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

        port = getSourcePortAt(e.getPoint());
        if (port != null && graph.isPortsVisible())
        {
            return true;
        }

        return super.isForceMarqueeEvent(e);
    }

    @Override
    public void mousePressed(final MouseEvent e)
    {
        if(SwingUtilities.isRightMouseButton(e))
        {
            Object cell = graph.getFirstCellForLocation(e.getX(),
                                                        e.getY());
            if (cell != null)
            {
                JPopupMenu menu = createPopupMenu(e.getPoint(), cell);
                menu.show(graph, e.getX(), e.getY());
            }
        }else if (port != null && graph.isPortsVisible())
        {
            start = graph.toScreen(port.getLocation());
            firstPort = port;
        }else
        {
            super.mousePressed(e);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        if (start != null)
        {
            Graphics g = graph.getGraphics();
            PortView newPort = getTargetPortAt(e.getPoint());
            if (newPort == null || newPort != port)
            {
                paintConnector(Color.black, graph.getBackground(), g);
                port = newPort;
                if (port != null)
                {
                    current = graph.toScreen(port.getLocation());
                }else
                {
                    current = graph.snap(e.getPoint());
                }
                paintConnector(graph.getBackground(), Color.black, g);
            }
        }

        super.mouseDragged(e);
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        if (e != null && port != null && firstPort != null &&
            firstPort != port)
        {
            connect((Port)firstPort.getCell(), (Port)port.getCell());
            e.consume();
        }else
            graph.repaint();

        firstPort = port = null;
        start = current = null;

        super.mouseReleased(e);
    }

    public void mouseMoved(MouseEvent e)
    {
        if (e != null && getSourcePortAt(e.getPoint()) != null &&
            graph.isPortsVisible())
        {
            graph.setCursor(new Cursor(Cursor.HAND_CURSOR));
            e.consume();
        }else
            super.mouseMoved(e);
    }

    private PortView getSourcePortAt(Point2D point)
    {
        graph.setJumpToDefaultPort(false);
        PortView result;
        try
        {
            result = graph.getPortViewAt(point.getX(), point.getY());
        }finally
        {
            graph.setJumpToDefaultPort(true);
        }
        return result;
    }

    private PortView getTargetPortAt(Point2D point)
    {
        return graph.getPortViewAt(point.getX(), point.getY());
    }

    private JPopupMenu createPopupMenu(final Point pt,
                                       final Object cell)
    {
        JPopupMenu menu = new JPopupMenu();
        DomainConcept dc =
            (DomainConcept)graph.getModel().getValue(cell);

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
                    "resources/icons/" +
                    mr.getIconName();
                ImageIcon icon = new ImageIcon(iconPath);
                JMenuItem menuItem = new JMenuItem(mr.getName(), icon);
                menuItem.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent ae)
                    {
                        DefaultGraphCell graphCell =
                            (DefaultGraphCell) cell;
                        DefaultPort defaultPort = new DefaultPort();
                        graphCell.add(defaultPort);
                    }
                });
                menu.add(menuItem);
            }
        }
        return menu;
    }

    private void paintConnector(Color fg, Color bg, Graphics g)
    {
        if (graph.isXorEnabled())
        {
            g.setColor(fg);
            g.setXORMode(bg);
            paintPort(graph.getGraphics());

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
            //            highlight(graph, port);
        }
    }

    public void paint(JGraph graph, Graphics g)
    {
        super.paint(graph, g);
        if (!graph.isXorEnabled())
        {
            g.setColor(Color.black);
            drawConnectorLine(g);
        }
    }

    private void drawConnectorLine(Graphics g)
    {
        if (firstPort != null && start != null && current != null)
        {
            g.drawLine((int)start.getX(), (int)start.getY(),
                       (int)current.getX(), (int)current.getY());
        }
    }

    private void paintPort(Graphics g)
    {
        if (port != null)
        {
            boolean o = (
               GraphConstants.getOffset(port.getAllAttributes()) != null
             );
            Rectangle2D r =
                o ? port.getBounds() : port.getParentView().getBounds();
            r = graph.toScreen((Rectangle2D) r.clone());
            r.setFrame(r.getX() - 3, r.getY() - 3, r.getWidth() + 6,
                       r.getHeight() + 6);
            graph.getUI().paintCell(g, port, r, true);
        }
    }

    private void connect(Port source, Port target)
    {
        DefaultEdge edge = createDefaultEdge();
        if (graph.getModel().acceptsSource(edge, source) &&
            graph.getModel().acceptsTarget(edge, target))
        {
            edge.getAttributes().applyMap(createEdgeAttributes());
            graph.getGraphLayoutCache().insertEdge(edge, source,
                                                   target);
        }
    }

    private DefaultEdge createDefaultEdge()
    {
        return new DefaultEdge();
    }

    private Map createEdgeAttributes()
    {
        Map map = new Hashtable();
        GraphConstants.setLineEnd(map, GraphConstants.ARROW_SIMPLE);
        GraphConstants.setLabelAlongEdge(map, true);
        return map;
    }
}

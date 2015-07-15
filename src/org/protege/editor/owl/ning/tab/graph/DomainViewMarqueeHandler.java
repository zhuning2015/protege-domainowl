package org.protege.editor.owl.ning.tab.graph;

import org.jgraph.JGraph;
import org.jgraph.graph.PortView;
import org.jgraph.graph.BasicMarqueeHandler;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

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

        }else
        {
            super.mousePressed(e);
        }
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

    private JPopupMenu createPopupMenu(final Point pt,
                                       final Object cell)
    {
        JPopupMenu menu = new JPopupMenu();
        menu.add(new JMenuItem("test"));
        return menu;
    }
}

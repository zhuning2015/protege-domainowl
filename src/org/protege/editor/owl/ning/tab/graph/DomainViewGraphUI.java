package org.protege.editor.owl.ning.tab.graph;

import org.protege.editor.owl.ning.domainOWL.DomainConcept;
import org.protege.editor.owl.ning.domainOWL.DomainOntology;
import org.protege.editor.owl.ning.exception.BasicException;

import org.jgraph.graph.CellView;
import org.jgraph.graph.GraphCellEditor;
import org.jgraph.plaf.basic.BasicGraphUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;
import javax.swing.event.CellEditorListener;

import java.awt.Point;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * The customed BasicGraphUI for DomainViewGraph to support edit of the
 * cells
 *
 * @author Zhu Ning
 * @version 0.1.0
 */
public class DomainViewGraphUI extends BasicGraphUI
{
    protected CellEditorListener cellEditorListener;
    protected JFrame editDialog = null;

    /**
     * Create the dialog using the cell's editing component
     *
     * @param cell The cell which the dialog handles
     * @param x    The x position of the dialog
     * @param y    The y position of the dialog
     */
    protected void createEditDialog(Object cell, int x, int y)
    {
        editDialog =
            new JFrame("Edit " + graph.convertValueToString(cell));

        JPanel panel = new JPanel();
        panel.add(new JLabel("Name:"));
        panel.add(editingComponent);
        editingComponent.validate();

        editDialog.add(panel);
        editDialog.setLocation(x, y);
        editDialog.pack();
        editDialog.setVisible(true);

        editDialog.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                completeEditing();
            }
        });
    }

    @Override
    protected boolean startEditing(Object cell, MouseEvent event)
    {
        completeEditing();
        if (graph.isCellEditable(cell) && editDialog == null)
        {
            CellView tmp = graphLayoutCache.getMapping(cell, false);
            cellEditor = tmp.getEditor();
            editingComponent =
                cellEditor.getGraphCellEditorComponent(graph,
                                                       cell,
                                   graph.isCellSelected(cell));
            if (cellEditor.isCellEditable(event))
            {
                editingCell = cell;
                createEditDialog(cell, event.getXOnScreen(),
                                       event.getYOnScreen());
                if (cellEditorListener == null)
                    cellEditorListener = createCellEditorListener();
                if (cellEditor != null && cellEditorListener != null)
                    cellEditor.
                        addCellEditorListener(cellEditorListener);
                if (cellEditor.shouldSelectCell(event))
                {
                    stopEditingInCompleteEditing = false;
                    try{
                        graph.setSelectionCell(cell);
                    }catch(Exception e)
                    {
                        System.err.println("Editing exception: " + e);
                    }
                    stopEditingInCompleteEditing = true;
                }

                if (event instanceof MouseEvent)
                {
                    Point componentPoint =
                        SwingUtilities.convertPoint(graph,
                    new Point(event.getX(), event.getY()),
                                        editingComponent);
                    Component activeComponent =
                        SwingUtilities.
                        getDeepestComponentAt(editingComponent,
                                              componentPoint.x,
                                              componentPoint.y);
                    if (activeComponent != null)
                    {
                        new MouseInputHandler(graph, activeComponent,
                                              event);
                    }
                }
                return true;
            }else
                editingComponent = null;
        }
        return false;
    }

    @Override
    protected void completeEditing(boolean messageStop,
                                   boolean messageCancel,
                                   boolean messageGraph)
    {
        if (stopEditingInCompleteEditing && editingComponent != null &&
            editDialog != null)
        {
            Object oldCell = editingCell;
            GraphCellEditor oldEditor = cellEditor;
            DomainConcept dc =
                (DomainConcept) graphLayoutCache.getModel().
                getValue(editingCell);

            Object newValue = oldEditor.getCellEditorValue();
            if (!dc.getName().equals(newValue))
            {
                try
                {
                    DomainOntology.getDomainOntology().
                        updateDomainConcept(dc, newValue.toString());
                    graph.refresh();
                }catch(BasicException e)
                {
                    JOptionPane.showMessageDialog(null, e.toString());
                    graph.clearSelection();
                }
            }

            boolean requestFocus = (graph != null &&
                     (graph.hasFocus() || editingComponent.hasFocus()));
            editingCell = null;
            editingComponent = null;
            if (messageStop)
            {
                oldEditor.stopCellEditing();
            }
            else if (messageCancel)
                oldEditor.cancelCellEditing();

            editDialog.dispose();

            if (requestFocus)
                graph.requestFocus();
            if (messageGraph)
            {
                graphLayoutCache.valueForCellChanged(oldCell, dc);
            }
            updateSize();
            if (oldEditor != null && cellEditorListener != null)
                oldEditor.removeCellEditorListener(cellEditorListener);
            cellEditor = null;
            editDialog = null;
        }
    }
}

package org.protege.editor.owl.ning.tab;

import org.protege.editor.owl.ning.domainOWL.DomainConcept;
import org.protege.editor.owl.ning.domainOWL.DomainOntology;

import org.jgraph.JGraph;
import org.jgraph.graph.GraphModel;
import org.jgraph.graph.GraphLayoutCache;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.GraphCellEditor;
import org.jgraph.graph.CellView;
import org.jgraph.plaf.basic.BasicGraphUI;

import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
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

    @Override
    public void updateUI()
    {
        setUI(new DomainViewGraphUI());
        invalidate();
    }

    public void addCell(DomainConcept dc)
    {
        DefaultGraphCell cell = new DefaultGraphCell(dc);
        GraphConstants.setBounds(cell.getAttributes(), new Rectangle2D.Double(20,20,60,60));
        String iconName = dc.getMetaConcept().getIconName();
        iconName = iconName.replace("16", "32");
        ImageIcon imageIcon = new ImageIcon(DomainOWLPanel.getPluginDir() + "resources/icons/" +
                                            iconName);
        GraphConstants.setIcon(cell.getAttributes(),imageIcon);
        GraphConstants.setOpaque(cell.getAttributes(), true);
        getGraphLayoutCache().insert(cell);
        getGraphLayoutCache().setVisible(cell, true);
        getGraphLayoutCache().update();
    }

    public class DomainViewGraphUI extends BasicGraphUI
    {
        JFrame editDialog = null;

        protected void createEditDialog(Object cell, int x, int y)
        {
            Dimension editorSize = editingComponent.getPreferredSize();
            editDialog = new JFrame("Edit " + graph.convertValueToString(cell));
            editDialog.setSize(editorSize.width, editorSize.height);
            JPanel panel = new JPanel();
            panel.add(new JLabel("Name:"));
            panel.add(editingComponent);
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
            if (graph.isCellEditable(cell))
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
                    createEditDialog(cell, event.getXOnScreen(), event.getYOnScreen());
                }
            }
            return true;
        }

        @Override
        protected void completeEditing(boolean messageStop, boolean messageCancel,
                                    boolean messageGraph)
        {
            if (stopEditingInCompleteEditing && editingComponent != null &&
                editDialog != null)
            {
                Object oldcell = editingCell;
                GraphCellEditor oldEditor = cellEditor;
                Object newValue = oldEditor.getCellEditorValue();
                DomainConcept dc = (DomainConcept) graphLayoutCache.getModel().getValue(editingCell);
                DomainOntology.getDomainOntology().updateDomainConcept(dc, newValue.toString());
                refresh();
            }
            super.completeEditing(messageStop, messageCancel, messageGraph);
        }
    }
}

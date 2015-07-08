package org.protege.editor.owl.ning.tab;

import org.jgraph.JGraph;

import javax.swing.JScrollPane;
import javax.swing.JPanel;

public class DomainViewPanel extends JPanel
{
    JGraph graph = new JGraph();
    public DomainViewPanel()
    {
        add(new JScrollPane(graph));
    }
}

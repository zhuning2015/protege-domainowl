package org.protege.editor.owl.ning.tab.dialog;

import org.protege.editor.owl.ning.domainOWL.MetaOntology;

import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.JScrollPane;

/**
 * The dialog to configure the domain owl panel to decide which
 * meta concepts, meta relations, individuals are included in
 * the domain owl panel to create the domain ontology
 *
 * @author Zhu Ning
 * @version 0.1.0
 */
public class DomainOWLPanelConfigureDlg extends JDialog
{
    /**
     * The current meta ontology
     */
    private MetaOntology metaOnt = null;

    /**
     * The configure table for configure the domain owl panel
     */
    private JTable configTable = null;

    public DomainOWLPanelConfigureDlg(MetaOntology metaOnt)
    {
        this.metaOnt = metaOnt;
        configTable = new JTable(new ConfigTableModel());
        add(new JScrollPane(configTable));
        pack();
    }



}

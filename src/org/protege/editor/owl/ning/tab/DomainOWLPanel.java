package org.protege.editor.owl.ning.tab;

import org.protege.editor.owl.model.OWLModelManager;

import org.protege.editor.owl.ning.domainOWL.MetaOntology;
import org.protege.editor.owl.ning.domainOWL.MetaNode;
import org.protege.editor.owl.ning.domainOWL.MetaRelation;
import org.protege.editor.owl.ning.domainOWL.MetaConcept;
import org.protege.editor.owl.ning.domainOWL.Instance;
import org.protege.editor.owl.ning.domainOWL.DomainOWLObjectVisitor;
import org.protege.editor.owl.ning.util.NameParser;
import org.protege.editor.owl.ning.tab.dialog.DomainOWLPanelConfigureDlg;
import org.protege.editor.owl.ning.tab.dialog.MetaConceptListModel;
import org.protege.editor.owl.ning.tab.dialog.MetaConceptListCellRenderer;

import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLIndividual;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JList;

import java.awt.BorderLayout;

/**
 * The main panel for the DomainOWL plugin
 *
 * @author Zhu Ning
 * @version 0.1.0
 */
public class DomainOWLPanel extends JPanel
{
    /**
     * The single meta ontology in the domain owl plugin
     */
    private MetaOntology metaOnt = null;

    /**
     * The installed dir of the domain owl plugin
     */
    private static String pluginDir = null;

    public DomainOWLPanel(OWLModelManager owlMdlMgr)
    {
        createMetaOntology(owlMdlMgr);

        setPluginPath();

        new DomainOWLPanelConfigureDlg(null,
                                       "Configure...",
                                       true).setVisible(true);
        buildUI();
    }

    /**
     * Sets the installed path of the plugin
     */
    private void setPluginPath()
    {
        String jarPath = this.getClass().getProtectionDomain().
            getCodeSource().getLocation().getPath();
        pluginDir = NameParser.getDir(jarPath);
    }
    
    /**
     * Gets the installed dir of the domain owl plugin
     * @return The installed dir of the domain owl plugin
     */
    public static String getPluginDir()
    {
        return pluginDir;
    }

    /**
     * Creates the meta ontology based on the interface from Protege
     * @param owlMdlMgr The interfce provided by Protege to visit the
     * owl ontology
     */
    private void createMetaOntology(OWLModelManager owlMdlMgr)
    {
        OWLOntology owlOnt = owlMdlMgr.getActiveOntology();
        String strOntologyIRI = owlOnt.getOntologyID().
            getOntologyIRI().toString();
        metaOnt = MetaOntology.create
            (NameParser.getOWLOntologyName(strOntologyIRI));
        fillMetaOntologyFromOWLOntology(owlOnt);
    }

    /**
     * Builds the user interface of the domain owl plugin
     */
    private void buildUI()
    {
        setLayout(new BorderLayout());
        JSplitPane splitPane = new JSplitPane();
        add(splitPane, BorderLayout.CENTER);
        splitPane.setDividerLocation(64);
        JList metaElementList = new JList(new MetaConceptListModel());
        metaElementList.setCellRenderer(new MetaConceptListCellRenderer());
        splitPane.setLeftComponent(metaElementList);
    }

    /**
     * Fills the meta ontology in the domain owl from the active
     * owl ontology
     * @param owlOnt The active owl ontology in Protege
     */
    private void fillMetaOntologyFromOWLOntology(OWLOntology owlOnt)
    {
        createMetaConceptsFromOWLClasses(owlOnt);
        createMetaRelationsFromOWLObjectProperties(owlOnt);
        createMetaRelationsFromOWLDataProperties(owlOnt);
        createRelationsFromOWLDataProperties(owlOnt);
    }

    /**
     * Creates the meta concepts in the meta ontology according to
     * the owl classes in the active ontology
     * @param owlOnt The active owl ontology in Protege
     */
    private void createMetaConceptsFromOWLClasses(OWLOntology owlOnt)
    {
        for(OWLClass owlCls : owlOnt.getClassesInSignature())
        {
            if (owlCls.isOWLThing())
            {
                continue;
            }
            String mcName = NameParser.getSimpleName(owlCls);
            MetaConcept mc = metaOnt.createMetaConcept(mcName);
            owlCls.accept(new DomainOWLObjectVisitor(mc));
        }
    }


    /**
     * Creates the meta relations in the meta ontology according to
     * the owl object properties in the active ontology
     * @param owlOnt The active owl ontology in Protege
     */
    private void createMetaRelationsFromOWLObjectProperties
        (OWLOntology owlOnt)
    {
        for(OWLObjectProperty owlObjPrpty :
                owlOnt.getObjectPropertiesInSignature())
        {
            if (owlObjPrpty.isOWLTopObjectProperty())
            {
                continue;
            }
            String mrName = NameParser.getSimpleName(owlObjPrpty);
            MetaRelation mr = metaOnt.createMetaRelation(mrName);
            mr.setMetaRelationType
                (MetaRelation.MetaRelationType.OBJECT_RELATION);
            owlObjPrpty.accept(new DomainOWLObjectVisitor(mr));
        }

    }

    /**
     * Creates the meta relations in the meta ontology according to
     * the owl data properties in the active ontology
     * @param owlOnt The active owl ontology in Protege
     */
    private void createMetaRelationsFromOWLDataProperties
        (OWLOntology owlOnt)
    {
        for(OWLDataProperty owlDataPrpty :
                owlOnt.getDataPropertiesInSignature())
        {
            if (owlDataPrpty.isOWLTopDataProperty())
            {
                continue;
            }
            String mrName = NameParser.getSimpleName(owlDataPrpty);
            MetaRelation mr = metaOnt.createMetaRelation(mrName);
            owlDataPrpty.accept(new DomainOWLObjectVisitor(mr));
        }

    }

    /**
     * Creates the instances in the meta ontology according to
     * the owl individuals in the active ontology
     * @param owlOnt The active owl ontology in Protege
     */
    private void createRelationsFromOWLDataProperties(OWLOntology owlOnt)
    {
        for(OWLIndividual owlIndv : owlOnt.getIndividualsInSignature())
        {
            String instName = NameParser.getSimpleName(owlIndv);
            Instance inst = metaOnt.createInstance(instName);
            owlIndv.accept(new DomainOWLObjectVisitor(inst));
        }
    }
    
    /**
     * Releases the resources applied if there are some
     */
    public void dispose()
    {

    }
}

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

import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLIndividual;

import javax.swing.JPanel;
import javax.swing.JOptionPane;

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
    MetaOntology metaOnt = null;

    /**
     * The active ontology in Protege
     */
    OWLOntology owlOnt = null;

    public DomainOWLPanel(OWLModelManager owlMdlMgr)
    {
        owlOnt = owlMdlMgr.getActiveOntology();
        String strOntologyIRI = owlOnt.getOntologyID().
            getOntologyIRI().toString();
        metaOnt = MetaOntology.create
            (NameParser.getOWLOntologyName(strOntologyIRI));
        fillMetaOntologyFromOWLOntology();
        new DomainOWLPanelConfigureDlg(null,
                                       "Configure...",
                                       true).setVisible(true);  
    }

    /**
     * Fills the meta ontology in the domain owl from the active
     * owl ontology
     */
    private void fillMetaOntologyFromOWLOntology()
    {
        createMetaConceptsFromOWLClasses();
        createMetaRelationsFromOWLObjectProperties();
        createMetaRelationsFromOWLDataProperties();
        createRelationsFromOWLDataProperties();        
    }

    /**
     * Creates the meta concepts in the meta ontology according to
     * the owl classes in the active ontology
     */
    private void createMetaConceptsFromOWLClasses()
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
     */
    private void createMetaRelationsFromOWLObjectProperties()
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
     */
    private void createMetaRelationsFromOWLDataProperties()
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
     */
    private void createRelationsFromOWLDataProperties()
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

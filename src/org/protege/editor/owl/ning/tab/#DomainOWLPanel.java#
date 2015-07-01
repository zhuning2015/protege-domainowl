package org.protege.editor.owl.ning.tab;

import org.protege.editor.owl.model.OWLModelManager;

import org.protege.editor.owl.ning.domainOWL.MetaOntology;
import org.protege.editor.owl.ning.domainOWL.MetaConcept;

import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.util.OWLClassExpressionVisitorAdapter;
import org.semanticweb.owlapi.model.OWLDataAllValuesFrom;

import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JLabel;

/**
 * The main panel for the DomainOWL plugin
 *
 * @author Zhu Ning
 * @version 0.1.0
 */
public class DomainOWLPanel extends JPanel
{
    /**
     * The inner class for visiting the owl class
     */
    private class DomainOWLClassExpressionVisitor
        extends OWLClassExpressionVisitorAdapter
    {
        public void visit(OWLDataAllValuesFrom ce)
        {
            
        };

    }
    MetaOntology metaOnt = null;
    public DomainOWLPanel(OWLModelManager owlMdlMgr)
    {
        OWLOntology owlOnt = owlMdlMgr.getActiveOntology();
        metaOnt = MetaOntology.create(getOWLOntologyName(owlOnt));

        for(OWLClass owlCls : owlOnt.getClassesInSignature())
        {
            if (owlCls.isOWLThing())
                continue;
            MetaConcept mc =
                metaOnt.createMetaConcept(getOWLClassName(owlCls));
            parse(mc, owlCls);
        }
    }

    public void parse(MetaConcept mc, OWLClass owlCls)
    {

    }

    /**
     * Gets the simple name of the owl ontology
     * @param owlOnt The target owl ontology
     * @return The simple name of the owl ontology. The original format is
     * OntologyID(OntologyIRI(<http://www.*./name.owl>)), the return value
     * is name
     */
    private String getOWLOntologyName(OWLOntology owlOnt)
    {
        String strOntologyIRI = owlOnt.getOntologyID().
            getOntologyIRI().toString();
        int left = strOntologyIRI.lastIndexOf("/") + 1;
        int right = strOntologyIRI.lastIndexOf(".");
        return strOntologyIRI.substring(left, right);
    }

    /**
     * Gets the simple name of the OWL class
     * @param owlCls The target owl class
     * @return The simple name of the OWL class. The original format is
     * http://.....#name, the return value is name
     */
    private String getOWLClassName(OWLClass owlCls)
    {
        String strIRI = owlCls.getIRI().toString();
        int left = strIRI.indexOf("#") + 1;
        int right = strIRI.length();
        return strIRI.substring(left, right);
    }

    /**
     * Releases the resources applied if there are some
     */
    public void dispose()
    {

    }
}

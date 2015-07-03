package org.protege.editor.owl.ning.util;

import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLObject;

/**
 * A utility class for parsing name from some elements such as OWLClass
 *
 * @author Zhu Ning
 * @version 0.1.0
 */
public class NameParser
{
    /**
     * Gets the simple name of the owl ontology
     * @param owlOnt The target owl ontology
     * @return The simple name of the owl ontology. The original format is
     * OntologyID(OntologyIRI(<http://www.*./name.owl>)), the return value
     * is name
     */
    public static String getOWLOntologyName(OWLOntology owlOnt)
    {
        String strOntologyIRI = owlOnt.getOntologyID().
            getOntologyIRI().toString();
        int left = strOntologyIRI.lastIndexOf("/") + 1;
        int right = strOntologyIRI.lastIndexOf(".");
        return strOntologyIRI.substring(left, right);
    }

    /**
     * Gets the simple name of the OWL class
     * @param owlNamedObject The target owl class
     * @return The simple name of the OWL class. The original format is
     * <http://.....#name>, the return value is name
     */
    public static String getSimpleName(OWLObject owlObject)
    {
        String strOWLObject = owlObject.toString();
        int left = strOWLObject.indexOf("#") + 1;
        int right = strOWLObject.length() - 1;
        return strOWLObject.substring(left, right);
    }

}
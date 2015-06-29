package org.protege.editor.owl.ning.domainOWL;

import java.util.HashMap;

/**
 * The domain ontology class. It's the root for domain concepts, domain * relations.
 * @author Zhu Ning
 * @version 0.0.1
 */
public class DomainOntology extends NamedObject
{
    /**
     * A hashmap-structured field for storing domain concepts
     */
    private HashMap<String, DomainConcept> domainConcepts =
        new HashMap<String, DomainConcept>();

    /**
     * Adds an domain concept to the domain ontology
     * @param dc The domain concept to be added
     */
    public void addDomainConcept(DomainConcept dc)
    {
        domainConcepts.put(dc.getName(), dc);
    }

    /**
     * Check if there exsits a domain concept called dcName in the
     * domain ontology
     * @param dcName the name of some domain concept
     * @return true if there exists, false if there doesn't exist
     */
    public boolean containsDomainConcept(String dcName)
    {
        return domainConcepts.containsKey(dcName);
    }

    /**
     * If a domain concept changed its name, it will be scheduled to
     * update its record in DomainOntology
     * @param dc The domain concept whose name is changed
     * @param oldName The old name of the domain concept
     * @param newName The new name of the domain concept
     */
    public void updateDomainConcept(DomainConcept dc, String oldName, String newName)
    {
        domainConcepts.remove(oldName);
        dc.setName(newName);
        domainConcepts.put(newName, dc);
    }

    /**
     * Removes a domain concept from the domain ontology
     * @param dc The domain concept to be removed
     */
    public void removeDomainConcept(DomainConcept dc)
    {
        domainConcepts.remove(dc.getName());
    }
}

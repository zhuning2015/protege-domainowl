package org.protege.editor.owl.ning.domainOWL;

import java.util.HashMap;
import org.protege.editor.owl.ning.exception.AddSameDomainConceptException;
import org.protege.editor.owl.ning.exception.AddDomainConceptWithSameNameException;
import org.protege.editor.owl.ning.exception.NullDomainOntologyException;

/**
 * The domain ontology class. It's the root for domain concepts, domain * relations.
 * @author Zhu Ning
 * @version 0.0.2
 */
public class DomainOntology extends NamedObject
{
    /**
     * A hash map storing domain concepts
     */
    private HashMap<String, DomainConcept> domainConcepts =
        new HashMap<String, DomainConcept>();

    /**
     * A hash map storing domain relations
     */
    private Hashmap<String, DomainRelation> domainRelations =
        new HashMap<String, DomainRelation>();

    /**
     * The single domain ontology at a time spot
     */
    static DomainOntology singleDomainOntology = null;

    private DomainOntology(String name)
    {
        super(name);
    }

    /**
     * Creates a domain ontology with the name
     * @param name the name of the domainontology to be created
     * @return the domain ontology created
     */
    public static DomainOntology create(String name)
    {
        singleDomainOntology = new DomainOntology(name);
        return singleDomainOntology;
    }

    /**
     * Gets the domain ontology present
     * @return the present domain ontology
     */
    public static DomainOntology getDomainOntology()
    {
        if (singleDomainOntology == null)
            throw new NullDomainOntologyException("The present domain ontology is null");
        return singleDomainOntology;
    }

    /**
     * Adds a domain concept to the domain ontology
     * @param dc The domain concept to add
     */
    public void addDomainConcept(DomainConcept dc)
    {
        DomainConcept oldDc = getDomainConcept(dc.getName());
        if (oldDc != null)
        {
            if (oldDc == dc)
            {
                throw new AddSameDomainConceptException("Add a same domain concept");
            }else
            {
                throw new AddDomainConceptWithSameNameException("Add a domain concpet with the same name of some existed domain concept");
            }
        }
        domainConcepts.put(dc.getName(), dc);
    }

    /**
     * Adds a domain relation to the domain ontology
     * @param dr The domain relation to add
     */
    public void addDomainRelation(DomainRelation dr)
    {
        DomainRelation oldDr = getDomainRelation(dr.getName());

    }

    /**
     * Gets the domain concept with name  dcName
     * @param dcName the name of the domain concept to get
     * @return The domain concept with the name dcName. Null if the
     * domain concept doesn't exist.
     */
    public DomainConcept getDomainConcept(String dcName)
    {
        if (containsDomainConcept(dcName))
            return domainConcepts.get(dcName);
        else
            return null;
    }

    /**
     * Gets the domain relation with name drName
     * @param drName the name of the domain relation to get
     * @return The domain relation with the name drName. Null if the
     * domain relation doesn't exist.
     */
     public DomainRelation getDomainRelation(String drName)
     {
         if (containsDomainRelation(drName))
             return domainRelations.get(drName);
         else
             return null;
     }

    /**
     * Checks if there exists a domain concept called dcName in the
     * domain ontology
     * @param dcName The name of some domain concept
     * @return True if there exists such a domain concept, false if
     * not
     */
    public boolean containsDomainConcept(String dcName)
    {
        return domainConcepts.containsKey(dcName);
    }

    /**
     * Checks if there exists a domain relation called drName in the
     * domain ontology
     * @param drName The name of some domain relation
     * @return True if there exists such a domain relation, false if
     * not
     */
    public boolean containsDomainRelation(String drName)
    {
        return domainRelations.containsKey(drName);
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
     * @param dcName The domain concept to be removed
     */
    public void removeDomainConcept(String dcName)
    {
        domainConcepts.remove(dcName);
    }
}

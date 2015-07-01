package org.protege.editor.owl.ning.domainOWL;

import org.protege.editor.owl.ning.exception.BasicException;

/**
 * The domain ontology class. It's the root for domain concepts, domain relations.
 * @author Zhu Ning
 * @version 0.1.0
 */
public class DomainOntology extends NamedObject
{
    /**
     * A DomainConcept-type OntologyContainer storing domain concepts
     */
    private OntologyContainer<DomainConcept> domainConcepts =
        new OntologyContainer<DomainConcept>();

    /**
     * A DomainRelation-type OntologyComponent storing domain relations
     */
    private OntologyContainer<DomainRelation> domainRelations =
        new OntologyContainer<DomainRelation>();

    /**
     * The single domain ontology at a time spot
     */
    private static DomainOntology singleDomainOntology = null;

    private DomainOntology(String name)
    {
        super(name);
    }

    /**
     * Creates a domain ontology with the name
     * @param name The name of the domainontology to create
     * @return The domain ontology created
     * @exception BasicException Throws when the name is empty or
     *         blank
     */
    public static DomainOntology create(String name)
    {
        singleDomainOntology = new DomainOntology(name);
        return singleDomainOntology;
    }

    /**
     * Gets the present domain ontology
     * @return The present domain ontology
     * @exception BasicException Throws when the present domain
     * ontology is null
     */
    public static DomainOntology getDomainOntology()
    {
        if (singleDomainOntology == null)
            throw new BasicException
                ("The present domain ontology is null");
        return singleDomainOntology;
    }

    /**
     * Adds a domain concept to the domain ontology
     * @param dc The domain concept to add
     */
    public void addDomainConcept(DomainConcept dc)
    {
        domainConcepts.addComponent(dc);
    }

    /**
     * Adds a domain relation to the domain ontology
     * @param dr The domain relation to add
     */
    public void addDomainRelation(DomainRelation dr)
    {
        domainRelations.addComponent(dr);
    }

    /**
     * Gets the domain concept with name  dcName
     * @param dcName the name of the domain concept to get
     * @return The domain concept with the name dcName. Null if the
     * domain concept doesn't exist.
     */
    public DomainConcept getDomainConcept(String dcName)
    {
        return domainConcepts.getComponent(dcName);
    }

    /**
     * Gets the domain relation with name drName
     * @param drName the name of the domain relation to get
     * @return The domain relation with the name drName. Null if the
     * domain relation doesn't exist.
     */
     public DomainRelation getDomainRelation(String drName)
     {
         return domainRelations.getComponent(drName);
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
        return domainConcepts.containsComponent(dcName);
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
        return domainRelations.containsComponent(drName);
    }

    /**
     * If a domain concept changed its name, it will be scheduled to
     * update its record in the domain ontology
     * @param dc The domain concept whose name is changed
     * @param newName The new name of the domain concept
     */
    public void updateDomainConcept(DomainConcept dc, String newName)
    {
        domainConcepts.update(dc, newName);
    }

    /**
     * If a domain relation changed its name, it will be schuled to
     * update its record in the domain ontology
     * @param dr The domain relation whose name is changed
     * @param newName The new name of the domain relation
     */
    public void updateDomainRelation(DomainRelation dr, String newName)
    {
        domainRelations.update(dr, newName);
    }

    /**
     * Removes a domain concept from the domain ontology
     * @param dcName The name of the domain concept to remove
     */
    public void removeDomainConcept(String dcName)
    {
        domainConcepts.remove(dcName);
    }

    /**
     * Removes a domain relation from the domain ontology
     * @param drName The name of the domain relation to remove
     */
    public void removeDomainRelation(String drName)
    {
        domainRelations.remove(drName);
    }
}

package org.protege.editor.owl.ning.domainOWL;

import org.protege.editor.owl.ning.exception.BasicException;
import org.protege.editor.owl.ning.domainOWL.DomainOntology;
import org.protege.editor.owl.ning.domainOWL.MetaConcept;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * The domain concept class representing the customed ontology concept
 * from the meta ontology concept
 *
 * @author Zhu Ning
 * @version 0.1.3
 */
public class DomainConcept extends NamedObject
{
    /**
     * A list which consists of all the outgoing relation names of the
     * domain concept.
     */
    private ArrayList<String> outgoingRelations =
        new ArrayList<String>();

    /**
     * Gets the names of the outgoing relations of the domain concept
     * @return The names of the outgoing relations of the domain
     * concept
     */
    public ArrayList<String> getOutgoingRelations()
    {
        return outgoingRelations;
    }

    /**
     * Sets the outgoing relations of the domain concept, mainly used
     * for persistence of domain concepts
     * @param outgoingRelations The names of the outgoing relations
     * of the domain concept
     */
    public void setOutgoingRelations(ArrayList<String>
                                                  outgoingRelations)
    {
        this.outgoingRelations = outgoingRelations;
    }

    /**
     * A list which consists of all the incoming relation names of
     * the domain concept.
     */
    private ArrayList<String> incomingRelations =
        new ArrayList<String>();

    /**
     * Gets the names of the incoming relations of the domain concepts
     * @return The names of the incoming relations of the domain
     * concept
     */
    public ArrayList<String> getIncomingRelations()
    {
        return incomingRelations;
    }

    /**
     * Sets the incoming relations of the domain concepts
     * @param incomingRelations The names of the incoming relations
     * of the domain concept
     */
    public void setIncomingRelations(ArrayList<String>
                                              incomingRelations)
    {
        this.incomingRelations = incomingRelations;
    }

    /**
     * The name of the meta concept in the meta ontlogy which
     * the domain concept correponds to
     */
    private String metaConceptName = "";

    /**
     * Gets the name of the corresponding meta concept
     * @return The name of the corresponing meta concept
     */
    public String getMetaConceptName()
    {
        return metaConceptName;
    }

    /**
     * Sets the name of the corresponding meta concept
     * @param The name of the corresponding meta concept
     */
    public void setMetaConceptName(String metaConceptName)
    {
        this.metaConceptName = metaConceptName;
    }

    /**
     * The name of the instance in the meta ontology which
     * the domain concept corresponds to
     */
    private String instanceName = "";

    /**
     * Gets the name of the corresponding instance
     * @return The name of the corresponding instance
     */
    public String getInstanceName()
    {
        return instanceName;
    }

    /**
     * Sets the name of the corresponding instance
     * @param instanceName The name of the specified corresponding
     * instance
     */
    public void setInstanceName(String instanceName)
    {
        this.instanceName = instanceName;
    }

    private DomainConcept(String name)
    {
        super(name);
    }

    public DomainConcept(String name,
                         ArrayList<String> outgoingRelations,
                         ArrayList<String> incomingRelations,
                         String metaConceptName,
                         String instanceName)
    {
        super(name);
        setOutgoingRelations(outgoingRelations);
        setIncomingRelations(incomingRelations);
        setMetaConceptName(metaConceptName);
        setInstanceName(instanceName);

        DomainOntology dt = DomainOntology.getDomainOntology();
        dt.addDomainConcept(this);
    }

    /**
     * Creates a domain concept whose name is name
     * @param name The name of the domain concept created to create
     * @return The domain concept created
     * @exception BasicException Throws when the name spefied is empty
     *         or blank
     */
    public static DomainConcept create(String name)
    {
        checkName(name,
                  "The specified name for the domain concept is empty",
                  "The specified name for the domain concept consists of all spaces");

        DomainConcept dc = new DomainConcept(name);

        DomainOntology dt = DomainOntology.getDomainOntology();
        dt.addDomainConcept(dc);

        return dc;
    }

    /**
     * Adds an outgoing relation to the domain concept
     * @param relationName The name of the outgoing relation
     * @exception BasicException Throws when adding another same
     *        outgoing relation
     */
    public void addOutgoingRelation(String relationName)
    {
        if (outgoingRelations.contains(relationName))
            throw new BasicException("a domain concept linked the same outgoing relation more than once");

        outgoingRelations.add(relationName);
    }

    /**
     * Chceks if the domain concept contains an outgoing relation called
     * relationName
     * @param relationName The name of the outgoing relation to check
     * @return True if the domain concept contains such a relation,
     *         false otherwise
     */
    public boolean containsOutgoingRelation(String relationName)
    {
        return outgoingRelations.contains(relationName);
    }

    /**
     * Removes the outgoing relation whose name is relationName
     * @param relationName The name of the outgoing relation to remove
     */
    public void removeOutgoingRelation(String relationName)
    {
        outgoingRelations.remove(relationName);
    }

    /**
     * Adds an incoming relation to the domain concept
     * @param relationName The name of the incoming relation to add
     * @exception BasicException Throws when adding another same
     *        incoming relation
     */
    public void addIncomingRelation(String relationName)
    {
        if (incomingRelations.contains(relationName))
            throw new BasicException("a domain concept linked the same incoming relation more than once");

        incomingRelations.add(relationName);
    }

    /**
     * Checks if the domain concept contains an incoming relation called
     * relationName
     * @param relationName The name of the incoming relation to check
     * @return True if it contains such a relation, false otherwise
     */
    public boolean containsIncomingRelation(String relationName)
    {
        return incomingRelations.contains(relationName);
    }

    /**
     * Removes the incoming relation whose name is relationName
     * @param relationName The name of the incoming relation to remove
     */
    public void removeIncomingRelation(String relationName)
    {
        incomingRelations.remove(relationName);
    }

    /**
     * Sets the meta concept of the domain concept
     * @param mc The meta concept to specify the domain concept
     */
    public void setMetaConcept(MetaConcept mc)
    {
        metaConceptName = mc.getName();
    }

    /**
     * Sets the corresponding instance in the meta ontolgy of the
     * domain ontology
     * @param ins The corresponding instance to specify the domain
     *        concept
     */
    public void setCorrespondInstance(Instance ins)
    {
        instanceName = ins.getName();
    }

    /**
     * Gets the meta concept of the domain concept
     * @return The meta concept of the domain concept
     */
    public MetaConcept getMetaConcept()
    {
        return MetaOntology.getMetaOntology().
            getMetaConcept(metaConceptName);
    }

    /**
     * Gets an iterator over the elements in the arraylist
     * outgoingRelations in a proper sequence
     * @return An iterator over the elements in outgoingRelations list
     * in a proper sequence
     */
    public Iterator<String> getOutgoingRelationIterator()
    {
        return outgoingRelations.iterator();
    }

    /**
     * Updates the outgoing relation whose name is originalName to
     * newName
     * @param originalName The original name of the outgoing relation
     * @param newName The new name of the outgoing relation
     */
    public void updateOutgoingRelation(String originalName,
                                       String newName)
    {
        int index = outgoingRelations.indexOf(originalName);
        outgoingRelations.set(index, newName);
    }

    /**
     * Updates the incoming relation whose name is originalName to
     * newName
     * @param originalName The original name of the incoming relation
     * @param newName The new name of the incoming relation
     */
    public void updateIncomingRelation(String originalName,
                                       String newName)
    {
        int index = incomingRelations.indexOf(originalName);
        incomingRelations.set(index, newName);
    }


    @Override
    public void changeName(String newName)
    {
        setName(newName);

        DomainOntology domainOnt = DomainOntology.getDomainOntology();
        for(String incomingRelationName : incomingRelations)
        {
            DomainRelation dr =
                domainOnt.getDomainRelation(incomingRelationName);
            dr.setDstDcName(newName);
        }

        for(String outgoingRelationName : outgoingRelations)
        {
            DomainRelation dr =
                domainOnt.getDomainRelation(outgoingRelationName);
            dr.setSrcDcName(newName);
        }
    }
}

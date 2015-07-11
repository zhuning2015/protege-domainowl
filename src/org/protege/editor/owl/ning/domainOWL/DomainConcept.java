package org.protege.editor.owl.ning.domainOWL;

import org.protege.editor.owl.ning.exception.BasicException;
import org.protege.editor.owl.ning.domainOWL.DomainOntology;
import org.protege.editor.owl.ning.domainOWL.MetaConcept;

import java.util.ArrayList;

/**
 * The domain concept class representing the customed ontology concept
 * from the meta ontology concept
 *
 * @author Zhu Ning
 * @version 0.1.0
 */
public class DomainConcept extends NamedObject
{
    /**
     * A list which consists of all the outgoing relation names of the
     * domain concept.
     */
    ArrayList<String> outgoingRelations = new ArrayList<String>();

    /**
     * A list which consists of all the incoming relation names of
     * the domain concept.
     */
    ArrayList<String> incomingRelations = new ArrayList<String>();

    /**
     * The corresponding meta concept in the meta ontology
     */
    private MetaConcept meta = null;

    /**
     * The corresponding instance in the meta ontology
     */
    private Instance correspond = null;

    private DomainConcept(String name)
    {
        super(name);
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
        checkName(name, "The specified name for the domain concept is empty", "The specified name for the domain concept consists of all spaces");
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
        meta = mc;
    }

    /**
     * Sets the corresponding instance in the meta ontolgy of the
     * domain ontology
     * @param ins The corresponding instance to specify the domain
     *        concept
     */
    public void setCorrespondInstance(Instance ins)
    {
        correspond = ins;
    }

    /**
     * Gets the meta concept of the domain concept
     * @return The meta concept of the domain concept
     */
    public MetaConcept getMetaConcept()
    {
        return meta;
    }

    @Override
    public String toString()
    {
        return getName();
    }
}

package org.protege.editor.owl.ning.domainOWL;

import org.protege.editor.owl.ning.exception.BasicException;
import org.protege.editor.owl.ning.domainOWL.Restriction;

import java.util.ArrayList;

/**
 * The basic class for MetaConcept and Instance in the meta
 * ontology
 *
 * @author Zhu Ning
 * @version 0.1.0
 */
public abstract class MetaNode extends NamedObject
{
    /**
     * A list which consists of all the restrictions of the meta node.
     */
    private ArrayList<Restriction> restrictions =
        new ArrayList<Restriction>();

    /**
     * A list which consists of all the outgoing relations from the
     * meta node
     */
    private ArrayList<String> outgoingMetaRelations =
        new ArrayList<String>();

    public MetaNode(String name)
    {
        super(name);
    }

    /**
     * Adds a restriction to the meta node
     * @param restrc The restriction to add
     * @exception BasicException Throws when the restriction
     * already exists
     */
    public void addRestriction(Restriction restrc)
    {
        if (restrictions.contains(restrc))
            throw new BasicException
                ("The restriction  already exists");
        restrictions.add(restrc);
    }

    /**
     * Adds an outgoing meta relation to the meta node
     * @param outgoingMrRltName The name of the outgoing meta relation
     * to add
     * @exception BasicException Throws when the outgoing meta relation
     * already exists
     */
    public void addOutgoingMetaRelation(String outgoingMrRltName)
    {
        if (outgoingMetaRelations.contains(outgoingMrRltName))
            throw new BasicException("duplicate outgoing relation name");
        outgoingMetaRelations.add(outgoingMrRltName);
    }

    /**
     * Checks if the meta node contains the meta relation called
     * outgoingRltName
     * @param outgoingMrRltName The name of the outgoing meta relation to
     * check
     * @return True if the meta node contains such an outgoing meta
     * relation, false otherwise
     */
    public boolean containsOutgoingMetaRelation(String outgoingMrRltName)
    {
        return outgoingMetaRelations.contains(outgoingMrRltName);
    }

    /**
     * Checks if the meta node contains the restriction restrc
     * @param restrc The restriction to check
     * @return True if the meta node contains such a restriction,
     * false otherwise.
     */
    public boolean containsRestriction(Restriction restrc)
    {
        return restrictions.contains(restrc);
    }
}

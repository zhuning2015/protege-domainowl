package org.protege.editor.owl.ning.domainOWL;

import org.protege.editor.owl.ning.exception.BasicException;

import java.util.ArrayList;

/**
 * The basic class for MetaConcept and Instance in the meta
 * ontology
 *
 * @author Zhu Ning
 * @version 0.0.1
 */
public abstract class MetaNode extends NamedObject
{
    /**
     * A list which consists of all the outgoing meta relation names
     * of the meta node.
     */
    private ArrayList<String> outgoingMrNames =
        new ArrayList<String>();

    /**
     *  A list which consists of all the incoming meta relation
     *  names of the meta node.
     */
    private ArrayList<String> incomingMrNames =
        new ArrayList<String>();

    public MetaNode(String name)
    {
        super(name);
    }

    /**
     * Adds an outgoing meta relation to the meta node
     * @param metaRelationName The name of the outgoing meta relation
     * @exception BasicException Throws the outgoing meta relation
     * already exists
     */
    public void addOutgoingMetaRelation(String metaRelationName)
    {
        if (outgoingMrNames.contains(metaRelationName))
            throw new BasicException
                ("The outgoing meta relation called "
                        + metaRelationName + " already exists");

        outgoingMrNames.add(metaRelationName);
    }

    /**
     * Adds an incoming meta relation to the meta node
     * @param metaRelationName The name of the incoming meta relation
     * @exception BasicException Throws when the incoming meta relation
     * already exists
     */
    public void addIncomingMetaRelation(String metaRelationName)
    {
        if (incomingMrNames.contains(metaRelationName))
            throw new BasicException
                ("The incoming meta relation called "
                 + metaRelationName + " already exists");

        incomingMrNames.add(metaRelationName);
    }

    /**
     * Checks if the meta node contains an outgoing meta relation
     * called metaRelationName
     * @param metaRelationName The name of the outgoing meta relation
     *        to check
     * @return True if the meta node contains such an outgoing meta
     *         relation, false otherwise.
     */
    public boolean containsOutgoingMetaRelation
        (String metaRelationName)
    {
        return outgoingMrNames.contains(metaRelationName);
    }

    /**
     * Checks if the meta node contains an incoming meta relation
     * called metaRelationName
     * @param metaRelationName The name of the incoming meta relation
     *        to check
     * @return True if the meta node contains such an incoming meta
     *         relation, false otherwise.
     */
    public boolean containsIncomingMetaRelation
        (String metaRelationName)
    {
        return incomingMrNames.contains(metaRelationName);
    }
}

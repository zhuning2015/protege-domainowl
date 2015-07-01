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

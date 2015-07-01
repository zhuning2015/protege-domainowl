package org.protege.editor.owl.ning.domainOWL;

import org.protege.editor.owl.ning.domainOWL.MetaOntologyElement;
import org.protege.editor.owl.ning.exception.BasicException;

import java.util.ArrayList;

/**
 * The meta relation class representing the meta ontology
 * relation in the meta ontology
 *
 * @author Zhu Ning
 * @version 0.1.0
 */
public class MetaRelation extends MetaOntologyElement
{
    /**
     * A list which consists of all the source meta nodes
     * (meta concepts or instances) of the meta relation
     */
    ArrayList<String> srcMnNames = new ArrayList<String>();

    /**
     * A list which consists of all the destination meta
     ＊nodes (meta concepts or instances) of the meta relation
     */
    ArrayList<String> dstMnNames = new ArrayList<String>();

    public MetaRelation(String name)
    {
        super(name);
    }

    /**
     * Adds the source meta node
     * @param srcMnName The meta node to add as the source
     * @exception BasicException Throws the source meta node
     * already exists
     */
    public void addSrc(String srcMnName)
    {
        if (srcMnNames.contains(srcMnName))
            throw new BasicException
                ("Source meta node already exists");

        srcMnNames.add(srcMnName);
    }

    /**
     * Adds the destination meta node
     * @param dstMnName The meta node to add as the destination
     * @exception BasicException Throws when the destination meta
     * node already exists
     */
    public void addDst(String dstMnName)
    {
        if (dstMnNames.contains(dstMnName))
            throw new BasicException
                ("Destination meta node already exists");

        dstMnNames.add(dstMnName);
    }

    /**
     * Checks if contains a source meta node called srcMnName
     * @param srcMnName The name of the meta node to check
     * @return True if the meta relation contains, false otherwise.
     */
    public boolean containsSrc(String srcMnName)
    {
       return srcMnNames.contains(srcMnName);
    }

    /**
     * Checks if contains a destination meta node called
     * dstMnName
     * @param dstMnName The name of the meta node to check
     * @return True if the meta relation contains, false otherwise.
     */
    public boolean containsDst(String dstMnName)
    {
        return dstMnNames.contains(dstMnName);
    }
}

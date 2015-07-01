package org.protege.editor.owl.ning.domainOWL;

import org.protege.editor.owl.ning.domainOWL.MetaNode;

/**
 * The instance representing the meta ontology instance in the
 * meta ontology
 *
 * @author Zhu Ning
 * @version 0.1.0
 */
public class Instance extends MetaNode
{
    /**
     * The corresponding meta concept
     */
    private MetaConcept meta = null;

    public Instance(String name)
    {
        super(name);
    }

    /**
     * Sets the corresponding meta concept of the instance in
     * the meta ontology
     * @param mc The corresponding meta concept of the instance
     */
    public void setMetaConcept(MetaConcept mc)
    {
        meta = mc;
    }
}

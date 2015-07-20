package org.protege.editor.owl.ning.domainOWL;

import org.protege.editor.owl.ning.exception.BasicException;
import org.protege.editor.owl.ning.domainOWL.DomainOntology;
import org.protege.editor.owl.ning.domainOWL.MetaRelation;
import org.protege.editor.owl.ning.domainOWL.DomainConcept;

/**
 * The domain relation class representing the customized relation from
 * the meta ontology relation
 *
 * @author Zhu Ning
 * @version 0.1.1
 */
public class DomainRelation extends NamedObject
{
    /**
     * The source domain concept of the relation
     */
    private DomainConcept srcDc = null;

    /**
     * The destination concept of the relation
     */
    private DomainConcept dstDc = null;

    /**
     * The corresponding meta relation in the meta ontology
     */
    private MetaRelation meta = null;

    public DomainRelation(String name)
    {
        super(name);
    }

    /**
     * Creates a domain relation whose name is name
     * @param name The name of the domain relation to create
     * @return The domain relation created
     * @exception BasicException Throws when the name is empty or blank
     */
    public static DomainRelation create(String name)
    {
        checkName(name,
                  "The name of the specified domain relation is null",
                  "The chars in the name are all spaces");

        DomainRelation dr = new DomainRelation(name);

        DomainOntology dt = DomainOntology.getDomainOntology();
        dt.addDomainRelation(dr);

        return dr;
    }

    /**
     * Sets the source domain concept of the relation
     * @param srcDc The source domain concept
     */
    public void setSrc(DomainConcept srcDc)
    {
        this.srcDc = srcDc;
        srcDc.addOutgoingRelation(getName());
    }

    /**
     * Get the source domain concept
     * @return The source domain concept
     */
    public DomainConcept getSrc()
    {
        return srcDc;
    }

    /**
     * Sets the destination domain concept of the relation
     * @param dstDc The destination domain concept
     */
    public void setDst(DomainConcept dstDc)
    {
        this.dstDc = dstDc;
        dstDc.addIncomingRelation(getName());
    }

    /**
     * Gets the destination domain concept of the relation
     * @return The destination domain concept
     */
    public DomainConcept getDst()
    {
        return dstDc;
    }

    /**
     * Sets the meta relation of the domain relation
     * @param mr The meta relation to specify to the domain relation
     */
    public void setMetaRelation(MetaRelation mr)
    {
        meta = mr;
    }

    @Override
    public void setName(String name)
    {
        if (srcDc != null)
        {
            srcDc.updateOutgoingRelation(getName(), name);
        }

        if (dstDc != null)
        {
            dstDc.updateIncomingRelation(getName(), name);
        }
        super.setName(name);
    }
}

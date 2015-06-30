package org.protege.editor.owl.ning.domainOWL;

/**
 * The domain relation class representing the customized relation from
 * the meta ontology relation
 *
 * @author Zhu Ning
 * @version 0.0.1
 */
public class DomainRelation extends NamedObject
{
    /**
     * The name of the source domain concept of the relation
     */
    private String srcDcName = "";

    /**
     * The name of the destination concept of the relation
     */
    private String dstDcName = "";

    public DomainRelation(String name)
    {
        super(name);
    }

    /**
     * Creates a domain relation whose name is name
     * @param name The name of the domain relation to create
     * @return The domain relation created
     */
    public static DomainRelation create(String name)
    {
        DomainRelation dr = new DomainRelation(name);

        DomainOntology dt = DomainOntology.getDomainOntology();
        dt.addDomainRelation(dr);

        return dr;
    }

    /**
     * Sets the source domain concept of the relation
     * @param srcDcName The name of the source domain concept
     */
    public void setSrc(String srcDcName)
    {
        if (srcDcName.isEmpty())
            return;
        if (srcDcName.trim().isEmpty())
            return;
        this.srcDcName = srcDcName;
    }

    /**
     * Sets the destination domain concept of the relation
     * @param dstDcName The name of the destination domain concept
     */
    public void setDst(String dstDcName)
    {
        if (dstDcName.isEmpty())
            return;
        if (dstDcName.trim().isEmpty())
            return;
        this.dstDcName = dstDcName;
    }
}

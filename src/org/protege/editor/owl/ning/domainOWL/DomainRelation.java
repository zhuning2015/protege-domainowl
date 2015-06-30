package org.protege.editor.owl.ning.domainOWL;

import org.protege.editor.owl.ning.exception.NullNameException;

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
        if (name.isEmpty())
            throw new NullNameException("The name of the specified domain relation is null");
        if (name.trim().isEmpty())
            throw new NullNameException("The chars consisting the name of the specified domain relation are all spaces");

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
            throw new NullNameException("The name of the specified source domain concept is empty");
        if (srcDcName.trim().isEmpty())
            throw new NullNameException("The chars consisting the name of the specified source domain concept are all spaces");
        this.srcDcName = srcDcName;
    }

    /**
     * Get the name of the source domain concept
     * @return The name of the source domain concept
     */
    public String getSrc()
    {
        return srcDcName;
    }

    /**
     * Sets the destination domain concept of the relation
     * @param dstDcName The name of the destination domain concept
     */
    public void setDst(String dstDcName)
    {
        if (dstDcName.isEmpty())
            throw new NullNameException("The name of the specified source domain concept is empty");
        if (dstDcName.trim().isEmpty())
            throw new NullNameException("The chars consisting the name of the specified source domain concept are all spaces");
        this.dstDcName = dstDcName;
    }

    /**
     * Get the name of the destination domain concept of the relation
     * @return The name of the destination domain concept
     */
    public String getDst()
    {
        return dstDcName;
    }
}

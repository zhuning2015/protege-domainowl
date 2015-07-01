package org.protege.editor.owl.ning.domainOWL;

import org.protege.editor.owl.ning.exception.BasicException;
import org.protege.editor.owl.ning.domainOWL.DomainOntology;
import org.protege.editor.owl.ning.domainOWL.MetaRelation;

/**
 * The domain relation class representing the customized relation from
 * the meta ontology relation
 *
 * @author Zhu Ning
 * @version 0.1.0
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
        checkName(name, "The name of the specified domain relation is null", "The chars consisting the name of the specified domain relation are all spaces");

        DomainRelation dr = new DomainRelation(name);

        DomainOntology dt = DomainOntology.getDomainOntology();
        dt.addDomainRelation(dr);

        return dr;
    }

    /**
     * Sets the source domain concept of the relation
     * @param srcDcName The name of the source domain concept
     * @exception BasicException Throws when the source domain concept
     *        is not existent
     */
    public void setSrc(String srcDcName)
    {
        DomainOntology dt = DomainOntology.getDomainOntology();
        if (!dt.containsDomainConcept(srcDcName))
            throw new BasicException("Source domain concept" + srcDcName+" is not existent");
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
     * @exception BasicException Throws when the destination domain
     *        concept is not existent
     */
    public void setDst(String dstDcName)
    {
        DomainOntology dt = DomainOntology.getDomainOntology();
        if (!dt.containsDomainConcept(dstDcName))
            throw new BasicException("The destination domain concept "
                                     + dstDcName+" is not existent");
        this.dstDcName = dstDcName;
    }

    /**
     * Gets the name of the destination domain concept of the relation
     * @return The name of the destination domain concept
     */
    public String getDst()
    {
        return dstDcName;
    }

    /**
     * Sets the meta relation of the domain relation
     * @param mr The meta relation to specify to the domain relation
     */
    public void setMetaRelation(MetaRelation mr)
    {
        meta = mr;
    }
}

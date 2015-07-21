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
 * @version 0.1.2
 */
public class DomainRelation extends NamedObject
{
    /**
     * The name of the source domain concept of the relation
     */
    private String srcDcName = "";

    /**
     * Gets the name of the source domain concept
     * @return The name of the source domain concept of the
     * domain relation
     */
    public String getSrcDcName()
    {
        return srcDcName;
    }

    /**
     * Sets the name of the source domain concept
     * @param srcDcName The name of the specified source domain concept
     * for the domain relation
     */
    public void setSrcDcName(String srcDcName)
    {
        this.srcDcName = srcDcName;
    }

    /**
     * The name of the destination concept of the relation
     */
    private String dstDcName = "";

    /**
     * Gets the name of the destination concept of the domain
     * relation
     * @return The name of the destination concept for the domain
     * relation
     */
    public String getDstDcName()
    {
        return dstDcName;
    }

    /**
     * Sets the name of the destination concept
     * @param dstDcName The name of the specified destination concept
     */
    public void setDstDcName(String dstDcName)
    {
        this.dstDcName = dstDcName;
    }

    /**
     * The name of the corresponding meta relation in the meta ontology
     */
    private String metaRelationName = "";

    /**
     * Gets the name of the meta relation
     * @return The name of the meta relation for the domain relation
     */
    public String getMetaRelationName()
    {
        return metaRelationName;
    }

    /**
     * Sets the name of the meta relation
     * @param metaRelationName The name of the specified meta relation
     */
    public void setMetaRelationName(String metaRelationName)
    {
        this.metaRelationName = metaRelationName;
    }

    private DomainRelation(String name)
    {
        super(name);
    }

    public DomainRelation(String name,
                          String srcDcName,
                          String dstDcName,
                          String metaRelationName)
    {
        super(name);
        setSrcDcName(srcDcName);
        setDstDcName(dstDcName);
        setMetaRelationName(metaRelationName);

        DomainOntology dt = DomainOntology.getDomainOntology();
        dt.addDomainRelation(this);
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
     * Links the source domain concept of the relation
     * @param srcDc The source domain concept
     */
    public void linkToSrc(DomainConcept srcDc)
    {
        srcDcName = srcDc.getName();
        srcDc.addOutgoingRelation(getName());
    }

    /**
     * Get the source domain concept
     * @return The source domain concept
     */
    public DomainConcept getSrc()
    {
        DomainOntology domainOnt =  DomainOntology.getDomainOntology();
        return domainOnt.getDomainConcept(srcDcName);
    }

    /**
     * Links the destination domain concept of the relation
     * @param dstDc The destination domain concept
     */
    public void linkToDst(DomainConcept dstDc)
    {
        dstDcName = dstDc.getName();
        dstDc.addIncomingRelation(getName());
    }

    /**
     * Gets the destination domain concept of the relation
     * @return The destination domain concept
     */
    public DomainConcept getDst()
    {
        DomainOntology domainOnt =  DomainOntology.getDomainOntology();
        return domainOnt.getDomainConcept(dstDcName);
    }

    /**
     * Sets the meta relation of the domain relation
     * @param mr The meta relation to specify to the domain relation
     */
    public void setMetaRelation(MetaRelation mr)
    {
        metaRelationName = mr.getName();
    }

    @Override
    public void changeName(String name)
    {
        DomainConcept srcDc = getSrc();
        if (srcDc != null)
        {
            srcDc.updateOutgoingRelation(getName(), name);
        }

        DomainConcept dstDc = getDst();
        if (dstDc != null)
        {
            dstDc.updateIncomingRelation(getName(), name);
        }

        super.setName(name);
    }
}

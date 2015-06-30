package org.protege.editor.owl.ning.domainOWL;

/**
 * The meta ontology class consisting all of the meta concepts, meta
 * relations and instances in the meta ontology
 *
 * @author Zhu Ning
 * @version 0.0.1
 */
public class MetaOntology extends NamedObject
{
    /**
     * A MetaConcept-type OntologyContainer storing meta concepts
     */
    private OntologyContainer<MetaConcept> metaCncpts =
        new OntologyContainer<MetaConcept>();

    /**
     * A Instance-type OntologyContainer storing instances
     */
    private OntologyContainer<Instance> instances =
        new OntologyContainer<Instance>();

    /**
     * A MetaRelation-type OntologyContainer storing meta relations
     */
    private OntologyContainer<MetaRelation> metaRelations =
        new OntologyContainer<MetaRelation>();

    public MetaOntology(String name)
    {
        super(name);
    }

    /**
     * Creates a meta concept named by mcName
     * @param mcName The name of the meta concept to create
     * @return The meta concept created
     */
    public MetaConcept createMetaConcept(String mcName)
    {
        MetaConcept mc = new MetaConcept(mcName);
        metaCncpts.addComponent(mc);
        return mc;
    }

    /**
     * Creates a meta relation named by mrName
     * @param mrName The name of the meta relation to create
     * @return The meta relation created
     */
    public MetaRelation createMetaRelation(String mrName)
    {
        MetaRelation mr = new MetaRelation(mrName);
        metaRelations.addComponent(mr);
        return mr;
    }

    /**
     * Creates an instance or individual named by instName
     * @param instName The name of the instance or the individual
     * to create
     * @return The instance created
     */
    public Instance createInstance(String instName)
    {
        Instance inst = new Instance(instName);
        instances.addComponent(inst);
        return inst;
    }
}

package org.protege.editor.owl.ning.domainOWL;

import org.protege.editor.owl.ning.exception.BasicException;

/**
 * The meta ontology class consisting all of the meta concepts, meta
 * relations and instances in the meta ontology
 *
 * @author Zhu Ning
 * @version 0.1.0
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

    private static MetaOntology singleMetaOntology = null;

    private MetaOntology(String name)
    {
        super(name);
    }

    /**
     * Creates a meta ontology with the name
     * @param name The name o the meta ontology to create
     * @return The meta ontology created
     * @exception BasicException Throws when the name is empty or blank
     */
    public static MetaOntology create(String name)
    {
        singleMetaOntology = new MetaOntology(name);
        return singleMetaOntology;
    }

    /**
     * Gets the present meta ontology
     * @return The present meta ontology
     * @exception BasicException Throws when the present meta ontology
     * is null
     */
    public static MetaOntology getMetaOntology()
    {
        if (singleMetaOntology == null)
            throw new BasicException("The present meta ontology is null");
        return singleMetaOntology;
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
     * Checks if the meta ontology contains a meta concept called mcName
     * @param mcName The name of the meta concept to check
     * @return True if the meta ontology contains such a meta concept,
     * otherwise false
     */
    public boolean containsMetaConcept(String mcName)
    {
        return metaCncpts.containsComponent(mcName);
    }

    /**
     * Gets the count of the meta concepts in the meta ontology
     * @return The count of the meta concepts in the meta ontology
     */
    public int getMetaConceptCount()
    {
        return metaCncpts.size();
    }

    /**
     * Get the meta concept called mcName in the meta ontology
     * @param mcName The name of the meta concept to get
     * @return The meta concept to get
     */
    public MetaConcept getMetaConcept(String mcName)
    {
        return metaCncpts.getComponent(mcName);
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
     * Checks if the meta ontology contains a meta relation called mrName
     * @param mrName The name of the meta relation to check
     * @return True if the meta ontology contains such a meta relation,
     * otherwise false
     */
    public boolean containsMetaRelation(String mrName)
    {
        return metaRelations.containsComponent(mrName);
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

    /**
     * Checks if the meta ontology contains an instance/individual
     * called instName
     * @param instName The name of the instance/individual to check
     * @return True if the meta ontology contains such an
     * instance/individual, false otherwise
     */
    public boolean containsInstance(String instName)
    {
        return instances.containsComponent(instName);
    }

    /**
     * Gets the index-th meta concept in the meta ontology
     * @param The index of the meta concept to get
     * @return The index-th meta concept
     */
    public MetaConcept getMetaConcept(int index)
    {
        return metaCncpts.getComponent(index);
    }

    public int getMetaRelationCount()
    {
        return metaRelations.size();
    }

    public MetaRelation getMetaRelation(int index)
    {
        return metaRelations.getComponent(index);
    }

    public int getInstanceCount()
    {
        return instances.size();
    }

    public Instance getInstance(int index)
    {
        return instances.getComponent(index);
    }

    public Object[] getKeyParameter()
    {
        return new Object[]{"Name", "IsIncluded", "Icon"};
    }
    public Object[][] getKeyParameterValues()
    {
        return new Object[][]{new Object[]{"Test1", false, "no"}};
    }
}

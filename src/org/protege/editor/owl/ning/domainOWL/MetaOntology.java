package org.protege.editor.owl.ning.domainOWL;

import org.protege.editor.owl.ning.exception.BasicException;

import java.util.ArrayList;

/**
 * The meta ontology class consisting all of the meta concepts, meta
 * relations and instances in the meta ontology
 *
 * @author Zhu Ning
 * @version 0.1.2
 */
public class MetaOntology extends NamedObject
{
    /**
     * A MetaConcept-type OntologyContainer storing meta concepts
     */
    private OntologyContainer<MetaConcept> metaCncpts =
        new OntologyContainer<MetaConcept>();

    /**
     * A cache for selected meta concepts to use to customizing
     * the domain concepts
     */
    private ArrayList<MetaConcept> selectedMetaCncpts =
        new ArrayList<MetaConcept>();

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

    /**
     * The single meta ontology in the domain owl plugin
     */
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
     * @return The meta concept with the name mcName in the meta
     * ontology
     */
    public MetaConcept getMetaConcept(String mcName)
    {
        return metaCncpts.getComponent(mcName);
    }

    /**
     * Gets the index-th meta concept in the meta ontology
     * @param index The index of the meta concept to get
     * @return The index-th meta concept
     */
    public MetaConcept getMetaConcept(int index)
    {
        return metaCncpts.getComponent(index);
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
     * Gets the count of the meta relations in the meta ontology
     * @return The count of the meta relations in the meta ontology
     */
    public int getMetaRelationCount()
    {
        return metaRelations.size();
    }

    /**
     * Gets the meta relation called mrName in the meta ontology
     * @param mrName The name of the meta relation to get
     * @return The meta relation with the name mrName in the meta
     * ontology
     */
    public MetaRelation getMetaRelation(String mrName)
    {
        return metaRelations.getComponent(mrName);
    }

    /**
     * Gets the meta relation in the meta ontology according to
     * its index
     * @param index The index of the meta relation in the meta
     * ontology
     * @return The index-th meta relation in the meta ontology
     */
    public MetaRelation getMetaRelation(int index)
    {
        return metaRelations.getComponent(index);
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
     * Gets the count of the instances in the meta ontology
     * @return The count of the instances in the meta ontology
     */
    public int getInstanceCount()
    {
        return instances.size();
    }

    public Instance getInstance(String instName)
    {
        return instances.getComponent(instName);
    }

    /**
     * Gets the instance in the meta ontology according to
     * its index
     * @param index The index of the instance in the meta ontology
     * @return The index-th instance in the meta ontology
     */
    public Instance getInstance(int index)
    {
        return instances.getComponent(index);
    }

    /**
     * Gets the count of the meta concepts selected to customize for
     * domain concepts
     * @return The count of the selected meta concepts
     */
    public int getSelectedMetaConceptsCount()
    {
        selectedMetaCncpts.clear();
        int metaCncptCount = getMetaConceptCount();
        for (int i = 0; i < metaCncptCount; i++)
        {
            MetaConcept mc = getMetaConcept(i);
            if (mc.getIsIncluded())
            {
                selectedMetaCncpts.add(mc);
            }
        }

        return selectedMetaCncpts.size();
    }

    /**
     * Get the index-th of the selected meta concepts to customize
     * for domain concepts
     * @param index The index of the selected meta concept in the list
     * @return The index-th selected meta concept
     */
    public MetaConcept getSelectedMetaConcept(int index)
    {
        return selectedMetaCncpts.get(index);
    }
}

package org.protege.editor.owl.ning.domainOWL;

import org.protege.editor.owl.ning.domainOWL.NamedObject;
import org.protege.editor.owl.ning.exception.AddSameOneException;
import org.protege.editor.owl.ning.exception.AddOnesWithSameNameException;

import java.util.HashMap;

/**
 * The template class representing containers of domain concepts, domain * relations and so on.
 * @author Zhu Ning
 * @versioin 0.0.1
 */
public class OntologyContainer<T extends NamedObject>
{
    /**
     * A hashmap-structured field storing ontology components with
     * their names as keys.
     */
    private HashMap<String, T> ontologyComponents = new HashMap<String, T>();

    /**
     * Adds a component
     * @param component The component to add
     * @throws AddSameOneException
     * @throws AddOnesWithSameNameException
     */
    public void addComponent(T component)
    {
        T oldComponent = getComponent(component.getName());
        if (oldComponent != null)
        {
            if (oldComponent == component)
            {
                throw new AddSameOneException("Add a same ontology element");
            }else
            {
                throw new AddOnesWithSameNameException("Add an ontology element with the same name of some already existed element");
            }
        }
        ontologyComponents.put(component.getName(), component);
    }

    /**
     * Get the component with the name componentName
     * @param componentName The name of the component to get
     * @return True if there exist such a component, false otherwise.
     */
    public T getComponent(String componentName)
    {
        if (containsComponent(componentName))
        {
            return ontologyComponents.get(componentName);
        }
        return null;
    }

    /**
     * Checks if the container contains a componet with the name
     * componentName
     * @param componentName The name of the component to check
     * @return True if the container contains such a component, false
     * otherwise.
     */
    public boolean containsComponent(String componentName)
    {
        return ontologyComponents.containsKey(componentName);
    }

    /**
     * Updates the component in the container from oldName to newName
     * @param component The component to update
     * @param oldName The old name of the component
     * @param newName The new name of the component
     */
    public void update(T component, String oldName, String newName)
    {
        ontologyComponents.remove(oldName);
        component.setName(newName);
        ontologyComponents.put(newName, component);
    }

    /**
     * Removes the component with the name ComponentName from the
     * domain ontology
     * @param componentName The name of the component to remove
     */
    public void remove(String componentName)
    {
        ontologyComponents.remove(componentName);
    }
}

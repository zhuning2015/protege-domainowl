package org.protege.editor.owl.ning.domainOWL;

import org.protege.editor.owl.ning.domainOWL.NamedObject;
import org.protege.editor.owl.ning.exception.BasicException;
import org.protege.editor.owl.ning.exception.BasicException;

import java.util.LinkedHashMap;
import java.util.ArrayList;

/**
 * The template class representing containers of domain concepts,
 * domain relations and so on.
 * @author Zhu Ning
 * @version 0.1.0
 */
public class OntologyContainer<T extends NamedObject>
{
    /**
     * A hashmap-structured field storing ontology components with
     * their names as keys.
     */
    private LinkedHashMap<String, T> ontologyComponents =
        new LinkedHashMap<String, T>();
    private ArrayList<T> indexedCpyOfComponents = new ArrayList<T>();

    /**
     * Adds a component
     * @param component The component to add
     * @exception BasicException Throws when duplicate name or same
     *        component to add
     */
    public void addComponent(T component)
    {
        T oldComponent = getComponent(component.getName());
        if (oldComponent != null)
        {
            if (oldComponent == component)
            {
                throw new BasicException("Add a same ontology element");
            }else
            {
                throw new BasicException
                    ("Add an ontology element with the same name of some already existed element");
            }
        }
        ontologyComponents.put(component.getName(), component);
        indexedCpyOfComponents.add(component);
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

    public T getComponent(int index)
    {
        return indexedCpyOfComponents.get(index);
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
     * @param newName The new name of the component
     */
    public void update(T component, String newName)
    {
        ontologyComponents.remove(component.getName());
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
        T component = ontologyComponents.remove(componentName);
        indexedCpyOfComponents.remove(component);
    }

    /**
     * Gets the size of the components in the container
     * @return The size of the components in the container
     */
    public int size()
    {
        return ontologyComponents.size();
    }

}

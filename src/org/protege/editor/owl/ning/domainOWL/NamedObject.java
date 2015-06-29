package org.protege.editor.owl.ning.domainOWL;

/**
 * The base class of classes with the field "name"
 *
 * @author Zhu Ning
 * @version 0.0.1
 */
public abstract class NamedObject
{
    /**
     * the name specified to an object
     */
    private String name;

    public NamedObject(String name)
    {
        setName(name);
    }

    /**
     * Sets the name of the named object
     * @param name the name for the named object
     */
    public void setName(String name)
    {
        if (name.isEmpty())
            return;

        String trimmedName = name.trim();
        if (trimmedName.isEmpty())
            return;

        this.name = name;
    }

    /**
     * Gets the name of the named object
     * @return the name of the named object
     */
    public String getName()
    {
        return name;
    }
}

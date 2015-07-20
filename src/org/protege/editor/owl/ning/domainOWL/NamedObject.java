package org.protege.editor.owl.ning.domainOWL;

import org.protege.editor.owl.ning.exception.BasicException;

/**
 * The base class of classes with the field "name"
 *
 * @author Zhu Ning
 * @version 0.1.1
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
        checkName(name, "The specifid name is empty", "The specified name is blank");
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

    /**
     * Checks if the name is valid
     * @param name The name specified
     * @param excptMsgIfEmpty The exception message when the name is
     * empty
     * @param excptMsgIfBlank The exception message when the name is
     * blank
     * @exception BasicException Throws when the name is empty or blank
     */
    public static void checkName(String name, String excptMsgIfEmpty, String excptMsgIfBlank)
    {
        if (name.isEmpty())
            throw new BasicException(excptMsgIfEmpty);

        if (name.trim().isEmpty())
            throw new BasicException(excptMsgIfBlank);
    }

    @Override
    public String toString()
    {
        return name;
    }
}

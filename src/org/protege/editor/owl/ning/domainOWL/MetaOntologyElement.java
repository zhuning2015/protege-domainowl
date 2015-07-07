package org.protege.editor.owl.ning.domainOWL;

import org.protege.editor.owl.ning.domainOWL.NamedObject;

/**
 * MetaOntologyElement, the basic class for all the components in the
 * meta ontology
 * @author Zhu Ning
 * @version 0.1.0
 */
public abstract class MetaOntologyElement extends NamedObject
{
    /**
     * A flag to indicate if the meta ontology element is used for
     * to customize for the domain ontology
     */
    private boolean isIncluded = false;
    /**
     * The name of the icon for visualizing the meta ontology element
     */
    private String iconName = "";

    public MetaOntologyElement(String name)
    {
        super(name);
    }

    /**
     * Sets if the meta ontology element is included
     * @param isIncluded A boolean value to notate if included
     */
    public void setIsIncluded(boolean isIncluded)
    {
        this.isIncluded = isIncluded;
    }

    /**
     * Gets the status whether the meta ontology element is included
     * @return True if the meta ontology element is included, false
     * otherwise
     */
    public boolean getIsIncluded()
    {
        return isIncluded;
    }

    /**
     * Sets the name of the icon for the meta ontology element
     * @param iconName The name of the icon
     */
    public void setIconName(String iconName)
    {
        this.iconName = iconName;
    }

    /**
     * Gets the name of the icon for the meta ontology element
     * @return The icon name for the meta ontology element
     */
    public String getIconName()
    {
        return iconName;
    }
}

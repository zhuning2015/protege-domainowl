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
     * A path to an image for visualize the meta ontology element
     */
    private String imagePath = "";

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
     * Sets the image path for the meta ontology element
     * @param imagePath The path for the image
     */
    public void setImagePath(String imagePath)
    {
        this.imagePath = imagePath;
    }

    /**
     * Gets the image path for the meta ontology element
     * @return The physical path for the image
     */
    public String getImagePath()
    {
        return imagePath;
    }
}

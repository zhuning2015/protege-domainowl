package org.protege.editor.owl.ning.domainOWL;

import org.protege.editor.owl.ning.domainOWL.RestrictionType;
import org.protege.editor.owl.ning.exception.BasicException;

/**
 * The restriction for the meta nodes in the meta ontology
 *
 * @author Zhu Ning
 * @version 0.1.0
 */
public class Restriction
{
    private RestrictionType restrcType;
    private String restrcPrptyName;
    private String restrcFillerName;
    private int cardinality;

    public Restriction(RestrictionType restrcType, String restrcPrptyName,
                       String restrcFillerName)
    {
        setRestrictionType(restrcType);
        setRestrictedPropertyName(restrcPrptyName);
        setRestrictionFillerName(restrcFillerName);
    }

    public Restriction(RestrictionType restrcType, String restrcPrptyName,
                       String restrcFillerName, int cardinality)
    {
        this(restrcType, restrcPrptyName, restrcFillerName);
        setCardinality(cardinality);
    }

    /**
     * Gets the type of the restriction
     * @return The type of the restriction
     */
    public RestrictionType getRestrictionType()
    {
        return restrcType;
    }

    /**
     * Sets the type of the restriction
     * @param restrcType The type specified for the restriction
     */
    public void setRestrictionType(RestrictionType restrcType)
    {
        this.restrcType = restrcType;
    }

    /**
     * Gets the name of the restricted property for the restriction
     * @return The name of the restricted property
     */
    public String getRestrictedPropertyName()
    {
        return restrcPrptyName;
    }

    /**
     * Sets the name of the restricted property for the restriction
     * @param restrcPrptyName The name for the specified restricted property
     * @exception BasicException Throws when restrcPrptyName is empty
     * or blank
     */
    public void setRestrictedPropertyName(String restrcPrptyName)
    {
        NamedObject.checkName(restrcPrptyName,
            "The name for the restricted property is empty",
            "The name for the restricted property is blank");
        this.restrcPrptyName = restrcPrptyName;
    }

    /**
     * Gets the name of the filler of the restriction
     * @return The name of the filler
     */
    public String getRestrictionFillerName()
    {
        return restrcFillerName;
    }

    /**
     * Sets the name of the filler of the restriction
     * @param restrcFillerName The name for the specified restriction filler
     * @exception BasicException Throws when restrcFillerName is empty
     * or blank
     */
    public void setRestrictionFillerName(String restrcFillerName)
    {
        NamedObject.checkName(restrcFillerName,
            "The name for the restriction filler is empty",
            "The name for the restriction filler is blank");

        this.restrcFillerName = restrcFillerName;
    }

    /**
     * Gets the cardinality of the restriction for the meta node
     * if its type is MIN, EXACTLY or MAX
     * @return the cardinality of the restriction for the meta node
     */
    public int getCardinality()
    {
        return cardinality;
    }

    /**
     * Sets the cardinality for the restriction of the meta node
     * @param cardinality The specified cardinality for the restriction
     * @exception BasicException Throws if the specified cardinality is less
     * than 0
     */
    public void setCardinality(int cardinality)
    {
        if (cardinality < 0)
            throw new BasicException
            ("The cardinality of the restriction must be not less than 0");
        this.cardinality = cardinality;
    }
}

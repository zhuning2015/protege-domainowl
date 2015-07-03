package org.protege.editor.owl.ning.domainOWL;

/**
 * The enumeration of the types of Restrictions for the meta nodes
 * in the meta ontology
 *
 * @author Zhu Ning
 * @version 0.1.0
 */
public enum RestrictionType
{
    /**
     * An existential class expression, corresponding to
     * DataSomeValuesFrom in OWL2
     */
    SOME_DATA,
    /**
     * A universal class expression, corresponding to
     * DataAllValuesFrom in OWL2
     */
    ONLY_DATA,
    /**
     * A minimum cardinality expression, corresponding to
     * DataMinCardinality in OWL2
     */
    MIN_DATA,
    /**
     * An exact cardinality expression, corresponding to
     * DataExactCardinality in OWL2
     */
    EXACTLY_DATA,
    /**
     * A maximum cardinality expression, corresponding to
     * DataMaxCardinality in OWL2
     */
    MAX_DATA,
    /**
     * An existential class expression, corresponding to
     * ObjectSomeValuesFrom in OWL2
     */
    SOME_OBJECT,
    /**
     * A universal class expression, corresponding to
     * ObjectAllValuesFrom in OWL2
     */
    ONLY_OBJECT,
    /**
     * A minimum cardinality expression, corresponding to
     * ObjectMinCardinality in OWL2
     */
    MIN_OBJECT,
    /**
     * An exact cardinality expression, corresponding to
     * ObjectExactCardinality in OWL2
     */
    EXACTLY_OBJECT,
    /**
     * A maximum cardinality expression, corresponding to
     * ObjectMaxCardinality in OWL2
     */
    MAX_OBJECT
}

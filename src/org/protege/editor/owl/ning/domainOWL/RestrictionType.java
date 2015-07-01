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
    SOME,    //existential
    ONLY,    //universal
    MIN,     //min cardinality
    EXACTLY, //exact cardinality
    MAX      //max cardinality
}

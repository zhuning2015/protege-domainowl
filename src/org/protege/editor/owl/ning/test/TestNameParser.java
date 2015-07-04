package org.protege.editor.owl.ning.test;

import org.protege.editor.owl.ning.util.NameParser;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * The test class for NameParser
 *
 * @author Zhu Ning
 * @version 0.1.0
 */
public class TestNameParser
{
    @Test
    public void testGetOWLOntologyName()
    {
        String strOntologyIRI = "http://www.semanticweb.org/ning/ontologies/2015/6/untitled-ontology-261";
        String ontologyName =
            NameParser.getOWLOntologyName(strOntologyIRI);
        assertEquals("untitled-ontology-261", ontologyName);

        String strOntologyIRI2 = "http://www.nudt.edu.cn/Ontologies/dm2.owl";
        String ontologyName2 =
            NameParser.getOWLOntologyName(strOntologyIRI2);
        assertEquals("dm2", ontologyName2);
    }
}

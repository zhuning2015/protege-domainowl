package org.protege.editor.owl.ning.test;

import org.protege.editor.owl.ning.domainOWL.MetaOntology;
import org.protege.editor.owl.ning.domainOWL.MetaConcept;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * The test class for MetaOntology
 *
 * @author Zhu Ning
 * @version 0.1.0
 */
public class TestMetaOntology
{
    MetaOntology metaOnt = null;

    @Before
    public void init()
    {
        metaOnt = MetaOntology.create("TestOntology");
    }

    @Test
    public void testGetMetaOntology()
    {
        assertNotNull(MetaOntology.getMetaOntology());
    }

    @Test
    public void testCreateMetaConcept()
    {
        assertFalse(metaOnt.containsMetaConcept("TestMc"));
        metaOnt.createMetaConcept("TestMc");
        assertTrue(metaOnt.containsMetaConcept("TestMc"));
    }

    @Test
    public void testGetMetaConcept()
    {
        MetaConcept mc = metaOnt.createMetaConcept("TestMc");
        assertEquals(mc, metaOnt.getMetaConcept("TestMc"));
    }

    @Test
    public void testCreateMetaRelation()
    {
        assertFalse(metaOnt.containsMetaRelation("TestMr"));
        metaOnt.createMetaRelation("TestMr");
        assertTrue(metaOnt.containsMetaRelation("TestMr"));
    }

    @Test
    public void testCreateInstance()
    {
        assertFalse(metaOnt.containsInstance("TestInstance"));
        metaOnt.createInstance("TestInstance");
        assertTrue(metaOnt.containsInstance("TestInstance"));
    }
}

package org.protege.editor.owl.ning.test;

import org.protege.editor.owl.ning.domainOWL.MetaOntology;
import org.protege.editor.owl.ning.domainOWL.MetaConcept;
import org.protege.editor.owl.ning.domainOWL.MetaRelation;
import org.protege.editor.owl.ning.domainOWL.Instance;

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
        MetaConcept mc2 = metaOnt.createMetaConcept("TestMc2");
        assertEquals(mc2, metaOnt.getMetaConcept("TestMc2"));
    }

    @Test
    public void testGetMetaConceptCount()
    {
        assertTrue(metaOnt.getMetaConceptCount() == 0);
        metaOnt.createMetaConcept("TestMc");
        assertTrue(metaOnt.getMetaConceptCount() == 1);
        metaOnt.createMetaConcept("TestMc2");
        assertTrue(metaOnt.getMetaConceptCount() == 2);
    }

    @Test
    public void testGetMetaConcept_Index()
    {
        MetaConcept mc = metaOnt.createMetaConcept("TestMc");
        assertEquals(mc, metaOnt.getMetaConcept(0));
        MetaConcept mc2 = metaOnt.createMetaConcept("TestMc2");
        assertEquals(mc2, metaOnt.getMetaConcept(1));
    }

    @Test
    public void testCreateMetaRelation()
    {
        assertFalse(metaOnt.containsMetaRelation("TestMr"));
        metaOnt.createMetaRelation("TestMr");
        assertTrue(metaOnt.containsMetaRelation("TestMr"));
    }

    @Test
    public void testGetMetaRelationCount()
    {
        assertTrue(metaOnt.getMetaRelationCount() == 0);
        metaOnt.createMetaRelation("TestMr");
        assertTrue(metaOnt.getMetaRelationCount() == 1);
        metaOnt.createMetaRelation("TestMr2");
        assertTrue(metaOnt.getMetaRelationCount() == 2);
    }

    @Test
    public void testGetMetaRelation_Index()
    {
        MetaRelation mc = metaOnt.createMetaRelation("TestMr");
        assertEquals(mc, metaOnt.getMetaRelation(0));
        MetaRelation mc2 = metaOnt.createMetaRelation("TestMr2");
        assertEquals(mc2, metaOnt.getMetaRelation(1));
    }

    @Test
    public void testCreateInstance()
    {
        assertFalse(metaOnt.containsInstance("TestInstance"));
        metaOnt.createInstance("TestInstance");
        assertTrue(metaOnt.containsInstance("TestInstance"));
    }

    @Test
    public void testGetInstanceCount()
    {
        assertTrue(metaOnt.getInstanceCount() == 0);
        metaOnt.createInstance("TestMr");
        assertTrue(metaOnt.getInstanceCount() == 1);
        metaOnt.createInstance("TestMr2");
        assertTrue(metaOnt.getInstanceCount() == 2);
    }

    @Test
    public void testGetInstance_Index()
    {
        Instance mc = metaOnt.createInstance("TestMr");
        assertEquals(mc, metaOnt.getInstance(0));
        Instance mc2 = metaOnt.createInstance("TestMr2");
        assertEquals(mc2, metaOnt.getInstance(1));
    }
}

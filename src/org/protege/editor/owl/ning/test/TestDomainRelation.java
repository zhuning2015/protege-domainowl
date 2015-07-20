package org.protege.editor.owl.ning.test;

import org.protege.editor.owl.ning.domainOWL.DomainOntology;
import org.protege.editor.owl.ning.domainOWL.DomainRelation;
import org.protege.editor.owl.ning.domainOWL.DomainConcept;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * The test class for DomainRelation
 *
 * @author Zhu Ning
 * @version 0.1.1
 */
public class TestDomainRelation
{
    private DomainOntology dt = null;

    @Before
    public void init()
    {
        dt = DomainOntology.create("TestOntology");
    }

    @Test
    public void TestCreate()
    {
        assertFalse(dt.containsDomainRelation("TestRelation"));
        DomainRelation.create("TestRelation");
        assertTrue(dt.containsDomainRelation("TestRelation"));
    }

    @Test
    public void TestSetSrc()
    {
        DomainRelation dr = DomainRelation.create("TestRelation");
        DomainConcept dc = DomainConcept.create("dc");
        dr.setSrc(dc);
        assertEquals(dc, dr.getSrc());
    }

    @Test
    public void TestSetDst()
    {
        DomainRelation dr = DomainRelation.create("TestRelation");
        DomainConcept dc = DomainConcept.create("dc");
        dr.setDst(dc);
        assertEquals(dc, dr.getDst());
    }

    @Test
    public void TestSetName()
    {
        DomainRelation dr = DomainRelation.create("TestRelation");
        DomainConcept src = DomainConcept.create("source");
        dr.setSrc(src);
        DomainConcept dst = DomainConcept.create("destination");
        dr.setDst(dst);
        assertFalse(src.containsOutgoingRelation("newTest"));
        assertFalse(dst.containsIncomingRelation("newTest"));
        dr.setName("newTest");
        assertEquals("newTest", dr.getName());
        assertTrue(src.containsOutgoingRelation("newTest"));
        assertTrue(dst.containsIncomingRelation("newTest"));
    }
}

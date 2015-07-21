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
 * @version 0.1.2
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
    public void TestLinkToSrc()
    {
        DomainRelation dr = DomainRelation.create("TestRelation");
        DomainConcept dc = DomainConcept.create("dc");
        dr.linkToSrc(dc);
        assertEquals(dc, dr.getSrc());
    }

    @Test
    public void TestLinkToDst()
    {
        DomainRelation dr = DomainRelation.create("TestRelation");
        DomainConcept dc = DomainConcept.create("dc");
        dr.linkToDst(dc);
        assertEquals(dc, dr.getDst());
    }

    @Test
    public void TestChangeName()
    {
        DomainRelation dr = DomainRelation.create("TestRelation");
        DomainConcept src = DomainConcept.create("source");
        dr.linkToSrc(src);
        DomainConcept dst = DomainConcept.create("destination");
        dr.linkToDst(dst);
        assertFalse(src.containsOutgoingRelation("newTest"));
        assertFalse(dst.containsIncomingRelation("newTest"));
        dr.changeName("newTest");
        assertEquals("newTest", dr.getName());
        assertTrue(src.containsOutgoingRelation("newTest"));
        assertTrue(dst.containsIncomingRelation("newTest"));
    }
}

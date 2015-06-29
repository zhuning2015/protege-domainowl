package org.protege.editor.owl.ning.test;

import org.protege.editor.owl.ning.domainOWL.DomainOntology;
import org.protege.editor.owl.ning.exception.LinkSameRelationMoreThanOnceException;
import org.protege.editor.owl.ning.domainOWL.DomainConcept;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestDomainConcept
{
    DomainOntology domainOnt = null;

    @Before
    public void init()
    {
        domainOnt = DomainOntology.create("TestOntology");
    }

    @Test
    public void TestCreate()
    {
        assertFalse(domainOnt.containsDomainConcept("test"));
        DomainConcept.create("test");
        assertTrue(domainOnt.containsDomainConcept("test"));
    }

    @Test
    public void TestDelete()
    {
        DomainConcept.create("test");
        DomainConcept.delete("test");
        assertFalse(domainOnt.containsDomainConcept("test"));
    }

    @Test
    public void TestAddOutgoingRelation()
    {
        DomainConcept dc = DomainConcept.create("test");
        assertFalse(dc.containsOutgoingRelation("testRelation"));
        dc.addOutgoingRelation("testRelation");
        assertTrue(dc.containsOutgoingRelation("testRelation"));
    }

    @Test(expected=LinkSameRelationMoreThanOnceException.class)
    public void TestAddOutgoingRelation_Exception()
    {
        DomainConcept dc = DomainConcept.create("test");
        dc.addOutgoingRelation("testRelation");
        dc.addOutgoingRelation("testRelation");
    }

    @Test
    public void TestRemoveOutgoingRelation()
    {
        DomainConcept dc = DomainConcept.create("test");
        dc.addOutgoingRelation("testRelation");
        assertTrue(dc.containsOutgoingRelation("testRelation"));
        dc.removeOutgoingRelation("testRelation2");
        assertTrue(dc.containsOutgoingRelation("testRelation"));
        dc.removeOutgoingRelation("testRelation");
        assertFalse(dc.containsOutgoingRelation("testRelation"));
    }

    @Test
    public void TestAddIncomingRelation()
    {
        DomainConcept dc = DomainConcept.create("test");
        assertFalse(dc.containsIncomingRelation("testRelation"));
        dc.addIncomingRelation("testRelation");
        assertTrue(dc.containsIncomingRelation("testRelation"));
    }

    @Test(expected=LinkSameRelationMoreThanOnceException.class)
    public void TestAddIncomingRelaion_Exception()
    {
        DomainConcept dc = DomainConcept.create("test");
        dc.addIncomingRelation("testRelation");
        dc.addIncomingRelation("testRelation");
    }

    @Test
    public void TestRemoveIncomingRelation()
    {
        DomainConcept dc = DomainConcept.create("test");
        dc.addIncomingRelation("testRelation");
        assertTrue(dc.containsIncomingRelation("testRelation"));
        dc.removeIncomingRelation("testRelation2");
        assertTrue(dc.containsIncomingRelation("testRelation"));
        dc.removeIncomingRelation("testRelation");
        assertFalse(dc.containsIncomingRelation("testRelation"));
    }
}

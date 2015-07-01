package org.protege.editor.owl.ning.test;

import org.protege.editor.owl.ning.domainOWL.DomainOntology;
import org.protege.editor.owl.ning.domainOWL.DomainConcept;
import org.protege.editor.owl.ning.exception.BasicException;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * The test class for DomainOntology
 * @author Zhu Ning
 * @version 0.1.0
 */
public class TestDomainOntology
{
    DomainOntology domainOnt = null;

    @Before
    public void init()
    {
        domainOnt = DomainOntology.create("TestOntolgy");
    }

    @Test
    public void TestAddDomainConcept()
    {
        assertFalse(domainOnt.containsDomainConcept("test"));

        DomainConcept.create("test");

        assertTrue(domainOnt.containsDomainConcept("test"));
    }

    @Test(expected=BasicException.class)
    public void TestAddDomainConcept_Same()
    {
        DomainConcept dc = DomainConcept.create("test");
        domainOnt.addDomainConcept(dc);
    }

    @Test(expected=BasicException.class)
    public void TestAddDomainConcept_SameName()
    {
        DomainConcept.create("test");
        DomainConcept.create("test");
    }

    @Test
    public void TestGetDomainConcept()
    {
        DomainConcept.create("test");

        assertNotNull(domainOnt.getDomainConcept("test"));
        assertNull(domainOnt.getDomainConcept("another"));
    }

    @Test
    public void TestUpdateDomainConcept()
    {
        DomainConcept dc = DomainConcept.create("test");

        domainOnt.updateDomainConcept(dc,"test1");

        assertTrue(domainOnt.containsDomainConcept("test1"));
        assertFalse(domainOnt.containsDomainConcept("test"));
    }

    @Test
    public void TestContainsDomainConcept()
    {
        DomainConcept dc = DomainConcept.create("test");

        assertTrue(domainOnt.containsDomainConcept("test"));
        assertFalse(domainOnt.containsDomainConcept("another"));
    }

    @Test
    public void TestRemoveDomainConcept()
    {
        DomainConcept dc = DomainConcept.create("test");

        domainOnt.removeDomainConcept("test");
        assertFalse(domainOnt.containsDomainConcept(dc.getName()));
    }
}

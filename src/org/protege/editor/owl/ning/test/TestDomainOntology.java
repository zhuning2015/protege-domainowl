package org.protege.editor.owl.ning.test;

import org.protege.editor.owl.ning.domainOWL.DomainOntology;
import org.protege.editor.owl.ning.domainOWL.DomainConcept;
import org.protege.editor.owl.ning.exception.AddSameDomainConceptException;
import org.protege.editor.owl.ning.exception.AddDomainConceptWithSameNameException;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * The Test class for DomainOntology
 * @author Zhu Ning
 * @version 0.0.1
 */
public class TestDomainOntology
{
    DomainOntology domainOnt = null;

    @Before
    public void init()
    {
        domainOnt = new DomainOntology();
    }

    @Test
    public void TestAddDomainConcept()
    {
        assertFalse(domainOnt.containsDomainConcept("test"));

        DomainConcept dc = new DomainConcept();
        dc.setName("test");

        domainOnt.addDomainConcept(dc);

        assertTrue(domainOnt.containsDomainConcept("test"));
    }

    @Test(expected=AddSameDomainConceptException.class)
    public void TestAddDomainConcept_Same()
    {
        DomainConcept dc = new DomainConcept();
        dc.setName("test");

        domainOnt.addDomainConcept(dc);
        domainOnt.addDomainConcept(dc);
    }

    @Test(expected=AddDomainConceptWithSameNameException.class)
    public void TestAddDomainConcept_SameName()
    {
        DomainConcept dc = new DomainConcept();
        dc.setName("test");
        domainOnt.addDomainConcept(dc);

        DomainConcept dc2 = new DomainConcept();
        dc2.setName("test");
        domainOnt.addDomainConcept(dc2);
    }

    @Test
    public void TestGetDomainConcept()
    {
        DomainConcept dc = new DomainConcept();
        dc.setName("test");
        domainOnt.addDomainConcept(dc);

        assertNotNull(domainOnt.getDomainConcept("test"));
        assertNull(domainOnt.getDomainConcept("another"));
    }

    @Test
    public void TestUpdateDomainConcept()
    {
        DomainConcept dc = new DomainConcept();
        dc.setName("test");
        domainOnt.addDomainConcept(dc);

        domainOnt.updateDomainConcept(dc,"test","test1");

        assertTrue(domainOnt.containsDomainConcept("test1"));
        assertFalse(domainOnt.containsDomainConcept("test"));
    }

    @Test
    public void TestContainsDomainConcept()
    {
        DomainConcept dc = new DomainConcept();
        dc.setName("test");
        domainOnt.addDomainConcept(dc);

        assertTrue(domainOnt.containsDomainConcept("test"));
        assertFalse(domainOnt.containsDomainConcept("another"));
    }

    @Test
    public void TestRemoveDomainConcept()
    {
        DomainConcept dc = new DomainConcept();
        dc.setName("test");
        domainOnt.addDomainConcept(dc);

        domainOnt.removeDomainConcept(dc);
        assertFalse(domainOnt.containsDomainConcept(dc.getName()));
    }
}

package org.protege.editor.owl.ning.test;

import org.protege.editor.owl.ning.domainOWL.DomainOntology;
import org.protege.editor.owl.ning.domainOWL.DomainConcept;

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
    public void TestRemoveDomainConcept()
    {
        DomainConcept dc = new DomainConcept();
        dc.setName("test");
        domainOnt.addDomainConcept(dc);

        domainOnt.removeDomainConcept(dc);
        assertFalse(domainOnt.containsDomainConcept(dc.getName()))
    }
}

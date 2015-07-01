package org.protege.editor.owl.ning.test;

import org.protege.editor.owl.ning.domainOWL.DomainOntology;
import org.protege.editor.owl.ning.domainOWL.DomainRelation;
import org.protege.editor.owl.ning.domainOWL.DomainConcept;
import org.protege.editor.owl.ning.exception.BasicException;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * The test class for DomainRelation
 *
 * @author Zhu Ning
 * @version 0.1.0
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
        dr.setSrc("dc");
        assertEquals("dc", dr.getSrc());
    }

    @Test(expected=BasicException.class)
    public void TestSetSrc_NonExistent()
    {
        DomainRelation dr = DomainRelation.create("TestRelation");
        dr.setSrc("dc");
    }


    @Test
    public void TestSetDst()
    {
        DomainRelation dr = DomainRelation.create("TestRelation");
        DomainConcept dc = DomainConcept.create("dc");
        dr.setDst("dc");
        assertEquals("dc", dr.getDst());
    }

    @Test(expected=BasicException.class)
    public void TestSetDst_NonExistent()
    {
        DomainRelation dr = DomainRelation.create("TestRelation");
        dr.setDst("dc");
    }
}

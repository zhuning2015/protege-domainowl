package org.protege.editor.owl.ning.test;

import org.protege.editor.owl.ning.domainOWL.DomainOntology;
import org.protege.editor.owl.ning.domainOWL.DomainRelation;
import org.protege.editor.owl.ning.exception.NullNameException;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

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

    @Test(expected=NullNameException.class)
    public void TestCreate_NullName()
    {
        DomainRelation.create("");
    }

    @Test(expected=NullNameException.class)
    public void TestCreate_BlankName()
    {
        DomainRelation.create("   ");
    }

    @Test
    public void TestSetSrc()
    {
        DomainRelation dr = DomainRelation.create("TestRelation");
        dr.setSrc("dc");
        assertEquals("dc", dr.getSrc());
    }

    @Test(expected=NullNameException.class)
    public void TestSetSrc_EmptyName()
    {
        DomainRelation dr = DomainRelation.create("TestRelatioin");
        dr.setSrc("");
    }

    @Test(expected=NullNameException.class)
    public void TestSrc_BlankName()
    {
        DomainRelation dr = DomainRelation.create("TestRelation");
        dr.setSrc("     ");
    }

    @Test
    public void TestSetDst()
    {
        DomainRelation dr = DomainRelation.create("TestRelation");
        dr.setDst("dc");
        assertEquals("dc", dr.getDst());
    }

    @Test(expected=NullNameException.class)
    public void TestSetDst_EmptyName()
    {
        DomainRelation dr = DomainRelation.create("TestRelation");
        dr.setDst("");
    }

    @Test(expected=NullNameException.class)
    public void TestSetDst_BlankName()
    {
        DomainRelation dr = DomainRelation.create("TestRelation");
        dr.setDst("      ");
    }

}

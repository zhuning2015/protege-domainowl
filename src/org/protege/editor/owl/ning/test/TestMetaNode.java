package org.protege.editor.owl.ning.test;

import org.protege.editor.owl.ning.domainOWL.MetaNode;
import org.protege.editor.owl.ning.exception.BasicException;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestMetaNode
{
    MetaNode mn = null;

    @Before
    public void init()
    {
        mn = new MetaNode("TestNode"){};
    }

    @Test
    public void testAddOutgoingMetaRelation()
    {
        assertFalse(mn.containsOutgoingMetaRelation("TestRelation"));
        mn.addOutgoingMetaRelation("TestRelation");
        assertTrue(mn.containsOutgoingMetaRelation("TestRelation"));
    }

    @Test(expected=BasicException.class)
    public void testAddOutgoingMetaRelation_Exception()
    {
        mn.addOutgoingMetaRelation("TestRelation");
        mn.addOutgoingMetaRelation("TestRelation");
    }

    @Test
    public void testAddIncomingMetaRelation()
    {
        assertFalse(mn.containsIncomingMetaRelation("TestRelation"));
        mn.addIncomingMetaRelation("TestRelation");
        assertTrue(mn.containsIncomingMetaRelation("TestRelation"));
    }

    @Test(expected=BasicException.class)
    public void testAddIncomingMetaRelation_Exception()
    {
        mn.addIncomingMetaRelation("TestRelation");
        mn.addIncomingMetaRelation("TestRelation");
    }
}

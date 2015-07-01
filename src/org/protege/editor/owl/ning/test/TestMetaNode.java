package org.protege.editor.owl.ning.test;

import org.protege.editor.owl.ning.domainOWL.MetaNode;
import org.protege.editor.owl.ning.exception.BasicException;
import org.protege.editor.owl.ning.domainOWL.Restriction;
import org.protege.editor.owl.ning.domainOWL.RestrictionType;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * The test class for MetaNode
 *
 * @author Zhu Ning
 * @version 0.1.0
 */
public class TestMetaNode
{
    MetaNode mn = null;
    Restriction restrc = null;
    @Before
    public void init()
    {
        mn = new MetaNode("TestNode"){};
        restrc = new Restriction(RestrictionType.MIN, "TestProperty",
                                 "TestFiller", 2);
    }

    @Test
    public void testAddRestriction()
    {
        assertFalse(mn.containsRestriction(restrc));
        mn.addRestriction(restrc);
        assertTrue(mn.containsRestriction(restrc));
    }

    @Test(expected=BasicException.class)
    public void testAddRestriction_Exception()
    {
        mn.addRestriction(restrc);
        mn.addRestriction(restrc);
    }
}

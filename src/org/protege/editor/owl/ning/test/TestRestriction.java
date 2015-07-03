package org.protege.editor.owl.ning.test;

import org.protege.editor.owl.ning.domainOWL.Restriction;
import org.protege.editor.owl.ning.domainOWL.RestrictionType;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * The test class for Restriction
 *
 * @author Zhu Ning
 * @version 0.1.0
 */
public class TestRestriction
{
    Restriction restrc = null;

    @Before
    public void init()
    {
        restrc = new Restriction("TestProperty", "TestFiller");
    }

    @Test
    public void TestGetSetRestrictionType()
    {
        assertFalse(restrc.getRestrictionType() ==
                    RestrictionType.MAX_OBJECT);
        restrc.setRestrictionType(RestrictionType.MAX_OBJECT);
        assertTrue(restrc.getRestrictionType() ==
                   RestrictionType.MAX_OBJECT);
    }

    @Test
    public void TestGetSetRestrictedPropertyName()
    {
        assertNotEquals(restrc.getRestrictedPropertyName(), "TestProperty2");
        restrc.setRestrictedPropertyName("TestProperty2");
        assertEquals(restrc.getRestrictedPropertyName(), "TestProperty2");
    }

    @Test
    public void TestGetSetRestrictionFiller()
    {
        assertNotEquals(restrc.getRestrictionFillerName(), "TestFiller2");
        restrc.setRestrictionFillerName("TestFiller2");
        assertEquals(restrc.getRestrictionFillerName(), "TestFiller2");
    }

    @Test
    public void TestGetSetCardinality()
    {
        assertFalse(restrc.getCardinality() == 2);
        restrc.setCardinality(2);
        assertTrue(restrc.getCardinality() == 2);
    }
}

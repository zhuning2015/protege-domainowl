package org.protege.editor.owl.ning.test;

import org.protege.editor.owl.ning.domainOWL.MetaRelation;
import org.protege.editor.owl.ning.domainOWL.MetaNode;
import org.protege.editor.owl.ning.exception.BasicException;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * The test class for MetaRelation
 *
 * @author Zhu Ning
 * @version 0.1.0
 */
public class TestMetaRelation
{
    MetaRelation mr = null;

    @Before
    public void init()
    {
        mr = new MetaRelation("TestMetaRelation",
                              MetaRelation.MetaRelationType.OBJECT_RELATION
                              );
    }

    @Test
    public void TestGetSetMetaRelationType()
    {
        assertFalse(mr.getMetaRelationType() ==
                    MetaRelation.MetaRelationType.DATA_RELATION);
        mr.setMetaRelationType(MetaRelation.MetaRelationType.DATA_RELATION);
        assertTrue(mr.getMetaRelationType() ==
                   MetaRelation.MetaRelationType.DATA_RELATION);
    }

    @Test
    public void TestAddSrc()
    {
        assertFalse(mr.containsSrc("testmn"));
        mr.addSrc("testmn");
        assertTrue(mr.containsSrc("testmn"));
    }

    @Test(expected=BasicException.class)
    public void TestAddSrc_Exception()
    {
        mr.addSrc("mn");
        mr.addSrc("mn");
    }

    @Test
    public void TestAddDst()
    {
        assertFalse(mr.containsDst("testmn"));
        mr.addDst("testmn");
        assertTrue(mr.containsDst("testmn"));
    }

    @Test(expected=BasicException.class)
    public void TestAddDst_Exception()
    {
        mr.addDst("mn");
        mr.addDst("mn");
    }
}

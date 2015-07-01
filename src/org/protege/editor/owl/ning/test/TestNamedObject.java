package org.protege.editor.owl.ning.test;

import org.protege.editor.owl.ning.domainOWL.NamedObject;
import org.protege.editor.owl.ning.exception.BasicException;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * The test class for NamedObject
 *
 * @author Zhu Ning
 * @version 0.1.0
 */
public class TestNamedObject
{
    NamedObject namedObject = null;

    @Before
    public void init()
    {
        namedObject = new NamedObject("test"){};
    }

    @Test
    public void TestGetSetNameInEnglish()
    {
        String name = "test";
        namedObject.setName(name);
        assertEquals(name, namedObject.getName());
    }

    @Test
    public void TestGetSetNameInChinese()
    {
        String name = "测试名称";
        namedObject.setName(name);
        assertEquals(name, namedObject.getName());
    }

    @Test(expected=BasicException.class)
    public void TestGetSetNameWhichIsNull()
    {
        String name = "";
        namedObject.setName(name);
    }

    @Test(expected=BasicException.class)
    public void TestGetSetNameWhichConsistsOfSpaces()
    {
        String name = "  ";
        namedObject.setName(name);
    }
}

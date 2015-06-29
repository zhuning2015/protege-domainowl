package org.protege.editor.owl.ning.test;

import org.protege.editor.owl.ning.domainOWL.NamedObject;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for NamedObject
 *
 * @author Zhu Ning
 * @version 0.0.1
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
        assertEquals("Error:Set English names",name, namedObject.getName());
    }

    @Test
    public void TestGetSetNameInChinese()
    {
        String name = "测试名称";
        namedObject.setName(name);
        assertEquals("Error:Set Chinese names", name, namedObject.getName());
    }

    @Test
    public void TestGetSetNameWhichIsNull()
    {
        String preName = "测试名称";
        namedObject.setName(preName);

        String name = "";
        namedObject.setName(name);
        assertEquals("error:Set names which are null",preName,namedObject.getName());
    }

    @Test
    public void TestGetSetNameWhichConsistsOfSpaces()
    {
        String preName = "测试名称";
        namedObject.setName(preName);

        String name = "  ";
        namedObject.setName(name);
        assertEquals("error:Set names which consists of spaces",namedObject.getName(), "测试名称");
    }
}

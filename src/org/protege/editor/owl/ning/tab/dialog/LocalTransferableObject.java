package org.protege.editor.owl.ning.tab.dialog;

import org.protege.editor.owl.ning.domainOWL.MetaConcept;

import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;

import javax.swing.JOptionPane;

/**
 * Local object encapsulation class for transfering between different
 * components
 *
 * Referring to the codes in the book Crazy Java
 *
 * @author Zhu Ning
 * @version 0.1.0
 */
public class LocalTransferableObject implements Transferable
{
    /**
     * The object to tranfer between different components
     */
    private Object obj = null;

    public LocalTransferableObject(Object obj)
    {
        this.obj = obj;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors()
    {
        DataFlavor[] flavors = new DataFlavor[2];
        Class cls = obj.getClass();
        String mimeType = "application/x-java-jvm-local-objectref;class="+
            cls.getName();
        try
        {
            flavors[0] =
                new DataFlavor(mimeType, cls.getName(), cls.getClassLoader());
            flavors[1] = DataFlavor.stringFlavor;
            return flavors;
        }catch (ClassNotFoundException e)
        {
            JOptionPane.showMessageDialog(null,e);
            return null;
        }
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException
    {
        if (!isDataFlavorSupported(flavor))
        {
            throw new UnsupportedFlavorException(flavor);
        }

        if (flavor.equals(DataFlavor.stringFlavor))
        {
            return obj.toString();
        }

        return obj;
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor)
    {
        return flavor.equals(DataFlavor.stringFlavor) ||
            flavor.getPrimaryType().equals("application") &&
            flavor.getSubType().equals("x-java-jvm-local-objectref") &&
            flavor.getRepresentationClass().
            isAssignableFrom(obj.getClass());
    }
}

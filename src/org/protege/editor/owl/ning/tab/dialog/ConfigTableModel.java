package org.protege.editor.owl.ning.tab.dialog;

import org.protege.editor.owl.ning.domainOWL.MetaOntology;
import org.protege.editor.owl.ning.domainOWL.MetaOntologyElement;
import org.protege.editor.owl.ning.exception.BasicException;

import javax.swing.table.AbstractTableModel;

public class ConfigTableModel extends AbstractTableModel
{
    private final int COLUMN_COUNT = 3;

    String[] columnNames = new String[]{"Name", "IsIncluded", "Icon"};

    private void checkColumnCount(int column)
    {
        if (column >= COLUMN_COUNT)
            throw new BasicException
                ("The column number in the table is not right");
    }

    @Override
    public int getColumnCount()
    {
        return COLUMN_COUNT;
    }

    @Override
    public String getColumnName(int column)
    {
        checkColumnCount(column);
        return columnNames[column];
    }

    @Override
    public int getRowCount()
    {
        MetaOntology metaOnt = MetaOntology.getMetaOntology();
        int rowCount =  metaOnt.getMetaConceptCount() +
            metaOnt.getMetaRelationCount() +
            metaOnt.getInstanceCount();
        return rowCount;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        checkColumnCount(columnIndex);
        MetaOntology metaOnt = MetaOntology.getMetaOntology();
        MetaOntologyElement moe = null;

        int metaCncptCount = metaOnt.getMetaConceptCount();
        int metaRelationCount = metaOnt.getMetaRelationCount();

        if (rowIndex < metaCncptCount)
        {
            moe = metaOnt.getMetaConcept(rowIndex);
        }else if (rowIndex - metaCncptCount < metaRelationCount)
        {
            moe = metaOnt.getMetaRelation(rowIndex - metaCncptCount);
        }else
        {
            moe = metaOnt.getInstance(rowIndex - metaCncptCount -
                                      metaRelationCount);
        }

        if (columnIndex == 0)
            return moe.getName();
        else if (columnIndex == 1)
            return moe.getIsIncluded();
        else
            return moe.getImagePath();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        if (columnIndex == 0)
            return false;
        return true;
    }

    @Override
    public Class getColumnClass(int c)
    {
        return getValueAt(0, c).getClass();
    }
}

package org.protege.editor.owl.ning.tab.dialog;

import org.protege.editor.owl.ning.domainOWL.MetaOntology;
import org.protege.editor.owl.ning.domainOWL.MetaOntologyElement;
import org.protege.editor.owl.ning.exception.BasicException;

import javax.swing.table.AbstractTableModel;

/**
 * The configure table model for the tables in the domain owl
 * configure dialog by customizing AbstractTableModel
 *
 * @author Zhu Ning
 * @version 0.1.0
 */
public abstract class ConfigTableModel extends AbstractTableModel
{
    /**
     * The column number of the table
     */
    private final int COLUMN_COUNT = 3;

    /**
     * The column names of the table
     */
    String[] columnNames = new String[]{"Name", "IsIncluded", "Icon"};

    /**
     * Checks if the column index is inside the scope of the max
     * column count constrainted by COLUMN_COUNT
     * @param column The column index to check
     * @exception BasicException Throws when the column index is not
     * less than the COLUMN_COUNT
     */
    protected void checkColumnCount(int column)
    {
        if (column >= COLUMN_COUNT)
            throw new BasicException
                ("The column number in the table is not right");
    }

    /**
     * Gets the meta ontology element (meta concept, meta relation and
     * instance) according to the row index in the table. It needs to
     * be overrided by different table models
     * @param rowIndex The row index of the meta ontology element is in
     * @return The meta ontology element in the row index rowIndex
     */
    protected MetaOntologyElement getMetaOntologyElement(int rowIndex)
    {
        return null;
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

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        checkColumnCount(columnIndex);
        MetaOntologyElement moe = getMetaOntologyElement(rowIndex);

        if (columnIndex == 0)
            return moe.getName();
        else if (columnIndex == 1)
            return moe.getIsIncluded();
        else
            return moe.getImagePath();
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
        checkColumnCount(columnIndex);
        MetaOntology metaOnt = MetaOntology.getMetaOntology();
        MetaOntologyElement moe = getMetaOntologyElement(rowIndex);

        if (columnIndex == 1)
            moe.setIsIncluded(!moe.getIsIncluded());

        else if (columnIndex == 2)
            moe.setImagePath((String)aValue);
    }

}

/**
 * The table model for the table listing meta concepts
 *
 * @author Zhu Ning
 * @version 0.1.0
 */
class MetaConceptConfigTableModel extends ConfigTableModel
{
    @Override
    public int getRowCount()
    {
        MetaOntology metaOnt = MetaOntology.getMetaOntology();
        return metaOnt.getMetaConceptCount();
    }

    @Override
    protected MetaOntologyElement getMetaOntologyElement(int rowIndex)
    {
        MetaOntology metaOnt = MetaOntology.getMetaOntology();
        return metaOnt.getMetaConcept(rowIndex);
    }
}

/**
 * The table model for the table listing meta relations
 *
 * @author Zhu Ning
 * @version 0.1.0
 */
class MetaRelationConfigTableModel extends ConfigTableModel
{
    @Override
    public int getRowCount()
    {
        MetaOntology metaOnt = MetaOntology.getMetaOntology();
        return metaOnt.getMetaRelationCount();
    }

    @Override
    protected MetaOntologyElement getMetaOntologyElement(int rowIndex)
    {
        MetaOntology metaOnt = MetaOntology.getMetaOntology();
        return metaOnt.getMetaRelation(rowIndex);
    }
}

/**
 * The table model for the table listing instances
 *
 * @author Zhu Ning
 * @version 0.1.0
 */
class InstanceConfigTableModel extends ConfigTableModel
{
    @Override
    public int getRowCount()
    {
        MetaOntology metaOnt = MetaOntology.getMetaOntology();
        return metaOnt.getInstanceCount();
    }

    @Override
    protected MetaOntologyElement getMetaOntologyElement(int rowIndex)
    {
        MetaOntology metaOnt = MetaOntology.getMetaOntology();
        return metaOnt.getInstance(rowIndex);
    }
}

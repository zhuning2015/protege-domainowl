package org.protege.editor.owl.ning.tab.dialog;

import org.protege.editor.owl.ning.domainOWL.MetaOntology;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

/**
 * The customized list model for the meta model list used to
 * creating domain concepts
 *
 * @author Zhu Ning
 * @version 0.1.0
 */
public class MetaConceptListModel implements ListModel
{

    @Override
    public int getSize()
    {
        MetaOntology metaOnt = MetaOntology.getMetaOntology();
        return metaOnt.getSelectedMetaConceptsCount();
    }

    @Override
    public Object getElementAt(int index)
    {
        MetaOntology metaOnt = MetaOntology.getMetaOntology();
        return metaOnt.getSelectedMetaConcept(index);
    }

    @Override
    public void addListDataListener(ListDataListener l)
    {
    }

    @Override
    public void removeListDataListener(ListDataListener l)
    {
    }
}

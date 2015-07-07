package org.protege.editor.owl.ning.tab.dialog;

import org.protege.editor.owl.ning.domainOWL.MetaOntology;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

public class MetaConceptListModel implements ListModel
{

    public int getSize()
    {
        MetaOntology metaOnt = MetaOntology.getMetaOntology();
        return metaOnt.getSelectedMetaConceptsCount();
    }

    public Object getElementAt(int index)
    {
        MetaOntology metaOnt = MetaOntology.getMetaOntology();
        return metaOnt.getSelectedMetaConcept(index);
    }

    public void addListDataListener(ListDataListener l)
    {
    }

    public void removeListDataListener(ListDataListener l)
    {
    }
}

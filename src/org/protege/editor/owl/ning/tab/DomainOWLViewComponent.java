package org.protege.editor.owl.ning.tab;

import java.awt.BorderLayout;
import javax.swing.*;

import org.apache.log4j.Logger;
import org.protege.editor.owl.model.hierarchy.AssertedClassHierarchyProvider;
import org.protege.editor.owl.ui.tree.OWLModelManagerTree;
import org.protege.editor.owl.ui.tree.OWLObjectTree;
import org.protege.editor.owl.ui.view.AbstractOWLViewComponent;
import org.protege.owl.example.Metrics;
import org.semanticweb.owlapi.model.OWLClass;

public class DomainOWLViewComponent extends AbstractOWLViewComponent {
    private static final long serialVersionUID = -4515710047558710080L;
    
    private static final Logger log = Logger.getLogger(DomainOWLViewComponent.class);
    
    private DomainOWLPanel domainOWLPanel = null;

    @Override
    protected void initialiseOWLView() throws Exception {
        setLayout(new BorderLayout());
        domainOWLPanel = new DomainOWLPanel(getOWLModelManager());
        
        add(domainOWLPanel, BorderLayout.CENTER);
        log.info("Example View Component initialized");
    }

    @Override
    protected void disposeOWLView() {
        domainOWLPanel.dispose();
    }
    
}

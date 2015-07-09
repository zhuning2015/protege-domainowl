package org.protege.editor.owl.ning.tab;

import org.protege.editor.owl.model.OWLModelManager;

import org.protege.editor.owl.ning.domainOWL.MetaOntology;
import org.protege.editor.owl.ning.domainOWL.MetaNode;
import org.protege.editor.owl.ning.domainOWL.MetaRelation;
import org.protege.editor.owl.ning.domainOWL.MetaConcept;
import org.protege.editor.owl.ning.domainOWL.Instance;
import org.protege.editor.owl.ning.domainOWL.DomainOWLObjectVisitor;
import org.protege.editor.owl.ning.domainOWL.DomainOntology;
import org.protege.editor.owl.ning.domainOWL.DomainConcept;
import org.protege.editor.owl.ning.util.NameParser;
import org.protege.editor.owl.ning.tab.dialog.DomainOWLPanelConfigureDlg;
import org.protege.editor.owl.ning.tab.dialog.MetaConceptListModel;
import org.protege.editor.owl.ning.tab.dialog.MetaConceptListCellRenderer;
import org.protege.editor.owl.ning.tab.dialog.LocalTransferableObject;
import org.protege.editor.owl.ning.tab.graph.DomainViewGraphModel;

import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLIndividual;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragGestureEvent;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;

import org.jgraph.graph.GraphModel;
import org.jgraph.graph.GraphLayoutCache;
import org.jgraph.graph.DefaultCellViewFactory;

/**
 * The main panel for the DomainOWL plugin
 *
 * @author Zhu Ning
 * @version 0.1.0
 */
public class DomainOWLPanel extends JPanel
{
    /**
     * The single meta ontology in the domain owl plugin
     */
    private MetaOntology metaOnt = null;

    /**
     * The installed dir of the domain owl plugin
     */
    private static String pluginDir = null;

    /**
     * The list in the left of the domain owl panel consisting
     * of meta concepts for developing domain concepts
     */
    private JList metaConceptList = null;

    /**
     * The panel in the left of the domain owl panel for
     * visualizing the domain concepts and their relations
     */
    DomainViewPanel domainViewPanel = null;

    /**
     * Sets the installed path of the plugin
     */
    private void setPluginPath()
    {
        String jarPath = this.getClass().getProtectionDomain().
            getCodeSource().getLocation().getPath();
        pluginDir = NameParser.getDir(jarPath);
    }

    /**
     * Gets the installed dir of the domain owl plugin
     * @return The installed dir of the domain owl plugin
     */
    public static String getPluginDir()
    {
        return pluginDir;
    }

    public DomainOWLPanel(OWLModelManager owlMdlMgr)
    {
        createMetaOntology(owlMdlMgr);

        setPluginPath();

        new DomainOWLPanelConfigureDlg(null,
                                       "Configure...",
                                       true).setVisible(true);
        buildUI();

        DomainOntology.create("Test");
    }

    /**
     * Creates the meta ontology based on the interface from Protege
     * @param owlMdlMgr The interfce provided by Protege to visit the
     * owl ontology
     */
    private void createMetaOntology(OWLModelManager owlMdlMgr)
    {
        OWLOntology owlOnt = owlMdlMgr.getActiveOntology();
        String strOntologyIRI =
            owlOnt.getOntologyID().getOntologyIRI().toString();
        String owlOntologyName =
            NameParser.getOWLOntologyName(strOntologyIRI);
        metaOnt = MetaOntology.create(owlOntologyName);
        fillMetaOntologyFromOWLOntology(owlOnt);
    }

    /**
     * Builds the user interface of the domain owl plugin
     */
    private void buildUI()
    {
        setLayout(new BorderLayout());

        metaConceptList = new JList(new MetaConceptListModel());
        metaConceptList.setCellRenderer(new MetaConceptListCellRenderer());
        DragSource dragSource = DragSource.getDefaultDragSource();
        dragSource.createDefaultDragGestureRecognizer(metaConceptList,
                                     DnDConstants.ACTION_COPY_OR_MOVE,
                            new MetaConceptListDragGestureListener());
        GraphModel model = new DomainViewGraphModel();
        GraphLayoutCache view = new GraphLayoutCache(model, new DefaultCellViewFactory());
        domainViewPanel = new DomainViewPanel(model, view);

        new DropTarget(domainViewPanel, DnDConstants.ACTION_COPY,
                       new DomainViewPanelDropTargetAdapter());

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                                         new JScrollPane(metaConceptList),
                                        new JScrollPane(domainViewPanel));
        splitPane.setDividerLocation(64);

        add(splitPane, BorderLayout.CENTER);
    }

    /**
     * Fills the meta ontology in the domain owl from the active
     * owl ontology
     * @param owlOnt The active owl ontology in Protege
     */
    private void fillMetaOntologyFromOWLOntology(OWLOntology owlOnt)
    {
        createMetaConceptsFromOWLClasses(owlOnt);
        createMetaRelationsFromOWLObjectProperties(owlOnt);
        createMetaRelationsFromOWLDataProperties(owlOnt);
        createRelationsFromOWLDataProperties(owlOnt);
    }

    /**
     * Creates the meta concepts in the meta ontology according to
     * the owl classes in the active ontology
     * @param owlOnt The active owl ontology in Protege
     */
    private void createMetaConceptsFromOWLClasses(OWLOntology owlOnt)
    {
        for(OWLClass owlCls : owlOnt.getClassesInSignature())
        {
            if (owlCls.isOWLThing())
            {
                continue;
            }
            String mcName = NameParser.getSimpleName(owlCls);
            MetaConcept mc = metaOnt.createMetaConcept(mcName);
            owlCls.accept(new DomainOWLObjectVisitor(mc));
        }
    }


    /**
     * Creates the meta relations in the meta ontology according to
     * the owl object properties in the active ontology
     * @param owlOnt The active owl ontology in Protege
     */
    private void createMetaRelationsFromOWLObjectProperties
        (OWLOntology owlOnt)
    {
        for(OWLObjectProperty owlObjPrpty :
                owlOnt.getObjectPropertiesInSignature())
        {
            if (owlObjPrpty.isOWLTopObjectProperty())
            {
                continue;
            }
            String mrName = NameParser.getSimpleName(owlObjPrpty);
            MetaRelation mr = metaOnt.createMetaRelation(mrName);
            mr.setMetaRelationType
                (MetaRelation.MetaRelationType.OBJECT_RELATION);
            owlObjPrpty.accept(new DomainOWLObjectVisitor(mr));
        }

    }

    /**
     * Creates the meta relations in the meta ontology according to
     * the owl data properties in the active ontology
     * @param owlOnt The active owl ontology in Protege
     */
    private void createMetaRelationsFromOWLDataProperties
        (OWLOntology owlOnt)
    {
        for(OWLDataProperty owlDataPrpty :
                owlOnt.getDataPropertiesInSignature())
        {
            if (owlDataPrpty.isOWLTopDataProperty())
            {
                continue;
            }
            String mrName = NameParser.getSimpleName(owlDataPrpty);
            MetaRelation mr = metaOnt.createMetaRelation(mrName);
            owlDataPrpty.accept(new DomainOWLObjectVisitor(mr));
        }

    }

    /**
     * Creates the instances in the meta ontology according to
     * the owl individuals in the active ontology
     * @param owlOnt The active owl ontology in Protege
     */
    private void createRelationsFromOWLDataProperties(OWLOntology owlOnt)
    {
        for(OWLIndividual owlIndv : owlOnt.getIndividualsInSignature())
        {
            String instName = NameParser.getSimpleName(owlIndv);
            Instance inst = metaOnt.createInstance(instName);
            owlIndv.accept(new DomainOWLObjectVisitor(inst));
        }
    }

    /**
     * Releases the resources applied if there are some
     */
    public void dispose()
    {

    }

    /**
     * The implementation of DragGestrueListener for dragging meta concepts
     * in the metaConceptList into the domainViewPanel to create new domain
     * concepts
     */
    private class MetaConceptListDragGestureListener
        implements DragGestureListener
    {
        @Override
        public void dragGestureRecognized(DragGestureEvent event)
        {
            MetaConcept mc =
                (MetaConcept)metaConceptList.getSelectedValue();

            Transferable transferable = new LocalTransferableObject(mc);
            event.startDrag(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR),
                            transferable);
        }
    }

    /**
     * The customized DropTragetAdapter for response on the drop of meta
     * concepts into the domainViewPanel to create new domain concepts
     */
    private class DomainViewPanelDropTargetAdapter
        extends DropTargetAdapter
    {
        public void drop(DropTargetDropEvent event)
        {
            event.acceptDrop(DnDConstants.ACTION_COPY);
            Transferable transferable =event.getTransferable();
            try{
                String mimeType =
                    "application/x-java-jvm-local-objectref;class=" +
                    MetaConcept.class.getName();
                DataFlavor objectRefFlavor =
                    new DataFlavor(mimeType, MetaConcept.class.getName(),
                                   MetaConcept.class.getClassLoader());
                if (transferable.isDataFlavorSupported(objectRefFlavor))
                {
                    Object transferData =
                        transferable.getTransferData(objectRefFlavor);
                    MetaConcept mc = (MetaConcept) transferData;
                    DomainConcept dc = DomainConcept.create(mc.getName());
                    dc.setMetaConcept(mc);
                    domainViewPanel.addCell(dc);
                }
            }catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, e.toString());
            }
            event.dropComplete(true);
        }
    }
}

package org.protege.editor.owl.ning.tab.dialog;

import org.protege.editor.owl.ning.domainOWL.MetaOntology;
import org.protege.editor.owl.ning.domainOWL.MetaConcept;
import org.protege.editor.owl.ning.domainOWL.MetaRelation;
import org.protege.editor.owl.ning.domainOWL.Instance;
import org.protege.editor.owl.ning.domainOWL.MetaOntologyElement;
import org.protege.editor.owl.ning.tab.DomainOWLPanel;

import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.DefaultCellEditor;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Frame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import java.io.File;
import java.io.FilenameFilter;
import java.io.Writer;
import java.io.FileWriter;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.XMLWriter;
import org.dom4j.Element;


/**
 * The dialog to configure the domain owl panel to decide which
 * meta concepts, meta relations, individuals are included in
 * the domain owl panel to create the domain ontology
 *
 * @author Zhu Ning
 * @version 0.1.1
 */
public class DomainOWLPanelConfigureDlg extends JDialog
{
    /**
     * The types for the tables shown in the configure dialog
     */
    private enum TableType
    {
        METACONCEPT_TABLE,
        METARELATION_TABLE,
        INSTANCE_TABLE
    }

    public DomainOWLPanelConfigureDlg(Frame owner,
                                      String title,
                                      boolean modal)
    {
        super(owner, title, modal);

        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2,2));

        JPanel metaCncptPanel =
            createSubConfigPanel(TableType.METACONCEPT_TABLE);
        JPanel metaRelationPanel =
            createSubConfigPanel(TableType.METARELATION_TABLE);
        JPanel instancePanel =
            createSubConfigPanel(TableType.INSTANCE_TABLE);

        mainPanel.add(metaCncptPanel);
        mainPanel.add(metaRelationPanel);
        mainPanel.add(instancePanel);

        JPanel buttonPanel = new JPanel();
        JCheckBox checkBox =
            new JCheckBox("Don't show the dialog next time",
                          DomainOWLPanel.hidesConfigDialog);
        checkBox.addItemListener(new ItemListener()
        {
            public void itemStateChanged(ItemEvent e)
            {
                DomainOWLPanel.hidesConfigDialog =
                    !DomainOWLPanel.hidesConfigDialog;
            }
        });

        JButton okBtn = new JButton("OK");
        buttonPanel.add(checkBox);
        buttonPanel.add(okBtn);
        okBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                try
                {
                    saveConfig();
                }catch(IOException e)
                {
                    JOptionPane.showMessageDialog(null, e.toString());
                }
                DomainOWLPanelConfigureDlg.this.dispose();
            }
        });

        add(new JScrollPane(mainPanel), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
    }

    /**
     * Saves the configure information for the domain owl plugin in the
     * plugin dir with name .configure
     * @exception IOException Throwed when IO errors happen
     */
    private void saveConfig() throws IOException
    {
        Document doc = DocumentHelper.createDocument();
        String absPathName = DomainOWLPanel.getPluginDir() +
            ".configure";
        Writer fileWriter = new FileWriter(absPathName);
        XMLWriter xmlWriter = new XMLWriter(fileWriter);
        Element rootElement = doc.addElement("MetaOntologyElements");
        rootElement.setText("All the elements of the meta ontology and their configure information");
        String hidesFlag =
            DomainOWLPanel.hidesConfigDialog ? "true" : "false";
        rootElement.addAttribute("hidesConfigDialog", hidesFlag);

        MetaOntology metaOntology = MetaOntology.getMetaOntology();
        for (int i = 0; i < metaOntology.getMetaConceptCount(); i++)
        {
            MetaConcept mc = metaOntology.getMetaConcept(i);
            if (mc.getIsIncluded())
            {
                createElement(rootElement, mc);
            }
        }

        for (int i = 0; i < metaOntology.getMetaRelationCount(); i++)
        {
            MetaRelation mr = metaOntology.getMetaRelation(i);
            if (mr.getIsIncluded())
            {
                createElement(rootElement, mr);
            }
        }

        for (int i = 0; i < metaOntology.getInstanceCount(); i++)
        {
            Instance inst = metaOntology.getInstance(i);
            if (inst.getIsIncluded())
                createElement(rootElement, inst);
        }

        xmlWriter.write(doc);
        xmlWriter.flush();
        xmlWriter.close();
    }

    /**
     * Create child XML Element of parentElement accroding to the
     * MetaOntologyElement moe
     * @param parentElement The parent element of the creating element
     * @param moe The information to create the element
     */
    private void createElement(Element parentElement,
                               MetaOntologyElement moe)
    {
        Element element =
            parentElement.addElement("MetaOntologyElement");
        element.setText(moe.getName());
        element.addAttribute("iconName", moe.getIconName());
    }

    /**
     * Create a sub config panel according to different type of meta
     * tables including MetaConcept Table, MetaRelation Table and
     * Instance Table
     * @param tableType The type of the table
     * @return The sub configure panel for the tableType-type table
     */
    private JPanel createSubConfigPanel(TableType tableType)
    {
        String title = null;
        JTable table = null;

        switch (tableType)
        {
        case METACONCEPT_TABLE:
            title = "Meta Concepts:";
            table = new JTable(new MetaConceptConfigTableModel());
            table.getColumnModel().getColumn(2).
            setCellEditor(new DefaultCellEditor(createImageIconComboBox("resources/icons/")));
            table.getColumnModel().getColumn(2).
            setCellRenderer(new ImageTableCellRenderer("resources/icons/"));
            break;
        case METARELATION_TABLE:
            title = "Meta Relations:";
            table = new JTable(new MetaRelationConfigTableModel());
            table.getColumnModel().getColumn(2).
            setCellEditor(new DefaultCellEditor(createImageIconComboBox("resources/icons/edgeEnds/")));
            table.getColumnModel().getColumn(2).
            setCellRenderer(new ImageTableCellRenderer("resources/icons/edgeEnds/"));
            break;
        case INSTANCE_TABLE:
            title = "Individuals:";
            table = new JTable(new InstanceConfigTableModel());
            table.getColumnModel().getColumn(2).
            setCellEditor(new DefaultCellEditor(createImageIconComboBox("resources/icons/")));
            table.getColumnModel().getColumn(2).
            setCellRenderer(new ImageTableCellRenderer("resources/icons/"));
        }

        JPanel configPanel = new JPanel();
        configPanel.setBorder(BorderFactory.createTitledBorder(title));

        configPanel.add(new JScrollPane(table));

        return configPanel;
    }

    /**
     * Creates a combo box for customizing the cell editor for the configure
     * Table
     * @return The combo box consisting all of the image icons in the
     * relative dir such as "../resource/icons/"
     */
    private JComboBox<ImageIcon> createImageIconComboBox(String relativePath)
    {
        JComboBox<ImageIcon> imageIconComboBox = new JComboBox<ImageIcon>();
        File file =
            new File(DomainOWLPanel.getPluginDir() + relativePath + ".");

        String[] pngs = file.list(new FilenameFilter()
        {
            @Override
            public boolean accept(File dir, String name)
            {
                return name.endsWith("_16_16.png");
            }
        });

        for (String png : pngs)
        {
            ImageIcon imageIcon =
                new ImageIcon(DomainOWLPanel.getPluginDir() +
                              relativePath + png);
            imageIcon.setDescription(png);
            imageIconComboBox.addItem(imageIcon);
        }

        return imageIconComboBox;
    }
}

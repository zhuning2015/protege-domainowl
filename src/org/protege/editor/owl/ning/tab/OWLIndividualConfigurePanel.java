package org.protege.editor.owl.ning.tab;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpressionVisitor;
import org.semanticweb.owlapi.model.OWLDataSomeValuesFrom;
import org.semanticweb.owlapi.model.OWLDataMaxCardinality;
import org.semanticweb.owlapi.model.OWLDataExactCardinality;
import org.semanticweb.owlapi.model.OWLDataMinCardinality;
import org.semanticweb.owlapi.model.OWLDataHasValue;
import org.semanticweb.owlapi.model.OWLDataAllValuesFrom;
import org.semanticweb.owlapi.model.OWLObjectOneOf;
import org.semanticweb.owlapi.model.OWLObjectHasSelf;
import org.semanticweb.owlapi.model.OWLObjectMaxCardinality;
import org.semanticweb.owlapi.model.OWLObjectExactCardinality;
import org.semanticweb.owlapi.model.OWLObjectMinCardinality;
import org.semanticweb.owlapi.model.OWLObjectHasValue;
import org.semanticweb.owlapi.model.OWLObjectAllValuesFrom;
import org.semanticweb.owlapi.model.OWLObjectSomeValuesFrom;
import org.semanticweb.owlapi.model.OWLObjectComplementOf;
import org.semanticweb.owlapi.model.OWLObjectUnionOf;
import org.semanticweb.owlapi.model.OWLObjectIntersectionOf;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;

class CustomedOWLClassExpressionVisitor implements OWLClassExpressionVisitor{
    JPanel panel = null;
    OWLOntology owlOntology = null;
    HashMap<String,HashMap<String, HashMap<String,ArrayList<String>>>> owlInstances = null;
    String owlClassName = "";
    String customedElementName = "";
    HashMap<String,HashMap<String,ArrayList<String>>> instances = null;
    HashMap<String, ArrayList<String>> connections = null;
    ArrayList<String> connectors = null;

    public CustomedOWLClassExpressionVisitor(String owlClassName, String customedElementName,
                                             JPanel contentPanel, OWLOntology owlOntology,
                                             HashMap<String,HashMap<String, HashMap<String,ArrayList<String>>>> owlInstances){
        panel = contentPanel;
        this.owlOntology = owlOntology;
        this.owlInstances = owlInstances;
        this.owlClassName = owlClassName;
        this.customedElementName = customedElementName;
        instances = owlInstances.get(owlClassName);
        connections = instances.get(customedElementName);
    }

    public void visit(OWLDataSomeValuesFrom ce){
    }

    public void visit(OWLDataMaxCardinality ce){
    }

    public void visit(OWLDataExactCardinality ce){
    }

    public void visit(OWLDataMinCardinality ce){
    }

    public void visit(OWLDataHasValue ce){
    }

    public void visit(OWLDataAllValuesFrom ce){
    }

    public void visit(OWLObjectOneOf ce){
    }

    public void visit(OWLObjectHasSelf ce){
    }

    public void visit(OWLObjectMaxCardinality ce){
    }

    public void visit(OWLObjectExactCardinality ce){
    }

    public void visit(OWLObjectMinCardinality ce){
    }

    public void visit(OWLObjectHasValue ce){
    }

    public void visit(OWLObjectAllValuesFrom ce){
    }

    public void visit(OWLObjectSomeValuesFrom ce){
        JPanel subPanel = new JPanel();
        Border border = BorderFactory.createEtchedBorder(EtchedBorder.RAISED,Color.RED,Color.GREEN);
        subPanel.setBorder(border);
        subPanel.setLayout(new BorderLayout());
        String fullPropertyName = ce.getProperty().toString();
        String propertyName = fullPropertyName.substring(fullPropertyName.indexOf('#') + 1, fullPropertyName.length() -1);
        subPanel.add(new JLabel(propertyName), BorderLayout.NORTH);
        DefaultListModel listModel = new DefaultListModel();
        JList list = new JList(listModel);
        if(connections != null){
            connectors = connections.get(propertyName);
            if (connectors != null){
                for (String connector : connectors){
                    listModel.addElement(connector);
                }
            }
        }
        list.setPreferredSize(new Dimension(160,240));
        subPanel.add(list, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add");
        JButton removeButton = new JButton("Remove");
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        subPanel.add(buttonPanel, BorderLayout.SOUTH);
        panel.add(subPanel);

        String fullFillerName = ce.getFiller().toString();
        String fillerName = fullFillerName.substring(fullFillerName.indexOf('#') + 1, fullFillerName.length() - 1);
        addButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                HashMap<String, HashMap<String, ArrayList<String>>> fillerInstances = owlInstances.get(fillerName);
                if (fillerInstances != null){
                    String selectedInstanceName = (String)JOptionPane.showInputDialog(null, "please select one from the list", "Select an instance",
                                                                                      JOptionPane.INFORMATION_MESSAGE,null,
                                                                                      fillerInstances.keySet().toArray(), "");
                    if (!listModel.contains(selectedInstanceName)){
                        listModel.addElement(selectedInstanceName);

                        if (connections == null){
                            connections = new HashMap<String, ArrayList<String>>();
                        }
                        if (connectors == null){
                            connectors = new ArrayList<String>();
                        }
                        connectors.add(selectedInstanceName);
                        connections.put(propertyName, connectors);
                        instances.put(customedElementName, connections);
                        owlInstances.put(owlClassName, instances);
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "There are no intances of type " + fillerName, "List build error", JOptionPane.ERROR_MESSAGE);
                }
            }});
        removeButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                Object selectedItem = list.getSelectedValue();
                if (selectedItem != null){
                    listModel.removeElement(selectedItem);
                    connectors.remove((String)selectedItem);
                    connections.put(propertyName, connectors);
                    instances.put(customedElementName, connections);
                    owlInstances.put(owlClassName, instances);
                }
            }
            });

    }

    public void visit(OWLObjectComplementOf ce){
    }

    public void visit(OWLObjectUnionOf ce){
    }

    public void visit(OWLObjectIntersectionOf ce){
    }

    public void visit(OWLClass ce){
        for (OWLSubClassOfAxiom ax : owlOntology.getSubClassAxiomsForSubClass(ce)){
            OWLClassExpression superCls = ax.getSuperClass();
            superCls.accept(new CustomedOWLClassExpressionVisitor(owlClassName, customedElementName, panel, owlOntology, owlInstances));
        }
    }
}

public class OWLIndividualConfigurePanel extends JPanel{
    public OWLIndividualConfigurePanel(String customedElementName, OWLClass owlClass, OWLOntology owlOntology,
                                      HashMap<String,HashMap<String, HashMap<String,ArrayList<String>>>> owlInstances){
        setLayout(new BorderLayout());
        //title panel
        JPanel titlePanel = new JPanel();
        titlePanel.add(new JLabel("Name: "));
        JTextField individualNameField = new JTextField(customedElementName);
        individualNameField.setEditable(false);
        titlePanel.add(individualNameField);
        add(titlePanel, BorderLayout.NORTH);
        //content panel
        JPanel contentPanel = new JPanel();

        String fullOWLClassName = owlClass.toString();
        String owlClassName = fullOWLClassName.substring(fullOWLClassName.indexOf('#') + 1, fullOWLClassName.length() - 1);
        for (OWLSubClassOfAxiom ax : owlOntology.getSubClassAxiomsForSubClass(owlClass)){
            OWLClassExpression superCls = ax.getSuperClass();
            superCls.accept(new CustomedOWLClassExpressionVisitor(owlClassName, customedElementName, contentPanel,owlOntology, owlInstances));
        }

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        add(scrollPane, BorderLayout.CENTER);
    }
}

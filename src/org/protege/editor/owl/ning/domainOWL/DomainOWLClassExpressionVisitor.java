package org.protege.editor.owl.ning.domainOWL;

import org.protege.editor.owl.ning.domainOWL.Restriction;
import org.protege.editor.owl.ning.domainOWL.RestrictionType;
import org.protege.editor.owl.ning.util.NameParser;

import org.semanticweb.owlapi.model.OWLQuantifiedDataRestriction;
import org.semanticweb.owlapi.model.OWLQuantifiedObjectRestriction;
import org.semanticweb.owlapi.model.OWLDataCardinalityRestriction;
import org.semanticweb.owlapi.model.OWLObjectCardinalityRestriction;
import org.semanticweb.owlapi.model.OWLDataAllValuesFrom;
import org.semanticweb.owlapi.model.OWLDataExactCardinality;
import org.semanticweb.owlapi.model.OWLDataHasValue;
import org.semanticweb.owlapi.model.OWLDataMaxCardinality;
import org.semanticweb.owlapi.model.OWLDataMinCardinality;
import org.semanticweb.owlapi.model.OWLDataSomeValuesFrom;
import org.semanticweb.owlapi.model.OWLObjectAllValuesFrom;
import org.semanticweb.owlapi.model.OWLObjectExactCardinality;
import org.semanticweb.owlapi.model.OWLObjectMaxCardinality;
import org.semanticweb.owlapi.model.OWLObjectMinCardinality;
import org.semanticweb.owlapi.model.OWLObjectSomeValuesFrom;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.util.OWLClassExpressionVisitorAdapter;

import javax.swing.JOptionPane;

public class DomainOWLClassExpressionVisitor
    extends OWLClassExpressionVisitorAdapter
{
    private MetaNode mc = null;

    public DomainOWLClassExpressionVisitor(MetaNode mc)
    {
        this.mc = mc;
    }

    private <T extends OWLQuantifiedDataRestriction>
                       Restriction createRestriction(T ce)
    {
        String restrcPrptyName = NameParser.getSimpleName(ce.getProperty());
        String restrcFillerName = NameParser.getSimpleName(ce.getFiller());
        Restriction restrc = new Restriction(restrcPrptyName,
                                             restrcFillerName);
        mc.addRestriction(restrc);
        return restrc;
    }

    private <T extends OWLQuantifiedObjectRestriction>
                       Restriction createRestriction(T ce)
    {
        String restrcPrptyName = NameParser.getSimpleName(ce.getProperty());
        String restrcFillerName = NameParser.getSimpleName(ce.getFiller());
        Restriction restrc = new Restriction(restrcPrptyName,
                                             restrcFillerName);
        mc.addRestriction(restrc);
        return restrc;
    }

    private <T extends OWLDataCardinalityRestriction>
                       Restriction createRestriction(T ce)
    {
        String restrcPrptyName = NameParser.getSimpleName(ce.getProperty());
        String restrcFillerName = NameParser.getSimpleName(ce.getFiller());
        int cardinality = ce.getCardinality();
        Restriction restrc =  new Restriction(RestrictionType.MIN_DATA,
                                              restrcPrptyName,
                                              restrcFillerName,
                                              cardinality
                                              );
        mc.addRestriction(restrc);
        return restrc;
    }

    private <T extends OWLObjectCardinalityRestriction>
                       Restriction createRestriction(T ce)
    {
        String restrcPrptyName = NameParser.getSimpleName(ce.getProperty());
        String restrcFillerName = NameParser.getSimpleName(ce.getFiller());
        int cardinality = ce.getCardinality();
        Restriction restrc =  new Restriction(RestrictionType.MIN_DATA,
                                              restrcPrptyName,
                                              restrcFillerName,
                                              cardinality
                                              );
        mc.addRestriction(restrc);
        return restrc;
    }

    public void visit(OWLClassExpression ce)
    {
        switch (ce.getClassExpressionType())
        {
            case DATA_ALL_VALUES_FROM:
            {
                visit((OWLDataAllValuesFrom) ce);
                break;
            }
            case DATA_EXACT_CARDINALITY:
            {
                visit((OWLDataExactCardinality) ce);
                break;
            }
            case DATA_HAS_VALUE:
            {
                visit( (OWLDataHasValue) ce);
                break;
            }
            case DATA_MAX_CARDINALITY:
            {
                visit((OWLDataMaxCardinality) ce);
                break;
            }
            case DATA_MIN_CARDINALITY:
            {
                visit((OWLDataMinCardinality) ce);
                break;
            }
            case DATA_SOME_VALUES_FROM:
            {
                visit((OWLDataSomeValuesFrom) ce);
                break;
            }
            case OBJECT_ALL_VALUES_FROM:
            {
                visit((OWLObjectAllValuesFrom) ce);
                break;
            }
            case OBJECT_EXACT_CARDINALITY:
            {
                visit((OWLObjectExactCardinality) ce);
                break;
            }
            case OBJECT_MAX_CARDINALITY:
            {
                visit((OWLObjectMaxCardinality) ce);
                break;
            }
            case OBJECT_SOME_VALUES_FROM:
            {
                visit((OWLObjectSomeValuesFrom) ce);
                break;
            }
        }
    }
    @Override
    public void visit(OWLDataAllValuesFrom ce)
    {
        createRestriction(ce);
    }

    @Override
    public void visit(OWLDataExactCardinality ce)
    {
        Restriction restrc = createRestriction(ce);
        restrc.setRestrictionType(RestrictionType.EXACTLY_DATA);
    };

    /**
     * Have not found the HasValue implementation in Protege
     * So OWLDataHasValue-type expressions are not visited
     */
    @Override
    public void visit(OWLDataHasValue ce)
    {

    }

    @Override
    public void visit(OWLDataMaxCardinality ce)
    {
        Restriction restrc = createRestriction(ce);
        restrc.setRestrictionType(RestrictionType.MAX_DATA);
    }

    @Override
    public void visit(OWLDataMinCardinality ce)
    {
        createRestriction(ce);
    }

    @Override
    public void visit(OWLDataSomeValuesFrom ce)
    {
        Restriction restrc = createRestriction(ce);
        restrc.setRestrictionType(RestrictionType.SOME_DATA);
    }

    @Override
    public void visit(OWLObjectAllValuesFrom ce)
    {
        Restriction restrc = createRestriction(ce);
        restrc.setRestrictionType(RestrictionType.ONLY_OBJECT);
    }

    @Override
    public void visit(OWLObjectExactCardinality ce)
    {
        Restriction restrc = createRestriction(ce);
        restrc.setRestrictionType(RestrictionType.EXACTLY_OBJECT);
    }

    @Override
    public void visit(OWLObjectMaxCardinality ce)
    {
        Restriction restrc = createRestriction(ce);
        restrc.setRestrictionType(RestrictionType.MAX_OBJECT);
    }

    @Override
    public void visit(OWLObjectMinCardinality ce)
    {
        Restriction restrc = createRestriction(ce);
        restrc.setRestrictionType(RestrictionType.MIN_OBJECT);
    }

    @Override
    public void visit(OWLObjectSomeValuesFrom ce)
    {
        Restriction restrc = createRestriction(ce);
        restrc.setRestrictionType(RestrictionType.SOME_OBJECT);
    }

}

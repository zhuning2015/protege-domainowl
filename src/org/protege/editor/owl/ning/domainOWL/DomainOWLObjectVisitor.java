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
import org.semanticweb.owlapi.model.OWLPropertyRange;
import org.semanticweb.owlapi.model.OWLDataHasValue;
import org.semanticweb.owlapi.model.OWLDataMaxCardinality;
import org.semanticweb.owlapi.model.OWLDataMinCardinality;
import org.semanticweb.owlapi.model.OWLDataSomeValuesFrom;
import org.semanticweb.owlapi.model.OWLObjectAllValuesFrom;
import org.semanticweb.owlapi.model.OWLObjectExactCardinality;
import org.semanticweb.owlapi.model.OWLObjectMaxCardinality;
import org.semanticweb.owlapi.model.OWLObjectMinCardinality;
import org.semanticweb.owlapi.model.OWLObjectSomeValuesFrom;
import org.semanticweb.owlapi.util.OWLObjectVisitorAdapter;
import org.semanticweb.owlapi.model.OWLDataPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLPropertyDomainAxiom;

/**
 * The class customizing OWLObjectVisitorAdapter in OWLAPI
 * for visiting OWLObjectProperty and OWLDataProperty
 */
public class DomainOWLObjectVisitor extends OWLObjectVisitorAdapter
{
    private MetaRelation mr = null;

    private MetaNode mc = null;

    public DomainOWLObjectVisitor(MetaNode mc)
    {
        this.mc = mc;
    }

    public DomainOWLObjectVisitor(MetaRelation mr)
    {
        this.mr = mr;
    }

    private <T extends OWLPropertyRangeAxiom> void visitRange(T axiom)
    {
        String dstCncpt =
            NameParser.getSimpleName(axiom.getRange());
        mr.addDst(dstCncpt);
    }

    private <T extends OWLPropertyDomainAxiom> void visitDomain(T axiom)
    {
        String srcCncpt =
            NameParser.getSimpleName(axiom.getDomain());
        mr.addSrc(srcCncpt);
        MetaConcept mc = MetaOntology.getMetaOntology().
            getMetaConcept("dstCncpt");
        String prptyName =
            NameParser.getSimpleName(axiom.getProperty());
        mc.addOutgoingMetaRelation(prptyName);
    }

    @Override
    public void visit(OWLDataPropertyRangeAxiom axiom)
    {
        visitRange(axiom);
    }

    @Override
    public void visit(OWLDataPropertyDomainAxiom axiom)
    {
        visitDomain(axiom);
    }

    @Override
    public void visit(OWLObjectPropertyRangeAxiom axiom)
    {
        visitRange(axiom);
    }

    @Override
    public void visit(OWLObjectPropertyDomainAxiom axiom)
    {
        visitDomain(axiom);
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

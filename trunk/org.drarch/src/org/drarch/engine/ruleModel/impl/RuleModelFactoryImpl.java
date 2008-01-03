/**
 * <copyright>
 * </copyright>
 *
 * $Id: RuleModelFactoryImpl.java,v 1.1 2006/10/07 19:08:21 nfrontini Exp $
 */
package org.drarch.engine.ruleModel.impl;

import org.drarch.engine.ruleModel.DrarchFileModel;
import org.drarch.engine.ruleModel.Fact;
import org.drarch.engine.ruleModel.FactSet;
import org.drarch.engine.ruleModel.Query;
import org.drarch.engine.ruleModel.Rule;
import org.drarch.engine.ruleModel.RuleModelFactory;
import org.drarch.engine.ruleModel.RuleModelPackage;
import org.drarch.engine.ruleModel.Var;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EFactoryImpl;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class RuleModelFactoryImpl extends EFactoryImpl implements RuleModelFactory {
	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RuleModelFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case RuleModelPackage.DRARCH_FILE_MODEL: return createDrarchFileModel();
			case RuleModelPackage.FACT: return createFact();
			case RuleModelPackage.FACT_SET: return createFactSet();
			case RuleModelPackage.QUERY: return createQuery();
			case RuleModelPackage.RULE: return createRule();
			case RuleModelPackage.VAR: return createVar();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DrarchFileModel createDrarchFileModel() {
		DrarchFileModelImpl drarchFileModel = new DrarchFileModelImpl();
		return drarchFileModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Fact createFact() {
		FactImpl fact = new FactImpl();
		return fact;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FactSet createFactSet() {
		FactSetImpl factSet = new FactSetImpl();
		return factSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Query createQuery() {
		QueryImpl query = new QueryImpl();
		return query;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Rule createRule() {
		RuleImpl rule = new RuleImpl();
		return rule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Var createVar() {
		VarImpl var = new VarImpl();
		return var;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RuleModelPackage getRuleModelPackage() {
		return (RuleModelPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	public static RuleModelPackage getPackage() {
		return RuleModelPackage.eINSTANCE;
	}

} //RuleModelFactoryImpl

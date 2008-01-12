package org.drarch.engine.jdt;

import org.apache.log4j.Logger;
import org.drarch.engine.DrarchEngine;
import org.drarch.engine.stepEngine.drarch.DrarchInteractivePhase;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SimpleType;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeLiteral;

/**
 * Overrides the visit method to add the facts into the Knowledge base.
 * 
 */
public class MethodInvocationVisitor extends ASTVisitor {

	public static final Logger logger = Logger
			.getLogger(MethodInvocationVisitor.class.getName());

	private String sourceLocation;

	/**
	 * Initialice the visitor with the source location of the invocated method.
	 * 
	 * @param theSourceLocation
	 *            The full method name (package.class.method).
	 */
	public MethodInvocationVisitor(String theSourceLocation) {
		sourceLocation = theSourceLocation;
	}

	public boolean visit(MethodInvocation node) {
		if (node.getExpression() instanceof SimpleName) {
			SimpleName simpleClassName = (SimpleName) node.getExpression();
			String className = simpleClassName.getFullyQualifiedName();
			String fullClassName = getFullClassName(simpleClassName, className);
			if (fullClassName == null) {
				fullClassName = getPackageName(sourceLocation) + "."
						+ className;
			}
			SimpleName methodName = node.getName();
			String fact = "invocationMethod(" + sourceLocation + ", "
					+ fullClassName + "." + methodName.getFullyQualifiedName()
					+ ").";
			
			DrarchInteractivePhase phase = (DrarchInteractivePhase) 
					DrarchEngine.INSTANCE.getCurrentPhase();
			phase.getPhaseHelper().getKnowledgebase().addFact(fact, phase.getName());

			// Add the parameters into a new fact.
			addArguments(node, fact);
		}
		return true;
	}

	private String getPackageName(String fullMethodName) {
		String fullClasName = fullMethodName.substring(0, fullMethodName
				.lastIndexOf("."));
		String fullPackageName = fullClasName.substring(0, fullClasName
				.lastIndexOf("."));
		return fullPackageName;
	}

	/**
	 * Add the facts 'invocationMethod' with the arguments.
	 * 
	 * @param node
	 *            The <code>ASTNode</code>.
	 * @param fact
	 *            The fact without the arguments.
	 */
	private void addArguments(MethodInvocation node, String fact) {
		for (Object object : node.arguments()) {
			String argument = "null";
			if (object instanceof TypeLiteral) {
				TypeLiteral typeLiteral = (TypeLiteral) object;
				Type type = typeLiteral.getType();
				if (type.isSimpleType()) {
					SimpleType simpleType = (SimpleType) type;
					argument = simpleType.getName().getFullyQualifiedName();
				}
			} else {
				if (object instanceof SimpleName) {
					SimpleName simpleArgumentName = (SimpleName) object;
					String i = simpleArgumentName.getIdentifier();
					ITypeBinding r = simpleArgumentName.resolveTypeBinding();
					argument = r.getName();
				}
			}
			String newFact = fact.substring(0, fact.indexOf(").")) + ", "
					+ argument + ").";
			DrarchInteractivePhase phase = (DrarchInteractivePhase) 
				DrarchEngine.INSTANCE.getCurrentPhase();
			phase.getPhaseHelper().getKnowledgebase().addFact(newFact, phase.getName());

		}
	}

	/**
	 * Get the full name of the specified class (e.g. package.class). Inspect
	 * the <code>ASTNode</code> to get the package name.
	 * 
	 * @param node
	 *            The <code>ASTNode</code>.
	 * @param className
	 *            The Name of the class.
	 * @return Returns a full qualified name of the class name specified.
	 *         Returns null if the import declaration don't have the specified
	 *         class.
	 */
	private String getFullClassName(ASTNode node, String className) {
		while (node != null) {
			if (node instanceof CompilationUnit) {
				break;
			}
			node = node.getParent();
		}
		CompilationUnit cu = (CompilationUnit) node;
		for (Object object : cu.imports()) {
			if (object instanceof ImportDeclaration) {
				ImportDeclaration importDeclaration = (ImportDeclaration) object;
				String importName = importDeclaration.getName()
						.getFullyQualifiedName();
				if (importName.endsWith(className)) {
					return importName;
				}
			}
		}
		return null;
	}
}

package Files;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.*;

/**
 * counts the number of references and declaration of a given name in a Java file
 * @author ben
 */
public class QualifiedNameCounter extends ASTVisitor{
	private String givenQualifiedName = null;
	private int declarationCount = 0;
	private int referenceCount = 0;
	
	/**
	 * @param input = qualified name to search for
	 */
	public QualifiedNameCounter(String input) {
		givenQualifiedName = input;
	}
	
	/**
	 * @overrides visit(TypeDeclaration node)
	 * counts the number of declaration of the qualified name
	 */
	public boolean visit(TypeDeclaration node) {
	//	System.out.println("TypeDeclaration name " + node.getName().getFullyQualifiedName() + node.getStartPosition());
		if (node.resolveBinding() != null && (!node.isInterface())) {
		//	System.out.println("TypeDeclaration " + node.resolveBinding().getQualifiedName());
			if (node.resolveBinding().getQualifiedName().equals(givenQualifiedName)) {
				declarationCount++;
			}
		}
		return true;
	}
	
	/**
	 * @overrides visit(SimpleType node)
	 * counts the number of references of the qualified name
	 */
	public boolean visit(SimpleType node) {
		//System.out.println("SimpleName " + node.getName().getFullyQualifiedName() + node.getStartPosition());
		if (node.resolveBinding() != null && (node.resolveBinding().getQualifiedName().equals(givenQualifiedName))) {
			referenceCount++;
			//System.out.println("SimpleTypeNode " + node.resolveBinding().getQualifiedName());
		}
		
		return true;
	}
	
	/*
	 * @overrides visit(PrimitiveType node)
	 * counts the number of references to a given primitive type
	 */
	public boolean visit(PrimitiveType node) {
		//System.out.println("SimpleName " + node.getName().getFullyQualifiedName() + node.getStartPosition());
		if (node.resolveBinding() != null && (node.resolveBinding().getQualifiedName().equals(givenQualifiedName))) {
			referenceCount++;
		}

		return true;
	}
	
	/**
	 * @return = int: declaration count of the given qualified name
	 */
	public int getDeclarationCount() {
		return declarationCount;
	}
	
	/**
	 * @return = int: reference count of the given qualified name
	 */
	public int getReferenceCount() {
		return referenceCount;
	}
}

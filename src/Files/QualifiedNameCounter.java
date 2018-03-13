package Files;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.*;

// counts the number of declaration and reference to a given qualified name
public class QualifiedNameCounter extends ASTVisitor{
	private String givenQualifiedName = null;
	private int declarationCount = 0;
	private int referenceCount = 0;
	
	/**
	 * 
	 * @param input = qualified name to search for
	 */
	public QualifiedNameCounter(String input) {
		givenQualifiedName = input;
	}
	
	/* IGNORE
	 * experimental method
	 * tell us which part of the code belongs to which binding 
	 */
	/*
	public boolean visit(SimpleName node) {
		//System.out.println(node.getStartPosition()); 
		//System.out.println("is declaration? " + node.isDeclaration());
		//System.out.println("SimpleName " + node.getIdentifier() + node.getStartPosition());
		//System.out.println("type of node " + node.getLocationInParent());
		//System.out.println("Identifier " + node.getLocationInParent());
		
		if (node.resolveBinding() != null) {
			if (node.resolveBinding().getKind() == IBinding.TYPE) {
				System.out.println("simpleName " + node.getIdentifier() + node.getStartPosition());
			
				ITypeBinding typeNode = (ITypeBinding) node.resolveBinding();
				System.out.println("Itype " + typeNode.getQualifiedName());
				if (typeNode.getQualifiedName().equals(declarationName)) {
					declarationCount++;
				}
				//System.out.println("SimpleName " + node.getFullyQualifiedName() + "Position " + node.getStartPosition());
				//System.out.println("qualified name " + typeNode.getQualifiedName());
			}
			
			if (node.resolveBinding().getKind() == IBinding.VARIABLE) {
				IVariableBinding varBind = (IVariableBinding) node.resolveBinding();
				System.out.println("simpleName " + node.getIdentifier() + node.getStartPosition());
				System.out.println("IVariableBinding " + varBind.getType().getQualifiedName());
			}
			if (node.resolveBinding().getKind() == IBinding.METHOD) {
				IMethodBinding varBind = (IMethodBinding) node.resolveBinding();
				System.out.println("simpleName " + node.getIdentifier() + node.getStartPosition());
				System.out.println("IMethodBinding " + varBind.getDeclaringClass().getQualifiedName());
			}
			
			if (node.resolveBinding().getKind() == IBinding.ANNOTATION) {
				IAnnotationBinding varBind = (IAnnotationBinding) node.resolveBinding();
				System.out.println("simpleName " + node.getIdentifier() + node.getStartPosition());
				System.out.println("IAnnotationBinding " + varBind.getAnnotationType().getQualifiedName());
			}
			//System.out.println("Binding " + node.resolveBinding().getAnnotations()); 
		}
		
		return true;
	}
	*/
	
	/*
	 * @overrides vist(TypeDeclaration node)
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
	
	/**
	 * 
	 * @return = declaration count of the given qualified name
	 */
	public int getDeclarationCount() {
		return declarationCount;
	}
	
	/**
	 * 
	 * @return = reference count of the given qualified name
	 */
	public int getReferenceCount() {
		return referenceCount;
	}
}

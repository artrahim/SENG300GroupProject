package Files;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.*;

// WORK IN PROGRESS
// experimental class that can sorta count declarations
public class DeclarationCounter extends ASTVisitor{
	private String declarationName = null;
	private int declarationCount = 0;
	
	public DeclarationCounter(String type) {
		declarationName = type;
	}
	
	/*
	public void preVisit(CompilationUnit node) {
		if (ASTNode.COMPILATION_UNIT != node.getNodeType()) {
			System.out.println("Root is not a compilation unit.");
		}
	}
	*/
	public boolean visit(SimpleName node) {
		//System.out.println(node.getStartPosition()); 
		//System.out.println("is declaration? " + node.isDeclaration());
		//System.out.println("SimpleName " + node.getIdentifier() + node.getStartPosition());
		//System.out.println("parentNode " + node.getLocationInParent());
		//System.out.println("Identifier " + node.getLocationInParent());
			
		if (node.resolveBinding() != null) {
			if (node.resolveBinding().getKind() == IBinding.TYPE) {
				ITypeBinding typeNode = (ITypeBinding) node.resolveBinding();
				if (typeNode.getQualifiedName().equals(declarationName)) {
					declarationCount++;
				}
				System.out.println("SimpleName " + node.getFullyQualifiedName() + "Position " + node.getStartPosition());
				System.out.println("qualified name " + typeNode.getQualifiedName());
			}
			//System.out.println("Binding " + node.resolveBinding().getAnnotations());
		}
		return true;
	}
	
	public int getCount() {
		return declarationCount;
	}
}

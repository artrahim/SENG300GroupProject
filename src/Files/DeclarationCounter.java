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
	
	public void preVisit(CompilationUnit node) {
		if (ASTNode.COMPILATION_UNIT != node.getNodeType()) {
			System.out.println("Root is not a compilation unit.");
		}
	}
	
	//counts declaration
	public boolean visit(SimpleType node) {
		System.out.println(node.getName().getFullyQualifiedName());
		System.out.println(node.getStartPosition());
		if (declarationName == node.getName().getFullyQualifiedName()) {
			declarationCount++;
		}
		return true;
	}
	
	// WORK IN PROGRESS
	public boolean visit(QualifiedName node) {
		System.out.println(node.getStartPosition());
		System.out.println(node.getQualifier().getFullyQualifiedName() + "-qualifiedName");
		return true;
	}
	
}

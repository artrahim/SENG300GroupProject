package Files;

import java.io.FileNotFoundException;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;

public class Driver {
	public static void main(String[] args) throws FileNotFoundException {
		DirectoryParser parser = new DirectoryParser(args[0]);
		CompilationUnit[] asts = parser.parseDirectory();
		
		//System.out.println(asts[0].toString());
		//System.out.println(asts[3].toString());
		QualifiedNameCounter counter = new QualifiedNameCounter(args[1]);
		//asts[0].accept(counter);
		//DeclarationCounter counter2 = new DeclarationCounter("java.lang.String");
		for (int i = 0; parse.getLength(); i++) { //ADD parse.getLenth() to DirectoryParser()
			asts[i].accept(counter);
		}
		//System.out.println(asts[0].toString());
		//System.out.println(asts[0].propertyDescriptors(AST.JLS8).get(2));
	//	System.out.println(asts[0].TYPES_PROPERTY);
		//System.out.println(asts[0].getNodeType() == ASTNode.COMPILATION_UNIT);
	//	System.out.println(asts[0].properties());
		//System.out.println("declaration count " + stringCounter.getDeclarationCount());
		//System.out.println("reference count " + stringCounter.getReferenceCount());
		//System.out.println(arg0);
	}
}

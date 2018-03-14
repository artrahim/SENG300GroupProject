package Files;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;

// calls the appropriate functions to run the program
public class Driver {
	public static void main(String[] args) throws FileNotFoundException {
		// initialize variables
		DirectoryParser parser;
		CompilationUnit[] asts;
		QualifiedNameCounter counter;
		
		// initialize the parser to the given directory
		if (Array.getLength(args) != 0) {
			parser = new DirectoryParser(args[0]);
			asts = parser.parseDirectory();
			// initialize the counter to the given name
			counter = new QualifiedNameCounter(args[1]);

			// visits the nodes for each ast
			for (int i = 0; i < Array.getLength(asts) ; i++) { //ADD parse.getLenth() to DirectoryParser()
				asts[i].accept(counter);
			}
			
			// prints the result
			System.out.println(args[1] + "." + " Declarations found: " + counter.getDeclarationCount() + ";" + " references found: " + counter.getReferenceCount());

		} else {
			System.out.println("Please input: java Driver <directory of java files> <java type to search> ");
		}
	}
}

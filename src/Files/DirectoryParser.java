package Files;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

public class DirectoryParser {
	private String pathName;
	private ASTParser parser;
	private Scanner reader;
	
	/**
	 * Creates a DirectoryParser object.
	 * @param dirPath the directory to parse
	 */
	public DirectoryParser(String dirPath) {
		this.pathName = dirPath;
		parser = ASTParser.newParser(AST.JLS8);
		parser.setResolveBindings(false);
	}
	
	/**
	 * Returns an array of ASTs corresponding to .java files in the directory.
	 * @return the complete array of ASTs, represented as CompilationUnit objects
	 * @throws FileNotFoundException if a source file cannot be found
	 */
	public CompilationUnit[] parseDirectory() throws FileNotFoundException {
		ArrayList<File> javaFiles = getJavaFiles();
	    
	    CompilationUnit[] astContainer = new CompilationUnit[javaFiles.size()];
	    
	    for (int i = 0; i < javaFiles.size(); i++) {
	    		astContainer[i] = parseFile(javaFiles.get(i));
	    }
	    
	    return astContainer;
	  }
	
	/**
	 * Returns a list of all files in the directory with .java extensions.
	 * @return the list of files, represented as File objects
	 */
	private ArrayList<File> getJavaFiles() {
		File directory = new File(pathName);
		ArrayList<File> allFiles = new ArrayList<File>(Arrays.asList(directory.listFiles()));
		ArrayList<File> javaFiles = new ArrayList<File>();
		for (File file : allFiles) {
			if(file.getName().contains(".java")) {
				javaFiles.add(file);
			}
		}
		
		return javaFiles;
	}
	
	/**
	 * Parses the code in a .java file and returns a corresponding AST.
	 * @param input the .java file to parse
	 * @return the created AST, represented as a CompilationUnit object
	 * @throws FileNotFoundException if the source file cannot be found
	 */
	private CompilationUnit parseFile(File input) throws FileNotFoundException {
		String source = "";
		reader = new Scanner(input);
    	
		while (reader.hasNextLine()) {
    			source += reader.nextLine();
		}
		reader.close();
    	
		parser.setSource(source.toCharArray());
		return (CompilationUnit) parser.createAST(null);
	}
}
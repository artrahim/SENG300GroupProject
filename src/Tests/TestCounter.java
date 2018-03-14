package Tests;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.junit.Test;

import Files.DirectoryParser;
import Files.QualifiedNameCounter;

public class TestCounter {
	private static String BASEDIR = "src/TestFiles";
	
	/**
	 * Test that the Counter counts the proper amount of declarations
	 * @throws FileNotFoundException 
	 */
	@Test
	public void testGetDeclarationCount() throws FileNotFoundException {
		DirectoryParser dirParser = new DirectoryParser(BASEDIR);
		CompilationUnit[] cus = dirParser.parseDirectory();
		QualifiedNameCounter counter = new QualifiedNameCounter("TestFiles.Multiply");
		cus[0].accept(counter);
		assertEquals(counter.getDeclarationCount(), 1);
	}
	
	/**
	 * Test that the Counter counts the proper amount of references
	 * @throws FileNotFoundException 
	 */
	@Test
	public void testGetPrimitiveReferenceCount() throws FileNotFoundException {
		DirectoryParser dirParser = new DirectoryParser(BASEDIR);
		CompilationUnit[] cus = dirParser.parseDirectory();
		QualifiedNameCounter counter = new QualifiedNameCounter("int");
		cus[1].accept(counter);
		assertEquals(counter.getReferenceCount(), 4);
	}
	
	/**
	 * Test that the Counter counts the proper amount of references
	 * @throws FileNotFoundException 
	 */
	@Test
	public void testGetNonPrimitiveReferenceCount() throws FileNotFoundException {
		DirectoryParser dirParser = new DirectoryParser(BASEDIR);
		CompilationUnit[] cus = dirParser.parseDirectory();
		QualifiedNameCounter counter = new QualifiedNameCounter("java.lang.String");
		cus[1].accept(counter);
		assertEquals(counter.getReferenceCount(), 1);
	}
}

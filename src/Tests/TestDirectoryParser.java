package Tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.junit.*;

import Files.DirectoryParser;

public class TestDirectoryParser {
	
	private static String BASEDIR = "src/TestFiles";
	
	/**
	 * Checks that a null source will throw a NullPointerException
	 * @throws FileNotFoundException 
	 */
	@Test(expected = NullPointerException.class)
	public void testCreateNullDirectoryParser() throws FileNotFoundException{
		DirectoryParser dirParser = new DirectoryParser(null);
		dirParser.parseDirectory();
	}
	
	/**
	 * Checks that a poor filepath will not be parsed and print a message to the user
	 * @throws FileNotFoundException 
	 */
	@Test
	public void testCreateNoneExistingDirectoryParser() throws FileNotFoundException {
		DirectoryParser dirParser = new DirectoryParser("foo");
		dirParser.parseDirectory();
	}
	
	/**
	 * Checks that a proper filepath with java files can be parsed
	 * @throws FileNotFoundException 
	 */
	@Test
	public void testCreateWorkingDirectoryParser() throws FileNotFoundException {
		DirectoryParser dirParser = new DirectoryParser(BASEDIR);
		CompilationUnit[] cus = dirParser.parseDirectory();
		assertNotNull(cus);
	}
	
	/**
	 * Checks that a proper filepath with java files can be parsed
	 * @throws FileNotFoundException 
	 */
	@Test
	public void testCompilationUnitGetTypes() throws FileNotFoundException {
		DirectoryParser dirParser = new DirectoryParser(BASEDIR);
		CompilationUnit[] cus = dirParser.parseDirectory();
		List type = cus[0].types();
		assertNotNull(type);
	}
	
	/**
	 * Check that the asts created are compilation units
	 * @throws FileNotFoundException 
	 */
	@Test
	public void testASTKindisCompilationUnit() throws FileNotFoundException {
		DirectoryParser dirParser = new DirectoryParser(BASEDIR);
		CompilationUnit[] cus = dirParser.parseDirectory();
		assertEquals(cus[0].getNodeType(), ASTNode.COMPILATION_UNIT);
	}
	
	/**
	 * Check that the parser correctly creates bindings
	 * @throws FileNotFoundException 
	 */
	@Test
	public void testASTHasBindings() throws FileNotFoundException {
		DirectoryParser dirParser = new DirectoryParser(BASEDIR);
		CompilationUnit[] cus = dirParser.parseDirectory();
		AST ast = cus[0].getAST();
		assertTrue(ast.hasResolvedBindings());
	}

}

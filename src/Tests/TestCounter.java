package Tests;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.junit.Test;

import Files.Counter;
import Files.DirectoryParser;

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
		Counter dc = new Counter("TestFiles.Multiply");
		cus[0].accept(dc);
		assertEquals(dc.getDeclarationCount(), 1);
	}
	
	/**
	 * Test that the Counter counts the proper amount of references
	 * @throws FileNotFoundException 
	 */
	@Test
	public void testGetReferenceCount() throws FileNotFoundException {
		DirectoryParser dirParser = new DirectoryParser(BASEDIR);
		CompilationUnit[] cus = dirParser.parseDirectory();
		Counter dc = new Counter("char");
		cus[1].accept(dc);
		assertEquals(dc.getReferenceCount(), 2);
	}
}

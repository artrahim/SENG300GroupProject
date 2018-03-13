package Tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.junit.*;

import Files.DirectoryParser;

public class TestDirectoryParser {
	
	private static String BASEDIR = "/Users/Amitiss/Desktop/A3SENG300_TestFiles";
	
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
	 * Checks that a poor filepath will not be parsed
	 * @throws FileNotFoundException 
	 */
	@Test(expected = NullPointerException.class)
	public void testCreateEmptyDirectoryParser() throws FileNotFoundException {
		DirectoryParser dirParser = new DirectoryParser("foo");
		dirParser.parseDirectory();
	}
	
	/**
	 * Checks that a proper filepath with java files can be parsed
	 * @throws FileNotFoundException 
	 */
	@Test
	public void testCreateWorkingDirectoryParser() {
		DirectoryParser dirParser = new DirectoryParser(BASEDIR);
		try {
			CompilationUnit[] cus = dirParser.parseDirectory();
			assertNotNull(cus);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Checks that a proper filepath with java files can be parsed
	 * @throws FileNotFoundException 
	 */
	@Test
	public void testCompilationUnitGetTypes() {
		DirectoryParser dirParser = new DirectoryParser(BASEDIR);
		try {
			CompilationUnit[] cus = dirParser.parseDirectory();
			List type = cus[0].types();
			assertNotNull(type);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}

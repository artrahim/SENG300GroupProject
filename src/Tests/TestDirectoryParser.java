package Tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import org.junit.*;

import Files.DirectoryParser;

public class TestDirectoryParser {

	/**
	 * Checks that a null source will throw a FileNotFoundException
	 */
	@Test(expected = FileNotFoundException.class)
	public void testCreateNullDirectoryParser() throws FileNotFoundException {
		DirectoryParser dirParser = new DirectoryParser(null);
		dirParser.parseDirectory();
	}
	
}

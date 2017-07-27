package editdict;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class EditDict {

	public static void main(String[] args) {
		
		String filename = "/Users/philhow/Documents/GitHub/languagelearn/vocab.xml";

		EditFrame editor = new EditFrame(filename);
	}

}

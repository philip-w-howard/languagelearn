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
		// TODO Auto-generated method stub
		try {

			File fXmlFile = new File("/Users/philhow/Documents/GitHub/languagelearn/vocab.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();

			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("word");

			System.out.println("----------------------------");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					System.out.print("Number : " + eElement.getAttribute("number"));
					System.out.print(" Gender : " + eElement.getAttribute("gender"));
					System.out.print(" part : " + eElement.getAttribute("part"));
					System.out.print(" category : " + eElement.getAttribute("category"));
					System.out.print(" English : " + eElement.getElementsByTagName("english").item(0).getTextContent());
					System.out.println(" French : " + eElement.getElementsByTagName("french").item(0).getTextContent());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

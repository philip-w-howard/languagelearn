import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class WordList extends Vector<Word>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6448537313629229034L;
	
	public WordList()
	{
	}
	
	public boolean containsEnglish(String english)
	{
		for (Word item : this)
		{
			if (item.english.equals(english)) return true;
			if (item.plural.equals(english)) return true;
		}
		return false;
	}

	public boolean containsFrench(String french)
	{
		for (Word item : this)
		{
			if (item.je.equals(french)) return true;
			if (item.tu.equals(french)) return true;
			if (item.il.equals(french)) return true;
			if (item.elle.equals(french)) return true;
			if (item.ils.equals(french)) return true;
			if (item.elles.equals(french)) return true;
			if (item.nous.equals(french)) return true;
			if (item.vous.equals(french)) return true;
		}
		return false;
	}
	
	public void load(String filename)
	{
		try {

			File fXmlFile = new File(filename);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("word");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					String plural = "";
					String je = "";
					String tu = "";
					String il = "";
					String elle = "";
					String ils = "";
					String elles = "";
					String nous = "";
					String vous = "";
					
					
					Element eElement = (Element) nNode;
					NodeList item;
					
					item = eElement.getElementsByTagName("plural");
					if (item != null && item.item(0) != null) plural = item.item(0).getTextContent();

					Word word = new Word(
							eElement.getElementsByTagName("english").item(0).getTextContent(),
							plural,
							Word.SpeechPart_t.valueOf(eElement.getAttribute("part")),
							eElement.getAttribute("category"),
							je,
							tu,
							il,
							elle,
							ils,
							elles,
							nous,
							vous
							);

					this.add(word);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void save(String filename)
	{
		try{
		    PrintWriter writer = new PrintWriter(filename, "UTF-8");
		    writer.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		    writer.println("<dictionary>");
		    writer.println(toString());
		    writer.println("</dictionary>");
		    writer.close();
		} catch (IOException e) {
		   // do something
		}
	}
	
	public String toString()
	{
		String result = new String("<wordlist>\n");
		for (Word item : this)
		{
			result += item;
		}
		
		result += "</wordlist>\n";
		
		return result;
	}
}

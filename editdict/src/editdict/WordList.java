package editdict;
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
	protected WordFormat m_format;
	
	public WordList(WordFormat format)
	{
		m_format = format;
	}
	
	public boolean containsEnglish(String english)
	{
		for (Word item : this)
		{
			if (item.english.equals(english)) return true;
		}
		return false;
	}

	public boolean containsFrench(String french)
	{
		for (Word item : this)
		{
			if (item.french.equals(french)) return true;
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

					Element eElement = (Element) nNode;
					Word word = new Word(
							eElement.getElementsByTagName("english").item(0).getTextContent(),
							eElement.getElementsByTagName("french").item(0).getTextContent(),
							eElement.getAttribute("category"),
							eElement.getAttribute("part"),
							eElement.getAttribute("gender"),
							eElement.getAttribute("number"),
							eElement.getAttribute("verbNumber"));
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
		    writer.println(m_format);
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
	
	public List<String> Genders() { return m_format.Genders(); }
	public List<String> Numbers() { return m_format.Numbers(); }
	public List<String> Parts() { return m_format.Parts(); }
	public List<String> VerbNumbers() { return m_format.VerbNumbers(); }

}

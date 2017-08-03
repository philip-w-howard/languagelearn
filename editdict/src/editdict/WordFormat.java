package editdict;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class WordFormat 
{
	public List<String> gender;
	public List<String> number;
	public List<String> part;
	public List<String> verbNumber;
	
	public WordFormat()
	{
		gender = new ArrayList<String>();
		number = new ArrayList<String>();
		part = new ArrayList<String>();
		verbNumber = new ArrayList<String>();
	}
	
	public void load(String filename) {
		try {
			File fXmlFile = new File(filename);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("meta");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
				NodeList metaList = nNode.getChildNodes();
				for (int temp2=0; temp2 < metaList.getLength(); temp2++)
				{
					Node metaNode = metaList.item(temp2);
					if (metaNode.getNodeType() == Node.ELEMENT_NODE)
					{
						NamedNodeMap attribs = metaNode.getAttributes();
						for (int attrib=0; attrib<attribs.getLength(); attrib++)
						{
							Node attr = attribs.item(attrib);
							
							if (metaNode.getNodeName().equals("gender"))
							{
								this.gender.add(attr.getTextContent());
							}
							else if (metaNode.getNodeName().equals("number"))
							{
								this.number.add(attr.getTextContent());
							}
							else if (metaNode.getNodeName().equals("part"))
							{
								this.part.add(attr.getTextContent());
							}
							else if (metaNode.getNodeName().equals("verbNumber"))
							{
								this.verbNumber.add(attr.getTextContent());
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String toString()
	{
		String result = "<meta>\n";
		
		for (String name: part)
		{
			result += "<part name=\"" + name + "\"/>\n";
		}
		
		for (String name: gender)
		{
			result += "<gender name=\"" + name + "\"/>\n";
		}
		
		for (String name: number)
		{
			result += "<number name=\"" + name + "\"/>\n";
		}
		
		for (String name: verbNumber)
		{
			result += "<verbNumber name=\"" + name + "\"/>\n";
		}
		
		result += "</meta>\n";
		
		return result;
	}
	
}

package editdict;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

public class EditMenu extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6406507374947809L;

	public EditMenu()
	{
		JMenu file = new JMenu("File");
		add(file);
		JMenuItem save = new JMenuItem("Save");
		file.add(save);
		
		JMenu extras = new JMenu("Extras");
		add(extras);
		JMenuItem meta = new JMenuItem("Meta");
		meta.addActionListener(new metaListener());
		extras.add(meta);
		
		
		
	}

	protected class metaListener implements ActionListener
	{

		private String filename = "/Users/philhow/Documents/GitHub/languagelearn/vocab.xml";
		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				WordFormat metaData = new WordFormat();
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
							//System.out.println("Node " + metaNode.getNodeName());
							//System.out.println(metaNode);

							NamedNodeMap attribs = metaNode.getAttributes();
							for (int attrib=0; attrib<attribs.getLength(); attrib++)
							{
								Node attr = attribs.item(attrib);
								System.out.println(metaNode.getNodeName() + ":" + 
								attr.getNodeName() + " " + attr.getTextContent());
								
								if (metaNode.getNodeName().equals("gender"))
								{
									metaData.gender.add(attr.getTextContent());
								}
								else if (metaNode.getNodeName().equals("number"))
								{
									metaData.number.add(attr.getTextContent());
								}
								else if (metaNode.getNodeName().equals("part"))
								{
									metaData.part.add(attr.getTextContent());
								}
								else if (metaNode.getNodeName().equals("verbNumber"))
								{
									metaData.verbNumber.add(attr.getTextContent());
								}
								
							}
							
							System.out.println(metaData);
							
							//Element eElement = (Element) metaNode;
							//System.out.println(eElement);

						}

					}

/*
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {

						Element eElement = (Element) nNode;
						Word word = new Word(
								eElement.getElementsByTagName("english").item(0).getTextContent(),
								eElement.getElementsByTagName("french").item(0).getTextContent(),
								eElement.getAttribute("category"),
								eElement.getAttribute("part"),
								eElement.getAttribute("gender"),
								eElement.getAttribute("number"));
						this.add(word);
					}
*/
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
}

public class Word {
	public Word(String english,
				String plural, 
				SpeechPart_t part, 
				String category,
				String je,
				String tu,
				String il,
				String elle,
				String ils,
				String elles,
				String nous,
				String vous)
	{
		this.part = part;
		this.category = category;
		this.english = english;
		this.plural = plural;
		this.je = je;
		this.tu = tu;
		this.il = il;
		this.elle = elle;
		this.ils = ils;
		this.elles = elles;
		this.nous = nous;
		this.vous = vous;
	}
	
	public Word()
	{
		this.english 	= "";
		this.plural 	= "";
		this.je 		= "";
		this.tu 		= "";
		this.il 		= "";
		this.elle		= "";
		this.ils 		= "";
		this.elles 		= "";
		this.nous 		= "";
		this.vous 		= "";
		this.category 	= "Unspecified";
		this.part		= SpeechPart_t.none;
	}
	
	public String toString()
	{
		String result = 
				"<word  part=\"" + part +"\" " +
				"category=\"" + category +"\">\n" +
				"<english>" + english + "</english>\n";
				
		//if (plural.length() > 0)
			result += "<plural>" + plural + "</plural>\n";
		//if (je.length() > 0)
			result += "<je>" + je + "</je>\n";
		//if (tu.length() > 0)
			result += "<tu>" + tu + "</tu>\n";
		//if (il.length() > 0)
			result += "<il>" + il + "</il>\n";
		//if (elle.length() > 0)
			result += "<elle>" + elle + "</elle>\n";
		//if (ils.length() > 0)
			result += "<ils>" + ils + "</ils>\n";
		//if (elles.length() > 0)
			result += "<elles>" + elles + "</elles>\n";
		//if (nous.length() > 0)
			result += "<nous>" + nous + "</nous>\n";
		//if (vous.length() > 0)
			result += "<vous>" + vous + "</vous>\n";
		
		result += "</word>\n";
		
		return result;
	}
	
	public enum SpeechPart_t {none, noun, verb, adjective, phrase };
	
	public SpeechPart_t part;
	public String category;
	public String english;
	public String plural;
	public String je;
	public String tu;
	public String il;
	public String elle;
	public String ils;
	public String elles;
	public String nous;
	public String vous;
}

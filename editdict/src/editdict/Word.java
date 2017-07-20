package editdict;

public class Word {
	public Word(String eng, String fr, String cat, String part, String gender, String number)
	{
		this.english = eng;
		this.french = fr;
		this.category = cat;
		this.part = SpeechPart.valueOf(part);
		this.gender = Gender_t.valueOf(gender);
		this.number = Number_t.valueOf(number);
	}
	public String toString()
	{
		return "<word number=\"" + number + "\" gender=\"" + gender + "\" part=\"" + part +"\" " +
	           "category=\"" + category +"\">\n" +
	           "<english>" + english + "</english>\n" +
	           "<french>" + french + "</french>\n" +
	           "</word>\n";
	}
	public enum Number_t { singular, plural };
	public enum Gender_t { masculine, feminine };
	public enum SpeechPart { noun, verb };
	
	public Number_t number;
	public Gender_t gender;
	public SpeechPart part;
	public String category;
	public String english;
	public String french;
}

package editdict;

public class Word {
	public Word(String eng, String fr, String cat, String part, String gender, 
			String number, String verbNumber)
	{
		this.english = eng;
		this.french = fr;
		this.category = cat;
		this.part = SpeechPart_t.valueOf(part);
		this.gender = Gender_t.valueOf(gender);
		this.number = Number_t.valueOf(number);
		this.verbNumber = VerbNumber_t.valueOf(verbNumber);
	}
	
	public Word()
	{
		this.english = "";
		this.french = "";
		this.category = "Unspecified";
		this.part = SpeechPart_t.none;
		this.gender = Gender_t.none;
		this.number = Number_t.none;
		this.verbNumber = VerbNumber_t.none;
	}
	
	public String toString()
	{
		return "<word number=\"" + number + "\" gender=\"" + gender + "\" part=\"" + part +"\" " +
	           "verbNumber=\"" + verbNumber + "\" " +
	           "category=\"" + category +"\">\n" +
	           "<english>" + english + "</english>\n" +
	           "<french>" + french + "</french>\n" +
	           "</word>\n";
	}
	public enum Number_t {none, singular, plural};
	public enum Gender_t {none, masculine, feminine };
	public enum SpeechPart_t {none, noun, verb };
	public enum VerbNumber_t {none, je, tu, il, elle, nous, vous, ils, elles};
	
	public Number_t number;
	public Gender_t gender;
	public SpeechPart_t part;
	public VerbNumber_t verbNumber;
	public String category;
	public String english;
	public String french;
}

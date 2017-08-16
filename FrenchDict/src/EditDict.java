
public class EditDict {

	public static void main(String[] args) {
		
		String filename = "/Users/philhow/Documents/GitHub/languagelearn/vocab2.xml";

		WordList dict = new WordList();
		
		dict.load(filename);
		
		System.out.println(dict);
		dict.save(filename);

	}

}

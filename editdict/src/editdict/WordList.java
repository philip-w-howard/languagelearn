package editdict;
import java.util.ArrayList;

public class WordList {
	public WordList()
	{
		list = new ArrayList<Word>();
	}
	
	public void add(Word word)
	{
		list.add(word);
	}
	
	public boolean containsEnglish(String english)
	{
		for (Word item : list)
		{
			if (item.english.equals(english)) return true;
		}
		return false;
	}

	public boolean containsFrench(String french)
	{
		for (Word item : list)
		{
			if (item.french.equals(french)) return true;
		}
		return false;
	}
	
	public Word get(int index)
	{
		return list.get(index);
	}
	
	public int size()
	{
		return list.size();
	}
	
	public String toString()
	{
		String result = new String();
		for (Word item : list)
		{
			result += item;
		}
		
		return result;
	}

	protected ArrayList<Word> list;
}

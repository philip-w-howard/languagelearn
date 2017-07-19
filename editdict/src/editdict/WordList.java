package editdict;
import java.util.ArrayList;
import java.util.Vector;

public class WordList extends Vector<Word>{
	public WordList()
	{
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
	
	public String toString()
	{
		String result = new String();
		for (Word item : this)
		{
			result += item;
		}
		
		return result;
	}
}

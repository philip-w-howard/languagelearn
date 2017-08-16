
import javax.swing.table.DefaultTableModel;

public class WordListTableModel extends DefaultTableModel{
	public WordListTableModel(WordList list)
	{
		//super(colNames,list.size());
		super(colNames,0);
		for (Word item : list)
		{
			addRow(item);
		}
	}
	//protected WordList m_wordList;
	protected Boolean m_modified = false;
	public Word.SpeechPart_t part;

	static protected String[] colNames = {"English", "Plural", "Part", "Je", "Tu", "Il", "Elle", "Ils", "Elles", "Nous", "Vous", "Category"};
	
	private static final long serialVersionUID = 7754700570472658260L;

	/*
	@Override
	public Object getValueAt(int row, int col) {
		Word item = get(row);
		switch (col)
		{
		case 0:
			return item.english;
		case 1:
			return item.plural;
		case 2:
			return item.part;
		case 3:
			return item.je;
		case 4:
			return item.tu;
		case 5:
			return item.il;
		case 6:
			return item.elle;
		case 7:
			return item.ils;
		case 8:
			return item.elles;
		case 9:
			return item.nous;
		case 10:
			return item.vous;
		case 11:
			return item.category;
		}
		return null;

	}
*/
	public void addRow(Word word)
	{
		Object[] rowData = {word.english, word.plural, word.part, word.je, word.tu, word.il, word.elle, word.ils, word.elles, word.nous, word.vous, word.category};
		addRow(rowData);
		m_modified = true;
	}
	
	@Override
	public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

	@Override
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        return true;
		/*
		if (col < 2) {
            return false;
        } else {
            return true;
        }
        */
    }
	
	@Override
    public void setValueAt(Object value, int row, int col) {
    	super.setValueAt(value, row, col);
/*
    	Word item = get(row);
		switch (col)
		{
		case 0:
			item.english = (String) value;
			break;
		case 1:
			item.plural = (String) value;
			break;
		case 2:
			item.part = (Word.SpeechPart_t)value;
			break;
		case 3:
			item.je = (String) value;
			break;
		case 4:
			item.tu = (String) value;
			break;
		case 5:
			item.il = (String) value;
			break;
		case 6:
			item.elle = (String) value;
			break;
		case 7:
			item.ils = (String) value;
			break;
		case 8:
			item.elles = (String) value;
			break;
		case 9:
			item.nous = (String) value;
			break;
		case 10:
			item.vous = (String) value;
			break;
		case 11:
			item.category = (String) value;
			break;
		}
	*/

		m_modified = true;
		fireTableCellUpdated(row, col);
    }
	
	public void save(String filename)
	{
		//m_wordList.save(filename);
	}
	
	public void set(int index, Word word)
	{
		if (index >= 0)	
		{
			setValueAt(word.english, index, 0);
			setValueAt(word.plural, index, 1);
			setValueAt(word.part, index, 2);
			setValueAt(word.je, index, 3);
			setValueAt(word.tu, index, 4);
			setValueAt(word.il, index, 5);
			setValueAt(word.elle, index, 6);
			setValueAt(word.ils, index, 7);
			setValueAt(word.elles, index, 8);
			setValueAt(word.nous, index, 9);
			setValueAt(word.vous, index, 10);
			setValueAt(word.category, index, 11);		
		}
	}
	
	
	public Word get(int index)
	{
		Word item = new Word();
		item.english 	= (String) getValueAt(index, 0);
		item.plural 	= (String) getValueAt(index, 1);
		item.part 		= (Word.SpeechPart_t)getValueAt(index, 2);
		item.je 		= (String) getValueAt(index, 3);
		item.tu 		= (String) getValueAt(index, 4);
		item.il 		= (String) getValueAt(index, 5);
		item.elle 		= (String) getValueAt(index, 6);
		item.ils 		= (String) getValueAt(index, 7);
		item.elles 		= (String) getValueAt(index, 8);
		item.nous 		= (String) getValueAt(index, 9);
		item.vous 		= (String) getValueAt(index, 10);
		item.category 	= (String) getValueAt(index, 11);

		return item;
	}
}

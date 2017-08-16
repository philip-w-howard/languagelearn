
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class WordListTableModel extends DefaultTableModel{
	public WordListTableModel(WordList list)
	{
		super(colNames,list.size());
		m_wordList = list;
	}
	protected WordList m_wordList;
	protected Boolean m_modified = false;
	public Word.SpeechPart_t part;

	static protected String[] colNames = {"English", "Plural", "Part", "Je", "Tu", "Il", "Elle", "Ils", "Elles", "Nous", "Vous", "Category"};
	
	private static final long serialVersionUID = 7754700570472658260L;

	@Override
	public Object getValueAt(int row, int col) {
		Word item = m_wordList.get(row);
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

	public void addRow(Word word)
	{
		Object[] rowData = {word.english, word.plural, word.part, word.je, word.tu, word.elle, word.ils, word.elles, word.nous, word.vous, word.category};
		addRow(rowData);
		m_wordList.add(word);
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
		Word item = m_wordList.get(row);
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

		m_modified = true;
		fireTableCellUpdated(row, col);
    }

	public void save(String filename)
	{
		m_wordList.save(filename);
	}
	
	public void set(int index, Word word)
	{
		if (index >= 0)	m_wordList.set(index,  word);
	}
	
	public Word get(int index)
	{
		return m_wordList.get(index);
	}
}

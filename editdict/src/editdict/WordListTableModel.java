package editdict;

import javax.swing.table.DefaultTableModel;

import editdict.Word.Gender_t;
import editdict.Word.Number_t;
import editdict.Word.SpeechPart;

public class WordListTableModel extends DefaultTableModel{
	public WordListTableModel(WordList list)
	{
		super(colNames,list.size());
		m_wordList = list;
	}
	protected WordList m_wordList;
	static protected String[] colNames = {"English", "French", "Number", "Gender", "Part", "Category"};
	private static final long serialVersionUID = 7754700570472658260L;

	@Override
	public Object getValueAt(int row, int col) {
		Word item = m_wordList.get(row);
		switch (col)
		{
		case 0:
			return item.english;
		case 1:
			return item.french;
		case 2:
			return item.number;
		case 3:
			return item.gender;
		case 4:
			return item.part;
		case 5:
			return item.category;
		}
		return null;

	}

	public void addRow(Word word)
	{
		Object[] rowData = {word.english, word.french, word.number, word.gender, word.part, word.category};
		addRow(rowData);
		System.out.println("Added a row");
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
			item.french = (String) value;
			break;
		case 2:
			item.number = (Number_t) value;
			break;
		case 3:
			item.gender = (Gender_t) value;
			break;
		case 4:
			item.part = (SpeechPart) value;
			break;
		case 5:
			item.category = (String) value;
			break;
		}

		fireTableCellUpdated(row, col);
    }

}

package editdict;

import javax.swing.table.AbstractTableModel;

public class WordListTableModel extends AbstractTableModel{
	public WordListTableModel(WordList list)
	{
		m_wordList = list;
	}
	protected WordList m_wordList;
	protected String[] colNames = {"English", "French", "Number", "Gender", "Part", "Category"};
	private static final long serialVersionUID = 7754700570472658260L;

	@Override
	public String getColumnName(int col)
	{
		return colNames[col];
	}
	@Override
	public int getColumnCount() {
		return 6;
	}
	@Override
	public int getRowCount() {
		return m_wordList.size();
	}
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


}

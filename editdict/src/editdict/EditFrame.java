package editdict;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

public class EditFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EditFrame(WordList list)
	{
		setSize(600,400);
		setTitle("Language Word Editor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		add(new EditPanel(list));
		setVisible(true);
	}
	
	protected class EditPanel extends JPanel
	{
		public EditPanel(WordList list)
		{
			GridBagLayout layout = new GridBagLayout();
			setLayout(layout);
			GridBagConstraints c = new GridBagConstraints();
			c.insets = new Insets(5,5,5,5);
			
			c.gridx = 0;
			c.gridy = 0;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0;
			add(new Label("English:"), c);
			
			c.gridx = 1;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridwidth = 3;
			c.weightx = 1;
			add(new JTextField(), c);
			
			c.gridx = 0;
			c.gridy = 1;
			c.fill = GridBagConstraints.NONE;
			c.gridwidth = 1;
			c.weightx = 0;
			add(new Label("French:"), c);
			
			c.gridx = 1;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridwidth = 3;
			c.weightx = 1;
			add(new JTextField(), c);	
			
			//***************************************
			c.gridx = 0;
			c.gridy = 2;
			c.fill = GridBagConstraints.NONE;
			c.gridwidth = 1;
			c.weightx = 0;
			c.anchor = GridBagConstraints.WEST;
			JRadioButton singular = new JRadioButton("singular");
			add(singular, c);
			
			c.gridx = 0;
			c.gridy = 3;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0;
			c.anchor = GridBagConstraints.WEST;
			JRadioButton plural = new JRadioButton("plural");
			add(plural, c);
			
			ButtonGroup numberGroup = new ButtonGroup();
			numberGroup.add(singular);
			numberGroup.add(plural);

			//***************************************
			c.gridx = 1;
			c.gridy = 2;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0;
			c.anchor = GridBagConstraints.WEST;
			JRadioButton masculine = new JRadioButton("masculine");
			add(masculine, c);
			
			c.gridx = 1;
			c.gridy = 3;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0;
			c.anchor = GridBagConstraints.WEST;
			JRadioButton feminine = new JRadioButton("feminine");
			add(feminine, c);
			
			ButtonGroup genderGroup = new ButtonGroup();
			genderGroup.add(masculine);
			genderGroup.add(feminine);

			//***************************************
			c.gridx = 2;
			c.gridy = 2;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0;
			c.anchor = GridBagConstraints.WEST;
			JRadioButton noun = new JRadioButton("noun");
			add(noun, c);
			
			c.gridx = 2;
			c.gridy = 3;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0;
			c.anchor = GridBagConstraints.WEST;
			JRadioButton verb = new JRadioButton("verb");
			add(verb, c);
			
			ButtonGroup partGroup = new ButtonGroup();
			genderGroup.add(noun);
			genderGroup.add(verb);

			//***************************************
			c.gridx = 3;
			c.gridy = 2;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0;
			c.anchor = GridBagConstraints.WEST;
			add(new Label("Category"), c);
			
			c.gridx = 3;
			c.gridy = 3;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0;
			c.anchor = GridBagConstraints.WEST;
			c.weightx = 1;
			add(new JTextField(), c);	
			
			//***************************************
			c.gridx = 0;
			c.gridy = 4;
			c.fill = GridBagConstraints.BOTH;
			c.weightx = 1;
			c.weighty = 1;
			c.gridwidth = 4;

			String[] names = {"English", "French", "Number", "Gender", "Part", "Category"};
			Vector<String> colNames = new Vector<String>();
			for (String item : names)
			{
				colNames.add(item);
			}
			JTable wordList = new JTable(new MyTableModel(list));
			wordList.setFillsViewportHeight(true);
			JScrollPane scrollPane = new JScrollPane(wordList);
			add(scrollPane, c);

		}
		private static final long serialVersionUID = 9071674198967674856L;
		
		protected class MyTableModel extends AbstractTableModel
		{
			public MyTableModel(WordList list)
			{
				m_wordList = list;
			}
			protected WordList m_wordList;
			
			private static final long serialVersionUID = 7754700570472658260L;
			public String getColumnName(int col)
			{
				return colNames[col];
			}
			protected String[] colNames = {"English", "French", "Number", "Gender", "Part", "Category"};
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
	}
}

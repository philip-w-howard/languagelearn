package editdict;

import java.awt.Graphics;
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
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
		private static final long serialVersionUID = 9071674198967674856L;
		protected Word m_word;
		protected WordList m_wordList;
		protected JTextField m_English;
		protected JTextField m_French;
		protected JTextField m_category;
		protected JRadioButton m_singular;
		protected JRadioButton m_plural;
		protected JRadioButton m_masculine;
		protected JRadioButton m_feminine;
		protected JRadioButton m_noun;
		protected JRadioButton m_verb;
		protected JTable m_wordTable;
		
		public EditPanel(WordList list)
		{
			m_wordList = list;
			m_word = m_wordList.get(0);
			
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
			m_English = new JTextField();
			add(m_English, c);

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
			m_French = new JTextField();
			add(m_French, c);	

			//***************************************
			c.gridx = 0;
			c.gridy = 2;
			c.fill = GridBagConstraints.NONE;
			c.gridwidth = 1;
			c.weightx = 0;
			c.anchor = GridBagConstraints.WEST;
			m_singular = new JRadioButton("singular");
			add(m_singular, c);

			c.gridx = 0;
			c.gridy = 3;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0;
			c.anchor = GridBagConstraints.WEST;
			m_plural = new JRadioButton("plural");
			add(m_plural, c);

			ButtonGroup numberGroup = new ButtonGroup();
			numberGroup.add(m_singular);
			numberGroup.add(m_plural);

			//***************************************
			c.gridx = 1;
			c.gridy = 2;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0;
			c.anchor = GridBagConstraints.WEST;
			m_masculine = new JRadioButton("masculine");
			add(m_masculine, c);

			c.gridx = 1;
			c.gridy = 3;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0;
			c.anchor = GridBagConstraints.WEST;
			m_feminine = new JRadioButton("feminine");
			add(m_feminine, c);

			ButtonGroup genderGroup = new ButtonGroup();
			genderGroup.add(m_masculine);
			genderGroup.add(m_feminine);

			//***************************************
			c.gridx = 2;
			c.gridy = 2;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0;
			c.anchor = GridBagConstraints.WEST;
			m_noun = new JRadioButton("noun");
			add(m_noun, c);

			c.gridx = 2;
			c.gridy = 3;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0;
			c.anchor = GridBagConstraints.WEST;
			m_verb = new JRadioButton("verb");
			add(m_verb, c);

			ButtonGroup partGroup = new ButtonGroup();
			partGroup.add(m_noun);
			partGroup.add(m_verb);

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
			m_category = new JTextField();
			add(m_category, c);	

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
			m_wordTable = new JTable(new WordListTableModel(list));
			m_wordTable.setFillsViewportHeight(true);
			ListSelectionModel listSelectionModel;
			listSelectionModel = m_wordTable.getSelectionModel();
			listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listSelectionModel.addListSelectionListener(new SharedListSelectionHandler());
			m_wordTable.setSelectionModel(listSelectionModel);
			
			m_wordTable.setRowSelectionInterval(0, 0);
			
			JScrollPane scrollPane = new JScrollPane(m_wordTable);
			add(scrollPane, c);

		}
		
		@Override
		protected void paintComponent(Graphics g)
		{
			m_English.setText(m_word.english);
			m_French.setText(m_word.french);
			m_category.setText(m_word.category);
			
			if (m_word.number == Word.Number_t.singular)
				m_singular.setSelected(true);
			else if (m_word.number == Word.Number_t.plural)
				m_plural.setSelected(true);
			
			if (m_word.gender == Word.Gender_t.masculine)
				m_masculine.setSelected(true);
			else if (m_word.gender == Word.Gender_t.feminine)
				m_feminine.setSelected(true);
			
			if (m_word.part == Word.SpeachPart_t.noun)
				m_noun.setSelected(true);
			else if (m_word.part == Word.SpeachPart_t.verb)
				m_verb.setSelected(true);

			super.paintComponent(g);
		}
		
		protected class SharedListSelectionHandler implements ListSelectionListener {
			public void valueChanged(ListSelectionEvent e) { 
				ListSelectionModel lsm = (ListSelectionModel)e.getSource();

				/*
				int firstIndex = e.getFirstIndex();
				int lastIndex = e.getLastIndex();
				boolean isAdjusting = e.getValueIsAdjusting(); 
				System.out.println("Event for indexes "
						+ firstIndex + " - " + lastIndex
						+ "; isAdjusting is " + isAdjusting
						+ "; selected indexes:");

				if (lsm.isSelectionEmpty()) {
					System.out.println(" <none>");
				} else {
					// Find out which indexes are selected.
					int minIndex = lsm.getMinSelectionIndex();
					int maxIndex = lsm.getMaxSelectionIndex();
					for (int i = minIndex; i <= maxIndex; i++) {
						if (lsm.isSelectedIndex(i)) {
							System.out.println(" " + i);
						}
					}
				}
				*/
				if (!e.getValueIsAdjusting())
				{
					int minIndex = lsm.getMinSelectionIndex();
					int maxIndex = lsm.getMaxSelectionIndex();
					for (int i = minIndex; i <= maxIndex; i++) {
						if (lsm.isSelectedIndex(i)) {
							System.out.println("selected: " + i);
							m_word = m_wordList.get(i);
							break;
						}
					}
					repaint();
					
				}
			}

		}
	}
}

package editdict;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;

public class EditFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected EditPanel m_editPanel;
	public EditFrame(String filename)
	{
		setSize(600,400);
		setTitle("Language Word Editor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		 

		m_editPanel = new EditPanel(filename);
		add(m_editPanel);
		
		setJMenuBar(new EditMenu());
		setVisible(true);
		addWindowListener(new closeListener(this));
	}

	protected class closeListener extends WindowAdapter
	{
		public closeListener(Component comp)
		{
			m_component = comp;
		}
		
		protected Component m_component;
		
		@Override
		public void windowClosing(WindowEvent arg0) {
			int opt = JOptionPane.showConfirmDialog(m_component, "Save file?", "Save file?", JOptionPane.YES_NO_OPTION);
			if (opt == JOptionPane.YES_OPTION)
			{
				m_editPanel.save();
			}
		}
	}
	
	protected class EditPanel extends JPanel implements TableModelListener
	{
		private static final long serialVersionUID = 9071674198967674856L;
		protected String m_filename;
		protected Word m_word;
		protected JTextField m_English;
		protected JTextField m_French;
		protected JTextField m_category;
		protected JRadioButton m_singular;
		protected JRadioButton m_plural;
		protected JRadioButton m_masculine;
		protected JRadioButton m_feminine;
		protected JRadioButton m_noun;
		protected JRadioButton m_verb;
		protected JRadioButton m_je;
		protected JRadioButton m_tu;
		protected JRadioButton m_il;
		protected JRadioButton m_elle;
		protected JRadioButton m_nous;
		protected JRadioButton m_vous;
		protected JRadioButton m_ils;
		protected JRadioButton m_elles;
		
		protected ButtonGroup numberGroup;
		protected ButtonGroup genderGroup;
		protected ButtonGroup partGroup;
		protected ButtonGroup verbNumberGroup;
		protected WordListTableModel m_model; 
		protected JTable m_wordTable;
		
		public EditPanel(String filename)
		{
			m_filename = filename;
			
			WordFormat wordFormat = new WordFormat();
			wordFormat.load(m_filename);
			
			WordList wordList = new WordList(wordFormat);
			wordList.load(m_filename);

			editListener my_editListener = new editListener();
			
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
			m_English.addActionListener(my_editListener);
			m_English.addFocusListener(my_editListener);
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
			m_French.addActionListener(my_editListener);
			m_French.addFocusListener(my_editListener);
			add(m_French, c);	

			//***************************************
			c.gridx = 0;
			c.gridy = 2;
			c.fill = GridBagConstraints.NONE;
			c.gridwidth = 1;
			c.weightx = 0;
			c.anchor = GridBagConstraints.WEST;
			m_singular = new JRadioButton("singular");
			m_singular.addActionListener(my_editListener);
			add(m_singular, c);

			c.gridx = 0;
			c.gridy = 3;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0;
			c.anchor = GridBagConstraints.WEST;
			m_plural = new JRadioButton("plural");
			m_plural.addActionListener(my_editListener);
			add(m_plural, c);

			numberGroup = new ButtonGroup();
			numberGroup.add(m_singular);
			numberGroup.add(m_plural);

			//***************************************
			c.gridx = 1;
			c.gridy = 2;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0;
			c.anchor = GridBagConstraints.WEST;
			m_masculine = new JRadioButton("masculine");
			m_masculine.addActionListener(my_editListener);
			add(m_masculine, c);

			c.gridx = 1;
			c.gridy = 3;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0;
			c.anchor = GridBagConstraints.WEST;
			m_feminine = new JRadioButton("feminine");
			m_feminine.addActionListener(my_editListener);
			add(m_feminine, c);

			genderGroup = new ButtonGroup();
			genderGroup.add(m_masculine);
			genderGroup.add(m_feminine);

			//***************************************
			c.gridx = 2;
			c.gridy = 2;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0;
			c.anchor = GridBagConstraints.WEST;
			m_noun = new JRadioButton("noun");
			m_noun.addActionListener(my_editListener);
			add(m_noun, c);

			c.gridx = 2;
			c.gridy = 3;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0;
			c.anchor = GridBagConstraints.WEST;
			m_verb = new JRadioButton("verb");
			m_verb.addActionListener(my_editListener);
			add(m_verb, c);

			partGroup = new ButtonGroup();
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
			m_category.addActionListener(my_editListener);
			m_category.addFocusListener(my_editListener);
			add(m_category, c);	

			//***************************************
			c.gridx = 0;
			c.gridy = 4;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0;
			c.anchor = GridBagConstraints.WEST;
			c.gridwidth = 4;
			JPanel verbPanel = new JPanel();
			add(verbPanel, c);
			
			m_je = new JRadioButton("je");
			m_je.addActionListener(my_editListener);
			verbPanel.add(m_je);

			m_tu = new JRadioButton("tu");
			m_tu.addActionListener(my_editListener);
			verbPanel.add(m_tu);

			m_il = new JRadioButton("il");
			m_il.addActionListener(my_editListener);
			verbPanel.add(m_il);

			m_elle = new JRadioButton("elle");
			m_elle.addActionListener(my_editListener);
			verbPanel.add(m_elle);

			m_nous = new JRadioButton("nous");
			m_nous.addActionListener(my_editListener);
			verbPanel.add(m_nous);

			m_vous = new JRadioButton("vous");
			m_vous.addActionListener(my_editListener);
			verbPanel.add(m_vous);

			m_ils = new JRadioButton("ils");
			m_ils.addActionListener(my_editListener);
			verbPanel.add(m_ils);

			m_elles = new JRadioButton("elles");
			m_elles.addActionListener(my_editListener);
			verbPanel.add(m_elles);

			verbNumberGroup = new ButtonGroup();
			verbNumberGroup.add(m_je);
			verbNumberGroup.add(m_tu);
			verbNumberGroup.add(m_il);
			verbNumberGroup.add(m_elle);
			verbNumberGroup.add(m_nous);
			verbNumberGroup.add(m_vous);
			verbNumberGroup.add(m_ils);
			verbNumberGroup.add(m_elles);

			//***************************************
			c.gridx = 0;
			c.gridy = 5;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0;
			c.weighty = 0;
			c.gridwidth = 1;
			JButton add = new JButton("Add Row");
			add.setMnemonic(java.awt.event.KeyEvent.VK_I);
			add.addActionListener(new addRowListener()); 
			add(add, c);
			
			//***************************************
			c.gridx = 2;
			c.gridy = 5;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0;
			c.weighty = 0;
			JButton savebtn = new JButton("Save");
			savebtn.addActionListener(new saveListener()); 
			add(savebtn, c);
			
			//** Data Table *************************************
			c.gridx = 0;
			c.gridy = 6;
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
			m_word = wordList.get(0);
			m_model = new WordListTableModel(wordList);
			m_wordTable = new JTable(m_model);
			m_wordTable.setFillsViewportHeight(true);
			m_wordTable.setAutoCreateRowSorter(true);
			m_wordTable.getModel().addTableModelListener(this);
			ListSelectionModel listSelectionModel;
			listSelectionModel = m_wordTable.getSelectionModel();
			listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listSelectionModel.addListSelectionListener(new SharedListSelectionHandler());
			m_wordTable.setSelectionModel(listSelectionModel);
			
			m_wordTable.setRowSelectionInterval(0, 0);
			
			JScrollPane scrollPane = new JScrollPane(m_wordTable);
			add(scrollPane, c);

			//***************************************
			// FIX THIS: this stuff should go in WordListTableModel thing
			TableColumn numberColumn = m_wordTable.getColumnModel().getColumn(2);

			JComboBox<Word.Number_t> numberBox = new JComboBox<Word.Number_t>();
			for (Word.Number_t number : Word.Number_t.values())
			{
				numberBox.addItem(number);
			}
			numberColumn.setCellEditor(new DefaultCellEditor(numberBox));

			TableColumn genderColumn = m_wordTable.getColumnModel().getColumn(3);

			JComboBox<Word.Gender_t> genderBox = new JComboBox<Word.Gender_t>();
			for (Word.Gender_t gender : Word.Gender_t.values())
			{
				genderBox.addItem(gender);
			}
			genderColumn.setCellEditor(new DefaultCellEditor(genderBox));
			
			TableColumn partColumn = m_wordTable.getColumnModel().getColumn(4);

			JComboBox<Word.SpeechPart_t> partBox = new JComboBox<Word.SpeechPart_t>();
			for (Word.SpeechPart_t part : Word.SpeechPart_t.values())
			{
				partBox.addItem(part);
			}
			partColumn.setCellEditor(new DefaultCellEditor(partBox));

			TableColumn verbNumberColumn = m_wordTable.getColumnModel().getColumn(5);

			JComboBox<Word.VerbNumber_t> verbNumberBox = new JComboBox<Word.VerbNumber_t>();
			for (Word.VerbNumber_t num : Word.VerbNumber_t.values())
			{
				verbNumberBox.addItem(num);
			}
			verbNumberColumn.setCellEditor(new DefaultCellEditor(verbNumberBox));
		}
		
		protected class addRowListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) {
				Word newWord = new Word();
				m_model.addRow(newWord);
				m_wordTable.setRowSelectionInterval(m_model.getRowCount()-1, m_model.getRowCount()-1);
				m_English.requestFocus();
				repaint();
			}	
		}
		
		public void save()
		{
			m_model.save(m_filename);;
		}
		
		protected class saveListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) {
				save();
			}	
		}
		
		protected class editListener implements ActionListener, FocusListener
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("ActionPerformed");
				update();
			}
			
			protected void update()
			{
				m_word.english = m_English.getText();
				m_word.french = m_French.getText();
				m_word.category = m_category.getText();
				
				if (m_singular.isSelected())
					m_word.number = Word.Number_t.singular;
				else if (m_plural.isSelected())
					m_word.number = Word.Number_t.plural;
				else
					m_word.number = Word.Number_t.none;
				
				if (m_masculine.isSelected())
					m_word.gender = Word.Gender_t.masculine;
				else if (m_feminine.isSelected())
					m_word.gender = Word.Gender_t.feminine;
				else
					m_word.gender = Word.Gender_t.none;
				
				if (m_noun.isSelected())
					m_word.part = Word.SpeechPart_t.noun;
				else if (m_verb.isSelected())
					m_word.part = Word.SpeechPart_t.verb;
				else
					m_word.part = Word.SpeechPart_t.none;
				
				if (m_je.isSelected())
					m_word.verbNumber = Word.VerbNumber_t.je;
				else if (m_tu.isSelected())
					m_word.verbNumber = Word.VerbNumber_t.tu;
				else if (m_il.isSelected())
					m_word.verbNumber = Word.VerbNumber_t.il;
				else if (m_elle.isSelected())
					m_word.verbNumber = Word.VerbNumber_t.elle;
				else if (m_nous.isSelected())
					m_word.verbNumber = Word.VerbNumber_t.nous;
				else if (m_vous.isSelected())
					m_word.verbNumber = Word.VerbNumber_t.vous;
				else if (m_ils.isSelected())
					m_word.verbNumber = Word.VerbNumber_t.ils;
				else if (m_elles.isSelected())
					m_word.verbNumber = Word.VerbNumber_t.elles;
				
				m_model.set(m_wordTable.getSelectedRow(), m_word);
				repaint();
				System.out.println("Updated item");
			}

			@Override
			public void focusGained(FocusEvent arg0) {
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				System.out.println("focusLost");
				update();
			}
		}
		@Override
		protected void paintComponent(Graphics g)
		{
			m_English.setText(m_word.english);
			m_French.setText(m_word.french);
			m_category.setText(m_word.category);
			
			numberGroup.clearSelection();
			genderGroup.clearSelection();
			partGroup.clearSelection();
			verbNumberGroup.clearSelection();
			
			if (m_word.number == Word.Number_t.singular)
				m_singular.setSelected(true);
			else if (m_word.number == Word.Number_t.plural)
				m_plural.setSelected(true);
			
			if (m_word.gender == Word.Gender_t.masculine)
				m_masculine.setSelected(true);
			else if (m_word.gender == Word.Gender_t.feminine)
				m_feminine.setSelected(true);
			
			if (m_word.part == Word.SpeechPart_t.noun)
				m_noun.setSelected(true);
			else if (m_word.part == Word.SpeechPart_t.verb)
				m_verb.setSelected(true);

			if (m_word.verbNumber == Word.VerbNumber_t.je)
				m_je.setSelected(true);
			else if (m_word.verbNumber == Word.VerbNumber_t.tu)
				m_tu.setSelected(true);
			else if (m_word.verbNumber == Word.VerbNumber_t.il)
				m_il.setSelected(true);
			else if (m_word.verbNumber == Word.VerbNumber_t.elle)
				m_elle.setSelected(true);
			else if (m_word.verbNumber == Word.VerbNumber_t.nous)
				m_nous.setSelected(true);
			else if (m_word.verbNumber == Word.VerbNumber_t.vous)
				m_vous.setSelected(true);
			else if (m_word.verbNumber == Word.VerbNumber_t.ils)
				m_ils.setSelected(true);
			else if (m_word.verbNumber == Word.VerbNumber_t.elles)
				m_elles.setSelected(true);

			super.paintComponent(g);
		}
		
		protected class SharedListSelectionHandler implements ListSelectionListener {
			public void valueChanged(ListSelectionEvent e) { 
				ListSelectionModel lsm = (ListSelectionModel)e.getSource();

				if (!e.getValueIsAdjusting())
				{
					int minIndex = lsm.getMinSelectionIndex();
					int maxIndex = lsm.getMaxSelectionIndex();
					for (int i = minIndex; i <= maxIndex; i++) {
						if (lsm.isSelectedIndex(i)) {
							m_word = m_model.get(i);
							break;
						}
					}
					repaint();
					
				}
			}

		}

		@Override
		public void tableChanged(TableModelEvent arg0) {
			repaint();		
		}
	}
}

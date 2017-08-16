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
		
		//setJMenuBar(new EditMenu());
		addWindowListener(new closeListener(this));
		setVisible(true);
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
		protected JTextField m_english;
		protected JTextField m_plural;
		protected JTextField m_je;
		protected JTextField m_tu;
		protected JTextField m_il;
		protected JTextField m_elle;
		protected JTextField m_ils;
		protected JTextField m_elles;
		protected JTextField m_nous;
		protected JTextField m_vous;
		protected JTextField m_category;
		protected JRadioButton m_noun;
		protected JRadioButton m_verb;
		protected JRadioButton m_adjective;
		
		protected ButtonGroup partGroup;
		protected WordListTableModel m_model; 
		protected JTable m_wordTable;
		
		public EditPanel(String filename)
		{
			m_filename = filename;
			
			WordList wordList = new WordList();
			wordList.load(m_filename);

			editListener my_editListener = new editListener();
			
			GridBagLayout layout = new GridBagLayout();
			setLayout(layout);
			GridBagConstraints c = new GridBagConstraints();
			c.insets = new Insets(5,5,5,5);

			//***************************************
			c.gridx = 0;
			c.gridy = 0;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0;
			c.anchor = GridBagConstraints.WEST;
			m_noun = new JRadioButton("noun");
			m_noun.addActionListener(my_editListener);
			add(m_noun, c);

			c.gridx = 1;
			c.gridy = 0;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0;
			c.anchor = GridBagConstraints.WEST;
			m_verb = new JRadioButton("verb");
			m_verb.addActionListener(my_editListener);
			add(m_verb, c);

			c.gridx = 2;
			c.gridy = 0;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0;
			c.anchor = GridBagConstraints.WEST;
			m_adjective = new JRadioButton("adjective");
			m_adjective.addActionListener(my_editListener);
			add(m_adjective, c);

			partGroup = new ButtonGroup();
			partGroup.add(m_noun);
			partGroup.add(m_verb);
			partGroup.add(m_adjective);

			//***************************************
			c.gridx = 0;
			c.gridy = 1;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0;
			add(new Label("English:"), c);

			c.gridx = 1;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridwidth = 3;
			c.weightx = 1;
			m_english = new JTextField();
			m_english.addActionListener(my_editListener);
			m_english.addFocusListener(my_editListener);
			add(m_english, c);

			//***************************************
			c.gridx = 0;
			c.gridy = 2;
			c.fill = GridBagConstraints.NONE;
			c.gridwidth = 1;
			c.weightx = 0;
			add(new Label("Plural:"), c);

			c.gridx = 1;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridwidth = 3;
			c.weightx = 1;
			m_plural = new JTextField();
			m_plural.addActionListener(my_editListener);
			m_plural.addFocusListener(my_editListener);
			add(m_plural, c);	

			//***************************************
			c.gridx = 0;
			c.gridy = 3;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0;
			c.anchor = GridBagConstraints.WEST;
			add(new Label("Category"), c);

			c.gridx = 1;
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
			add(new Label("Je"), c);

			c.gridx = 1;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0;
			c.anchor = GridBagConstraints.WEST;
			c.weightx = 1;
			m_je = new JTextField();
			m_je.addActionListener(my_editListener);
			m_je.addFocusListener(my_editListener);
			add(m_je, c);	

			//***************************************
			c.gridx = 0;
			c.gridy = 5;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0;
			c.anchor = GridBagConstraints.WEST;
			add(new Label("Tu"), c);

			c.gridx = 1;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0;
			c.anchor = GridBagConstraints.WEST;
			c.weightx = 1;
			m_tu = new JTextField();
			m_tu.addActionListener(my_editListener);
			m_tu.addFocusListener(my_editListener);
			add(m_tu, c);	

			//***************************************
			c.gridx = 0;
			c.gridy = 6;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0;
			c.anchor = GridBagConstraints.WEST;
			add(new Label("Il"), c);

			c.gridx = 1;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0;
			c.anchor = GridBagConstraints.WEST;
			c.weightx = 1;
			m_il = new JTextField();
			m_il.addActionListener(my_editListener);
			m_il.addFocusListener(my_editListener);
			add(m_il, c);	

			//***************************************
			c.gridx = 0;
			c.gridy = 7;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0;
			c.anchor = GridBagConstraints.WEST;
			add(new Label("Elle"), c);

			c.gridx = 1;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0;
			c.anchor = GridBagConstraints.WEST;
			c.weightx = 1;
			m_elle = new JTextField();
			m_elle.addActionListener(my_editListener);
			m_elle.addFocusListener(my_editListener);
			add(m_elle, c);	

			//***************************************
			c.gridx = 0;
			c.gridy = 8;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0;
			c.anchor = GridBagConstraints.WEST;
			add(new Label("Ils"), c);

			c.gridx = 1;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0;
			c.anchor = GridBagConstraints.WEST;
			c.weightx = 1;
			m_ils = new JTextField();
			m_ils.addActionListener(my_editListener);
			m_ils.addFocusListener(my_editListener);
			add(m_ils, c);	

			//***************************************
			c.gridx = 0;
			c.gridy = 9;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0;
			c.anchor = GridBagConstraints.WEST;
			add(new Label("Elles"), c);

			c.gridx = 1;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0;
			c.anchor = GridBagConstraints.WEST;
			c.weightx = 1;
			m_elles = new JTextField();
			m_elles.addActionListener(my_editListener);
			m_elles.addFocusListener(my_editListener);
			add(m_elles, c);	

			//***************************************
			c.gridx = 0;
			c.gridy = 10;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0;
			c.anchor = GridBagConstraints.WEST;
			add(new Label("Nous"), c);

			c.gridx = 1;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0;
			c.anchor = GridBagConstraints.WEST;
			c.weightx = 1;
			m_nous = new JTextField();
			m_nous.addActionListener(my_editListener);
			m_nous.addFocusListener(my_editListener);
			add(m_nous, c);	

			//***************************************
			c.gridx = 0;
			c.gridy = 11;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0;
			c.anchor = GridBagConstraints.WEST;
			add(new Label("Vous"), c);

			c.gridx = 1;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0;
			c.anchor = GridBagConstraints.WEST;
			c.weightx = 1;
			m_vous = new JTextField();
			m_vous.addActionListener(my_editListener);
			m_vous.addFocusListener(my_editListener);
			add(m_vous, c);	

			//***************************************
			//***************************************
			c.gridx = 0;
			c.gridy = 12;
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
			c.gridy = 12;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0;
			c.weighty = 0;
			JButton savebtn = new JButton("Save");
			savebtn.addActionListener(new saveListener()); 
			add(savebtn, c);
			
			//** Data Table *************************************
			c.gridx = 0;
			c.gridy = 13;
			c.fill = GridBagConstraints.BOTH;
			c.weightx = 1;
			c.weighty = 1;
			c.gridwidth = 4;

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
			TableColumn partColumn = m_wordTable.getColumnModel().getColumn(2);

			JComboBox<Word.SpeechPart_t> partBox = new JComboBox<Word.SpeechPart_t>();
			for (Word.SpeechPart_t part : Word.SpeechPart_t.values())
			{
				partBox.addItem(part);
			}
			partColumn.setCellEditor(new DefaultCellEditor(partBox));
		}
		
		protected class addRowListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) {
				Word newWord = new Word();
				m_model.addRow(newWord);
				m_wordTable.setRowSelectionInterval(m_model.getRowCount()-1, m_model.getRowCount()-1);
				m_english.requestFocus();
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
				m_word.english = m_english.getText();
				m_word.plural = m_plural.getText();
				m_word.category = m_category.getText();
				
				m_word.je = m_je.getText();
				m_word.tu = m_tu.getText();
				m_word.il = m_il.getText();
				m_word.elle = m_elle.getText();
				m_word.ils = m_ils.getText();
				m_word.elles = m_elles.getText();
				m_word.nous = m_nous.getText();
				m_word.vous = m_vous.getText();
							
				if (m_noun.isSelected())
					m_word.part = Word.SpeechPart_t.noun;
				else if (m_verb.isSelected())
					m_word.part = Word.SpeechPart_t.verb;
				else if (m_adjective.isSelected())
					m_word.part = Word.SpeechPart_t.adjective;
				else
					m_word.part = Word.SpeechPart_t.none;
				
				
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
			m_english.setText(m_word.english);
			m_plural.setText(m_word.plural);
			m_category.setText(m_word.category);
			
			m_je.setText(m_word.je);
			m_tu.setText(m_word.tu);
			m_il.setText(m_word.il);
			m_elle.setText(m_word.elle);
			m_ils.setText(m_word.ils);
			m_elles.setText(m_word.elles);
			m_nous.setText(m_word.nous);
			m_vous.setText(m_word.vous);

			partGroup.clearSelection();
			
			if (m_word.part == Word.SpeechPart_t.noun)
				m_noun.setSelected(true);
			else if (m_word.part == Word.SpeechPart_t.verb)
				m_verb.setSelected(true);
			else if (m_word.part == Word.SpeechPart_t.adjective)
				m_adjective.setSelected(true);

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

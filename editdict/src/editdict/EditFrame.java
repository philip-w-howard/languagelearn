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
			JTable wordList = new JTable(new WordListTableModel(list));
			wordList.setFillsViewportHeight(true);
			ListSelectionModel listSelectionModel;
			listSelectionModel = wordList.getSelectionModel();
			listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listSelectionModel.addListSelectionListener(new SharedListSelectionHandler());
			wordList.setSelectionModel(listSelectionModel);

			JScrollPane scrollPane = new JScrollPane(wordList);
			add(scrollPane, c);

		}
		private static final long serialVersionUID = 9071674198967674856L;

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
						}
					}
					
				}
			}

		}
	}
}

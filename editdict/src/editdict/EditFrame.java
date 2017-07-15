package editdict;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class EditFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EditFrame()
	{
		setSize(600,400);
		setTitle("Language Word Editor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		add(new EditPanel());
		setVisible(true);
	}
	
	protected class EditPanel extends JPanel
	{
		public EditPanel()
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
		}
		private static final long serialVersionUID = 9071674198967674856L;
		
	}
}

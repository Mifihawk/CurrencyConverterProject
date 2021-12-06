package currencyConverter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.EventQueue;

public class CurrencyConverterApp {

	private JFrame frame;
	private JTextField textFieldCurrency_1;
	private JTextField textFieldCurrency_2;
	private JLabel lblCurrencyPrefix_1;
	private JLabel lblCurrencyPrefix_2;
	private JLabel lblError;
	private JComboBox<String> comboBoxCurrency_1;
	private JComboBox<String> comboBoxCurrency_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CurrencyConverterApp window = new CurrencyConverterApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CurrencyConverterApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//creates the main window frame
		frame = new JFrame();
		BorderLayout borderLayout = (BorderLayout) frame.getContentPane().getLayout();
		borderLayout.setVgap(10);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//creates and adds the main panel to the center
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(5, 2, 10, 25));
		
		//creates and adds labels at the top of the main panel
		JLabel lblCurrencyLabel_1 = new JLabel("Currency 1");
		lblCurrencyLabel_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCurrencyLabel_1.setFont(new Font("Arial", Font.PLAIN, 13));
		lblCurrencyLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblCurrencyLabel_1);
		
		JLabel lblCurrencyLabel_2 = new JLabel("Currency 2");
		lblCurrencyLabel_2.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCurrencyLabel_2.setFont(new Font("Arial", Font.PLAIN, 13));
		lblCurrencyLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblCurrencyLabel_2);
		
		//adds the drop down menus to the main panel
		comboBoxCurrency_1 = new JComboBox<String>();
		comboBoxCurrency_1 = populateCurrencies(comboBoxCurrency_1); 
		comboBoxCurrency_1.addActionListener(comboBoxCurrency_1ActionListener);
		panel.add(comboBoxCurrency_1);
		
		comboBoxCurrency_2 = new JComboBox<String>();
		comboBoxCurrency_2 = populateCurrencies(comboBoxCurrency_2);
		comboBoxCurrency_2.addActionListener(comboBoxCurrency_2ActionListener);
		panel.add(comboBoxCurrency_2);
		
		//creates a new panel to hold the currency unit and the user input field
		JPanel panelCurrency_1 = new JPanel();
		panel.add(panelCurrency_1);
		panelCurrency_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		//adds the currency unit to the panel
		lblCurrencyPrefix_1 = new JLabel(String.format(" %-2s", Currencies.values()[0].CURRENCYUNIT));
		panelCurrency_1.add(lblCurrencyPrefix_1);
		lblCurrencyPrefix_1.setToolTipText("");
		lblCurrencyPrefix_1.setFont(new Font("Arial", Font.PLAIN, 12));
		
		//adds the text input field to the panel
		textFieldCurrency_1 = new JTextField();
		panelCurrency_1.add(textFieldCurrency_1);
		textFieldCurrency_1.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldCurrency_1.setColumns(10);
		
		//creates another new panel to hold the currency unit and the output
		JPanel panelCurrency_2 = new JPanel();
		panel.add(panelCurrency_2);
		panelCurrency_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		//adds the currency unit to the panel
		lblCurrencyPrefix_2 = new JLabel(String.format(" %-2s", Currencies.values()[0].CURRENCYUNIT));
		panelCurrency_2.add(lblCurrencyPrefix_2);
		lblCurrencyPrefix_2.setFont(new Font("Arial", Font.PLAIN, 12));
		
		//adds the output text field to the panel
		textFieldCurrency_2 = new JTextField();
		panelCurrency_2.add(textFieldCurrency_2);
		textFieldCurrency_2.setEditable(false);
		textFieldCurrency_2.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldCurrency_2.setColumns(10);
		
		//creates and adds the button used to convert the currencies to the main panel 
		JButton btnConvert = new JButton("Convert");
		btnConvert.setFont(new Font("Arial", Font.PLAIN, 12));
		btnConvert.addActionListener(btnConvertActionListener);
		panel.add(btnConvert);
		
		//creates and adds the button used to swap the currencies to the main panel 
		JButton btnSwapCurrencies = new JButton("Swap currencies");
		btnSwapCurrencies.setFont(new Font("Arial", Font.PLAIN, 12));
		btnSwapCurrencies.addActionListener(btnSwapCurrenciesActionListener);
		panel.add(btnSwapCurrencies);
		
		//adds a blank label to be used if an error occurs when converting the user input to a double
		lblError = new JLabel("");
		lblError.setFont(new Font("Arial", Font.PLAIN, 12));
		panel.add(lblError);
		
		//creates and adds a header above the main panel
		JLabel lblCurrencyConverterLabel = new JLabel("Currency Converter");
		frame.getContentPane().add(lblCurrencyConverterLabel, BorderLayout.NORTH);
		lblCurrencyConverterLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		lblCurrencyConverterLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		//creates and adds 2 panels to the sides to be used as padding to make things look a bit better
		JPanel panelPadding_W = new JPanel();
		frame.getContentPane().add(panelPadding_W, BorderLayout.WEST);		
		JPanel panelPadding_E = new JPanel();
		frame.getContentPane().add(panelPadding_E, BorderLayout.EAST);
	}
	
	//listens for when the drop down menu selection is changed and changes the currency unit to the correct one
	ActionListener comboBoxCurrency_1ActionListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			lblCurrencyPrefix_1.setText(String.format(" %-2s", Currencies.values()[comboBoxCurrency_1.getSelectedIndex()].CURRENCYUNIT));
		}
	};
	
	//listens for when the drop down menu selection is changed and changes the currency unit to the correct one
	ActionListener comboBoxCurrency_2ActionListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			lblCurrencyPrefix_2.setText(String.format(" %-2s", Currencies.values()[comboBoxCurrency_2.getSelectedIndex()].CURRENCYUNIT));	
		}
	};
	
	//listens for when the convert button is pressed and calls the convert method with the number the user entered
	ActionListener btnConvertActionListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				CurrencyConverter converter = new CurrencyConverter();
				double amount = converter.convert(Currencies.values()[comboBoxCurrency_1.getSelectedIndex()], Currencies.values()[comboBoxCurrency_2.getSelectedIndex()], textFieldCurrency_1.getText());
				textFieldCurrency_2.setText(Double.toString(amount));
			}catch(NumberFormatException exc) {
				System.out.println(exc);
				lblError.setText("Error: Not a number.");
			}
		}
	};
	
	//listens for when the swap currencies button is pressed and swaps the drop down selection
	ActionListener btnSwapCurrenciesActionListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			int temp = comboBoxCurrency_1.getSelectedIndex();
			comboBoxCurrency_1.setSelectedIndex(comboBoxCurrency_2.getSelectedIndex());
			comboBoxCurrency_2.setSelectedIndex(temp);
		}
	};
	
	//returns a JComboBox that has all of the menu options populated
	private JComboBox<String> populateCurrencies(JComboBox<String> box) {
		for(Currencies currency : Currencies.values()){ //a for each loop to get all of the enum values and add them to the drop down menu
			box.addItem(currency.NAME);
		}
		return box;
	}
}

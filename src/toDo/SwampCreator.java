//Incomplete JFrame Welcome Panel

package toDo;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SwampCreator extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private int sizeInt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SwampCreator frame = new SwampCreator();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SwampCreator() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblChooseTheSize = new JLabel("Choose the size of the Swamp");
		lblChooseTheSize.setBounds(0, 43, 424, 41);
		lblChooseTheSize.setFont(new Font("Viner Hand ITC", Font.BOLD, 25));
		lblChooseTheSize.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblChooseTheSize);
		
		textField = new JTextField();
		textField.setBounds(91, 95, 205, 49);
		textField.setFont(new Font("Viner Hand ITC", Font.PLAIN, 30));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(textField);
		textField.setColumns(3);
		
		JButton btnNewButton = new JButton("Create Swamp");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String size = textField.getText();
				int sizeInt;
				try { 
					sizeInt = Integer.parseInt(size);
					if (sizeInt > 50){
						JOptionPane.showMessageDialog(contentPane, "Only number smaller than 50");
					} else {
					}
						} catch(NumberFormatException e){
							JOptionPane.showMessageDialog(contentPane, "Numbers only please");
							//default value							
							//System.out.println(e.toString());
							//e.printStackTrace();
					} 
				}
			
		});
		btnNewButton.setBounds(91, 173, 205, 35);
		btnNewButton.setFont(new Font("Verdana", Font.BOLD, 20));
		contentPane.add(btnNewButton);
	}
	
	public int getChosenSize(){
		return this.sizeInt;
	}

}

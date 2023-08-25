package game;

import swamp.Swamp;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class SwampGame extends JFrame implements Observer {

	// All the components needed for the GUI
	private JSplitPane splitPane;
	private JPanel swampPanel;
	private JPanel controlsPanel;
	private JButton btnNewRound;
	private JButton btnGrumpy;
	private JButton btnPassive;
	private JButton btnStart;

	// Changing this variable changes the size of the Swamp built
	private static int SIZE;

	public Swamp theSwamp = Swamp.getSwamp(SIZE);
	public MessageHandler messageHandler = MessageHandler.getMessageHandler();

	// This allow to hold a non-specified number of JLabel objects, the TreeMap
	// use in the data structure allows to add and update the labels in the
	// correct order
	private GuiGridBuilder guiBuilder = new GuiGridBuilder();
	private TreeMap<Integer, TreeMap<Integer, JLabel>> theGrid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String sizeS;
					boolean valueOk = false;
					do {
						sizeS = JOptionPane
								.showInputDialog("Write a number between 4 and 50");
						try {
							SIZE = Integer.parseInt(sizeS);
							if (SIZE > 3 && SIZE < 50) {
								valueOk = true;
							} else {
								JOptionPane
										.showMessageDialog(null,
												"The number has to be between 4 and 50");
							}
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null,
									"Insert a number!");
						}
					} while (!valueOk);

					SwampGame frame = new SwampGame();
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
	public SwampGame() {

		// Add the GUI to the message handler, the GUI will display messages
		// when updated
		messageHandler.addGame(this);

		setTitle("Get Out of My Swamp!");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(640, 480));
		splitPane = new JSplitPane();
		getContentPane().setLayout(new GridLayout());
		getContentPane().add(splitPane);

		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setBorder(new EmptyBorder(2, 2, 2, 2));
		splitPane.setDividerLocation(400);

		splitPane.setTopComponent(getSwampPanel());

		splitPane.setBottomComponent(getControlsPanel());

		// This function is needed to make the GUI appear as it should, with all
		// the component visible
		// Without this, only the tab appears, and the user needs to resize to
		// the correct size
		pack();

	}

	private JPanel getSwampPanel() {
		if (swampPanel == null) {
			swampPanel = new JPanel();
			// Using the variable SIZE allows to change the size of the GUI
			// grid, this will be populated with the labels
			swampPanel.setLayout(new GridLayout(SIZE, SIZE, 0, 0));
			theGrid = guiBuilder.buildGridGUI(SIZE);
			guiBuilder.populateGrid(SIZE, swampPanel, theGrid);

		}
		return swampPanel;
	}

	private JPanel getControlsPanel() {
		if (controlsPanel == null) {
			controlsPanel = new JPanel();
			controlsPanel.setLayout(null);
			controlsPanel.add(getBtnNewRound());
			controlsPanel.add(getBtnStart());
			controlsPanel.add(getBtnGrumpy());

			controlsPanel.add(getBtnPassive());
		}
		return controlsPanel;

	}

	private JButton getBtnStart() {
		if (btnStart == null) {
			btnStart = new JButton("Re/Start Game");
			btnStart.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					theSwamp.startGame();
					updateGrid();
				}
			});
			btnStart.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
			btnStart.setBounds(0, 0, 169, 34);
		}
		return btnStart;
	}

	private JButton getBtnNewRound() {
		if (btnNewRound == null) {
			btnNewRound = new JButton("Move");
			btnNewRound.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						theSwamp.newRound();
						updateGrid();
						// Pressing this button before starting the game
						// will make the program look for a Swamp that is not
						// being instantiated yet, we avoid this with exception
						// handling
						// and let the user know it has to start the game first
					} catch (NullPointerException e) {
						JOptionPane.showMessageDialog(swampPanel,
								"Press the Start button before making a move",
								"Game not started yet...", 2);
					}

				}
			});
			btnNewRound.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
			btnNewRound.setBounds(472, 0, 148, 34);
		}
		return btnNewRound;
	}

	private JButton getBtnGrumpy() {
		if (btnGrumpy == null) {
			btnGrumpy = new JButton("Grumpy");
			btnGrumpy.setFont(new Font("Viner Hand ITC", Font.BOLD, 18));
			btnGrumpy.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					// This and the next button use methods to change the fight
					// behaviour of Hek while the program is running
					theSwamp.Hek.Grumpy();
				}
			});
			btnGrumpy.setBounds(189, 0, 120, 34);
		}
		return btnGrumpy;
	}

	private JButton getBtnPassive() {
		if (btnPassive == null) {
			btnPassive = new JButton("Passive");
			btnPassive.setFont(new Font("Viner Hand ITC", Font.PLAIN, 15));
			btnPassive.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					theSwamp.Hek.Passive();
				}
			});
			btnPassive.setBounds(308, 0, 96, 34);
		}
		return btnPassive;
	}

	// This is where using the same data structure in the game logic and the GUI
	// paid off
	// We update the label one by one checking the corresponding square in the
	// game grid
	// And printing the names of the character presents in the GUI grid

	private void updateLabel(int row, int column) {
		ArrayList<players.GameCharacter> tempList = theSwamp.Grid.get(row).get(
				column).whoIsHere;

		if (tempList.size() == 0) {
			theGrid.get(row).get(column).setText(row + "-" + column);
		} else {
			String newLabel = "";
			for (players.GameCharacter c : tempList) {
				newLabel = newLabel + c.getName() + " ";
			}
			theGrid.get(row).get(column).setText(newLabel);
		}
		;
	}

	private void updateGrid() {
		for (int row = 1; row < theSwamp.getSize() + 1; row++) {
			for (int column = 1; column < theSwamp.getSize() + 1; column++) {
				updateLabel(row, column);
			}
		}
		;
	}

	public void display(String message, String title, int typeMessage) {
		JOptionPane.showMessageDialog(swampPanel, message, title, typeMessage);

	}
}

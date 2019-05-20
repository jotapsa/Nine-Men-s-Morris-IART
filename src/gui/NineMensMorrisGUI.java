package gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class NineMensMorrisGUI extends JFrame {

	private JPanel contentPane;
	private JButton newGameButton;
	private JButton aboutButton;
	private JComboBox gameModeSelector;
	private GameBoard board;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NineMensMorrisGUI frame = new NineMensMorrisGUI();
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
	public NineMensMorrisGUI() {
		super("Nine Men's Morris");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(555, 240, 720, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		createNewGameButton();
		
		createAboutButton();
		
		createGameModeSelector();
		
		createGameBoard();
	}
	
	private void createGameBoard() {
		board = new GameBoard(this);
		board.setBounds(12, 60, 680, 600);
//		board.setBackground(new Color(128, 0, 128));
		contentPane.add(board);
	}

	public void createGameModeSelector() {
		JLabel lblGameMode = new JLabel("Game Mode");
		lblGameMode.setBounds(220, 10, 76, 26);
		contentPane.add(lblGameMode);
		
		String[] options = {"Select...", "Player vs Player", "Player vs PC", "PC vs PC"};
		gameModeSelector = new JComboBox(options);
		gameModeSelector.setBounds(295, 10, 130, 25);
		contentPane.add(gameModeSelector);
	}
	
	public void createNewGameButton() {
		newGameButton = new JButton("Start new game");
		newGameButton.setSize(130, 26);
		newGameButton.setLocation(440, 10);
		newGameButton.setPreferredSize(new Dimension(116, 40));
		contentPane.add(newGameButton);
	}
	
	public void createAboutButton() {
		aboutButton = new JButton("About");
		aboutButton.setSize(100, 26);
		aboutButton.setLocation(585, 10);
		aboutButton.setPreferredSize(new Dimension(116, 40));
		aboutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//JFrame principal
				JFrame p = (JFrame)aboutButton.getParent().getParent().getParent().getParent();
				
				JDialog d = new JDialog(p, "About");
				
				d.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
				
				d.getContentPane().add(new JLabel("Bernardo Manuel Costa Barbosa - up201503477")); 
				  
				d.getContentPane().add(new JLabel("Duarte Nuno Andre Carvalho - up201503661"));
				
				d.getContentPane().add(new JLabel("Joao Pedro Teixeira Pereira de Sa - up201506252"));
				
				d.getContentPane().add(new JLabel("Artificial Intelligence"));
				
				d.getContentPane().add(new JLabel("Master in Informatics and Computing Engineering"));
				
				d.getContentPane().add(new JLabel("Faculty of Engineering  --- University of Porto"));
				
				d.setLocation(p.getLocation().x + 200, p.getLocation().y + 200);
				
	            d.setSize(400, 270); 
	  
	            d.setVisible(true); 
			}
		});
		contentPane.add(aboutButton);
		
	}
}

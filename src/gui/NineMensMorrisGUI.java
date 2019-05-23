package gui;

import java.awt.Color;
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

import logic.BoardEval;
import logic.Computer;
import logic.GameState;
import logic.Player;
import utilities.Global;

public class NineMensMorrisGUI extends JFrame {

	private JPanel contentPane;
	private JButton newGameButton;
	private JButton hintButton;
	private JButton aboutButton;
	private JComboBox gameModeSelector;
	private GameBoard board;
	private GameState state;
	private JLabel lblHint;
	

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
		setBounds(555, 240, Global.WIDTH, Global.HEIGHT);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		createNewGameButton();
		
		createHintButton();
		
		createAboutButton();
		
		createGameModeSelector();
		
		createGameBoard();
	}
	
	private void createGameBoard() {
		board = new GameBoard(this);
		board.setBounds(Global.BOARD_BORDER_X, 50, Global.BOARD_WIDTH, Global.BOARD_HEIGHT);
		board.setBackground(Global.BACKGROUND_COLOR);
		contentPane.add(board);
	}

	public void createGameModeSelector() {
		JLabel lblGameMode = new JLabel("Game Mode");
		lblGameMode.setBounds(Global.LABEL_START_X, Global.BUTTONS_Y, Global.LABEL_WIDTH, Global.BUTTONS_HEIGHT);
		contentPane.add(lblGameMode);
		
		gameModeSelector = new JComboBox<Object>(Global.GAME_MODES);
		gameModeSelector.setBounds(Global.PICKER_START_X, Global.BUTTONS_Y, Global.PICKER_WIDTH, 25);
		contentPane.add(gameModeSelector);
	}
	
	public void createNewGameButton() {
		newGameButton = new JButton("Start new game");
		newGameButton.setSize(Global.START_BUTTON_WIDTH, Global.BUTTONS_HEIGHT);
		newGameButton.setLocation(Global.START_BUTTON_X, Global.BUTTONS_Y);
		newGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startNewGame();
			}
		});
		contentPane.add(newGameButton);
	}
	
	public void createHintButton() {
		hintButton = new JButton("HINT");
		hintButton.setSize(Global.HINT_BUTTON_WIDTH, Global.BUTTONS_HEIGHT);
		hintButton.setLocation(Global.HINT_BUTTON_X, Global.BUTTONS_Y);
		
		lblHint = new JLabel("");
		lblHint.setSize(Global.HINT_LABEL_WIDTH, Global.BUTTONS_HEIGHT);
		lblHint.setLocation(Global.HINT_LABEL_X, Global.BUTTONS_Y);
		
		contentPane.add(hintButton);
		contentPane.add(lblHint);
	}
	
	public void createAboutButton() {
		aboutButton = new JButton("About");
		aboutButton.setSize(Global.ABOUT_BUTTON_WIDTH, Global.BUTTONS_HEIGHT);
		aboutButton.setLocation(Global.ABOUT_BUTTON_X, Global.BUTTONS_Y);
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
				
				d.setLocation(p.getLocation().x + Global.WIDTH/4, p.getLocation().y + Global.HEIGHT/4);
				
	            d.setSize(400, 270); 
	  
	            d.setVisible(true); 
			}
		});
		contentPane.add(aboutButton);
		
	}
	
	public void startNewGame() {
		
		String option = String.valueOf(gameModeSelector.getSelectedItem());
		
		switch(option) {
			case "Player vs Player":
				board.setP1(new Player(Global.maximizerPlayer));
				board.setP2(new Player(Global.minimizerPlayer));
				break;
			case "Player vs PC":
				board.setP1(new Computer(Global.maximizerPlayer, Global.pc1PlacingDepth, Global.pc1Depth, BoardEval::fav1));
				board.setP2(new Player(Global.minimizerPlayer));
				break;	
			case "PC vs PC":
				board.setP1(new Computer(Global.maximizerPlayer, Global.pc1PlacingDepth, Global.pc1Depth, BoardEval::fav1));
				board.setP2(new Computer(Global.minimizerPlayer, Global.pc2PlacingDepth, Global.pc2Depth, BoardEval::fav1));
				break;
			default:
				return;
		}
		
		state = new GameState();
		
		board.resetUserOptions();
		
		board.setGameState(state);
		
		board.repaint();
	}

}

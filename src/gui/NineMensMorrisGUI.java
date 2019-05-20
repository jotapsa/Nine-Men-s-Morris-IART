package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class NineMensMorrisGUI extends JFrame {

	private JPanel contentPane;
	private JButton newGameButton;
	private JButton aboutButton;
	private JFrame aux;
	

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
//		super("Nine Men's Morris");
		aux = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(555, 240, 720, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		
		newGameButton = new JButton("Start new game");
		newGameButton.setSize(130, 26);
		newGameButton.setLocation(440, 10);
		newGameButton.setPreferredSize(new Dimension(116, 40));
		contentPane.add(newGameButton);
		
		createAboutButton();
		
	}
	
	public void createAboutButton() {
		aboutButton = new JButton("About");
		aboutButton.setSize(100, 26);
		aboutButton.setLocation(585, 10);
		aboutButton.setPreferredSize(new Dimension(116, 40));
		aboutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog d = new JDialog(aux, "About");
				
				d.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
				
				d.add(new JLabel("Bernardo Manuel Costa Barbosa - up201503477")); 
				  
				d.add(new JLabel("Duarte Nuno Andre Carvalho - up201503661"));
				
				d.add(new JLabel("Joao Pedro Teixeira Pereira de Sa - up201506252"));
				
				d.add(new JLabel("Artificial Intelligence"));
				
				d.add(new JLabel("Master in Informatics and Computing Engineering"));
				
				d.add(new JLabel("Faculty of Engineering  --- University of Porto"));
				
				d.setLocation(aux.getLocation().x + 200, aux.getLocation().y + 200);
				
	            d.setSize(400, 270); 
	  
	            d.setVisible(true); 
			}
		});
		contentPane.add(aboutButton);
	}

}

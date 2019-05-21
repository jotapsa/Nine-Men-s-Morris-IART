package gui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import logic.GameState;
import utilities.Global;

public class GameBoard extends JPanel implements MouseListener {
	private JFrame parentComponent;
	private GameState game;
	
	public GameBoard(JFrame parent) {
		
		parentComponent = parent;
		GameState game = null;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void setGameState(GameState newGame) {
		game = newGame;
	}
	
	public boolean hasGameStarted() {
		return game != null;
	}
	
	public void renderSpots(Graphics g) {
		int startX, startY;
		
		g.setColor(Global.LINES_COLOR);
		
		for(Integer[] coords : GameState.coords) {
			startX = Global.BOARD_BORDER_X + Global.BOARD_SPACING * coords[0];
			startY = Global.BOARD_BORDER_Y + Global.BOARD_SPACING * coords[1];
			g.fillOval(startX, startY, Global.ROCK_SPOT_RADIUS, Global.ROCK_SPOT_RADIUS);
		}
	}
	
	public void renderLines(Graphics g) {
		int startX, startY, endX, endY;
		
		for(int i = 0; i < GameState.neighbours.length; i++) {
			startX = Global.BOARD_BORDER_X + Global.ROCK_SPOT_RADIUS / 2 + Global.BOARD_SPACING * GameState.coords[i][0];
			startY = Global.BOARD_BORDER_Y + Global.ROCK_SPOT_RADIUS / 2 +  Global.BOARD_SPACING * GameState.coords[i][1];
			
			for(int neighboor : GameState.neighbours[i]) {
				endX = Global.BOARD_BORDER_X + Global.ROCK_SPOT_RADIUS / 2 + Global.BOARD_SPACING * GameState.coords[neighboor][0];
				endY = Global.BOARD_BORDER_Y + Global.ROCK_SPOT_RADIUS / 2 + Global.BOARD_SPACING * GameState.coords[neighboor][1];
				
				g.drawLine(startX, startY, endX, endY);
			}	
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int startX, startY, endX, endY;
		
		g.setColor(Global.P1_COLOR);
		g.fillOval(Global.P1_N_AVAILABLE_ROCKS_X, 15, Global.ROCK_RADIUS, Global.ROCK_RADIUS);
		
		g.setColor(Global.P2_COLOR);
		g.fillOval(Global.P2_N_AVAILABLE_ROCKS_X, 15, Global.ROCK_RADIUS, Global.ROCK_RADIUS);
		
		
		
		renderSpots(g);

		renderLines(g);
		
		//PRECISO MUDAR TAMANHO DE LETRA E ALINHAMENTO
		if(hasGameStarted()) {
			g.setFont(new Font("Tahoma", Font.PLAIN, 40));
			g.setColor(Global.P1_COLOR);
			g.drawString("9", Global.P1_N_AVAILABLE_ROCKS_X - 30, 50);
			g.setColor(Global.P2_COLOR);
			g.drawString("9", Global.P2_N_AVAILABLE_ROCKS_X + 60, 50);
			
			for(int i = 0; i < game.getBoard().size(); i++) {
				startX = Global.BOARD_BORDER_X + Global.BOARD_SPACING * GameState.coords[i][0];
				startY = Global.BOARD_BORDER_Y + Global.BOARD_SPACING * GameState.coords[i][1];
				if(game.getBoard().get(i) == Global.maximizerPlayer) {
					g.setColor(Global.P1_COLOR);
				} else if(game.getBoard().get(i) == Global.minimizerPlayer) {
					g.setColor(Global.P2_COLOR);
				}
				else {
					continue;
				}
				g.fillOval(startX, startY, Global.ROCK_RADIUS, Global.ROCK_RADIUS);
			}
		}
	}
}

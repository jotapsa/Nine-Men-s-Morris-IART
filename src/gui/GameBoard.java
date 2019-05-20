package gui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import logic.GameState;
import utilities.Global;

public class GameBoard extends JPanel implements MouseListener {
	private JFrame parentComponent;
	
	public GameBoard(JFrame parent) {
		
		parentComponent = parent;
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
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int startX, startY, endX, endY;
		
		for(Integer[] coords : GameState.coords) {
			startX = Global.BOARD_BORDER + Global.BOARD_SPACING * coords[0];
			startY = Global.BOARD_BORDER + Global.BOARD_SPACING * coords[1];
			g.fillOval(startX, startY, Global.ROCK_SPOT_RADIUS, Global.ROCK_SPOT_RADIUS);
		}
		
		for(int i = 0; i < GameState.neighbours.length; i++) {
			startX = Global.BOARD_BORDER + Global.ROCK_SPOT_RADIUS / 2 + Global.BOARD_SPACING * GameState.coords[i][0];
			startY = Global.BOARD_BORDER + Global.ROCK_SPOT_RADIUS / 2 +  Global.BOARD_SPACING * GameState.coords[i][1];
			
			for(int neighboor : GameState.neighbours[i]) {
				endX = Global.BOARD_BORDER + Global.ROCK_SPOT_RADIUS / 2 + Global.BOARD_SPACING * GameState.coords[neighboor][0];
				endY = Global.BOARD_BORDER + Global.ROCK_SPOT_RADIUS / 2 + Global.BOARD_SPACING * GameState.coords[neighboor][1];
				
				g.drawLine(startX, startY, endX, endY);
			}	
		}
	}
}

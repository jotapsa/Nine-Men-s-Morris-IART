package gui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import logic.GameState;
import logic.Move;
import utilities.Global;

public class GameBoard extends JPanel implements MouseListener {
	private JFrame parentComponent;
	private GameState game;
	private Move storedMove = null;
	private int start = -1;

	public GameBoard(JFrame parent) {

		parentComponent = parent;
		GameState game = null;
		addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void reset() {
		storedMove = null;
	}

	@Override
	public void mousePressed(MouseEvent e) {

		int clickedSpot = clickedSpotIndex(e);
		Move m = null;

		if(clickedSpot == Global.INVALID_INDEX || game == null) {
			return;
		}

		System.out.println(game.getCurrentState());

		switch(game.getCurrentState()) {
			case PLACING:
				if(storedMove != null) {
					storedMove.setTaken(clickedSpot);
					if(game.isValidTake(storedMove)) {
						game.doMove(storedMove);
						storedMove = null;
					}
				}
				else {
					m = new Move(clickedSpot);
					if(game.isValidMove(m)) {
						if(game.moveCausesMill(m)) {
							storedMove = m;
						}
						else {
							game.doMove(m);
						}
					}
				}
				break;
			case MOVING:
			case FLYING:
//				if origin is not yet picked
//				needs to check rock ownership
				if(start == Global.INVALID_INDEX && game.getCurrentPlayer() == game.getBoard().get(clickedSpot)) {
					start = clickedSpot;
				}
				else if(start == clickedSpot) {
					start = Global.INVALID_INDEX;
				}
				else if(start != Global.INVALID_INDEX) {
					if(storedMove != null) {
						storedMove.setTaken(clickedSpot);
						if(game.isValidTake(storedMove)) {
							game.doMove(storedMove);
							storedMove = null;
							start = Global.INVALID_INDEX;
						}
					}
					else {
						if(start == Global.INVALID_INDEX) {
							return;
						}
						m = new Move(start, clickedSpot);
						System.out.println(start + " ->  " + clickedSpot);
						if(game.isValidMove(m)) {
							if(game.moveCausesMill(m)) {
								storedMove = m;
							}
							else {
								game.doMove(m);
								start = Global.INVALID_INDEX;
							}
						}
					}
				}
				break;
			default:
				break;
		}

		repaint();
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
	//Returns -1 if invalid spot
	// Return index of the clicked spot if move is valid
	public int clickedSpotIndex(MouseEvent e) {
		int x, y, spotX, spotY;

		x = e.getX();
		y = e.getY();

		//		System.out.println(x+"-"+y);

		for(int i = 0; i < game.coords.length; i++) {
			spotX = Global.BOARD_BORDER_X + Global.BOARD_SPACING * game.coords[i][0];
			spotY = Global.BOARD_BORDER_Y + Global.BOARD_SPACING * game.coords[i][1];
			//			System.out.println("X: " + x  + " >= " + (spotX - Global.ROCK_SPOT_RADIUS) + " &&  <= " + (spotX + Global.ROCK_SPOT_RADIUS) + "?");
			if(x >= spotX - Global.ROCK_RADIUS && x <= spotX + Global.ROCK_RADIUS) {
				//				System.out.println("yes");
				//				System.out.println("Y: " + y  + " >= " + (spotY - Global.ROCK_SPOT_RADIUS) + " &&  <= " + (spotY + Global.ROCK_SPOT_RADIUS) + "?");
				if(y >= spotY - Global.ROCK_RADIUS && y <= spotY + Global.ROCK_RADIUS) {
					//					System.out.println("yes");
					return i;
				}
			}
		}

		return -1;
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
			startX = Global.BOARD_BORDER_X + Global.BOARD_SPACING * coords[0] - Global.ROCK_SPOT_RADIUS / 2;
			startY = Global.BOARD_BORDER_Y + Global.BOARD_SPACING * coords[1] - Global.ROCK_SPOT_RADIUS / 2;
			g.fillOval(startX, startY, Global.ROCK_SPOT_RADIUS, Global.ROCK_SPOT_RADIUS);
		}
	}

	public void renderLines(Graphics g) {
		int startX, startY, endX, endY;

		for(int i = 0; i < GameState.neighbours.length; i++) {
			startX = Global.BOARD_BORDER_X + Global.BOARD_SPACING * GameState.coords[i][0];
			startY = Global.BOARD_BORDER_Y +  Global.BOARD_SPACING * GameState.coords[i][1];

			for(int neighboor : GameState.neighbours[i]) {
				endX = Global.BOARD_BORDER_X + Global.BOARD_SPACING * GameState.coords[neighboor][0];
				endY = Global.BOARD_BORDER_Y + Global.BOARD_SPACING * GameState.coords[neighboor][1];

				g.drawLine(startX, startY, endX, endY);
			}	
		}
	}

	public void renderAvailbleStones(Graphics g) {
		int p1Available, p2Available;

		p1Available = game.getPlayerAvailableRocks(Global.maximizerPlayer);
		p2Available = game.getPlayerAvailableRocks(Global.minimizerPlayer);

		g.setFont(new Font("Tahoma", Font.PLAIN, 40));
		g.setColor(Global.P1_COLOR);
		g.drawString(Integer.toString(p1Available), Global.P1_N_AVAILABLE_ROCKS_X - 30, 50);
		g.setColor(Global.P2_COLOR);
		g.drawString(Integer.toString(p2Available), Global.P2_N_AVAILABLE_ROCKS_X + 60, 50);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int startX, startY, endX, endY;

		g.setColor(Global.P1_COLOR);
		g.fillOval(Global.P1_N_AVAILABLE_ROCKS_X, Global.N_AVAILABLE_ROCKS_Y, Global.ROCK_RADIUS, Global.ROCK_RADIUS);

		g.setColor(Global.P2_COLOR);
		g.fillOval(Global.P2_N_AVAILABLE_ROCKS_X, Global.N_AVAILABLE_ROCKS_Y, Global.ROCK_RADIUS, Global.ROCK_RADIUS);


		renderSpots(g);

		renderLines(g);

		//PRECISO MUDAR TAMANHO DE LETRA E ALINHAMENTO
		if(hasGameStarted()) {

			renderAvailbleStones(g);

			for(int i = 0; i < game.getBoard().size(); i++) {
				startX = Global.BOARD_BORDER_X + Global.BOARD_SPACING * GameState.coords[i][0] - Global.ROCK_RADIUS / 2;
				startY = Global.BOARD_BORDER_Y + Global.BOARD_SPACING * GameState.coords[i][1] - Global.ROCK_RADIUS / 2;
				if(i == start && storedMove == null) {
					g.setColor(Global.SELECTED_ROCK_COLOR);
				}
				else if(i == start && storedMove.getEnd() != Global.INVALID_INDEX) {
					continue;
				}
				else if(game.getBoard().get(i) == Global.maximizerPlayer) {
					g.setColor(Global.P1_COLOR);
				} else if(game.getBoard().get(i) == Global.minimizerPlayer) {
					g.setColor(Global.P2_COLOR);
				}
				else {
					continue;
				}
				g.fillOval(startX, startY, Global.ROCK_RADIUS, Global.ROCK_RADIUS);
			}

			if(storedMove != null) {
				if(storedMove.getEnd() == Global.INVALID_INDEX) {
					startX = Global.BOARD_BORDER_X + Global.BOARD_SPACING * GameState.coords[storedMove.getStart()][0] - Global.ROCK_RADIUS / 2;
					startY = Global.BOARD_BORDER_Y + Global.BOARD_SPACING * GameState.coords[storedMove.getStart()][1] - Global.ROCK_RADIUS / 2;
				}
				else {
					startX = Global.BOARD_BORDER_X + Global.BOARD_SPACING * GameState.coords[storedMove.getEnd()][0] - Global.ROCK_RADIUS / 2;
					startY = Global.BOARD_BORDER_Y + Global.BOARD_SPACING * GameState.coords[storedMove.getEnd()][1] - Global.ROCK_RADIUS / 2;
				}

				if(game.getCurrentPlayer() == Global.maximizerPlayer) {
					g.setColor(Global.P1_COLOR);
				}
				else {
					g.setColor(Global.P2_COLOR);
				}

				g.fillOval(startX, startY, Global.ROCK_RADIUS, Global.ROCK_RADIUS);
			}
		}
	}
}

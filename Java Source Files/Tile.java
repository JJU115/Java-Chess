/*
*	Tile.java
*	Date of creation: April 14, 2018
*	Date of last modification: Sept 6, 2018
*	
*	Author: Justin Underhay
*	
*/

/*
	Tile.java is the class representing all tiles that make up the chessboard.
	There are 64 tiles on a chess board and hence 64 Tile objects.
	Each Tile can hold a Piece object displayed by a BufferedImage and contains a JButton which can be clicked.
	The JButton's icon property is set to the image of the Piece it is holding or nothing.
	When clicked, the Tile is considered 'activated' represented by the activated boolean member.
	
	Inherits from MouseInputAdapter.java
	
	Data members:
		square - Jpanel			- The graphical container for the Tile
		button - JButton		- The interactive part of the Tile that displays Piece icons 
		icon - BufferedImage	- An image of the Piece currently occupying the Tile or nothing meaning an unoccupied Tile
		tileID - int			- The ID number of the Tile
		curr - Piece			- The Piece currently occupying the Tile if there is any
		activated - boolean		- True if the Tile has been clicked, false otherwise and set to false after a move has been made

*/


import java.awt.Color;
import java.awt.Dimension;	
import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;	
import java.awt.image.BufferedImage;	
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;		


public class Tile extends MouseInputAdaptor {

	private JPanel square;
	private JButton button;
	private BufferedImage icon;
	private int tileID;
	private Piece curr;
	private boolean activated;
	

	public Tile(int ID) {	
		icon = new BufferedImage(100,100,BufferedImage.TYPE_INT_ARGB);
		
		activated = false;
		curr = null;
		tileID = ID;
		
		button = new JButton();
		button.setMargin(new Insets(0,0,0,0));
		button.setIcon(new ImageIcon(icon));
		button.addMouseListener(this);
		
		square = new JPanel(new BorderLayout());
		square.setSize(new Dimension(100,100));
		square.add(button, BorderLayout.CENTER);
	}
	
	
	/*
		setColor(Color set) - Sets the background color of the button member to the set parameter.
	*/
	public void setColor(Color set) {
		button.setBackground(set);
	}	
	
	
	/*
		getPiece() - Returns the curr Piece member.
	*/
	public Piece getPiece() {
		return curr;
	}


	/*
		setPiece(Piece P) - Sets the curr member to p and updates the icon of the button member accordingly.
	*/
	public void setPiece(Piece p) {
		curr = p;
		if (p != null)
			button.setIcon(p.getImage());
		else
			button.setIcon(new ImageIcon(icon));
	}	
	
	
	/*
		getAct() - Returns the activated boolean member.
	*/
	public boolean getAct() {
		return activated;
	}


	/*
		setAct(boolean b) - Sets the activated member to b.
	*/
	public void setAct(boolean b) {
		activated = b;
	}	
	
	/*
		getID() - Returns the tileID member.
	*/
	public int getID() {
		return tileID;
	}	
	
	
	/*
		getPanel() - Returns the square JPanel member.
	*/
	public JPanel getPanel() {
		return square;
	}	
	
	
	/*
		mouseClicked(MouseEvent event) - When the button representing the Tile is clicked by a user this method sets the activated member to true.
	*/
	public void mouseClicked(MouseEvent event) {
		activated = true;
	}
}
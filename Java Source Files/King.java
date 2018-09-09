/*
*	King.java
*	Date of creation: May 20, 2018
*	Date of last modification: Sept 6, 2018
*	
*	Author: Justin Underhay
*	
*/

/*
	King.java represents the king piece.
	This class contains exclusive data members and methods to handle the king's castling move.

	Inherits from Piece.java.	
	
	Data members:
		firstMove - boolean 	- True if the king has moved at all during the game, false otherwise.
*/


import javax.swing.ImageIcon;


public class King extends Piece {
	
	private boolean firstMove;
	
	public King(char pieceColor, char pieceType, int ID) {
		
		if (pieceColor == 'W')
			setImage(new ImageIcon("Supplemental\\W-King.png"));
		else
			setImage(new ImageIcon("Supplemental\\B-King.png"));
		
		setColor(pieceColor);
		setType(pieceType);
		setID(ID);
		setCapture(false);	
		firstMove = true;
	}
	
	/*
		notMoved() - Returns the firstMove boolean member.
	*/
	public boolean notMoved() {
		return firstMove;
	}


	/*
		moved() - Sets the firstMove boolean member to false, signifying that the King has made its first move.
	*/
	public void moved() {
		firstMove = false;
	}	


	/*
		move(Chessboard cBoard) - Generates an integer array containing the Tile ID numbers of Tiles the King may legally move to.
		Kings may move one square in any direction including diagonally.
		Once per game if the King has not moved it may castle, moving two squares horizontally towards a Rook of the same color and placing
		that Rook on the last square the King moved over provided the Rook has not moved yet either.	
	*/
	public int[] move(Chessboard cBoard) {
		//First check if the King is on the fringe of the board to rule out potential moves then examine all 8 adjacent squares
		//Also assert the validity of castling conditions	
		
		int I = cBoard.fetchTileOfPiece(getID()).getID();
		int[] moves = new int[11];
		
		moves[0] = cBoard.fetchTileOfPiece(getID()).getID();
		
		if (I <= 7)			//King is on the top edge of the board
			moves[1] = moves[2] = moves[3] = -1;
		if (I%8 == 0)		//King is on left edge of the board
			moves[1] = moves[4] = moves[6] = -1;
		if (I >= 56)		//King is on bottom edge of the board
			moves[6] = moves[7] = moves[8] = -1;
		if ((I+1)%8 == 0)	//King is on right edge of board
			moves[3] = moves[5] = moves[8] = -1;
		
		
		//Check all 8 squares around the King
		Piece P;
		int[] mods = {-9,-8,-7,-1,1,7,8,9};
		
		for (int i=0; i<8; i++) {
			if (moves[i+1] == -1)
				continue;

			P = cBoard.fetchTile(I+mods[i]).getPiece();
			if (P == null || P.getColor() != getColor())
				moves[i+1] = I+mods[i];
			else
				moves[i+1] = -1;	
		}	

		//Check elligibility for castling
		if (firstMove) {
			int pos = cBoard.fetchTileOfPiece(getID()).getID();
			Piece R = cBoard.fetchTile(pos+3).getPiece();
		
			if (cBoard.fetchTile(pos+1).getPiece() == null && cBoard.fetchTile(pos+2).getPiece() == null && R != null && R.getType() == 'R' && R.notMoved() == true)	
				moves[9] = pos+2;
			else
				moves[9] = -1;
				
			R = cBoard.fetchTile(pos-4).getPiece();
			if (cBoard.fetchTile(pos-1).getPiece() == null && cBoard.fetchTile(pos-2).getPiece() == null && cBoard.fetchTile(pos-3).getPiece() == null && R != null && R.getType() == 'R' && R.notMoved() == true)	
				moves[10] = pos-2;
			else
				moves[10] = -1;	
			
		} else 
			moves[9] = moves[10] = -1;
		
		
		return moves;
	}	

}	
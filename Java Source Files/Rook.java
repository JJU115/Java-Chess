/*
*	Rook.java
*	Date of creation: May 10, 2018
*	Date of last modification: Sept 6, 2018
*	
*	Author: Justin Underhay
*	
*/

/*
	Rook.java represents the Rook piece.
	This class contains exclusive data members and methods to handle the rook's castling move.

	Inherits from Piece.java.
	
	Data members:
		firstMove - boolean		- True if the Rook has moved at all during the game, false otherwise

*/


import javax.swing.ImageIcon;


public class Rook extends Piece {
	
	private boolean firstMove;
	
	public Rook(char pieceColor, char pieceType, int ID) {
		
		if (pieceColor == 'W')
			setImage(new ImageIcon("Supplemental\\W-Rook.png"));
		else
			setImage(new ImageIcon("Supplemental\\B-Rook.png"));
		
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
		moved() - Sets the firstMove boolean member to false indicating that the Rook has been moved.
	*/
	public void moved() {
		firstMove = false;
	}	


	/*
		move(Chessboard cBoard) - Generates an integer array containing the Tile ID numbers of Tiles the Rook may legally move to.
		Rooks can move any number of spaces horizontally or vertically in any direction but cannot jump over other pieces.
	*/
	public int[] move(Chessboard cBoard) {
		//Initialize moves array and auxiliary variables
		
		int[] moves = new int[15];
		int P = cBoard.fetchTileOfPiece(getID()).getID();
		int I, q, mod = 0, amt = 0, i = 1;
		
		moves[0] = cBoard.fetchTileOfPiece(getID()).getID();
		
		//For each direction, horizontally and vertically, determine number of squares Rook can move
		for (int j=0; j<4; j++) {
			I = P;
			switch (j) {
				case 0:
				mod = -8;
				amt = (I-(I%8))/8;
				break;
				case 1:
				mod = 8;
				amt = 7-((I-(I%8))/8);
				break;
				case 2:
				mod = -1;
				amt = I%8;
				break;
				case 3:
				mod = 1;
				amt = 7-(I%8);
				break;
				
			}
			
			I += mod;
			for (q=0; q<amt; q++) {
				Tile T = cBoard.fetchTile(I);
				if (T.getPiece() != null) {
					if (T.getPiece().getColor() != getColor())
						moves[i++] = T.getID();
					else
						moves[i++] = -1;
					I += mod;
					break;
				}
				moves[i++] = T.getID();
				I += mod;		
			}

			for (int l=0; l<amt-q-1; l++) 
				moves[i++] = -1;		
		}
		return moves;
	}
	
}	
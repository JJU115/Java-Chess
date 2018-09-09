/*
*	Bishop.java
*	Date of creation: May 15, 2018
*	Date of last modification: Sept 6, 2018
*	
*	Author: Justin Underhay
*	
*/

/*
	Bishop.java represents the Bishop piece.
	The Bishop possesses no special moves.
*/

import javax.swing.ImageIcon;


public class Bishop extends Piece {
	
	public Bishop(char pieceColor, char pieceType, int ID) {
		
		if (pieceColor == 'W')
			setImage(new ImageIcon("Supplemental\\W-Bishop.png"));
		else
			setImage(new ImageIcon("Supplemental\\B-Bishop.png"));
		
		setColor(pieceColor);
		setType(pieceType);
		setID(ID);
		
		setCapture(false);	
	}	
	

	/*
		move(Chessboard cBoard) - Generates an integer array containing the Tile ID numbers of Tiles the Bishop may legally move to.
		The bishop can move any number of squares diagonally but cannot jump over other pieces. 
	*/
	public int[] move(Chessboard cBoard) {
		//Determine the max number of squares movable in each direction then check for another piece in the same path

		int[] directions = new int[4];
		int I = cBoard.fetchTileOfPiece(getID()).getID();
		int P = I;
		int q, mLen = 0, amt = 0, mod = 0, i = 1;
	
		for (int p=0; p<2; p++) {
			while (I > 7 && (I+p)%8 != 0) {
				directions[p]++;
				I -= (9 - p*2);
			}
			I = P;
			
			while (I < 56 && (I+p)%8 != 0) {
				directions[p+2]++;
				I += (7 + p*2);
			}	
			I = P;
			mLen += (directions[p]+directions[p+2]);
		}	
	
	
		int[] moves = new int[mLen+1];
		int[] mods = {-9, -7, 7, 9};
		
		moves[0] = cBoard.fetchTileOfPiece(getID()).getID();
		
		for (int j=0; j<4; j++) {
			I = P;
			mod = mods[j];
			amt = directions[j];
		
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
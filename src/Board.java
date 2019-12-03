
import javax.swing.*;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.Arrays;

public class Board {
	
	private char[][] _board;
	private int _colLength;
	private int _rowLength;
	
	Board(){
		_rowLength = 3;
		_colLength = 3;
		_board = new char[_rowLength][_colLength];
	}
	
	private boolean inBounds(int index ,int length) {
		return ((index >= 0) && (index < length));
	}
	
	
	
	private boolean checkCol(int n, char sign, int r, int c) {
		int nInRow = 1;
		
		for(int i = 1; i < n; i+=1) {
			if(inBounds(r+i,_rowLength)) {
				if(_board[r+i][c] == sign) {
					nInRow += 1;
				} else {
					break;
				}
			} else {
				break;
			}
		}
		
		for(int i = 1; i < n; i+=1) {
			if(inBounds(r-i,_rowLength)) {
				if(_board[r-i][c] == sign) {
					nInRow += 1;
				} else {
					break;
				}
			} else {
				break;
			}
		}
		
		if(nInRow == n) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean checkRow(int n, char sign, int r,int c) {
		int nInRow = 1;
		
		for(int i = 1; i < n; i+=1) {
			if(inBounds(c+i,_colLength)) {
				if(_board[r][c+i] == sign) {
					nInRow += 1;
				} else {
					break;
				}
			}
		}
		
		for(int i = 1; i < n; i+=1) {
			if(inBounds(c-i,_colLength)) {
				if(_board[r][c-i] == sign) {
					nInRow += 1;
				} else {
					break;
				}
			}
		}
			
		if(nInRow == n) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean checkDiagonal(int n, char sign, int r, int c) {
		int nInRow = 1;
		
		for(int i = 1; i < n; i+=1) {
			if(inBounds(r+i,_colLength) && inBounds(c+i,_colLength)) {
				if(_board[r+i][c+i] == sign) {
					nInRow += 1;
				} else {
					break;
				}
			} else {
				break;
			}
		}
		
		for(int i = 1; i < n; i+=1) {
			if(inBounds(r-i,_colLength) && inBounds(c-i,_colLength)) {
				if(_board[r-i][c-i] == sign) {
					nInRow += 1;
				} else {
					break;
				}
			} else {
				break;
			}
		}
			
		if(nInRow == n) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean checkAntiDiagonal(int n, char sign, int r, int c) {
		int nInRow = 1;
		
		for(int i = 1; i < n; i+=1) {
			if(inBounds(r+i,_colLength) && inBounds(c-i,_colLength)) {
				if(_board[r+i][c-i] == sign) {
					nInRow += 1;
				} else {
					break;
				}
			} else {
				break;
			}
		}
		
		for(int i = 1; i < n; i+=1) {
			if(inBounds(r-i,_colLength) && inBounds(c+i,_colLength)) {
				if(_board[r-i][c+i] == sign) {
					nInRow += 1;
				} else {
					break;
				}
			} else {
				break;
			}
		}
			
		if(nInRow == n) {
			return true;
		} else {
			return false;
		}
	}
	
	public void setBoardSize(int row,int col) {
		_board = new char[row][col];
	}
	
	public char[][] getBoard() {
		return _board;
	}
	
	public void addCharToBoard(char sign, int r, int c) {
		_board[r][c] = sign;
	}
	
	public boolean findNInRow(int n, char sign, int r, int c) {
		System.out.println(Arrays.deepToString(_board));
		return(checkRow(n, sign, r, c) || checkCol(n, sign, r, c) || checkDiagonal(n, sign, r, c) || checkAntiDiagonal(n, sign, r, c));
	}
}

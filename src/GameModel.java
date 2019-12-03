
import javax.swing.*;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;

public class GameModel {
	private int _numOfPlayers;
	private ArrayList<Player> _players;
	private Board _board;
	private int _turnNum;
	private int _maxPlayers;
	private int _minPlayers;
	private int _inRowNum;
	private char _playerSigns[];
	
	GameModel(){
		_turnNum = 0;
		_maxPlayers = 10;
		_minPlayers = 2;
		_inRowNum = 3;
		_players = new ArrayList<Player>();
		_numOfPlayers = 2;
		_playerSigns = new char[]{'O','X','M','F','G','H','A','P','U','W'};
		_board = new Board();
		for(int i = 0; i < _numOfPlayers; i+=1) {
			_players.add(new Player(i+1, _playerSigns[i]));
		}	
	}
	
	public void addPlayer() {
		if(_numOfPlayers == _maxPlayers) {
			return;
		}
		_numOfPlayers += 1;
		_players.add(new Player(_numOfPlayers, _playerSigns[_numOfPlayers-1]));
	}
	
	public void removePlayer() {
		if(_numOfPlayers == _minPlayers) {
			return;
		}
		_players.remove(_players.size()-1);
		_numOfPlayers -= 1;
	}
	
	public int getNumOfPlayers() {
		return _numOfPlayers;
	}
	
	public void placeSignOnBoard(char playerSign, int r, int c) {
		_board.addCharToBoard(playerSign, r ,c);
	}
	
	public boolean hasPlayerWon(char playerChar, int r, int c ) {
		return _board.findNInRow(_inRowNum, playerChar, r, c);
	}
	
	public char[][] getBoard() {
		return _board.getBoard();
	}
	
	public Player getPlayer(int playerNum) {
		return _players.get(playerNum);
	}
	
	public int checkWhichPlayerTurn() {
		return (_turnNum%_numOfPlayers) + 1;
	}
	
	public int checkNextPlayerTurn() {
		return ((_turnNum+1)%_numOfPlayers) + 1;
	}
	
	public void newTurn() {
		_turnNum += 1;
	}
	
	public boolean hasPlayerWon(){
		
		return false;
	}
	
}

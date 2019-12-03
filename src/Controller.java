
import javax.swing.*;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;

public class Controller {

	private View _view;
	private GameModel _gameModel;
	boolean _manyGUI;
	boolean _gameOver;
	
	public Controller(){
		_gameOver = false;
		_manyGUI = false;
		_gameModel = new GameModel();
		int numOfPlayers = _gameModel.getNumOfPlayers();
		char board[][] = _gameModel.getBoard();
		_view = new View(this, numOfPlayers, board);
	}
	
	public void unitClickedListener(int i, int r, int c) {
		if(!_gameOver) {
			char[][] board = _gameModel.getBoard();
			if(board[r][c] != '\u0000') {
				return;
			}
			int currentTurn = _gameModel.checkWhichPlayerTurn();
			//System.out.println(currentTurn);
			Player currentPlayer = _gameModel.getPlayer(currentTurn-1);
			int currentPlayerNum = currentPlayer.getPlayerNum();
			System.out.println("i:" + i);
			System.out.println("currentPlayer: " + currentPlayer.getPlayerNum());
			if(i != currentPlayerNum-1 && _manyGUI) {
				return;
			}
			char currentPlayerSign = currentPlayer.getPlayerSign();
			_gameModel.placeSignOnBoard(currentPlayerSign, r, c);
			int numOfPlayers = _gameModel.getNumOfPlayers();
			_view.setPlayerText("Player " + Character.forDigit(_gameModel.checkNextPlayerTurn(), 10) + "'s turn");
			_view.placePlayerSign(_manyGUI, numOfPlayers, currentPlayerSign, r, c);
			if(_gameModel.hasPlayerWon(currentPlayerSign, r, c)) {
				_view.setPlayerText("Player " + Character.forDigit(currentPlayerNum, 10) + " is the winner");
				_gameOver = true;
			}
			_gameModel.newTurn();
		}
	}
	
	public void addPlayerListener() {
		_gameModel.addPlayer();
	}

	public void removePlayerListener() {
		_gameModel.removePlayer();
	}

	public void toggleGUIListener() {
		if(_manyGUI) {
			_view.createOneticTacToePanel();
			_view.setGUIText("many GUI");
			_manyGUI = false;
		} else {
			_view.createManyticTacToePanels(_gameModel.getNumOfPlayers());
			_view.setGUIText("one GUI");
			_manyGUI = true;
		}
	}

	public void restartGameListener() {
		_gameOver = false;
		_gameModel = new GameModel();
		int numOfPlayers = _gameModel.getNumOfPlayers();
		_view.clearButtons(numOfPlayers);
		_view.setPlayerText("Player 1's turn");
		//_view = new View(this, numOfPlayers, board);
	}

	public void changeRowsListener() {
		System.out.println("change rows");
		// TODO Auto-generated method stub
		
	}

	public void changeColsListener() {
		System.out.println("change cols");
		// TODO Auto-generated method stub
		
	}

	public void changeWinConditionListener() {
		System.out.println("change win");
		// TODO Auto-generated method stub
		
	}
	
	
		
	
	
	
	

}

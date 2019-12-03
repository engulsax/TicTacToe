
import javax.swing.*;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;

public class Player {

	private int _playerNum;
	private char _playerSign;
	
	Player(int playerNum, char playerSign){
		_playerNum = playerNum;
		_playerSign = playerSign;
	}
	
	public void setPlayerSign(char playerSign){
		_playerSign = playerSign;
	}
	
	public char getPlayerSign() {
		return _playerSign;
	}
	
	public int getPlayerNum() {
		return _playerNum;
	}
}
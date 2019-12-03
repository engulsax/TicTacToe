import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

public class View {
	
	private Controller _controller;
	private int _boardRows = 3;
	private int _boardCols = 3;
	private JPanel _playAreasPanel;
	private JButton[][][] _buttons;
	private JPanel _textPanel;
	private JLabel _textLabel;
	private JPanel _ticTacToePanel;
	private JFrame _frame;
	private JPanel _mainPanel;
	private JPanel _gamePanel;
	
	private JPanel _menuPanel;
	
	private JPanel _buttonPanel;
	
	private JButton _addPlayerButton;
	private JButton _removePlayerButton;
	private JButton _GUIButton;
	private JButton _restartGame;
	
	private JPanel _textFieldPanel;
	
	private JTextField _numOfRowField;
	private JLabel _rowLabel;
	private JPanel _rowPanel;
	
	private JTextField _numOfColField;
	private JLabel _colLabel;
	private JPanel _colPanel;
	
	private JTextField _winConditionField;
	private JLabel _winCondition;
	private JPanel _winConditionPanel;
	
	public View(Controller controller, int numOfPlayers, char[][] board) {
		_boardRows = board[0].length;
		_boardCols = board.length;
		_frame = new JFrame("TicTacToe");
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_gamePanel = new JPanel();
		_controller = controller;
		_menuPanel = createMenu();
		_gamePanel.add(_menuPanel);
		
		_ticTacToePanel = new JPanel();
		_ticTacToePanel.add(createPlayerArea());
		_ticTacToePanel.setLayout(new BoxLayout(_ticTacToePanel, BoxLayout.Y_AXIS));
		_ticTacToePanel.add(_playAreasPanel);
		_ticTacToePanel.setAlignmentY(Component.CENTER_ALIGNMENT);
		 
		_gamePanel.add(_ticTacToePanel);
		
		_textPanel = new JPanel();
		_textPanel.setLayout(new GridLayout(1,1));
		_textPanel.setPreferredSize(new Dimension(150,50));
		_textLabel = new JLabel("Player 1's turn", SwingConstants.CENTER);
	  	_textPanel.add(_textLabel);
	  	_gamePanel.add(_textPanel);
	  	_gamePanel.setLayout(new BoxLayout(_gamePanel, BoxLayout.Y_AXIS));
	  	
	  	_mainPanel = new JPanel();
	  	_mainPanel.add(_gamePanel);
	  	_mainPanel.setLayout(new BoxLayout(_mainPanel, BoxLayout.Y_AXIS));
	  	
	  	_frame.getContentPane().add(_mainPanel);
	     //Display window
	  	_frame.pack();
	    _frame.setVisible(true);
	}
	
	private JPanel createMenu() {
		
		_addPlayerButton = new JButton("Add Player");
		_addPlayerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_controller.addPlayerListener();
			}
		});
		
		_removePlayerButton = new JButton("Remove Player");
		_removePlayerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_controller.removePlayerListener();
			}
		});
		
		_GUIButton = new JButton("Many GUIs");
		_GUIButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_controller.toggleGUIListener();
			}
		});
		
		_restartGame = new JButton("Restart");
		_restartGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_controller.restartGameListener();
			}
		});
		
		_buttonPanel = new JPanel();
		_buttonPanel.add(_addPlayerButton);
		_buttonPanel.add(_removePlayerButton);
		_buttonPanel.add(_GUIButton);
		_buttonPanel.add(_restartGame);
		_buttonPanel.setLayout(new BoxLayout(_buttonPanel, BoxLayout.X_AXIS));
		
		_numOfRowField = new JTextField(5);
		_numOfRowField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_controller.changeRowsListener();
			}
		});
		_rowLabel = new JLabel("Pick Rows:");
		_rowPanel = new JPanel();
		_rowPanel.add(_rowLabel);
		_rowPanel.add(_numOfRowField);
		
		_numOfColField = new JTextField(5);
		_numOfColField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_controller.changeColsListener();
			}
		});
		_colLabel = new JLabel("Pick Cols:");
		_colPanel = new JPanel();
		_colPanel.add(_colLabel);
		_colPanel.add(_numOfColField);
		
		_winConditionField = new JTextField(5);
		_winConditionField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_controller.changeWinConditionListener();
			}
		});
		_winCondition = new JLabel("Winning Condition:");
		_winConditionPanel = new JPanel();
		_winConditionPanel.add(_winCondition);
		_winConditionPanel.add(_winConditionField);
	
		_textFieldPanel = new JPanel();
		_textFieldPanel.add(_rowPanel);
		_textFieldPanel.add(_colPanel);
		_textFieldPanel.add(_winConditionPanel);
		_textFieldPanel.setLayout(new BoxLayout(_textFieldPanel, BoxLayout.X_AXIS));
		
		_menuPanel = new JPanel();
		_menuPanel.add(_buttonPanel);
		_menuPanel.add(_textFieldPanel);
		_menuPanel.setLayout(new BoxLayout(_menuPanel, BoxLayout.Y_AXIS));
		
		return _menuPanel;
	}
	
	private JPanel createPlayerArea() {
		 _playAreasPanel = new JPanel();		
		 _playAreasPanel.setLayout(new GridLayout(_boardRows,_boardCols));
		 _buttons = new JButton[10][_boardRows][_boardCols];
		 createButtons();
	     return _playAreasPanel;
	}
	
	private void createButtons(){
		for(int r = 0; r < _boardRows; r++) {
			for(int c = 0; c < _boardCols; c++) {
					final int _r = r;
					final int _c = c;
					JButton button = _buttons[0][r][c] = new JButton();
					button.setPreferredSize(new Dimension(50, 50)); 
					button.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							_controller.unitClickedListener(0 ,_r, _c);
						}
					});
					_playAreasPanel.add(button);
			}
		}
    } 
	
	private JButton[][] deepCopyButtons(JButton[][] toCopy, int i) {
		JButton[][] tempButtons = new JButton[toCopy.length][toCopy[0].length];

		for (int r = 0; r < toCopy.length; r++) {
		    for (int c = 0; c < toCopy[0].length; c++) {
		    	tempButtons[r][c] = new JButton(toCopy[r][c].getText());
		                final int _r = r;
						final int _c = c;
						tempButtons[r][c].setPreferredSize(new Dimension(50, 50)); 
						tempButtons[r][c].addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								_controller.unitClickedListener(i, _r, _c);
							}
						});
		    }
		                
		}
		 return tempButtons;
	}
	
	public void placePlayerSign(boolean manyGUI, int numOfPlayers, char playerSign, int r, int c) { 
		String sign = String.valueOf(playerSign);
		if(manyGUI) {
			for(int i = 0; i < numOfPlayers; i += 1) {
				_buttons[i][r][c].setText(sign);
			}
		} else {
			_buttons[0][r][c].setText(sign);
		}
	}
	
	public void setPlayerText(String playerText) {
		_textLabel.setText(playerText);
	}
	
	public void createManyticTacToePanels(int numOfPanels) {
		_ticTacToePanel.removeAll();
		for(int i = 0; i < numOfPanels; i+=1) {
			_ticTacToePanel.add(new JLabel("Player: " + Character.forDigit(i+1, 10)));	
			_playAreasPanel = new JPanel();
			_playAreasPanel.setLayout(new GridLayout(_boardRows,_boardCols));
			 _buttons[i] =  deepCopyButtons(_buttons[0], i);
			for(int r = 0; r < _boardRows; r++) {
				for(int c = 0; c < _boardCols; c++) {
					_playAreasPanel.add(_buttons[i][r][c]);
					
				}
			}
			_ticTacToePanel.add(_playAreasPanel);
		}
		
		_ticTacToePanel.revalidate();
		_ticTacToePanel.repaint();
	}
	
	public void createOneticTacToePanel() {
		_ticTacToePanel.removeAll();
		_playAreasPanel = new JPanel();
		_playAreasPanel.setLayout(new GridLayout(_boardRows,_boardCols));
		for(int r = 0; r < _boardRows; r++) {
			for(int c = 0; c < _boardCols; c++) {
				_playAreasPanel.add(_buttons[0][r][c]);
			}
		}
		_ticTacToePanel.add(_playAreasPanel);
		_ticTacToePanel.revalidate();
		_ticTacToePanel.repaint();
	}
	
	public void setGUIText(String text) {
		_GUIButton.setText(text);
	}
	
	public void clearButtons(int numOfPlayers) {
		for(int i = 0; i < numOfPlayers; i += 1) {
			for(int r = 0; r < _boardRows; r+= 1) {
				for(int c = 0; c < _boardCols; c += 1) {
					_buttons[i][r][c].setText("");
				}
			}
		}
	}
}

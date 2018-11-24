import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;

import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

class Board extends JFrame {

	private Square[][] squares = new Square[10][10];
	private boolean firstRun = true;
	private List<Piece> pieces = range(0, 5).mapToObj(i -> new Piece(this)).collect(toList());
	private MainMenu mainMenu;
	private int delay;
	private Timer timer;

	Board(MainMenu mainMenu, int delay) {
		this.mainMenu = mainMenu;
		this.delay = delay;
		this.setLayout(new GridLayout(10, 10));
		setSize(750, 650);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		initBoard();
		setVisible(true);
		run();
	}

	private void run() {
		timer = new Timer(delay, actionEvent -> {
			for (Piece piece : pieces) {
				new Worker(() -> move(piece)).execute();
			}
		});
		timer.setInitialDelay(0);
		timer.start();
	}

	synchronized Square getEmptySquare(){
		Coordinate coordinate = getRandomCoordinate();
		return !squares[coordinate.x][coordinate.y].isSelected() ? squares[coordinate.x][coordinate.y] : getEmptySquare();
	}

	private synchronized void move(Piece piece) {
		if (!firstRun && allSquaresAreEmpty()){
			JOptionPane.showMessageDialog(this, "Ganhou!");
			this.dispose();
			mainMenu.setVisible(true);
			timer.stop();
		}
		piece.move();
		firstRun = false;
	}

	private Coordinate getRandomCoordinate() {
		return new Coordinate(new Random().nextInt(9), new Random().nextInt(9));
	}

	private void initBoard() {
		for (int i = 0; i < squares.length; i++) {
			for (int j = 0; j < squares.length; j++) {
				squares[i][j] = new Square();
				add(squares[i][j]);
			}
		}
	}

	private boolean allSquaresAreEmpty() {
		for (int i = 0; i < squares.length; i++) {
			for (int j = 0; j < squares.length; j++) {
				if (squares[i][j].isSelected()) return false;
			}
		}
		return true;
	}

}

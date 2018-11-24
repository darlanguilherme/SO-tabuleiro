import javax.swing.*;
import java.awt.*;
import java.util.Random;

class Board extends JFrame {

	private Square[][] squares = new Square[10][10];
	private boolean firstRun = true;
	private MainMenu mainMenu;
	private int delay;
	private Timer timer;

	Board(MainMenu mainMenu, int delay) {
		this.mainMenu = mainMenu;
		this.delay = delay;
		this.setLayout(new GridLayout(10, 10));
		setSize(750, 650);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initBoard();
		setVisible(true);
		run();
	}

	private void run() {
		timer = new Timer(delay, actionEvent -> {
			for (int i = 0; i < 5; i++) {
				new Worker(this::checkRandomSquare).execute();
			}
		});
		timer.setInitialDelay(0);
		timer.start();
	}

	private synchronized void checkRandomSquare() {
		if (!firstRun && allSquaresAreEmpty()){
			JOptionPane.showMessageDialog(this, "Ganhou!");
			this.dispose();
			mainMenu.setVisible(true);
			timer.stop();
		}
		System.out.println("call");
		Coordinate coordinate = getRandomCoordinate();
		if (squares[coordinate.x][coordinate.y].isSelected()) checkRandomSquare();
		squares[coordinate.x][coordinate.y].check(true);
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

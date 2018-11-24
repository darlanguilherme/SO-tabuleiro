class Piece {

	private Board board;
	private Square square;

	Piece(Board board){
		this.board = board;
	}

	void move() {
		if (square == null){
			square = board.getEmptySquare();
			square.setSelected(true);
		}else {
			square.setSelected(false);
			square = board.getEmptySquare();
			square.setSelected(true);
		}
	}
}

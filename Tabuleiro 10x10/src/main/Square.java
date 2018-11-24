import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

class Square extends JLabel {

	private boolean selected;

	Square() {
		setVisible(true);
		setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		setHorizontalAlignment(JLabel.CENTER);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mouseEvent) {
				if (selected){
					setSelected(false);
				}
			}
		});
	}

	public boolean isSelected() {
		return selected;
	}

	void setSelected(boolean selected){
		this.selected = selected;
		if (selected) fill();
		else empty();
	}

	private void empty(){
		setIcon(new PlaceHolderIcon(32, 32));
	}

	private void fill(){
		setIcon(new ImageIcon(getCircleImage()));
	}

	private Image getCircleImage() {
		BufferedImage bufferedImage = new BufferedImage(32,32,BufferedImage.TYPE_INT_ARGB);
		Graphics2D graphics = bufferedImage.createGraphics();
		graphics.setColor(Color.RED);
		graphics.fillOval(0,0,32,32);
		graphics.dispose();
		return bufferedImage;
	}

}

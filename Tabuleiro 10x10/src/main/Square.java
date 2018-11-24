import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

class Square extends JToggleButton {

	Square() {
		setVisible(true);
		setIcon(new PlaceHolderIcon(32, 32));
		setSelectedIcon(new ImageIcon(getCircleImage(Color.RED)));
	}

	private Image getCircleImage(Color c) {
		BufferedImage bufferedImage = new BufferedImage(32,32,BufferedImage.TYPE_INT_ARGB);
		Graphics2D graphics = bufferedImage.createGraphics();
		graphics.setColor(c);
		graphics.fillOval(0,0,32,32);
		graphics.dispose();
		return bufferedImage;
	}

	void check(boolean b) {
		setSelected(b);
	}
}

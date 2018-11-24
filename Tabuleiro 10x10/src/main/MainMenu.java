import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {

	public MainMenu() throws HeadlessException {
		buildMenu();
		setVisible(true);
	}

	private void buildMenu() {
		Box box = Box.createVerticalBox();
		box.add(new JLabel("Tabuleiro 10x10") {{
			setAlignmentX(Component.CENTER_ALIGNMENT);
		}});
		box.add(Box.createVerticalStrut(80));
		box.add(new JButton("Iniciar") {{
			setAlignmentX(Component.CENTER_ALIGNMENT);
			addActionListener(actionEvent -> {
				String[] choices = {"FÁCIL", "MÉDIO", "DIFÍCIL"};
				String input = (String) JOptionPane.showInputDialog(null, "Dificuldade",
						"Dificuldade", JOptionPane.QUESTION_MESSAGE, null, choices, choices[1]);
				int delay = input.equals(choices[0]) ? 10000 : input.equals(choices[1]) ? 5000 : input.equals(choices[2]) ? 3000 : 3000;
				new Board(MainMenu.this, delay);
				MainMenu.this.setVisible(false);
			});
		}});
		add(box);
		setSize(300, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}

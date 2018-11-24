import javax.swing.*;

public class Worker extends SwingWorker<Void, Void> {

	private Runnable runnable;

	public Worker(Runnable runnable) {
		this.runnable = runnable;
	}

	@Override
	protected Void doInBackground() {
		return null;
	}

	@Override
	protected void done() {
		runnable.run();
	}
}



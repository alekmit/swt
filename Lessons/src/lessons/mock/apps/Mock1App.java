package lessons.mock.apps;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

public class Mock1App extends ApplicationWindow {

	public Mock1App() {
		super(null);
	}

	@Override
	protected Control createContents(Composite parent) {
		parent.pack();
		return parent;
	}


	public static void main(String[] args) {
		Mock1App app = new Mock1App();
		app.setBlockOnOpen(true);
		app.open();
		Display.getCurrent().dispose();
	}


}

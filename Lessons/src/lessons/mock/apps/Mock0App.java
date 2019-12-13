package lessons.mock.apps;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import lessons.mock.swt.CalculatorMock;

public class Mock0App {

	private static final int FIXED_STYLE = SWT.CLOSE | SWT.TITLE | SWT.MIN;

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display, FIXED_STYLE);
		new CalculatorMock(shell);
		shell.open();
		shell.pack();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();

	}

}

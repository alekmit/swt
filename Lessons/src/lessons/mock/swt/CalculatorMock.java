package lessons.mock.swt;
import java.util.regex.Pattern;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

import lessons.mock.model.CalculatorEngine;
import lessons.mock.model.Operators;

public class CalculatorMock {

	private final Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

	private Text arg0Txt;

	private Text arg1Txt;

	private Combo opSel;

	private Button onFlyBtn;

	private Button calculateBtn;

	private Text resultTxt;

	private List logList;

	private Text statusLine;

	private Shell shell;

	private CalculatorEngine calculator;

	private Listener buttonListener = new Listener() {

		@Override
		public void handleEvent(Event event) {
			if (event.button == 1) {
				calculate();
			}

		}

	};

	private ModifyListener changeListener = new ModifyListener(){

		@Override
		public void modifyText(ModifyEvent e) {
			if (onFlyBtn.getSelection()) {
				calculate();
			}

		}

	};

	private Listener validateListener = new Listener() {

		@Override
		public void handleEvent(Event event) {
			String input = event.text;
			event.doit = input.isBlank() || validate(input);
			statusLine.setText(event.doit ? "" : "Wrong values!");
		}
	};


	public CalculatorMock(Shell shell) {
		this.shell = shell;
		calculator = CalculatorEngine.getInstance();
		prepareContent();
		addHandlers();
	}

	private boolean validate(String text) {
		return pattern.matcher(text.trim()).matches();

	}

	private void calculate() {
		int opi = opSel.getSelectionIndex();
		String aT = arg0Txt.getText();
		String bT = arg1Txt.getText();
		if (opi != -1 && validate(aT) && validate(bT)) {
			String op = opSel.getText();
			double a = Double.parseDouble(aT);
			double b = Double.parseDouble(bT);
			Double result = calculator.getOperation(op).apply(a, b);
			if (result != null) {
				resultTxt.setText(String.valueOf(result));
				addLog(a, b, op, result);
			}
		}
	}

	private void addLog(double a, double b, String op, double result) {
		logList.add(String.format("%f %s %f = %f", a, op, b, result));

	}

	private void addHandlers() {
		calculateBtn.addListener(SWT.MouseDown, buttonListener);
		arg0Txt.addListener(SWT.Verify, validateListener  );
		arg1Txt.addListener(SWT.Verify, validateListener);
		arg0Txt.addModifyListener(changeListener);
		arg1Txt.addModifyListener(changeListener);
	}


	private void prepareContent() {
		shell.setText("SWT Calculator");
		shell.setLayout(new RowLayout(SWT.VERTICAL));
		TabFolder folder = new TabFolder(shell, SWT.NONE);
		TabItem item0 = new TabItem(folder, SWT.NONE);
		item0.setText("Calculator");
		TabItem item1 = new TabItem(folder, SWT.NONE);
		item1.setText("History");
		SashForm sashForm0 = new SashForm(folder, SWT.VERTICAL);
		sashForm0.setLayout(new RowLayout(SWT.VERTICAL));
		Group groupArgs = new Group(sashForm0, SWT.NONE);
		groupArgs.setLayout(new GridLayout(3, false));
		arg0Txt = new Text(groupArgs, SWT.SINGLE | SWT.BORDER);
		arg0Txt.setTextLimit(8);
		opSel = new Combo(groupArgs, SWT.SINGLE | SWT.BORDER);
		for (Operators op : Operators.values()) {
			opSel.add(op.getValue());
		}
		arg1Txt = new Text(groupArgs, SWT.SINGLE | SWT.BORDER);
		arg1Txt.setTextLimit(8);
		Group groupFlags = new Group(sashForm0, SWT.NONE);
		groupFlags.setLayout(new GridLayout(2, true));
		onFlyBtn = new Button(groupFlags, SWT.CHECK);
		onFlyBtn.setText("Calculate on the fly");
		calculateBtn = new Button(groupFlags, SWT.PUSH);
		calculateBtn.setText("Calculate");
		Group groupRes = new Group(sashForm0, SWT.NONE);
		groupRes.setLayout(new GridLayout(2, true));
		Label label = new Label(groupRes, SWT.NONE);
		label.setText("Result: ");
		resultTxt = new Text(groupRes, SWT.SINGLE | SWT.BORDER);
		resultTxt.setTextLimit(16);
		resultTxt.setEditable(false);
		item0.setControl(sashForm0);
		SashForm sashForm1 = new SashForm(folder, SWT.HORIZONTAL);
		sashForm1.setLayout(new FillLayout());
		logList = new List(sashForm1, SWT.BORDER);
		item1.setControl(sashForm1);
		statusLine = new Text(shell, SWT.SINGLE);
		statusLine.setEditable(false);
	}


}

package lessons.mock.model;

public class SubOperation implements IMathOperation {

	@Override
	public double doOperation(double a, double b) {
		return a - b;
	}

}

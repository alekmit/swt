package lessons.mock.model;

public class MulOperation implements IMathOperation {

	@Override
	public double doOperation(double a, double b) {
		return a * b;
	}

}

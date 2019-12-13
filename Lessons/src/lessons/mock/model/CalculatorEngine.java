package lessons.mock.model;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public final class CalculatorEngine {

	private Map<String, BiFunction<Double,Double,Double>> operationMap = new HashMap<>();

	private CalculatorEngine() {
		operationMap.put(Operators.ADD.getValue(), (a,b) -> a + b);
		operationMap.put(Operators.SUB.getValue(), (a,b) -> a - b);
		operationMap.put(Operators.MUL.getValue(), (a,b) -> a * b);
		operationMap.put(Operators.DIV.getValue(), (a,b) -> a / b);
	}

	public static CalculatorEngine getInstance() {
		return new CalculatorEngine();
	}

	public BiFunction<Double,Double,Double> getOperation(String operator) {
		return operationMap.get(operator);
	}

}

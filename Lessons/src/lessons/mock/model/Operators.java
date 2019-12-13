package lessons.mock.model;

public enum Operators {

	ADD("+"), SUB("-"), MUL("*"), DIV("/");

	private String value;

	private Operators(String value) {
		this.setValue(value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}

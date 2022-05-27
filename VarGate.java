package circuits;

public class VarGate extends Gate { // sub class of Gate
	private String name;
	private boolean var;
	protected boolean set = false; // for checking if insert a value

	public VarGate(String name) {
		super(null);
		this.name = name;
	}

	public String getName() {
		return ("V" + name);
	}

	protected boolean func(boolean[] inValues) throws CircuitException {
		if (set == false) // if the value is not changed - we have Exception
			throw new CircuitException();
		return var; // else return var
	}

	public void setVal(boolean value) { // we change value
		this.var = value;
		set = true; // now set has changed so gets true
	}

	public Gate simplify() {
		if (set == false) // if not change
			return this;
		else if (var == true) // return the value inside VarGate
			return TrueGate.instance();
		return FalseGate.instance();
	}

}

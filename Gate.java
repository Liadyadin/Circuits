package circuits;

public abstract class Gate {
	protected Gate[] inGates;
	protected boolean[] temp; // for calc function

	public Gate(Gate[] inGates) { // constructor for Gate
		this.inGates = inGates;
		if (inGates != null)
			temp = new boolean[inGates.length];
	}

	public boolean calc() throws CircuitException {
		if (inGates != null)
			for (int i = 0; i < inGates.length; i++) {
				if (inGates[i] instanceof VarGate)
					return inGates[i].func(null);
				if (inGates[i].getName() == "T")
					temp[i] = true; // if the value is true
				else
					temp[i] = false; // if the value is false
			}
		return func(temp); // calculate the Gate outcome
	}

	//abstract methods we will implements in the sub classes
	protected abstract boolean func(boolean[] inValues) throws CircuitException;

	public abstract String getName();

	public abstract Gate simplify();

	public String toString() { //string of specific Gate
		String s;
		if (inGates == null) //no entering
			return this.getName();
		s = this.getName() + "[";
		for (int i = 0; i < inGates.length; i++) {
			if (inGates[i] != null)
				s += inGates[i].toString();
			if (i != inGates.length - 1)
				s += ", ";
		}
		s += "]";
		return s;
	}

}

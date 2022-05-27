package circuits;

public class AndGate extends Gate { // sub class of Gate

	public AndGate(Gate[] inGates) { // Contractor
		super(inGates);
	}

	protected boolean func(boolean[] inValues) throws CircuitException { // checking if there is one false - return
																			// false
		for (int i = 0; i < inValues.length; i++) {
			if (inValues[i] == false)
				return false;
		}
		return true; // else- true

	}

	public String getName() {
		return "AND";
	}

	public Gate simplify() { // simplify of AndGate
		int cnt = 0, j = 0; // cnt for checking how many Gates not false in the array
		Gate[] ansGates;
		Gate and;
		for (int i = 0; i < inGates.length; i++) // if we have false - return false
			if (inGates[i].simplify() instanceof FalseGate)
				return FalseGate.instance();
		for (int i = 0; i < inGates.length; i++) // increase counter
			if (!(inGates[i].simplify() instanceof TrueGate))
				cnt++;
		if (cnt == 1) { // case 1: return this simplify
			for (int i = 0; i < inGates.length; i++)
				if (!(inGates[i].simplify() instanceof TrueGate))
					return inGates[i].simplify();
		}
		if (cnt > 1) { // case >1 : creating new AndGate with simplify Gates
			ansGates = new Gate[cnt];
			for (int i = 0; i < inGates.length; i++)
				if (!(inGates[i].simplify() instanceof TrueGate)) {
					ansGates[j] = inGates[i].simplify();
					j++;
				}
			and = new AndGate(ansGates);
			return and; // the new array AndGate
		}
		return TrueGate.instance();
	}

}

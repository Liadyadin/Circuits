package circuits;

public class OrGate extends Gate { // sub class of Gate

	public OrGate(Gate[] inGates) { // constructor
		super(inGates);
	}

	protected boolean func(boolean[] inValues) throws CircuitException {// checking if there is two false - return false
		boolean temp = false;
		for (int i = 0; i < inValues.length - 1; i++) {
			if (inValues[i] == false && inValues[i + 1] == false)
				temp = false;
			else
				temp = true; // else true
		}
		if (inValues[inValues.length - 1] == false && temp == false) // for last index in the array
			return false;
		return true;

	}

	public String getName() {
		return "OR";
	}

	public Gate simplify() { // simplify of AndGate
		int cnt = 0, j = 0; // cnt for checking how many Gates not true in the array
		Gate[] ansGates;
		Gate or;
		for (int i = 0; i < inGates.length; i++)// if we have true - return true
			if (inGates[i].simplify() instanceof TrueGate)
				return TrueGate.instance();
		for (int i = 0; i < inGates.length; i++)// increase counter
			if (!(inGates[i].simplify() instanceof FalseGate))
				cnt++;
		if (cnt == 1) {// case 1: return this simplify
			for (int i = 0; i < inGates.length; i++)
				if (!(inGates[i].simplify() instanceof FalseGate))
					return inGates[i].simplify();
		}
		if (cnt > 1) {// case >1 : creating new AndGate with simplify Gates
			ansGates = new Gate[cnt];
			for (int i = 0; i < inGates.length; i++)
				if (!(inGates[i].simplify() instanceof FalseGate)) {
					ansGates[j] = inGates[i].simplify();
					j++;
				}
			or = new OrGate(ansGates);
			return or;// the new array OrGate
		}
		return FalseGate.instance();
	}

}

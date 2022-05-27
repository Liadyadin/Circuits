package circuits;

public class NotGate extends Gate { //subclass of Gate
	private int cnt = 0; //for count in simplify function - for checking how many NotGate we have

	public NotGate(Gate in) { //constructor
		super(new Gate[] { in });
	}

	protected boolean func(boolean[] inValues) throws CircuitException {
		if (inValues[0] == true) 
			return false;
		return true;
	}

	public String getName() {
		return "NOT";
	}

	public Gate simplify() { //Not simplify
		if (inGates[0] instanceof TrueGate) //if true - return false
			return FalseGate.instance();
		else if (inGates[0] instanceof FalseGate) //if false- return true
			return TrueGate.instance();
		else if (this instanceof NotGate) { //increase counter(for NotGate)
			cnt++;
			if (inGates[0] instanceof VarGate) ;//if we have varGate inside - return this VarGate
				return this;
		} else if (inGates[0] instanceof VarGate) { //checking VarGate inside (how mant NotGate were befor)
			if (cnt % 2 == 0)
				return inGates[0].simplify();
			else
				return new NotGate(inGates[0]);
		}
		return inGates[0].inGates[0].simplify();

	}
}

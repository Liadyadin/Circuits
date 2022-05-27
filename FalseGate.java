package circuits;

public class FalseGate extends Gate { //sub class of Gate
	private static FalseGate instance; //specific instance of FalseGate - false only

	private FalseGate(Gate[] inGates) {
		super(inGates);
	}

	public static Gate instance() { //specific instance
		if (instance == null)
			instance = new FalseGate(null);
		return instance;
	}

	protected boolean func(boolean[] inValues) throws CircuitException {
		return false;
	} 

	public String getName() {
		return "F";
	}

	public Gate simplify() {//this=false
		return this;
	}

}

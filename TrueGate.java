package circuits;

public class TrueGate extends Gate { //Sub class of Gate
	private static TrueGate instance; //specific instance of TrueGate - true only

	private TrueGate(Gate[]inGates) { //Contractor
		super(inGates);
	}

	public static Gate instance() { //specific instance
		if (instance == null)
			instance = new TrueGate(null);
		return instance;
	}

	protected boolean func(boolean[] inValues) throws CircuitException { 
		return true;
	} 

	public String getName(){
		return "T";
	}

	public Gate simplify() { //this=true
		return this;
	}

}

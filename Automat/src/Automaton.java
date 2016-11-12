import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Automaton {

	private Alphabet alphabet;
	private TreeSet<State> states;
	private String startStateLabel;
	private Set<String> endStateLabels;

	private String curStateLabel;

	private Set<StateChangeListener> listeners;

	public Automaton(Alphabet alphabet, TreeSet<State> states, String startStateLabel, Set<String> endStateLabels) {
		this.alphabet = alphabet;
		this.states = states;
		this.startStateLabel = startStateLabel;
		this.endStateLabels = endStateLabels;
		curStateLabel = "";
		listeners = new HashSet<>();
	}

	public Automaton() {
		this(new Alphabet(), new TreeSet<>(), "", new HashSet<>());
	}

	public Alphabet getAlphabet() {
		return alphabet;
	}

	public void addState(State state) {
		states.add(state);
	}

	public TreeSet<State> getStates() {
		return states;
	}

	public void setStartState(String label) {
		if (!states.contains(new State(label)))
			throw new InvalidParameterException("startState must be in states");
		startStateLabel = label;
	}

	public void addEndState(String label) {
		if (!states.contains(new State(label)))
			throw new InvalidParameterException("endState must be one of the states");
		endStateLabels.add(label);
	}

	public void registerListener(StateChangeListener listener) {
		listeners.add(listener);
	}

	public void unregisterListener(StateChangeListener listener) {
		listeners.remove(listener);
	}

	public boolean processWord(String word) {
		if (startStateLabel == "")
			throw new RuntimeException("startState must be set");

		char[] c = word.toCharArray();
		curStateLabel = startStateLabel;
		for (char d : c) {
			State curState = findState(curStateLabel);
			String nextState = curState.getNextStateFor(d);
			if (nextState == null)
				return false; // throw new RuntimeException("the dea isn't
								// complete"); Just assuming it should end in a
								// Trap
			curStateLabel = nextState;
			for (StateChangeListener list : listeners) {
				list.onStateChange(curState.getLabel(), d, curStateLabel);
			}
		}
		if (endStateLabels.contains(curStateLabel))
			return true;
		return false;
	}

	private State findState(String label) {
		for (Iterator<State> iterator = states.iterator(); iterator.hasNext();) {
			State next = iterator.next();
			if (next.getLabel().equals(label))
				return next;
		}
		return null;
	}

}

package Automaton;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class State implements Comparable<State> {
	private String label;
	// private Map<List<Character>, String> transitions;
	private Map<String, List<Character>> transitions;

	// public State(String label, Map<List<Character>, String> transitions) {
	public State(String label, Map<String, List<Character>> transitions) {
		if (label.isEmpty())
			throw new InvalidParameterException("Label should not be an empty String");
		this.label = label;
		this.transitions = transitions;
	}

	public State(String label) {
		this(label, new HashMap<>());
	}

	public String getLabel() {
		return label;
	}

	public void addTransition(String nextState, List<Character> characters) {
		if (transitions.containsKey(nextState)) {
			transitions.get(nextState).addAll(characters);
		} else {
			transitions.put(nextState, characters);
		}
	}

	public String getNextStateFor(Character character) {
		/*
		 * for (Entry<List<Character>, String> entry : transitions.entrySet()) {
		 * if(entry.getKey().contains(character)) return entry.getValue(); }
		 */
		for (Entry<String, List<Character>> entry : transitions.entrySet()) {
			if (entry.getValue().contains(character))
				return entry.getKey();
		}
		return null;
	}

	@Override
	public int compareTo(State o) {
		return label.compareTo(o.getLabel());
	}

}

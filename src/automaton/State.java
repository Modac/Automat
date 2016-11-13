package automaton;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class State implements Comparable<State> {
	private String label;
	// private Map<List<Character>, String> transitions;
	private Map<String, List<AlphabetItem>> transitions;

	// public State(String label, Map<List<Character>, String> transitions) {
	public State(String label, Map<String, List<AlphabetItem>> transitions) {
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

	public void addTransition(String nextState, List<AlphabetItem> characters) {
		if (transitions.containsKey(nextState)) {
			transitions.get(nextState).addAll(characters);
		} else {
			transitions.put(nextState, characters);
		}
	}

	public String getNextStateFor(AlphabetItem alphabetItem) {
		/*
		 * for (Entry<List<Character>, String> entry : transitions.entrySet()) {
		 * if(entry.getKey().contains(character)) return entry.getValue(); }
		 */
		for (Entry<String, List<AlphabetItem>> entry : transitions.entrySet()) {
			if (entry.getValue().contains(alphabetItem))
				return entry.getKey();
		}
		return null;
	}

	public Map<String, List<AlphabetItem>> getTransitions() {
		return transitions;
	}

	@Override
	public int compareTo(State o) {
		return label.compareTo(o.getLabel());
	}

}

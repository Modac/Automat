package automaton;

@FunctionalInterface
public interface StateChangeListener {

	public void onStateChange(String prevStateLabel, AlphabetItem alphabetItem, String newStateLabel);

}

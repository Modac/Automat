@FunctionalInterface
public interface StateChangeListener {

	public void onStateChange(String prevStateLabel, Character transitionChar, String newStateLabel);

}

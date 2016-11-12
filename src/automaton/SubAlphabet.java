package automaton;

import java.util.List;

public class SubAlphabet implements AlphabetElement {

	private String label;
	private List<AlphabetItem> alphabetItems;

	@Override
	public boolean isSingle() {
		return false;
	}

}

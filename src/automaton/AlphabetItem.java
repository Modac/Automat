package automaton;

public class AlphabetItem {

	String item;

	public AlphabetItem(String item) {
		this.item = item;
	}

	public AlphabetItem(char item) {
		this(((Character) item).toString());
	}

	public String getItem() {
		return item;
	}

	public int getLength() {
		return item.length();
	}

	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj))
			return true;

		if (obj instanceof AlphabetItem) {
			return getItem().equals(((AlphabetItem) obj).getItem());
		}

		return false;
	}
}

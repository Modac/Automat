package automaton;

import java.security.InvalidParameterException;

public class Range {
	private int startIndex;
	private int endIndex;

	public Range(int startIndex, int endIndex) {
		if (startIndex > endIndex)
			throw new InvalidParameterException("startIndex should not exceed endIndex");
		this.startIndex = startIndex;
		this.endIndex = endIndex;
	}

	public boolean withinBounds(int maxIndex) {
		return endIndex <= maxIndex;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

}

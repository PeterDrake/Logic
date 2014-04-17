package edu.lclark.logic;

/**
 * A box that potentially contains a String. This can be passed into a method
 * which can then leave a message "in the box". The calling method can inspect
 * the box afterward.
 */
public class StringBox {

	private String contents;

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	/**
	 * Returns true if the contents of this box have been set to something other
	 * than null.
	 */
	public boolean isSet() {
		return contents != null;
	}

}

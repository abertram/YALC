/**
 * 
 */
package de.fhw.swp.yalc;

import java.net.URL;
import java.util.Vector;

/**
 * Klasse fuer die Speicherung der URL-Details.
 * 
 * @author Alex
 */
public class URLDetailsVector extends Vector<URLDetails> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -569361383180250712L;
	
	/**
	 * Gibt zurueck, ob die uebergebenen URL-Details bereits gespeichert sind.
	 * 
	 * @param ud URL-Details.
	 * @return true, wenn die URL-Details beriets gespeichert sind.
	 */
	public boolean contains(URLDetails ud) {
		return contains(ud.getURL());
	}
	
	/**
	 * Gibt zurueck, ob die uebergebene URL bereits gespeichert ist.
	 * 
	 * @param url URL.
	 * @return true, wenn URL bereits gespeichert ist.
	 */
	public boolean contains(String url) {
		for (int i = 0; i < size(); i++)
			if (get(i).getURL().equals(url))
				return true;
		return false;
	}
	
	/**
	 * Liefert den Index einer gespeicherten URL.
	 * 
	 * @param url URL.
	 * @return Index der gespeicherten URL.
	 */
	public int indexOf(URL url) {
	    for (int i = 0 ; i < elementCount ; i++)
	    	if (((URLDetails) elementData[i]).getURL().equals(url.toString()))
	    		return i;
		return -1;
	}
	
	/**
	 * Zaelt die kaputten URLs.
	 * 
	 * @return Anzahl der kaputten URLs.
	 */
	public int sizeOfBrokenURLs() {
		int result = 0;
		for (int i = 0; i < size() && get(i).getStatusCode() >= 400; i++)
			result++;
		return result;
	}
}

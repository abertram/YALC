package de.fhw.swp.yalc;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.net.URL;

/**
 * Klasse zum Speichern aller vom Crawler gefundenen URLs. <br>
 * Speicherung mittels Hashmap, wobei der Key der jeweiligen Basis-URL
 * entspricht. Als Value wird ein HashSet verwendet, das alle auf der jeweiligen
 * URL gefundenen Content-URLs enthaelt.
 * 
 * @author Ruediger Klante
 * @author Alexander Bertram
 */
public class URLContainer {
	/**
	 * Speicherung der Basis-URLs und der darauf gefundenen URLs.
	 */
	private AbstractMap<URL, Set<URL>> urlMap;

	/**
	 * Konstruktor.
	 */
	public URLContainer() {
		urlMap = new HashMap<URL, Set<URL>>();
	}

	/**
	 * Getter-Methode fuer alle gefundenen Basis-URLs als Array (die Keys der
	 * urlMap).
	 * 
	 * @return Array mit Base-URLs.
	 */
	public URL[] getBaseURLsAsArray() {
		URL[] ua = new URL[urlMap.keySet().size()];
		return urlMap.keySet().toArray(ua);
	}

	/**
	 * Getter-Methode fuer alle zu einer BasisURL gehoerigen ContentURLs als
	 * Array.
	 * 
	 * @param baseURL
	 *            BasisURL, zu der die ContentURLs gesucht werden.
	 * @return Array mit ContentURLs.
	 */
	public URL[] getContentURLsAsArray(URL baseURL) {
		URL[] ua = new URL[urlMap.get(baseURL).size()];
		return urlMap.get(baseURL).toArray(ua);
	}

	/**
	 * Hinzufuegen einer Content-URL zu einer Base-URL.
	 * 
	 * @param baseURL
	 *            Basis-URL, zu der die URL gehoert.
	 * @param url
	 *            Content-URL, die hinzugefuegt wird.
	 * @param addURLAsBase
	 *            Flag, ob die URL gleich als Basis-URL hinzugefuegt werden
	 *            soll.
	 * 
	 * @return this.
	 */
	public URLContainer addContentURL(URL baseURL, URL url, boolean addURLAsBase) {
		Set<URL> set = new HashSet<URL>();
		if (urlMap.containsKey(baseURL))
			set = urlMap.get(baseURL);
		set.add(url);
		urlMap.put(baseURL, set);
		if (addURLAsBase && !urlMap.containsKey(url)) {
			set = new HashSet<URL>();
			urlMap.put(url, set);
		}
		return this;
	}

	/**
	 * Ueberpruefen, ob eine URL als Basis-URL vorhanden ist.
	 * 
	 * @param url
	 *            Zu pruefende URL.
	 * @return True, wenn vorhanden.
	 */
	public boolean containsContentURL(URL url) {
		return urlMap.containsKey(url);
	}

	/**
	 * Stringrepraesentation des URL-Containers.
	 * 
	 * @return Stringrepraesentation des URL-Containers.
	 */
	public String toString() {
		URL[] baseURLArray = getBaseURLsAsArray();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < baseURLArray.length; i++) {
			sb.append(String.format("baseURL: %s\n%s\n\n", baseURLArray[i],
					urlMap.get(baseURLArray[i])));
		}
		return sb.toString();
	}

	/**
	 * Liefert alle Basis-URLs einer URL.
	 * 
	 * @param url
	 *            URL, deren Basis-URLs gesucht werden.
	 * @return Basis-URLs der uebergebenen URL.
	 */
	public URL[] getBaseURLs(URL url) {
		if (url == null)
			return null;
		List<URL> resultList = new Vector<URL>();
		URL[] ua = getBaseURLsAsArray();
		for (int i = 0; i < ua.length; i++) {
			if (ua[i] != null && urlMap.get(ua[i]).contains(url))
				resultList.add(ua[i]);
		}
		ua = new URL[resultList.size()];
		return resultList.toArray(ua);
	}
}

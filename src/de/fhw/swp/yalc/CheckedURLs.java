package de.fhw.swp.yalc;

import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * Speichert die Details der ueberprueften URLs, die ignorierten und die nicht
 * gecheckten URLs.
 * 
 * @author Ruediger Klante
 * @author Alexander Bertram
 */
public class CheckedURLs {
	/**
	 * URLs, deren Inhalt ueberprueft wurde.
	 */
	private URLDetailsVector contentURLsDetails;

	/**
	 * URLs, die auf Existenz ueberprueft wurden.
	 */
	private URLDetailsVector existURLsDetails;

	/**
	 * URLs, die ignoriert wurden.
	 */
	private Set<URL> ignoreURLs;

	/**
	 * URLs, die nicht gecheckt werden konnten.
	 */
	private Set<URL> uncheckedURLs;

	/**
	 * Konstruktor.
	 */
	public CheckedURLs() {
		contentURLsDetails = new URLDetailsVector();
		existURLsDetails = new URLDetailsVector();
		ignoreURLs = new HashSet<URL>();
		uncheckedURLs = new HashSet<URL>();
	}

	/**
	 * Liefert die Content-URLs als Array zurueck.
	 * 
	 * @return URLs, deren Inhalt ueberprueft wurde, als Array.
	 */
	public URLDetails[] getContentURLsDetailsAsArray() {
		URLDetails[] ua = new URLDetails[contentURLsDetails.size()];
		return contentURLsDetails.toArray(ua);
	}

	/**
	 * Liefert die Exist-URLs als Array zurueck.
	 * 
	 * @return URLs, die auf Existenz ueberpueft wurden, als Array.
	 */
	public URLDetails[] getExistURLsAsArray() {
		URLDetails[] ua = new URLDetails[existURLsDetails.size()];
		return existURLsDetails.toArray(ua);
	}

	/**
	 * Fuegt Details einer Content-URL hinzu.
	 * 
	 * @param urlDetails
	 *            Details der URL.
	 * @return this.
	 */
	public CheckedURLs addContentURLDetails(URLDetails urlDetails) {
		return addURLDetails(contentURLsDetails, urlDetails);
	}

	/**
	 * Fuegt Details einer Exist-URL hinzu.
	 * 
	 * @param urlDetails
	 *            Details der URL.
	 * @return this.
	 */
	public CheckedURLs addExistURLDetails(URLDetails urlDetails) {
		return addURLDetails(existURLsDetails, urlDetails);
	}

	/**
	 * Fuegt URL-Details in den uebergebenen Vector ein.
	 * 
	 * @param udv Vector, in den die Details eingefuegt werden sollen.
	 * @param ud URL-Details.
	 * @return this.
	 */
	private CheckedURLs addURLDetails(URLDetailsVector udv, URLDetails ud) {
		if (ud == null)
			return this;
		if (!udv.contains(ud)) {
			// URL-Details mit kaputten Links vorne einfuegen
			if (ud.getStatusCode() >= 400)
				udv.add(0, ud);
			else
				udv.add(ud);
		}
		return this;
	}

	/**
	 * Fuegt einer bereits gecheckten URL weitere Basis-URL(s) hinzu.
	 * 
	 * @param url
	 *            URL, deren Basis-URLs vervollstaendigt werden sollen.
	 * @param baseURLs
	 *            Basis-URLs.
	 */
	public void addBaseURLsToURLDetails(URL url, URL[] baseURLs) {
		if (baseURLs == null)
			return;
		// Content-URLs verarbeiten
		addBaseURLsToURLDetails(contentURLsDetails, url, baseURLs);
		// Exist-URLs verarbeiten
		addBaseURLsToURLDetails(existURLsDetails, url, baseURLs);
	}

	/**
	 * Fuegt einer bereits gecheckten URL weitere Basis-URL(s) hinzu.
	 * 
	 * @param udv Vector mit den gespeicherten URL-Details.
	 * @param url URL.
	 * @param baseURLs Basis-URLs.
	 */
	private void addBaseURLsToURLDetails(URLDetailsVector udv, URL url, URL[] baseURLs) {
		for (int i = 0; i < udv.size(); i++) {
			if (udv.get(i).getURL().equals(url.toString())) {
				for (int j = 0; j < baseURLs.length; j++)
					udv.get(i).addBaseURL(baseURLs[j]);
				return;
			}
		}
	}
	
	/**
	 * Stringrepraesentation der Klasse.
	 * 
	 * @return Stringrepraesentation der Klasse.
	 */
	public String toString() {
		URLDetails[] uda = getContentURLsDetailsAsArray();
		StringBuilder sb = new StringBuilder();
		sb.append("Content-URLs:\n\n");
		for (int i = 0; i < contentURLsDetails.size(); i++)
			sb.append(uda[i]);
		uda = getExistURLsAsArray();
		sb.append("Exist-URLs:\n\n");
		for (int i = 0; i < existURLsDetails.size(); i++)
			sb.append(uda[i]);
		return sb.toString();
	}

	/**
	 * Gibt die Details einer URL zurueck.
	 * 
	 * @param url
	 *            URL, deren Details gesucht werden.
	 * @return Details der gesuchten URL.
	 */
	public URLDetails getURLDetails(URL url) {
		return contentURLsDetails.contains(url.toString()) ? contentURLsDetails
				.get(contentURLsDetails.indexOf(url)) : existURLsDetails
				.contains(url.toString()) ? existURLsDetails
				.get(existURLsDetails.indexOf(url)) : null;
	}

	/**
	 * Fuegt eine URL in die Liste der ignorierten URLs ein.
	 * 
	 * @param url
	 *            URL, die eingefuegt werden soll.
	 * 
	 * @return this.
	 */
	public CheckedURLs addIgnoreURL(URL url) {
		ignoreURLs.add(url);
		return this;
	}

	/**
	 * Gibt die Liste der ignorierten URLs als Array zurueck.
	 * 
	 * @return Array mit den ignorierten URLs.
	 */
	public URL[] getIgnoreURLsAsArray() {
		URL[] ua = new URL[ignoreURLs.size()];
		return ignoreURLs.toArray(ua);
	}

	/**
	 * Fuegt eine URL in die Liste der nicht gecheckten URLs ein.
	 * 
	 * @param url
	 *            URL, die eingefuegt werden soll.
	 * 
	 * @return this.
	 */
	public CheckedURLs addUncheckedURL(URL url) {
		if (url != null)
			uncheckedURLs.add(url);
		return this;
	}

	/**
	 * Liefert ungecheckte URLs als Array zurueck.
	 * 
	 * @return Array mit den ungecheckten URLs.
	 */
	public URL[] getUncheckedURLsAsArray() {
		URL[] ua = new URL[uncheckedURLs.size()];
		return uncheckedURLs.toArray(ua);
	}

	/**
	 * Liefert die Content-URLs-Details zurueck.
	 * 
	 * @return Content-URLs-Details.
	 */
	public URLDetailsVector getContentURLsDetails() {
		return contentURLsDetails;
	}

	/**
	 * Liefert die Exist-URLs-Details zurueck.

	 * @return Exist-URLs-Details.
	 */
	public URLDetailsVector getExistURLsDetails() {
		return existURLsDetails;
	}

	/**
	 * Liefert die Ignore-URLs zurueck.
	 * 
	 * @return Ignore-URLs.
	 */
	public Set<URL> getIgnoreURLs() {
		return ignoreURLs;
	}

	/**
	 * Liefert die Uncheckes-URLs zurueck.
	 * 
	 * @return Unchecked-URLs.
	 */
	public Set<URL> getUncheckedURLs() {
		return uncheckedURLs;
	}
}

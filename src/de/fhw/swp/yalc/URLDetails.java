/**
 * 
 */
package de.fhw.swp.yalc;

import java.net.URL;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Klasse zum Abspeichern der Details einer URL.
 * 
 * @author Ruediger Klante
 * @author Alexander Bertram
 */
public class URLDetails {
	/**
	 * URL
	 */
	private String url;

	/**
	 * Content-Type
	 */
	private String contentType;

	/**
	 * Content-Length
	 */
	private Integer contentLength;

	/**
	 * Last-Modified
	 */
	private Date lastModified;

	/**
	 * Status-Code
	 */
	private Integer statusCode;

	/**
	 * Status-Message
	 */
	private String statusMessage;

	/**
	 * Basis-URLs
	 */
	private Set<URL> baseURLs;

	/**
	 * Konstruktor
	 * 
	 * @param url
	 *            URL
	 * @param contentType
	 *            Content-Type
	 * @param contentLength
	 *            Content-Length
	 * @param lastModified
	 *            Last-Modified
	 * @param statusCode
	 *            Status-Code
	 * @param statusMessage
	 *            Status-Message
	 * @param baseURLs
	 *            Basis-URLs
	 */
	public URLDetails(String url, String contentType, Integer contentLength,
			Date lastModified, Integer statusCode, String statusMessage,
			URL[] baseURLs) {
		this.url = url;
		this.contentType = contentType;
		this.contentLength = contentLength;
		this.lastModified = lastModified;
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		this.baseURLs = new HashSet<URL>();
		for (int i = 0; i < baseURLs.length; i++)
			this.baseURLs.add(baseURLs[i]);
	}

	/**
	 * Stringrepraesentation der Klasse.
	 * 
	 * @return Stringrepraesentation der Klasse.
	 */
	public String toString() {
		return String.format("" + "url:            %s\n"
				+ "content-type:   %s\n" + "content-length: %d\n"
				+ "last-modified:  %tD\n" + "status-code:    %d\n"
				+ "status-message: %s\n" + "base-url:       %s\n\n", url,
				contentType, contentLength, lastModified, statusCode,
				statusMessage, baseURLs.toString());
	}

	/**
	 * Fuegt eine Basis-URL den Details hinzu.
	 * 
	 * @param url
	 *            hinzuzufuegende baseURL
	 */
	public void addBaseURL(URL url) {
		baseURLs.add(url);
	}

	/**
	 * Gibt den Status-Code zurueck.
	 * 
	 * @return Status-Code.
	 */
	public Integer getStatusCode() {
		return statusCode;
	}

	/**
	 * Gibt die Basis-URLs zurueck.
	 * 
	 * @return Basis-URLs.
	 */
	public Set<URL> getBaseURLs() {
		return baseURLs;
	}

	/**
	 * Gibt die Groesse des Inhaltes der URL zurueck.
	 * 
	 * @return Die Groesse des Inhaltes.
	 */
	public Integer getContentLength() {
		return contentLength;
	}

	/**
	 * Gibt den Content-Typ zurueck.
	 * 
	 * @return Content-Typ.
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * Gibt das Datum der letzten Aenderung zurueck.
	 * 
	 * @return Datum der letzten Aenderung.
	 */
	public Date getLastModified() {
		return lastModified;
	}

	/**
	 * Gibt die Status-Meldung zurueck.
	 * 
	 * @return Status-Meldung.
	 */
	public String getStatusMessage() {
		return statusMessage;
	}

	/**
	 * Gibt die URL zurueck.
	 * 
	 * @return URL.
	 */
	public String getURL() {
		return url;
	}

	/**
	 * Gibt die Basis-URLs als Array zurueck.
	 * 
	 * @return Basis-URLs als Array.
	 */
	public URL[] getBaseURLsAsArray() {
		URL[] ua = new URL[baseURLs.size()];
		return baseURLs.toArray(ua);
	}

	/**
	 * Gibt je nach Status die Klasse der gecheckten URL zurueck. <br>
	 * Noetig fuer die css-Formatierung.
	 * 
	 * @return Die Klasse der URL.
	 */
	public String getStatusClass() {
		if (statusCode >= 100 && statusCode < 200)
			return "class1xx";
		if (statusCode >= 200 && statusCode < 300)
			return "class2xx";
		if (statusCode >= 300 && statusCode < 400)
			return "class3xx";
		if (statusCode >= 400 && statusCode < 500)
			return "class4xx";
		if (statusCode >= 500 && statusCode < 600)
			return "class5xx";
		return "";
	}
}

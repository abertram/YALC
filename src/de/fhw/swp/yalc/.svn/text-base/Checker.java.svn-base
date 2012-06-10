/**
 * 
 */
package de.fhw.swp.yalc;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

/**
 * Sucht URLs in den Webseiten aus dem URLContainer und holt sich je nach
 * Kategorie der URL die Details vom Server.
 * 
 * @author Ruediger Klante
 * @author Alexander Bertram
 */
public class Checker {
	/**
	 * Attribute, nach den gesucht wird.
	 */
	private static final String[] attributes = new String[] { "href",
			"background", "cite", "src", "action", "data", "codebase" };

	/**
	 * URLContainer mit den zu durchsuchenden Seiten.
	 */
	private URLContainer urlContainer;

	/**
	 * Hier werden die Detials der URLs abgelegt.
	 */
	private CheckedURLs checkedURLs = new CheckedURLs();

	/**
	 * Vom User angegebenen Optionen.
	 */
	private Options options;

	/**
	 * Liste der bereits durchsuchten URLs.
	 */
	private List<URL> checkedURLsList = new ArrayList<URL>();

	/**
	 * Filter fuer den Parser.
	 */
	private NodeFilter nodeFilter = buildAttributesFilter(attributes);

	/**
	 * Bereits geparste URLs.
	 */
	private Set<URL> parsedURLs = new HashSet<URL>();

	/**
	 * Konstruktor.
	 * 
	 * @param urlContainer
	 *            URLContainer mit den zu durchsuchenden Seiten.
	 * @param options
	 *            Vom User eingegebene Optionen.
	 */
	public Checker(URLContainer urlContainer, Options options) {
		this.urlContainer = urlContainer;
		this.options = options;
	}

	/**
	 * Checkt die Webseiten aus dem Crawler.
	 * 
	 * @return Datenstruktur mit den Details der URLs.
	 */
	public CheckedURLs check() {
		// URLs, die durchsucht werden
		URL[] baseURLs = urlContainer.getBaseURLsAsArray();
		checkURLs(baseURLs);
		return checkedURLs;
	}

	/**
	 * Checkt die URLs.
	 * 
	 * @param urls
	 *            URLs, die gecheckt werden sollen.
	 */
	public void checkURLs(URL[] urls) {
		for (int i = 0; i < urls.length; i++) {
			// Start-URLs
			if (urls[i] == null)
				checkURLs(urlContainer.getContentURLsAsArray(urls[i]));
			// Details holen
			URLDetails ud = checkURL(urls[i], urlContainer.getBaseURLs(urls[i]));
			// Details vorhanden?
			if (ud == null)
				continue;
			// Details speichern
			checkedURLs.addContentURLDetails(ud);
			// Statuscode holen
			int sc = ud.getStatusCode();
			// Anfrage ok?
			if (sc >= 200 && sc <= 299) {
				try {
					if (parsedURLs.contains(urls[i]))
						continue;
					if (options.isVerbose())
						System.err.printf("Parser: %s\n", urls[i].toString());
					// Parser erstellen
					Parser parser = new Parser(urls[i].toString());
					parsedURLs.add(urls[i]);
					// Webseite nach den Attributen parsen
					NodeList nodeList = parser
							.extractAllNodesThatMatch(nodeFilter);
					// Liste der Tags verarbeiten
					processNodeList(nodeList, URLTools.getBaseURL(urls[i]));
				} catch (ParserException e) {
					// e.printStackTrace(System.err);
				}
			}
		}
	}

	/**
	 * Verarbeitet die vom Parser gelieferte Liste der Tags.
	 * 
	 * @param nodeList
	 *            Liste der gefundenen Tags.
	 * @param baseURL
	 *            URL auf der die Tags gefunden wurden.
	 */
	private void processNodeList(NodeList nodeList, URL baseURL) {
		// Schleife ueber die Liste der Tags
		for (int i = 0; i < nodeList.size(); i++) {
			// Schleife ueber die Attribute
			for (int j = 0; j < attributes.length; j++) {
				// Attribut auslesen
				String attributeValue = ((TagNode) nodeList.elementAt(i))
						.getAttribute(attributes[j]);
				// Attribut vorhanden?
				if (attributeValue == null || attributeValue.equals(""))
					continue;
				// absolute URL erstellen
				URL url = URLTools.getAbsoluteURL(baseURL, attributeValue);
				// zu ignorierende URL?
				if (URLTools.isIgnoreURL(url, options)) {
					checkedURLs.addIgnoreURL(url);
					continue;
				}
				
				// Content-URL?
				if (URLTools.isContentURL(url, options)
						&& urlContainer.containsContentURL(url))
					// Details holen und speichern
					checkedURLs.addContentURLDetails(checkURL(url, baseURL));
				// Exist-URL?
				if (URLTools.isExistURL(url, options))
					// Details holen und speichern
					checkedURLs.addExistURLDetails(checkURL(url, baseURL));
			}
		}
	}

	/**
	 * Holt die Details einer URL.
	 * 
	 * @param url
	 *            URL, deren Details geholt werden.
	 * @param baseURL
	 *            URL, auf der die URL gefunden wurde.
	 * 
	 * @return Details der uebergebenen URL.
	 */
	private URLDetails checkURL(URL url, URL baseURL) {
		URL[] baseURLs = new URL[1];
		baseURLs[0] = baseURL;
		return checkURL(url, baseURLs);
	}

	/**
	 * Holt die Details einer URL.
	 * 
	 * @param url
	 *            URL, deren Details geholt werden.
	 * @param baseURLs
	 *            URLs, auf der die URL gefunden wurde.
	 * 
	 * @return Details der uebergebenen URL.
	 */
	private URLDetails checkURL(URL url, URL[] baseURLs) {
		// URL null?
		if (url == null)
			return null;
		// # abschneiden
		url = URLTools.truncReference(url);
		// URL bereits gecheckt?
		if (checkedURLsList.contains(url)) {
			checkedURLs.addBaseURLsToURLDetails(url, baseURLs);
			return checkedURLs.getURLDetails(url);
		}
		if (options.isVerbose())
			System.err.printf("Checker: %s\n", url);
		// URL als gecheckt markieren
		checkedURLsList.add(url);
		try {
			// Verbindung herstellen
			HttpURLConnection huc = (HttpURLConnection) url.openConnection();
			// Details holen und zurueckgeben
			URLDetails ud = new URLDetails(url.toString(),
					huc.getContentType(), huc.getContentLength(), new Date(huc
							.getLastModified()), huc.getResponseCode(), huc
							.getResponseMessage(), baseURLs);
			return ud;
		} catch (ClassCastException e) {
			// URL konnte nicht gecheckt werden
			checkedURLs.addUncheckedURL(url);
			// e.printStackTrace(System.err);
		} catch (IOException e) {
			// e.printStackTrace(System.err);
		}
		return null;
	}

	/**
	 * Erstellt den Filter fuer den Parser.
	 * 
	 * @param attributes
	 *            Attribute, aus denen der Filter erstellt werden soll.
	 * 
	 * @return Filter, der die uebergebenen Attribute beinhaltet.
	 */
	private NodeFilter buildAttributesFilter(String[] attributes) {
		// Filter erstellen
		NodeFilter nf = new HasAttributeFilter(attributes[0]);
		for (int i = 1; i < attributes.length; i++)
			// Filter mit OR verknuepfen
			nf = new OrFilter(nf, new HasAttributeFilter(attributes[i]));
		return nf;
	}
}
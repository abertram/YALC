/**
 * 
 */
package de.fhw.swp.yalc;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Vector;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

/**
 * Durchsucht Webseiten nach Links, speichert gefundene Links, die
 * nach Pruefung durch Regulaere Ausdruecke weiter durchsucht werden sollen, in
 * einem URLContainer ab und durchsucht solche Links dann rekursiv weiter.
 * 
 * @author Ruediger Klante
 * @author Alexander Bertram
 */
public class Crawler {
	/**
	 * Vom User eingegebene Optionen.
	 */
	private Options options;

	/**
	 * ein URLContainer zur Speicherung gefundener URLs
	 */
	private URLContainer urlContainer;

	/**
	 * Konstruktor.
	 * 
	 * @param options
	 *            Vom User eingegebene Optionen.
	 */
	public Crawler(Options options) {
		this.options = options;
		this.urlContainer = new URLContainer();
	}

	/**
	 * Methode zum Starten des Crawlers, alle Start-URLs in der anfangs
	 * uebergebenen Liste werden abgearbeitet.
	 * 
	 * @return URLContainer mit den Basis- und den darauf gefundenen URLs.
	 */
	public URLContainer crawl() {
		for (int i = 0; i < options.getStartURLs().size(); i++) {
			try {
				URL url = new URL(options.getStartURLs().get(i));
				url = URLTools.getAbsoluteURL(url, options.getStartURLs()
						.get(i));
				urlContainer.addContentURL(null, URLTools.truncReference(url),
						true);
				findLink(url);
			} catch (MalformedURLException e) {
				System.err.println("Invalid start url.");
//				e.printStackTrace(System.err);
			}
		}
		return urlContainer;
	}

	/**
	 * Durchsucht eine angegebene Start-URL nach Links. Diese werden dann nach
	 * Ueberpruefung mittels regulaeren Ausdruecken einem URL-Container
	 * gespeichert, sofern sie als Content-URLs identifiziert wurden. Sollte eine
	 * URL rekursiv weiter durchsucht werden muessen, ruft sich die Methode
	 * selbst auf.
	 * 
	 * @param startURL
	 *            URL, auf der alle URLs geprueft werden sollen.
	 */
	private void findLink(URL startURL) {
		if (options.isVerbose())
			System.err.printf("Crawler: %s\n", startURL);
		
		// Basis-URL, noetig zur Vervollstaendigung relativer Links
		// URL baseURL = findBaseURL(startURL);
		URL baseURL = URLTools.getBaseURL(startURL);

		// Vektor, in den die gefundenen und ggf. komplettierten
		// URLs geschrieben werden
		List<URL> urlVec = new Vector<URL>();

		// Liste, in die extrahierte Tags geschrieben werden
		NodeList linkNodeList = null;
		// Erstellen des Filters fuer den Parser
		// Links
		NodeFilter filter = new NodeClassFilter(LinkTag.class);
		// Frames
		filter = new OrFilter(filter, new TagNameFilter("frame"));
		// Eingebettete Frames
		filter = new OrFilter(filter, new TagNameFilter("iframe"));
		try {
			// Parsen der aktuellen Seite
			Parser parser = new Parser(startURL.toString());
			// Extrahieren der Tags
			linkNodeList = parser.extractAllNodesThatMatch(filter);
		} catch (ParserException e) {
			urlContainer.addContentURL(startURL, null, false);
//			e.printStackTrace(System.err);
			return;
		}

		// Sollten Tags gefunden worden sein, wird die Liste
		// dieser in processNodeList() verarbeitet
		if (linkNodeList != null && linkNodeList.size() > 0)
			urlVec = processNodeList(linkNodeList, baseURL, startURL);

		for (int i = 0; i < urlVec.size(); i++) {
	
			// Content-URL?
			if (URLTools.isContentURL(urlVec.get(i), options)) {
				// URL schon im Container?
				if (!urlContainer.containsContentURL(urlVec.get(i))) {
					try {
						// dafuer sorgen, dass nur html geparst wird
						URLConnection uc = urlVec.get(i).openConnection();
						boolean isHtmlFile = uc != null
								&& uc.getContentType() != null
								&& uc.getContentType().contains("text/html");
						if (!isHtmlFile)
							continue;
						urlContainer.addContentURL(startURL, urlVec.get(i),
								isHtmlFile);
						findLink(urlVec.get(i));
						// kein http-Link
					} catch (ClassCastException e) {
//						e.printStackTrace(System.err);
						// URL konnte nicht gecheckt werden
					} catch (IOException e) {
//						e.printStackTrace(System.err);
					}
				} else
					urlContainer.addContentURL(startURL, urlVec.get(i), false);
			}
		}
	}

	/**
	 * Verarbeitung der geparseten Tags, Aussortieren nicht benoetigter Links
	 * Komplettieren relativer Links
	 * 
	 * @param list
	 *            Liste mit vom Parser extrahierten Nodes
	 * @param baseURL
	 *            aktuelle Basis-URL
	 * @param startURL
	 *            aktuell durchsuchte URL
	 * @return Vektor mit verarbeiteten URLs
	 */
	private List<URL> processNodeList(NodeList list, URL baseURL, URL startURL) {
		// Vektor, in den die gefundenen URLs eingeschrieben werden
		List<URL> urlVec = new Vector<URL>();
		// Schleife ueber die gefundenen Tags
		for (int i = 0; i < list.size(); i++) {
			// Extrahieren des href-Attributs
			String link = ((TagNode) list.elementAt(i)).getAttribute("href");
			if (link == null)
				link = ((TagNode) list.elementAt(i)).getAttribute("src");
			if (link != null && !link.equals("")) {
				URL url = URLTools.getAbsoluteURL(baseURL, link.trim());
				url = URLTools.truncReference(url);
				// URL merken
				if (url != null)
					urlVec.add(url);
			}
		}
		return urlVec;
	}

	/**
	 * Gibt den URL-Container zurueck.
	 * 
	 * @return URLContainer.
	 */
	public URLContainer getURLContainer() {
		return urlContainer;
	}
}

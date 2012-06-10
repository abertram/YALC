package de.fhw.swp.yalc;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

/**
 * Klasse zum Manipulieren von URLs.
 * 
 * @author Ruediger Klante
 * @author Alexander Bertram
 */
public class URLTools {
	/**
	 * Erstellt aus einer URL und der dazugehoerigen Basis-URL eine absolute
	 * URL.
	 * 
	 * @param baseURL
	 *            Basis-URL.
	 * @param url
	 *            URL.
	 * @return Absolute URL.
	 * 
	 */
	public static URL getAbsoluteURL(URL baseURL, String url) {
		if (baseURL == null || url == null)
			return null;
		URL resultURL = null;
		try {
			// URI aus der url erstellen
			URI uri = new URI(url).normalize();
			try {
				// URL ist sowas wie mailto:... oder news:...
				if (uri.isOpaque())
					return null;
				// URL absolut?
				resultURL = uri.toURL();
			} catch (MalformedURLException e) {
				// e.printStackTrace(System.err);
			}
			// URL ist nicht absolut
			catch (IllegalArgumentException e) {
				// e.printStackTrace(System.err);
				try {
					// 
					if (!baseURL.getPath().contains(".")
							&& !baseURL.toString().endsWith("/"))
						baseURL = new URL(baseURL.toString() + "/");
					// URL von URI aufloesen lassen
					resultURL = baseURL.toURI().resolve(uri).toURL();
				} catch (MalformedURLException e1) {
					// e1.printStackTrace(System.err);
				}
			}
		} catch (URISyntaxException e) {
			// e.printStackTrace(System.err);
		}
		if (resultURL != null && !resultURL.getPath().contains(".")
				&& !resultURL.toString().endsWith("/")) {
			try {
				resultURL = new URL(resultURL.toString() + "/");
			} catch (MalformedURLException e) {
				// e.printStackTrace(System.err);
			}
		}
		return resultURL;
	}

	/**
	 * Findet die Basis-URL zu einer URL.
	 * 
	 * @param url
	 *            URL, von der die Basis gefunden werden soll.
	 * @return Basis-URL zu fer uebergebenen URL.
	 */
	public static URL getBaseURL(URL url) {
		try {
			// Parser erstellen
			Parser parser = new Parser(url.toString(), Parser.DEVNULL);
			// Webseite nach dem Tag "base" parsen
			NodeList nodeList = parser
					.extractAllNodesThatMatch(new TagNameFilter("base"));
			// Wenn ein Base-Tag gefunden wurde, wird die URL aus dem Attribut
			// "href" ausgelesen
			if (nodeList.elementAt(0) != null) {
				String attributeValue = ((TagNode) nodeList.elementAt(0))
						.getAttribute("href");
				if (attributeValue != null && attributeValue.length() > 0)
					try {
						return new URL(attributeValue);
					} catch (MalformedURLException e) {
						// e.printStackTrace(System.err);
					}
			} else
				// Wenn kein Base-Tag gefunden wurde, wird die aktuell
				// durchsuchte URL als Basis verwendet
				return url;
		} catch (ParserException e) {
			// e.printStackTrace(System.err);
		}
		return null;
	}

	/**
	 * Schneidet die Referenz (#) einer URL ab.
	 * 
	 * @param url
	 *            URL, deren Referenz abgeschnitten werden soll.
	 * 
	 * @return URL ohne Referenz.
	 */
	public static URL truncReference(URL url) {
		if (url == null)
			return null;
		try {
			URL resultURL = new URL(url.getProtocol(), url.getHost(), url
					.getPath());
			if (url.getQuery() == null)
				return resultURL;
			return new URL(resultURL.toString() + "?" + url.getQuery());
		} catch (MalformedURLException e) {
			// e.printStackTrace(System.err);
		}
		return null;
	}

	/**
	 * Ueberprueft, ob die uebergebene URL eine Content-URL ist.
	 * 
	 * @param url
	 *            Zu ueberprufende URL.
	 * @param options
	 *            Vom User eingegebene Optionen.
	 * 
	 * @return True, wenn die uebergebene URL eine Content-URL ist.
	 */
	public static boolean isContentURL(URL url, Options options) {
		String s = getURLType(url, options);
		return ((s != null) && ((s.equals("c") || s.equals("ce")))) ? true
				: false;
	}

	/**
	 * Ueberprueft, ob der uebergebene URL ein Exist-URL ist.
	 * 
	 * @param url
	 *            Zu ueberprufender URL.
	 * @param options
	 *            Die vom User eingegebenen Optionen.
	 * @return True, wenn der uebergebene URL ein Exist-URL ist.
	 */
	public static boolean isExistURL(URL url, Options options) {
		String s = getURLType(url, options);
		return ((s != null) && ((s.equals("e") || s.equals("ce")))) ? true
				: false;
	}

	/**
	 * Ueberprueft, ob der uebergebene URL ein Ignore-URL ist.
	 * 
	 * @param url
	 *            Zu ueberprufender URL.
	 * @param options
	 *            Die vom User eingegebenen Optionen.
	 * @return True, wenn der uebergebene URL ein Ignore-URL ist.
	 */
	public static boolean isIgnoreURL(URL url, Options options) {
		String s = getURLType(url, options);
		return ((s != null) && (s.equals("i"))) ? true : false;
	}

	/**
	 * Ueberprueft eine URL hinsichtlich ihres Typs, unterteilt in Content,
	 * Exist und Ignore-URL. Gibt "i" fuer IgnoreURL, "e" fuer ExistURL, "c"
	 * fuer ContentURL und "ce" fuer den Fall, dass sowohl Content als auch
	 * Exist zutreffen zurueck. Falls die URL in keine der Kategorien passt,
	 * wird "null" zurueckgegeben.
	 * 
	 * @param url
	 *            zu pruefende URL
	 * @param options
	 *            Optionen
	 * @return i -> IgnoreURL, e -> ExistURL, c -> ContentURL, ce -> content und
	 *         exist, null -> no Match!
	 */
	private static String getURLType(URL url, Options options) {
		String[] sa = new String[options.getURLFilters().keySet().size()];
		sa = options.getURLFilters().keySet().toArray(sa);
		if (url != null) {
			for (int i = 0; i < options.getURLFilters().size(); i++) {
				if (url.toString().matches(sa[i]))
					return options.getURLFilters().get(sa[i]);
			}
		}
		return null;
	}
}

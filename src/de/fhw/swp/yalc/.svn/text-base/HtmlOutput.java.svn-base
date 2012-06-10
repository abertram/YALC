/**
 * 
 */
package de.fhw.swp.yalc;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

/**
 * Ausgabe des Ergebnisses in Html-Form.
 * 
 * @author Ruediger Klante
 * @author Alexander Bertram
 */
public class HtmlOutput extends Output {
	/**
	 * XML-Deklaration.
	 */
	private String xmlDeclaration =
		"<?xml version=\"1.1\" encoding=\"ISO-8859-1\" ?>\n";
	
	/**
	 * Doctype.
	 */
	private String doctype = 
		"<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN\"\n" +
		"\t\"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">\n";
	
	/**
	 * Webseiten-Titel.
	 */
	private String title = 
		"<title>YALC Result</title>\n";
	
	/**
	 * Css-Style.
	 */
	private String style = 
		"<style type=\"text/css\">\n" +
		"<!--\n" +
		"* {\n" +
			"background-color: #cccccc;\n" +
			"margin: 0;\n" +
			"padding: 0;\n" +
		"}\n" +
		"body {\n" +
			"margin: 10px;\n" +
		"}\n" +
		"h1, h2 {\n" +
			"background-color: #a9a9a9;\n" +
			"border: 1px solid black;\n" +
			"padding: 0px 5px;\n" +
		"}\n" +
		"h1 *, h2 * {\n" +
			"background-color: #a9a9a9;\n" +
		"}\n" +
		"h1 {\n" +
			"font-size: 28px;\n" +
			"text-align: center;\n" +
		"}\n" +
		"h2 {\n" +
			"font-size: 24px;\n" +
		"}\n" +
		"h3 {\n" +
			"font-size: 20px;\n" +
		"}\n" +
		"ul {\n" +
			"list-style: none;\n" +
		"}\n" +
		"table {\n" +
			"border: 1px solid black;\n" +
			"width: 100%;\n" +
			"border-collapse: collapse;\n" +
		"}\n" +
		"th {\n" +
			"text-align: center;\n" +
			"border-bottom: 1px solid black;\n" +
		"}\n" +
		"th a:link {\n" +
			"color: white;\n" +
		"}\n" +
		"th a:visited {\n" +
			"color: white;\n" +
		"}\n" +
		"th a:hover {\n" +
			"color: white;\n" +
		"}\n" +
		"th a:active {\n" +
			"color: white;\n" +
		"}\n" +
		"th a:focus {\n" +
			"color: white;\n" +
		"}\n" +
		"td {\n" +
			"vertical-align: top;\n" +
		"}\n" +
		"a:link {\n" +
			"color: black;\n" +
		"}\n" +
		"a:visited {\n" +
			"color: black;\n" +
		"}\n" +
		"a:hover {\n" +
			"color: black;\n" +
		"}\n" +
		"a:active {\n" +
			"color: black;\n" +
		"}\n" +
		"a:focus {\n" +
			"color: black;\n" +
		"}\n" +
		"td.caption {\n" +
			"text-align: right;\n" +
			"width: 50%;\n" +
			"font-weight: bold;\n" +
			"padding-right: 5px;\n" +
		"}\n" +
		"td.value {\n" +
			"text-align: left;\n" +
			"width: 50%;\n" +
			"padding-left: 5px;\n" +
		"}\n" +
		".class1xx {\n" +
			"" +
		"}\n" +
		".class2xx * {\n" +
			"background-color: #006400;\n" +
		"}\n" +
		".class3xx {\n" +
			"\n" +
		"}\n" +
		".class4xx * {\n" +
			"background-color: #8b0000;\n" +
		"}\n" +
		".class5xx * {\n" +
			"background-color: #8b0000;\n" +
		"}\n" +
		".start-urls {\n" +
			"border: 1px solid black;\n" +
			"padding: 5px;\n" +
			"margin: 20px 0px;\n" +
		"}\n" +
		".start-urls ul {\n" +
			"margin: 0px 10px;\n" +
			"margin-top: 5px;\n" +
		"}\n" +
		".filter {\n" +
			"border: 1px solid black;\n" +
			"padding: 5px;\n" +
			"margin: 20px 0px;\n" +
		"}\n" +
		".filter table {\n" +
			"border: 0px;\n" +
			"width: auto;\n" +
			"margin: 0px 10px;\n" +
			"margin-top: 5px;\n" +
		"}\n" +
		".filter td {\n" +
			"width: auto;\n" +
		"}\n" +
		".goto {\n" +
			"border: 1px solid black;\n" +
			"padding: 5px;\n" +
			"margin: 20px 0px;\n" +
		"}\n" +
		".goto ul {\n" +
			"margin: 0px 10px;\n" +
			"margin-top: 5px;\n" +
		"}\n" +
		".content-urls {\n" +
			"border: 1px solid black;\n" +
			"padding: 5px;\n" +
			"margin: 20px 0px;\n" +
		"}\n" +
		".content-urls table {\n" +
			"margin-top: 10px;\n" +
		"}\n" +
		".exist-urls {\n" +
			"border: 1px solid black;\n" +
			"padding: 5px;\n" +
			"margin: 20px 0px;\n" +
		"}\n" +
		".exist-urls table {\n" +
			"margin-top: 20px;\n" +
		"}\n" +
		".ignore-urls {\n" +
			"border: 1px solid black;\n" +
			"padding: 5px;\n" +
			"margin: 20px 0px;\n" +
		"}\n" +
		".ignore-urls ul {\n" +
			"margin: 0px 10px;\n" +
			"margin-top: 5px;\n" +
		"}\n" +
		".unchecked-urls {\n" +
			"border: 1px solid black;\n" +
			"padding: 5px;\n" +
			"margin: 20px 0px;\n" +
		"}\n" +
		".unchecked-urls ul {\n" +
			"margin: 0px 10px;\n" +
			"margin-top: 5px;\n" +
		"}\n" +
		".shortcuts {" +
			"text-align: right;" +
			"padding: 0px 5px;" +
			"font-size: 12px;" +
		"}" +
		"-->\n" +
		"</style>\n";
	
	/**
	 * Html-Header.
	 */
	private String header =
		xmlDeclaration +
		doctype + 
		"<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
		"<head>\n" + 
		title +
		style +
		"</head>\n" +
		"<body>\n";
	
	/**
	 * Html-Footer.
	 */
	private String footer =
		"</body>\n" +
		"</html>";
	
	/**
	 * Konstruktor.
	 * 
	 * @param checkedURLs Die vom Checker gechekten URLs.
	 * @param options Die vom User eingegebene Optionen.
	 */
	public HtmlOutput(CheckedURLs checkedURLs, Options options) {
		this(System.out, checkedURLs, options);
	}
	
	/**
	 * Konstruktor.
	 * 
	 * @param destination Ziel fuer die Ausgabe.
	 * @param checkedURLs Die vom Checker gecheckten URLs.
	 * @param options Die vom User eingegebenen Optionen.
	 */
	public HtmlOutput(PrintStream destination, CheckedURLs checkedURLs, Options options) {
		super(destination, checkedURLs, options);
	}

	@Override
	public void createOutput() {
		String output = createHeader() + createBody() + createFooter();
		destination.print(output);
		destination.flush();
	}
	
	/**
	 * Gibt den Html-Header zurueck.
	 * 
	 * @return Html-Header.
	 */
	private String createHeader() {
		return header;
	}
	
	/**
	 * Erzeugt den Html-Body.
	 * 
	 * @return Html-Body.
	 */
	private String createBody() {
		StringBuilder sb = new StringBuilder();
		sb.append("<h1><a id=\"top\">YALC Result</a></h1>\n");
		sb.append(createHtmlForStartURLs());
		sb.append("<div class=\"filter\">\n");
		sb.append("<h2>Filter</h2>\n");
		if (options.getContentURLs().size() > 0
				|| options.getExistURLs().size() > 0
				|| options.getIgnoreURLs().size() > 0) {
			sb.append("<table>\n");
			sb.append(createHtmlForFilter("Content", options.getContentURLs()));
			sb.append(createHtmlForFilter("Exist", options.getExistURLs()));
			sb.append(createHtmlForFilter("Ignore", options.getIgnoreURLs()));
			sb.append(createHtmlForProxy());
			sb.append("</table>\n");
		}
		sb.append("</div>\n");
		sb.append(createHtmlForGoTo());
		sb.append(createHtmlForURLDetails("Content-URLs", "content-urls",
				checkedURLs.getContentURLsDetailsAsArray(), null, "exist-urls"));
		sb.append(createHtmlForURLDetails("Exist-URLs", "exist-urls",
				checkedURLs.getExistURLsAsArray(), "content-urls", "ignore-urls"));
		sb.append(createHtmlForIgnoreURLs());
		sb.append(createHtmlForUncheckedURLs());
		return sb.toString();
	}
	
	/**
	 * Erzeugt den Html-Footer.
	 * 
	 * @return Html-Footer.
	 */
	private String createFooter() {
		return footer;
	}
	
	/**
	 * Erzeugt Html-Code fuer die URL-Details.
	 * 
	 * @return Html-Code fuer die URL-Details.
	 */
	private String createHtmlForStartURLs() {
		StringBuilder sb = new StringBuilder();
		sb.append("<div class=\"start-urls\">\n");
		sb.append("<h2>Start-URLs</h2>\n");
		List<String> startURLs = options.getStartURLs();
		sb.append("<ul>\n");
		for (int i = 0; i < startURLs.size(); i++)
			sb.append(String.format("" +
					"<li>%s</li>\n", createLink(startURLs.get(i))));
		sb.append("</ul>\n");
		sb.append("</div>\n");
		return sb.toString();
	}
	
	/**
	 * Erzeugt Html-Code fuer die Filtereinstellungen.
	 * 
	 * @param filterName Name des Filters.
	 * @param filterURLs Filter-URLs.
	 * @return Html-Code fuer den Filter.
	 */
	private String createHtmlForFilter(String filterName, List<String> filterURLs) {
		if (filterURLs.size() == 0)
			return "";
		StringBuilder sb = new StringBuilder();
		sb.append("<tr>\n");
		sb.append(String.format("<td class=\"caption\">%s:</td>\n", filterName));
		sb.append("<td class=\"value\">\n");
		if (filterURLs.size() > 0) {
			sb.append("<ul>\n");
			for (int i = 0; i < filterURLs.size(); i++)
				sb.append(String.format("" +
						"<li>%s</li>\n", filterURLs.get(i)));
			sb.append("</ul>\n");
		}
		sb.append("</td>\n");
		sb.append("</tr>\n");
		return sb.toString();
	}

	/**
	 * Erzeugt Html-Code fuer den Proxy.
	 * 
	 * @return Html-Code fuer den Proxy.
	 */
	private String createHtmlForProxy() {
		return (options.getProxy() == null)
			? ""
			: String.format("" +
					"<tr>\n" +
					"<td class=\"caption\">Proxy:</td>\n" +
					"<td class=\"value\">%s</td>\n" +
					"</tr>\n", options.getProxy().toString());
	}

	/**
	 * Erzeugt Html-Code fuer die Verweise zu den einzelnen URL-Kategorien.
	 * 
	 * @return Html-Code fuer die Verweise.
	 */
	private String createHtmlForGoTo() {
		return(String.format("" +
				"<div class=\"goto\">" +
				"<h2>\n" +
				"Go to\n" +
				"</h2>\n" +
				"<ul>\n" +
				"<li>\n" +
				"<a href=\"#content-urls\">Content-URLs</a> (total: %d, broken: %d)\n" +
				"</li>\n" +
				"<li>\n" +
				"<a href=\"#exist-urls\">Exist-URLs</a> (total: %d, broken: %d)\n" +
				"</li>\n" +
				"<li>\n" +
				"<a href=\"#ignore-urls\">Ignore-URLs</a> (total: %d)\n" +
				"</li>\n" +
				"<li>\n" +
				"<a href=\"#unchecked-urls\">Unchecked-URLs</a> (total: %d)\n" +
				"</li>\n" +
				"</ul>\n" +
				"</div>", 
				checkedURLs.getContentURLsDetails().size(),
				checkedURLs.getContentURLsDetails().sizeOfBrokenURLs(),
				checkedURLs.getExistURLsDetails().size(),
				checkedURLs.getExistURLsDetails().sizeOfBrokenURLs(),
				checkedURLs.getIgnoreURLs().size(),
				checkedURLs.getUncheckedURLs().size()));
	}

	/**
	 * Erzeugt Html-Code fuer die URL-Details.
	 * 
	 * @param caption Beschriftung.
	 * @param cssClass Css-Klasse.
	 * @param ud URL-Details.
	 * @param prevCat Naechste Kategorie.
	 * @param nextCat Vorige Kategorie.
	 * @return Html-Code fuer die Details.
	 */
	private String createHtmlForURLDetails(String caption, String cssClass, 
			URLDetails[] ud, String prevCat, String nextCat) {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("" +
				"<div class=\"%s\">\n" +
				"<h2>\n" +
				"<a id=\"%1$s\">\n" +
				"%s\n" +
				"</a>\n" +
				"</h2>\n" +
				"", 
				cssClass, caption));
		for (int i = 0; i < ud.length; i++) {
			sb.append(String.format("" +
					"<table>\n" +
					"<tr class=\"%s\">\n" +
					"<th colspan=\"2\"><h3><a id=\"%s%d\"></a>%s</h3></th>\n" +
					"</tr>\n" +
					"<tr>\n",
					ud[i].getStatusClass(),
					cssClass, i,
					createLink(ud[i].getURL())));
			sb.append("" +
					"<td colspan=\"2\" class=\"shortcuts\">\n" +
					"<a href=\"#top\">top</a>");
			sb.append("&nbsp;|&nbsp;");
			if (prevCat != null)
				sb.append(String.format("" +
					"<a href=\"#%s\">", prevCat));
			sb.append("prev. category");
			if (prevCat != null)
				sb.append("</a>");
			sb.append("&nbsp;|&nbsp;");
			if (i > 0)
				sb.append(String.format("" +
					"<a href=\"#%s%d\">", cssClass, i - 1));
			sb.append("prev. url");
			if (i > 0)
				sb.append("" +
						"</a>\n");
			sb.append("&nbsp;|&nbsp;");
			if (nextCat != null)
				sb.append(String.format("" +
					"<a href=\"#%s\">", nextCat));
			sb.append("next. category");
			if (nextCat != null)
				sb.append("</a>");
			sb.append("&nbsp;|&nbsp;");
			if (i < ud.length - 1)
				sb.append(String.format("" +
					"<a href=\"#%s%d\">", cssClass, i + 1));
			sb.append("next url");
			if (i < ud.length - 1)
				sb.append("" +
						"</a>\n");
			sb.append("" +
					"</td>\n" +
					"</tr>\n");
			sb.append(String.format("" +
					"<tr>\n" +
					"<td class=\"caption\">Content-Type:</td>\n" +
					"<td class=\"value\">%s</td>\n" +
					"</tr>\n" +
					"<tr>\n" +
					"<td class=\"caption\">Content-Length:</td>\n" +
					"<td class=\"value\">%d</td>\n" +
					"</tr>\n" +
					"<tr>\n" +
					"<td class=\"caption\">Last-Modified:</td>\n" +
					"<td class=\"value\">%tF</td>\n" +
					"</tr>\n" +
					"<tr>\n" +
					"<td class=\"caption\">Status-Code:</td>\n" +
					"<td class=\"value\">%d</td>\n" +
					"</tr>\n" +
					"<tr>\n" +
					"<td class=\"caption\">Status-Message:</td>\n" +
					"<td class=\"value\">%s</td>\n" +
					"</tr>\n", ud.clone()[i].getContentType(),
					ud[i].getContentLength(), ud[i].getLastModified(), 
					ud[i].getStatusCode(), ud[i].getStatusMessage()));
			if (!ud[i].getStatusClass().contains("2")) {
				URL[] ua = ud[i].getBaseURLsAsArray();
				sb.append("" +
						"<tr>\n" +
						"<td class=\"caption\">Base-URL");
				if (ua.length > 1)
					sb.append("s");
				sb.append(":</td>\n");
				if (ua.length > 0) {
					sb.append("" +
							"<td class=\"value\">\n" +
							"<ul>\n");
					for (int j = 0; j < ua.length; j++)
						sb.append(String.format("" +
								"<li>%s</li>\n", createLink(ua[j].toString())));
					sb.append("" +
							"</ul>\n" +
							"</td>\n" +
							"</tr>\n");
				}
			}
			sb.append("" +
					"</table>\n");
		}
		sb.append("</div>\n");
		return sb.toString();
	}

	/**
	 * Erzeugt Html-Code fuer die Ignore-URLs.
	 * 
	 * @return Html-Code fuer die Ignore-URLs.
	 */
	private String createHtmlForIgnoreURLs() {
		URL[] ignoreURLs = checkedURLs.getIgnoreURLsAsArray();
		StringBuilder sb = new StringBuilder();
		sb.append("" +
				"<div class=\"ignore-urls\">\n" +
				"<h2>\n" +
				"<a id=\"ignore-urls\">\n" +
				"Ignore-URLs\n" +
				"</a>\n" +
				"</h2>\n" +
				"<div class=\"shortcuts\">\n" +
				"<a href=\"#top\">top</a>\n" +
				"&nbsp;|&nbsp;<a href=\"#exist-urls\">prev. category</a>" +
				"&nbsp;|&nbsp;<a href=\"#unchecked-urls\">next category</a>" + 
				"</div>\n");
		if (ignoreURLs.length > 0) {
			sb.append("<ul>\n");
			for (int i = 0; i < ignoreURLs.length; i++)
				sb.append(String.format("" +
						"<li>%s</li>\n", createLink(ignoreURLs[i].toString())));
			sb.append("</ul>\n");
		}
		sb.append("</div>");
		return sb.toString();
	}

	/**
	 * Erzeugt Html-Code fuer die ungecheckten URLs.
	 * 
	 * @return Html-Code fuer die ungecheckten URLs.
	 */
	private String createHtmlForUncheckedURLs() {
		URL[] uncheckedURLs = checkedURLs.getUncheckedURLsAsArray();
		StringBuilder sb = new StringBuilder();
		sb.append("" +
				"<div class=\"unchecked-urls\">\n" +
				"<h2>\n" +
				"<a id=\"unchecked-urls\">\n" +
				"Unchecked-URLs\n" +
				"</a>\n" +
				"</h2>\n" +
				"<div class=\"shortcuts\">\n" +
				"<a href=\"#top\">top</a>\n" +
				"&nbsp;|&nbsp;<a href=\"#ignore-urls\">prev. category</a>" +
				"&nbsp;|&nbsp;next category" +
				"</div>\n");
		if (uncheckedURLs.length > 0) {
			sb.append("<ul>\n");
			for (int i = 0; i < uncheckedURLs.length; i++)
				sb.append(String.format("" +
						"<li>%s</li>\n", createLink(uncheckedURLs[i].toString())));
			sb.append("</ul>\n");
		}
		sb.append("</div>");
		return sb.toString();
	}

	/**
	 * Erzeugt einen Link.
	 * 
	 * @param s Link-Beschriftung.
	 * @return Html-Code fuer einen Link.
	 */
	private String createLink(String s) {
		return String.format("<a href=\"%s\">%1$s</a>\n", encodeURL(s));
	}

	/**
	 * Kodiert die Query einer URL in UTF-8.
	 * 
	 * @param s URL.
	 * @return UTF-8 kodierte URL.
	 */
	private String encodeURL(String s) {
		try {
			URL url = new URL(s);
			try {
				String protocol = url.getProtocol() != null ? url.getProtocol() + ":" : "";
				String host = url.getHost() != null ? "//" + url.getHost() : "";
				String path = url.getPath() != null ? url.getPath() : "";
				String query = url.getQuery() != null ? "?" + URLEncoder.encode(url.getQuery(), "UTF-8") : "";
				String reference = url.getRef() != null ? "#" + URLEncoder.encode(url.getRef(), "UTF-8") : "";
				url = new URL(protocol + host + path + query + reference);
				return url.toString();
			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace(System.err);
			}
		} catch (MalformedURLException e1) {
//			e1.printStackTrace(System.err);
		}
		return s;
	}
}

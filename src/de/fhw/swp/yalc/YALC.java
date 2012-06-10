/**
 * 
 */
package de.fhw.swp.yalc;

import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * YALC - Yet Another Link Checker<br>
 * 
 * Programm zum Checken der Hyperlinks eines 
 * HTML-Dokumentes oder eines ganzen
 * Dokumentenbaums.
 * Einschraenkung der zu checkenden Links
 * mittels regulaerer Ausdruecke,
 * Ausgabe der Ergebnisse als HTML-Dokument.
 * 
 * @author Ruediger Klante
 * @author Alexander Bertram
 */
public class YALC {

	/**
	 * Hauptprogramm<br>
	 * Steuert den Ablauf des Programms
	 * 
	 * @param args
	 *            Konsolenargumente.
	 */
	public static void main(String[] args) {
		// Parsen der Optionen
		Options options = OptionsParser.parseOptions(args);
		// Optionen vorhanden?
		if (options != null) {
			// Crawler erstellen
			Crawler crawler = new Crawler(options);
			// Checker erstellen
			Checker checker = new Checker(crawler.crawl(), options);
			// URLs checken
			CheckedURLs checkedURLs = checker.check();
			// Ausgabe erzeugen
			Output output = null;
			if (options.getFile() != null)
				try {
					output = new HtmlOutput(new PrintStream(options.getFile()),
							checkedURLs, options);
				} catch (FileNotFoundException e) {
//					e.printStackTrace(System.err);
					System.err.printf("Couldn't write to \"%s\".\n", options.getFile());
					output = new HtmlOutput(checkedURLs, options);
				}
			else
				output = new HtmlOutput(checkedURLs, options);
			output.createOutput();
		}
	}
}

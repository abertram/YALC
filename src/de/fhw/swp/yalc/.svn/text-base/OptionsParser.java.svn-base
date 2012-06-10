/**
 * 
 */
package de.fhw.swp.yalc;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Einfacher Kommandozeilenparser.
 * 
 * @author Ruediger Klante
 * @author Alexander Bertram
 */
public class OptionsParser {

	/**
	 * Parst die Kommandozeilenargumente.
	 * 
	 * @param args
	 *            Kommandoazeilenrgumente
	 * @return Geparste Optionen
	 */
	public static Options parseOptions(String[] args) {
		Options options = new Options();
		String arg;

		// keine Argumnente
		if (args.length == 0) {
			printHelp();
			return null;
		}

		for (int i = 0; i < args.length; ++i) {
			arg = args[i].trim();

			// Hilfe
			if (arg.equals("-h") || arg.equals("--help")) {
				printHelp();
				return null;
			}
			// zu analysierende URL
			else if (arg.equals("-c") || arg.equals("--content")) {
				try {
					arg = args[i + 1];
					if (arg != null) {
						options.addContentURL(arg);
						++i;
					}
				} catch (Exception e) {
//					e.printStackTrace(System.err);
				}
			}
			// zu ignorierende URL
			else if (arg.equals("-i") || arg.equals("--ignore")) {
				try {
					arg = args[i + 1];
					if (arg != null) {
						options.addIgnoreURL(arg);
						++i;
					}
				} catch (Exception e) {
//					e.printStackTrace(System.err);
				}
			}
			// auf Existenz zu ueberpruefende URL
			else if (arg.equals("-x") || arg.equals("-e")
					|| arg.equals("--exist")) {
				try {
					arg = args[i + 1];
					if (arg != null) {
						options.addExistURL(arg);
						++i;
					}
				} catch (Exception e) {
//					e.printStackTrace(System.err);
				}
			}
			// Proxy
			else if (arg.equals("-p") || arg.equals("--proxy")) {
				try {
					arg = args[i + 1];
					if (arg != null) {
						Proxy proxy = null;
						// Server und Port angegeben
						if (arg.contains(":")) {
							String[] proxyData = arg.split(":");
							proxy = new Proxy(proxyData[0], Integer
									.parseInt(proxyData[1]));
						}
						// nur Server angegeben
						else
							proxy = new Proxy(arg, null);
						options.setProxy(proxy);
						i++;
					}
				} catch (Exception e) {
//					e.printStackTrace(System.err);
				}
			}
			// Datei fuer die Resultate
			else if (arg.equals("-f") || arg.equals("--file")) {
				try {
					arg = args[i + 1];
					if (arg != null) {
						options.setFile(arg);
						++i;
					}
				} catch (Exception e) {
//					e.printStackTrace(System.err);
				}
			}
			// Verbose-Modus
			else if (arg.equals("-v") || arg.equals("--verbose")) {
				options.setVerbose(true);
				i++;
			}
			// unbekannte Option
			else if (arg.charAt(0) == '-') {
				System.err.printf("Unknown option: %s\n", arg);
				printHelp();
				return null;
			}
			// Start-URL
			else {
				URI uri;
				try {
					uri = new URI(arg);
					options.addStartURL(uri.toString());
				} catch (URISyntaxException e) {
//					e.printStackTrace(System.err);
				}
			}
		}

		return options;
	}

	/**
	 * Gibt die Hilfe auf stdout aus
	 */
	private static void printHelp() {
		System.out
				.println("YALC [option...] url [url...]\n" +
						"  Die Reihenfolge der angegebenen regulaeren Ausdruecke bestimmt deren Prioritaet!\n" +
						"  -h, --help                  Hilfe ausgeben\n" +
						"  -c, --content <url>         Inhalt aller URLs ueberpruefen, auf die der regulaere Ausdruck <url>, passt.\n" +
						"  -i, --ignore <url>          Alle URLs, auf die der regulaere Ausdruck <url> passt, ignorieren\n" +
						"  -x, -e, --exist <url>       Alle URLs ueberpruefen, auf die der regulaere Ausdruck <url> passt\n" +
						"  -p, --proxy <server[:port]> Proxyserver (z.B.: www.proxy.de oder www.proxy.de:1234))\n" +
						"  -v, --verbose               Ausgabe auf stderr, was das Programm gerade macht.\n");
	}
}

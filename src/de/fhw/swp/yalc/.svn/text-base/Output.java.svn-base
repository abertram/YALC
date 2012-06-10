/**
 * 
 */
package de.fhw.swp.yalc;

import java.io.PrintStream;

/**
 * Abstrakte Klasse fuer die Ausgabe.
 * 
 * @author Ruediger Klante
 * @author Alexander Bertram
 */
public abstract class Output {
	/**
	 * Ziel fuer die Ausgabe.
	 */
	protected PrintStream destination;

	/**
	 * Die gecheckten URLs.
	 */
	protected CheckedURLs checkedURLs;

	/**
	 * Die vom User eingegebenen Optionen.
	 */
	protected Options options;

	/**
	 * Konstruktor.
	 * 
	 * @param destination
	 *            Ziel fuer die Ausgabe.
	 * @param checkedURLs
	 *            Gecheckte URLs.
	 * @param options
	 *            Die vom User eingegebenen Optionen.
	 */
	public Output(PrintStream destination, CheckedURLs checkedURLs,
			Options options) {
		this.destination = destination;
		this.checkedURLs = checkedURLs;
		this.options = options;
	}

	/**
	 * Erzeugt die Ausgabe.
	 */
	public abstract void createOutput();
}

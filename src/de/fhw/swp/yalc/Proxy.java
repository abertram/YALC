/**
 * 
 */
package de.fhw.swp.yalc;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Properties;

/**
 * Proxy-Klasse.
 * 
 * @author Ruediger Klante
 * @author Alexander Bertram
 */
public class Proxy {
	/**
	 * Serveradresse
	 */
	private String server;

	/**
	 * Serverport
	 */
	private Integer port;

	/**
	 * Konstruktor
	 * 
	 * @param server
	 *            Serveradresse
	 * @param port
	 *            Serverport
	 */
	public Proxy(String server, Integer port) {
		// IP angegeben?
		if (!isValidIP(server))
			// Adresse auflosen
			server = resolveAddress(server);
		this.server = server;
		this.port = port;
		// Proxy setzen
		setProxy();
	}

	/**
	 * Teilt der JVM die Proxyparameter mit.
	 */
	public void setProxy() {
		Properties p = System.getProperties();
		p.put("http.proxySet", true);
		p.put("http.proxyHost", server);
		if (port != null)
			p.put("http.proxyPort", port.toString());
		System.setProperties(p);
	}

	/**
	 * Gibt die IP zu einer Adresse zurueck.
	 * 
	 * @param address
	 *            Adresse, die aufegloest werden soll
	 * @return IP, die zu der angegebenen Adresse gehoert
	 */
	private String resolveAddress(String address) {
		try {
			return Inet4Address.getByName(address).getHostAddress();
		} catch (UnknownHostException e) {
//			e.printStackTrace(System.err);
		}
		return null;
	}

	/**
	 * Ueberprueft, ob eine IP gueltig ist (xxx.xxx.xxx.xxx).
	 * 
	 * @param ip
	 *            Zu ueberpruefende IP
	 * @return true, wenn IP gueltig
	 */
	private boolean isValidIP(String ip) {
		return ip.matches("(\\d{1,3}\\.){3}\\d{1,3}");
	}

	/**
	 * Stringrepraesentation des Proxys.
	 * 
	 * @return Stringrepraesentation des Proxys.
	 */
	@Override
	public String toString() {
		return String.format("%s:%d", server, port);
	}
}

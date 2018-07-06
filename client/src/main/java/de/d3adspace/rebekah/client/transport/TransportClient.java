package de.d3adspace.rebekah.client.transport;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public interface TransportClient {

    /**
     * Connect to the server.
     */
    void connect();

    /**
     * Check if the client is connected to the server.
     *
     * @return If the client is connected,
     */
    boolean isConnected();

    /**
     * Disconnect from the server.
     */
    void disconnect();
}
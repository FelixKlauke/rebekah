package de.d3adspace.rebekah.client;

import de.d3adspace.rebekah.commons.agent.PacketAgent;
import de.d3adspace.rebekah.commons.message.OutgoingMessage;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public interface RebekahClient extends PacketAgent {

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

  /**
   * Send the given message to the server.
   *
   * @param outgoingMessage The message.
   */
  void sendMessage(OutgoingMessage outgoingMessage);
}

package de.d3adspace.rebekah.commons.packet.io;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public interface PacketReader {

  /**
   * Read the next string.
   *
   * @return The read string.
   */
  String readString();

  /**
   * Read the next integer.
   *
   * @return The read integer.
   */
  int readInt();

  /**
   * Read the next float.
   *
   * @return The read float.
   */
  float readFloat();

  /**
   * Read the next double.
   *
   * @return The read double.
   */
  double readDouble();
}

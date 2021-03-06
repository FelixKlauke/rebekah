package de.d3adspace.rebekah.commons.handler;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import de.d3adspace.rebekah.commons.context.MessageContext;
import de.d3adspace.rebekah.commons.message.IncomingMessage;
import de.d3adspace.rebekah.commons.packet.Packet;
import de.d3adspace.rebekah.commons.packet.io.PacketReader;
import de.d3adspace.rebekah.commons.packet.io.PacketWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
@ExtendWith(MockitoExtension.class)
class IncomingMessageHandlerManagerImplTest {

  private static final IncomingMessageHandler TEST_EMPTY_REQUEST_HANDLER = new EmptyTestIncomingMessageHandler();
  private static final IncomingMessageHandler TEST_REQUEST_HANDLER = new TestIncomingMessageHandler0();

  @Mock
  MessageContext messageContext;

  IncomingMessage incomingMessage;

  private IncomingMessageHandlerManager incomingMessageHandlerManager;

  @BeforeEach
  void setUp() {
    incomingMessage = new TestPacket();
    incomingMessageHandlerManager = new IncomingMessageHandlerManagerImpl();
    incomingMessageHandlerManager.registerMessageHandler(TEST_REQUEST_HANDLER);
    incomingMessageHandlerManager
      .registerMessageHandler(TEST_EMPTY_REQUEST_HANDLER);
  }

  @Test
  void testRegisterRequestHandler() {
    TestIncomingMessageHandler testRequestHandler = new TestIncomingMessageHandler();

    incomingMessageHandlerManager.registerMessageHandler(testRequestHandler);

    assertTrue(incomingMessageHandlerManager
        .isMessageHandlerRegistered(testRequestHandler),
      "Packet handler should be registered.");
  }

  @Test
  void testRegisterRequestHandlerTwiceInstance() {
    TestIncomingMessageHandler testPacketHandler = new TestIncomingMessageHandler();
    incomingMessageHandlerManager.registerMessageHandler(testPacketHandler);

    Executable executable = () -> incomingMessageHandlerManager
      .registerMessageHandler(testPacketHandler);
    assertThrows(IllegalStateException.class, executable);
  }

  @Test
  void testUnregisterRequestHandler() {
    incomingMessageHandlerManager
      .unregisterMessageHandler(TEST_REQUEST_HANDLER);

    assertFalse(incomingMessageHandlerManager
        .isMessageHandlerRegistered(TEST_REQUEST_HANDLER),
      "Packet handler should not be registered.");
  }

  @Test
  void testIsRequestHandlerRegistered() {
    assertTrue(incomingMessageHandlerManager
        .isMessageHandlerRegistered(TEST_REQUEST_HANDLER),
      "Packet handler should be registered.");
  }

  @Test
  void testProcess() {
    incomingMessageHandlerManager.process(messageContext, incomingMessage);
  }

  public static class EmptyTestIncomingMessageHandler implements
    IncomingMessageHandler {

  }

  public static class TestIncomingMessageHandler implements
    IncomingMessageHandler {

    public void handleRequest(MessageContext messageContext,
      TestPacket testPacket) {

    }

    public void handleRequest2(MessageContext messageContext,
      TestPacket testPacket) {

    }

    public void handleRequestNot0(MessageContext messageContext) {
      throw new IllegalStateException();
    }

    public void handleRequestNot1(IncomingMessage request) {
      throw new IllegalStateException();
    }

    public void handleRequestNot2(TestPacket request) {
      throw new IllegalStateException();
    }

    public void handleRequestNot3(TestPacket testPacket,
      MessageContext messageContext) {
      throw new IllegalStateException();
    }

    public void handleRequestNot4(MessageContext messageContext,
      MessageContext messageContext1) {
      throw new IllegalStateException();
    }

  }

  public static class TestIncomingMessageHandler0 implements
    IncomingMessageHandler {

    public void handleRequest(MessageContext messageContext,
      TestPacket testPacket) {

    }
  }

  public static class TestPacket implements Packet {

    @Override
    public void encode(PacketWriter packetWriter) {

    }

    @Override
    public void decode(PacketReader packetReader) {

    }
  }
}

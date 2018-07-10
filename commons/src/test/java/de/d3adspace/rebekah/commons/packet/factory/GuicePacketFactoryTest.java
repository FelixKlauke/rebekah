package de.d3adspace.rebekah.commons.packet.factory;

import com.google.inject.Injector;
import de.d3adspace.rebekah.commons.packet.Packet;
import de.d3adspace.rebekah.commons.packet.description.annotation.PacketMeta;
import de.d3adspace.rebekah.commons.packet.io.PacketReader;
import de.d3adspace.rebekah.commons.packet.io.PacketWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
@ExtendWith(MockitoExtension.class)
class GuicePacketFactoryTest {

    @Mock
    Injector injector;

    private Class<? extends Packet> aClass = TestPacket.class;
    private GuicePacketFactory packetFactory;

    @BeforeEach
    void setUp() {
        packetFactory = new GuicePacketFactory(injector);
    }

    @Test
    void testCreatePacket() {
        Packet packet = packetFactory.createPacket(aClass);

        assertNotNull(packet, "Packet should not be null.");

        verify(injector).getInstance(aClass);
    }

    @PacketMeta(id = 0)
    public static class TestPacket implements Packet {

        @Override
        public void encode(PacketWriter packetWriter) {

        }

        @Override
        public void decode(PacketReader packetReader) {

        }
    }
}
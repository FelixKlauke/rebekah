package de.d3adspace.rebekah.commons.packet.io.binary;

import io.netty.buffer.ByteBuf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
@ExtendWith(MockitoExtension.class)
class BinaryPacketWriterTest {

    @Mock
    ByteBuf byteBuf;

    private BinaryPacketWriter packetWriter;

    @BeforeEach
    void setUp() {
        packetWriter = new BinaryPacketWriter(byteBuf);
    }

    @Test
    void testWriteString() {
        String testString = "afwafagfwawwfgagawga";

        packetWriter.writeString(testString);

        verify(byteBuf).writeInt(testString.getBytes().length);
        verify(byteBuf).writeBytes(testString.getBytes());
    }
}
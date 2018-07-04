package de.d3adspace.rebekah.server.netty;

import de.d3adspace.rebekah.commons.request.Request;
import de.d3adspace.rebekah.commons.response.Response;
import de.d3adspace.rebekah.server.transport.TransportServer;
import io.reactivex.netty.RxNetty;
import io.reactivex.netty.server.RxServer;

import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Default implementation of the transport layer represented by the {@link TransportServer} based on netty. For netty
 * usage the Wrapper {@link RxNetty} is used.
 *
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class NettyServerImpl implements TransportServer {

    /**
     * The logger that will log all actions.
     */
    private static final Logger LOGGER = Logger.getLogger(NettyServerImpl.class.getName());

    /**
     * The underlying rx server.
     */
    private final RxServer<Request, Response> rxServer;

    /**
     * The current running state of the server.
     */
    private boolean running;

    /**
     * Create a new netty server by its underlying rx server.
     *
     * @param rxServer The underlying rx server.
     */
    @Inject
    public NettyServerImpl(RxServer<Request, Response> rxServer) {
        this.rxServer = rxServer;
    }

    @Override
    public void start() {
        running = true;
        rxServer.start();
    }

    @Override
    public void stop() {
        running = false;

        try {
            rxServer.shutdown();
        } catch (InterruptedException e) {
            LOGGER.log(Level.SEVERE, "Error during shutdown.", e);
        }
    }

    @Override
    public boolean isRunning() {
        return rxServer != null && running;
    }
}

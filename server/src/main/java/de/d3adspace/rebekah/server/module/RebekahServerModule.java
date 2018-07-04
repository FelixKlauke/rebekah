package de.d3adspace.rebekah.server.module;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;
import de.d3adspace.rebekah.commons.request.Request;
import de.d3adspace.rebekah.commons.response.Response;
import de.d3adspace.rebekah.server.RebekahServer;
import de.d3adspace.rebekah.server.RebekahServerImpl;
import de.d3adspace.rebekah.server.netty.NettyServerImpl;
import de.d3adspace.rebekah.server.provider.RxServerProvider;
import de.d3adspace.rebekah.server.transport.TransportServer;
import io.reactivex.netty.server.RxServer;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class RebekahServerModule extends AbstractModule {

    @Override
    protected void configure() {
        bindConstant().annotatedWith(Names.named("serverPort")).to(8081);

        // The main server entry point
        bind(RebekahServer.class).to(RebekahServerImpl.class);

        // Transport layer
        bind(TransportServer.class).to(NettyServerImpl.class);

        // Rx Server
        bind(new TypeLiteral<RxServer<Request, Response>>() {
        }).toProvider(RxServerProvider.class);
    }
}

package org.dragonfei.http.pp.deco;

import io.netty.handler.codec.marshalling.*;
import io.netty.handler.codec.marshalling.MarshallingEncoder;
import org.jboss.marshalling.*;

import java.io.IOException;

import static org.jboss.marshalling.Marshalling.getProvidedMarshallerFactory;

/**
 * Created by longfei on 16-7-9.
 */
public final class MarshallingCodeCFactory {
    public static io.netty.handler.codec.marshalling.MarshallingDecoder buildMarshallingDecoder(){
        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        UnmarshallerProvider provider = new DefaultUnmarshallerProvider(marshallerFactory,configuration);
        io.netty.handler.codec.marshalling.MarshallingDecoder decoder = new io.netty.handler.codec.marshalling.MarshallingDecoder(provider,1024);
        return decoder;
    }

    public static io.netty.handler.codec.marshalling.MarshallingEncoder buildMarshallingEncoder(){
        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        MarshallerProvider provider = new DefaultMarshallerProvider(marshallerFactory,configuration);
        io.netty.handler.codec.marshalling.MarshallingEncoder encoder = new io.netty.handler.codec.marshalling.MarshallingEncoder(provider);
        return encoder;
    }

    public static Marshaller buildMarsshalling() throws IOException{
        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        return marshallerFactory.createMarshaller(configuration);
    }

    public static Unmarshaller buildUnMarsshalling() throws IOException{
        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        return marshallerFactory.createUnmarshaller(configuration);
    }

    public static MarshallerProvider buildProvider(){
        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        MarshallerProvider provider = new DefaultMarshallerProvider(marshallerFactory,configuration);
        return provider;
    }
}

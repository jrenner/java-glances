package org.jrenner.glances;

import org.apache.xmlrpc.XmlRpcRequest;
import org.apache.xmlrpc.client.*;

import java.util.HashMap;
import java.util.Map;


/**
 * In order to enable password authentication, we need to add custom headers to the http request.
 * This requires overriding some methods of XmlRpcTransportFactory/XmlRpcTransport
 *
 * <p>
 * Usage: instantiate the CustomFactoryWrapper, then add your headers using addHeader()
 *        finally get the factory object using getFactory()
 *        you will then need to use the clients method for setting a new transport factory. </p>
 */
public class CustomFactoryWrapper {
    Map<String, String> headers = new HashMap<String, String>();

    /**
     * Get the factory, and despite the client in the argument, you still need to use the client's
     * method for setting a new transport factory.
     * @param client
     * @return
     */
    public XmlRpcTransportFactory getFactory(XmlRpcClient client) {
        XmlRpcTransportFactory factory = new XmlRpcCommonsTransportFactory(client) {
            @Override
            public XmlRpcTransport getTransport() {
                return new XmlRpcCommonsTransport(this) {
                    @Override
                    protected void initHttpHeaders(XmlRpcRequest pRequest) throws XmlRpcClientException {
                        super.initHttpHeaders(pRequest);
                        //add custom header
                        for (String key : headers.keySet()) {
                            String value = headers.get(key);
                            setRequestHeader(key, value);
                        }
                    }
                };
            }
        };
        return factory;
    }

    /**
     * Add headers to the http request in key, value format
     * @param header
     * @param content
     */
    public void addHeader(String header, String content) {
        headers.put(header, content);
    }
}

package org.jrenner.glances;

import org.apache.xmlrpc.XmlRpcRequest;
import org.apache.xmlrpc.client.*;

import java.util.HashMap;
import java.util.Map;

public class CustomFactoryWrapper{
    Map<String, String> headers = new HashMap<String, String>();

    /**
     * You must first add headers before getting the factory
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
                            System.out.println("Trying to add header:" + key + " - " + value);
                            setRequestHeader(key, value);
                        }
                    }
                };
            }
        };
        return factory;
    }

    public void addHeader(String header, String content) {
        headers.put(header, content);
    }
}

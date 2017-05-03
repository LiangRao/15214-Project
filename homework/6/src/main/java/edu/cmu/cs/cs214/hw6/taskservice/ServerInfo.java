package edu.cmu.cs.cs214.hw6.taskservice;

import jdk.nashorn.internal.ir.annotations.Immutable;

import java.io.Serializable;

/**
 * A configuration class that describes the IP and port of a set of worker Servers. 
 *
 */
public class ServerInfo implements Serializable {
    @Immutable
    private final String hostName, serviceName;
    @Immutable
    private final int port;

    /**
     * A constructor
     * @param hostName the hostname of coordinator
     * @param serviceName the name a specific worker
     * @param port the port number of a specific worker
     */
    public ServerInfo(String hostName, String serviceName, int port) {
        this.hostName = hostName;
        this.serviceName = serviceName;
        this.port = port;
    }

    /**
     * Return the coordinator's hostname
     * @return the hostname
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * Return the worker's port number
     * @return
     */
    public int getPort() {
        return port;
    }

    /**
     * Return the worker's name
     * @return the worker's name
     */
    public String getServiceName() {
        return serviceName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServerInfo that = (ServerInfo) o;

        return port == that.port
                && (hostName != null ? hostName.equals(that.hostName) : that.hostName == null)
                && (serviceName != null ? serviceName.equals(that.serviceName) : that.serviceName == null);
    }

    @Override
    public int hashCode() {
        int result = hostName != null ? hostName.hashCode() : 0;
        result = 31 * result + (serviceName != null ? serviceName.hashCode() : 0);
        result = 31 * result + port;
        return result;
    }
}

package m.jedison;

import java.net.URI;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocketFactory;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.JedisSocketFactory;

/**
 * In case we need access to protected stuff, we extend Jedis main
 *
 * All below are constructors (thus far)
 *
 * @author Joseph
 */
public class Jedis extends redis.clients.jedis.Jedis {
    public Jedis() {}
    
    public Jedis(String host) {
        super(host);
    }
    
    public Jedis(HostAndPort hp) {
        super(hp);
    }
    
    public Jedis(String host, int port) {
        super(host, port);
    }
    
    public Jedis(String host, int port, boolean ssl) {
        super(host, port, ssl);
    }
    
    public Jedis(String host, int port, boolean ssl, SSLSocketFactory sslSocketFactory, SSLParameters sslParameters, HostnameVerifier hostnameVerifier) {
        super(host, port, ssl, sslSocketFactory, sslParameters, hostnameVerifier);
    }
    
    public Jedis(String host, int port, int timeout) {
        super(host, port, timeout);
    }
    
    public Jedis(String host, int port, int timeout, boolean ssl) {
        super(host, port, timeout, ssl);
    }
    
    public Jedis(String host, int port, int timeout, boolean ssl, SSLSocketFactory sslSocketFactory, SSLParameters sslParameters, HostnameVerifier hostnameVerifier) {
        super(host, port, timeout, ssl, sslSocketFactory, sslParameters, hostnameVerifier);
    }
    
    public Jedis(String host, int port, int connectionTimeout, int soTimeout) {
        super(host, port, connectionTimeout, soTimeout);
    }
    
    public Jedis(String host, int port, int connectionTimeout, int soTimeout, boolean ssl) {
        super(host, port, connectionTimeout, soTimeout, ssl);
    }
    
    public Jedis(String host, int port, int connectionTimeout, int soTimeout, boolean ssl, SSLSocketFactory sslSocketFactory, SSLParameters sslParameters, HostnameVerifier hostnameVerifier) {
        super(host, port, connectionTimeout, soTimeout, ssl, sslSocketFactory, sslParameters, hostnameVerifier);
    }
    
    public Jedis(JedisShardInfo shardInfo) {
        super(shardInfo);
    }
    
    public Jedis(URI uri) {
        super(uri);
    }
    
    public Jedis(URI uri, SSLSocketFactory sslSocketFactory, SSLParameters sslParameters, HostnameVerifier hostnameVerifier) {
        super(uri, sslSocketFactory, sslParameters, hostnameVerifier);
    }
    
    public Jedis(URI uri, int timeout) {
        super(uri, timeout);
    }
    
    public Jedis(URI uri, int timeout, SSLSocketFactory sslSocketFactory, SSLParameters sslParameters, HostnameVerifier hostnameVerifier) {
        super(uri, timeout, sslSocketFactory, sslParameters, hostnameVerifier);
    }
    
    public Jedis(URI uri, int connectionTimeout, int soTimeout) {
        super(uri, connectionTimeout, soTimeout);
    }
    
    public Jedis(URI uri, int connectionTimeout, int soTimeout, SSLSocketFactory sslSocketFactory, SSLParameters sslParameters, HostnameVerifier hostnameVerifier) {
        super(uri, connectionTimeout, soTimeout, sslSocketFactory, sslParameters, hostnameVerifier);
    }
    
    public Jedis(JedisSocketFactory jedisSocketFactory) {
        super(jedisSocketFactory);
    }
    
}

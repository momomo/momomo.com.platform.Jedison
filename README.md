### Background
A high performance Java Redis library offering  distributed locks, maps, sets, lists, queues, streams, etc.. to meet all your data needs.

### Under development, more documentation in due time... 
Under development and not fully made ready.    
Please look at the src folder for now for demo purposes or some short examples below.
Main starting point is **[Jedison](src/m/jedison/Jedison.java)** which can be iniated or subclassed as you prefer for a new instance.  

### Quick demo examples

```java
package m.bloomberg;

import m.jedison.Jedison;
import m.jedison.JMap;
import m.jedison.JSet;
import m.jedison.spaces.JSpaceMap;
import m.jedison.spaces.JSpaceSet;

class Bloomberg {
        private final Jedison jedison;
        {       
                // Normally done in the constructor but for your eyes, we place it right here
                JedisonConfigApache config = new JedisonConfigApache();
                
                config.setModuleName("bloomberg");
                config.setUrl(SysProp.REDIS_URL.get());
                config.setPort(SysProp.REDIS_PORT.asInt());
                config.setPassword(SysProp.REDIS_PASSWORD.get());
                config.getPoolConfig().setMinIdle(SysProp.REDIS_IDLE_MIN.asInt());
                
                this.jedison = new Jedison(config);
        }
        
        // A Map and a Set that we wish to store distributed database data in
        private final JMap<String, BloombergIn.Row> map = new JMap<>("bloomberg.map");
        private final JSet<String>                  set = new JSet<>("bloomberg.set");
        
        public Bloomberg() {
                // The constructor
        }
        
        // Exmaple 1
        private void insert(Map<String, Object> row) {
                BloombergIn.Row row = new BloombergIn.Row(row);
                
                // On the JMap, we place the row on the key ticker, typicall AAPL, or GOOGL, 
                // as it isserialized by magic on write, and deserialized by magic on read
                // Serialization can be customized to fit your needs
                map.put(row.ticker, row);
        }
        
        // Exmaple 2
        private void process() {
            // The JSet will contain say 8000 map, such as AAPL, GOOGL, which we iterate here
            // For each, we will call download to get the data, and then call parse, which we have not shared here     
            set.each(query -> {
                    String data = download(query);
                    
                    // Remove from distributed set
                    set.remove(query);
                    
                    parse(data);
            });
        }
        
        // Exmaple 3
        public void reset() {
                // We delete all data store in the distributed redis database 
                set.delete();
                map.delete();
        }
}
```

```java
class QueueExample {
        
    // A distributed concurrencly safe queue, that can be pushed messages on and consume messages from
    private  final JQueue<JedisonQueueProductImageDownloadMessage> queue = JQueue<>("bigbuy.image.download");
    
    
    void publisher() {
        // A publisher would do
        queue.queue( new JedisonQueueProductImageDownloadMessage(p, url, source) );
    }
    
    /////////////////////////////////////////////////////////////////////
    // The message definition and consumption happens in this case in the same class for simplicity. 
    /////////////////////////////////////////////////////////////////////
        
    public class JedisonQueueProductImageDownloadMessage extends JMessage {
        private final UUID       id;
        private final String     url;
        private final Source     source;
        
        public JedisonQueueProductImageDownloadMessage(Product product, String url, Source source) {
                this.id     = product.getId();
                this.url    = url;
                this.source = source;
        }
        
        /**
         * When a new message of this instance type is put on the queue and then one of the servers consume it, the process() method will be called to handle the message. 
         */
        @Override
        public void process() {
                
            // This is a distributed concurrently safe lock, that allows any server to lock on any string or instance, so that no other server may enter the same area   
            JLock lock = new JLock("product.image.download." + Hashcodes.hashcode(url));
            
                lock.call(() -> {
                    log.info("Got distributed lock for: ", lock.key(), " and product having id " + id);
                        
                    // Only one consumer can enter here and we can in a concurrently and distributed system get a decentralized lock and execute with safety. 
                    // Think bank transactions.
                    Banking.DB.requireTransaction(() -> {
                            Product product = Product.Service.get(id);
                            
                            ... do more ...
                    });
                        
                    log.info("Releasing distributed lock for: ", lock.key());
                });
            
            log.info("Released distributed lock for: ", lock.key());
        }
    }
        
    /////////////////////////////////////////////////////////////////////
    
} 
```

```java
    // Distributed concurrently safe locks 
    JLock lock = new JLock("product.image.download." + Hashcodes.hashcode(url));
    
    // Many subsribers will all get messages here, once per subscriber
    JStream stream = new JStream<>( "key.for.stream", new JedisonIdConsumerStreamQueue(jedison().id()) );

    // Instant and concurrently safe propagation of read and writes across distributed servers    
    JLive<Animal> favorites = new JLive<>("favorite.animals")
    
    // Can replace native Java based JSESSIONID server sessions with distributed one allowing ease of server delegation of request to any server  
    JSession 
    
    // Collection support
    JList, JSet, JMap  
    
    // Primitive support    
    // JLong, JBool, JString       
```

Also take a look at **[JSession](src/m/jedison/JSession.java)** which can be set up instead of a Java Enterprise JSession to be fully distributed and cached, and sync writes across all possible servers serving and interacting with a server. 
It persists the data and in case multiple servers are interacting with the same user, all will get to see the same data. If one or all goes down, the JSession can be fully restored when user reconnects. 
Default storage of the data is 180 days.     


#### Can be used with any Java objects, or Protobuf classes and will handle the serialization to and from Redis. 

 

### License
The full license can be found here [`MoL9`](https://raw.githubusercontent.com/momomo/momomo.com.yz.licenses/master/MoL9?raw=true?raw=true)

### Contribute
Send an email to `opensource{at}momomo.com` if you would like to contribute in any way, make changes or otherwise have thoughts and/or ideas on things to improve.

### Background
A high performance Java Redis library offering  distributed locks, maps, sets, lists, queues, streams, etc.. to meet all your data needs.

### Under development, more documentation in due time... 
Under development and not fully made ready.    
Please look at the src folder for now for demo purposes or some short examples below.
Main starting point is **[Jedison](src/m/jedison/Jedison.java)** which can be iniated or subclassed as you prefer for a new instance.  

### Quick demo examples

```java
package m.bloomberg;

import m.jedison.JMap;
import m.jedison.JSet;
import m.jedison.spaces.JSpaceMap;
import m.jedison.spaces.JSpaceSet;

class Bloomberg {
        private final Jedison jedison;
        
        private final JSet<String>                  queries = new JSet<>(new JSetKey("bloomberg.queries"));
        private final JMap<String, BloombergIn.Row> tickers = new JMap<>(new JMapKey("bloomberg.tickers"));
        
        public Bloomberg() {
                JedisonConfigApache config = new JedisonConfigApache();
                
                confit.setModuleName("bloomberg");
                config.setUrl(SysProp.REDIS_URL.get());
                config.setPort(SysProp.REDIS_PORT.asInt());
                config.setPassword(SysProp.REDIS_PASSWORD.get());
                config.getPoolConfig().setMinIdle(SysProp.REDIS_IDLE_MIN.asInt());
                
                this.jedison = new m.jedison.Jedison(config);
        }
        
        private void insert(Map<String, Object> row) {
                Row result = new Row(row);
                
                tickers.put(result.ticker, result);
        }
        
        private void process() {
            queries.each(query -> {
                    String downloaded = download(query);
                    
                    queries.remove(query);
                    
                    parse(downloaded);
            });
        }
        
        public void reset() {
                queries.delete();
                tickers.delete();
        }
}
```

```java
class Other {
    private  final JQueue<JedisonQueueProductImageDownloadMessage> queue = JQueue<>(new JQueueKey("bigbuy.image.download"));
    
    // A publisher would do
    void publisher() {
        queue.queue(new JedisonQueueProductImageDownloadMessage(p, url, source));
    }
    
    /////////////////////////////////////////////////////////////////////
    // The message definitioon and consumption happens in this case in the same class for simplicity. 
    // 
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
            JLock lock = new JLock(new JSpaceLock(Jedison.get().key(), "product.image.download." + Hashcodes.hashcode(url)));
            
            log.info(getClass(), "Getting distributed lock for: ", lock.key(), " and product having id " + id);
            
            lock.call(() -> {
                log.info(getClass(), "Got distributed lock for: ", lock.key(), " and product having id " + id);
                    
                // Only one consumer can enter here and we can in a concurrently and distributed system get a decentralized lock and execute with safety. 
                // Think bank transactions.
                
                Banking.DB.requireTransaction(() -> {
                        Product product = Product.S.get(id);
                        
                        ... does more ...
                });
                
            });
            
            log.info(getClass(), "Released distributed lock for: ", lock.key());
        }
    }
    
} 
```

Also take a look at **[JSession](src/m/jedison/JSession.java)** which can be set up instead of a Java Enterprise JSession to be fully distributed and cached, and sync writes across all possible servers serving and interacting with a server. 
It persists the data and in case multiple servers are interacting with the same user, all will get to see the same data. If one or all goes down, the JSession can be fully restored when user reconnects. 
Default storage of the data is 180 days.     


#### Can be used with any Java objects, or Protobuf classes and will handle the serialization to and from Redis. 



 

### License
The full license can be found here [`MoL9`](https://raw.githubusercontent.com/momomo/momomo.com.yz.licenses/master/MoL9?raw=true?raw=true)

### Contribute
Send an email to `opensource{at}momomo.com` if you would like to contribute in any way, make changes or otherwise have thoughts and/or ideas on things to improve.

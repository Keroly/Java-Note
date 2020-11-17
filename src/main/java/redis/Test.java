package redis;

import redis.clients.jedis.Jedis;

public class Test {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("101.200.211.66", 6379);
        jedis.auth("123456");
        System.out.println(jedis.ping());

    }
}

package redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

public class MULTI {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("101.200.211.66", 6379);
        jedis.auth("123456");

        Transaction transaction = jedis.multi();// 被当作一个命令进行执行

        Response<String> response;
        transaction.set("serialNum1", "s003");
        response = transaction.get("serialNum1");
        transaction.lpush("list3", "a");
        transaction.lpush("list3", "b");
        transaction.lpush("list3", "c");

        transaction.exec();
        // transaction.discard();
        System.out.println("serialNum : " + response.get());

    }
}

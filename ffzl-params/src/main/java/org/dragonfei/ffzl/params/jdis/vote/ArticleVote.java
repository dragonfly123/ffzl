package org.dragonfei.ffzl.params.jdis.vote;

import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

/**
 * Created by longfei on 16/8/24.
 */
public class ArticleVote {
    final int ONE_WEEK_IN_SECONDS = 7 * 86400;
    final long VOTE_SCORE = 432;
    final Jedis jedis;
    public ArticleVote(){
        jedis = new Jedis();
    }
    public void articleVote(Jedis jedis,String user,String article){
       Long cutoff = System.currentTimeMillis()-ONE_WEEK_IN_SECONDS;
        if(jedis.zscore("time:",article) < cutoff){
            return;
        }
        String articleId = article.split(":")[1];
        if(jedis.sadd("voted:"+articleId,user)>0){
            jedis.zincrby("score:",VOTE_SCORE,article);
            jedis.hincrBy(article,"votes",1);
        }
    }

    public void postArticle(Jedis jedis,String user,String titile,String line){
        String auticle_id = String.valueOf(jedis.incr("article:"));
        String voted = "voted:"+auticle_id;
        jedis.sadd(voted,user);
        jedis.expire(voted,ONE_WEEK_IN_SECONDS);
        long now = System.currentTimeMillis();
        String article = "article:"+auticle_id;
        jedis.hmset()
    }
}

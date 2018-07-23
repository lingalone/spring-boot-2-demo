package io.github.lingalone.springboottokenssoclientdemo.util;



import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @author <a href="https://github.com/lingalone">lingalone</a>
 * @version 1.0.0
 * @link
 * @since 2018/7/23
 */

@Component
public class JwtUtil {

    private static final String REDIS_SET_ACTIVE_SUBJECTS = "active-subjects";


    @Autowired
    RedisUtil redisUtil;
    /**
     * 生成Token
     * @param signingKey
     * @param subject
     * @return
     */
    public String generateToken(String signingKey, String subject) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        JwtBuilder builder = Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, signingKey);
        /*return builder.compact();*/

        String token = builder.compact();
        redisUtil.add(REDIS_SET_ACTIVE_SUBJECTS, subject);
        return token;
    }

    /**
     * 解析Token
     * @param httpServletRequest
     * @param jwtTokenCookieName
     * @param signingKey
     * @return
     */
    public String getSubject(HttpServletRequest httpServletRequest, String jwtTokenCookieName, String signingKey){
        String token = CookieUtil.getValue(httpServletRequest, jwtTokenCookieName);
        if(token == null) {
            return null;
        }
        /*return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody().getSubject();*/
        String subject = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody().getSubject();
        if (!redisUtil.isMember(REDIS_SET_ACTIVE_SUBJECTS, subject)) {
            return null;
        }
        return subject;
    }

    public void invalidateRelatedTokens(HttpServletRequest httpServletRequest) {
        redisUtil.del(REDIS_SET_ACTIVE_SUBJECTS, (String) httpServletRequest.getAttribute("username"));
    }
}

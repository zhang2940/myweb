package com.example.myweb.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import java.util.Date;
import java.util.Map;


public class JwtUtil {
    private static JwtUtil jwtUtil=null;

    private JwtUtil(){

    }

    public static JwtUtil getInstance(){
        if (jwtUtil==null){
            jwtUtil=new JwtUtil();
        }
        return jwtUtil;
    }

    public boolean verify(String token){
        try{
            Algorithm algorithm=Algorithm.HMAC256("secret");
            JWTVerifier auth0 = JWT.require(algorithm).withIssuer("auth0").build();
            auth0.verify(token);
            return true;
        }catch (JWTVerificationException e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 创建token
     * @param name map key
     * @param map map
     * @param subject 主题
     * @param expiresAt 超时时间
     * @param issuedAt 签发时间
     * @return
     */
    public String create(String name, Map<String,String> map,String subject,Date expiresAt,Date issuedAt){
        Algorithm secret = Algorithm.HMAC256("secret");
        String token = JWT.create().withIssuer("auth0").withSubject(subject).withClaim(name,map).withExpiresAt(expiresAt).withIssuedAt(issuedAt).sign(secret);
        return token;

    }
//    public static void main(String[] args) {
//        System.out.println(create());
//        Algorithm secret = Algorithm.HMAC256("secret");
//        JWTVerifier verifier=JWT.require(secret).withIssuer("auth0").build();
//        DecodedJWT verify = verifier.verify(create());
//
//        String payload = verify.getPayload();
//        String name = verify.getClaim("name").asString();
//        String header =verify.getHeader();
//        System.out.println(payload +"====="+name+"====="+header+"==="+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(verify.getExpiresAt()));
//    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import com.openuproject.shopappserver.dtos.UserDto;
import exceptions.AuthorizationException;
import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author ronna
 */
public class JwtManager {
    
    private final Dotenv dotenv = Dotenv.load();
    private String SECRET_KEY = dotenv.get("JWT_SECRET");

    public Claims getDecodedJwtFromContext(ContainerRequestContext reqContext){
        String jwt = getJwtFromContext(reqContext);
        return decodeJWT(jwt);
    }
    
    public String getJwtFromRequest(HttpServletRequest request){
        try {
            return request.getHeader("authorization").split(" ")[1];
        } catch (Exception e) {
            throw new AuthorizationException("Failed to get JWT from request ");
        }
    }

    
    public String getJwtFromContext(ContainerRequestContext reqContext){
        MultivaluedMap<String, String> map = reqContext.getHeaders();
        return getJwtFromHeaders(map);
    }
    
    public String getJwtFromHeaders(MultivaluedMap<String, String> map){
        List<String> auth = map.get("authorization");
        if (auth == null || auth.size() != 1) throw new AuthorizationException();
        return auth.get(0).split(" ")[1];
    }
    
    public int getUserIdFromContext(ContainerRequestContext reqContext){
        String jwt = getJwtFromContext(reqContext);
        Claims jwtClaims = decodeJWT(jwt);
        return jwtClaims.get("userId", Integer.class);
    }

    public int getUserIdFromRequest(HttpServletRequest request){
        String jwt = getJwtFromRequest(request);
        Claims jwtClaims = decodeJWT(jwt);
        return jwtClaims.get("userId", Integer.class);
    }

    
    public String createJWT(String id, String issuer, String subject, long ttlMillis, Map<String, Object> claims) {
  
    //The JWT signature algorithm we will be using to sign the token
    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    long nowMillis = System.currentTimeMillis();
    Date now = new Date(nowMillis);

    //We will sign our JWT with our ApiKey secret
    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

    //Let's set the JWT Claims
    JwtBuilder builder = Jwts.builder()
            .setId(id)
            .setSubject(subject)
            .setIssuer(issuer);
    claims.forEach((key, value) -> {
        switch (key) {
            case "iss":
                builder.setIssuer((String) value);
                break;
            case "sub":
                builder.setSubject((String) value);
                break;
            case "aud":
                builder.setAudience((String) value);
                break;
            case "jti":
                builder.setId((String) value);
                break;
            default:
                builder.claim(key, value);
        }
    });
    if (ttlMillis > 0) {
        long expMillis = nowMillis + ttlMillis;
        Date exp = new Date(expMillis);
        builder.setExpiration(exp);
    }  
    builder
            .setIssuedAt(now)
            .signWith(signatureAlgorithm, signingKey);
    return builder.compact();
}

    
    public Claims decodeJWT(String jwt) {
    Claims claims = Jwts.parser()
            .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
            .parseClaimsJws(jwt).getBody();
    return claims;
    }
    
    public String createUserJWT(UserDto userDto){
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString();
        String issuer = "Admin";
        String subject = userDto.getUsername();
        long exp = 1000000000L;
        Map map = new HashMap<String, Object>();
        map.put("permissions", userDto.getPermissions());
        map.put("userId", userDto.getId());
        return createJWT(id, issuer, subject, exp, map);
    }
    
    public boolean checkIfAdminOrMe(int userId, HttpServletRequest request){
        String jwt = getJwtFromRequest(request);
        Claims claims = decodeJWT(jwt);
        int jwtId = claims.get("userId", Integer.class);
        String userPermissions = claims.get("permissions", String.class);
        return userId == jwtId || userPermissions.equals("Admin");
    }
    
}

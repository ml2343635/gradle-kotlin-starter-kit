package com.monstarlab.service.component

import com.monstarlab.domain.database.User
import com.monstarlab.domain.enum.UserRole
import com.monstarlab.domain.security.JwtToken
import com.monstarlab.domain.security.JwtUser
import com.monstarlab.service.property.JwtProperties
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Component
import java.util.*

@Component
@EnableConfigurationProperties(JwtProperties::class)
class JwtComponent(
    private val jwtProperties: JwtProperties,
) {
    private val jwtSecretKey = Base64.getDecoder().decode(jwtProperties.secretKey)
    private val expirationTime = jwtProperties.expirationTime * 1000

    fun generateUserToken(user: User): String {
        return Jwts.builder()
            .setHeaderParam("typ", TOKEN_TYPE)
            .signWith(Keys.hmacShaKeyFor(jwtSecretKey), SignatureAlgorithm.HS256)
            .setSubject(user.id!!.toString())
            .setIssuer(TOKEN_ISSUER)
            .setIssuedAt(Date())
            .setAudience(TOKEN_AUDIENCE)
            .setExpiration(Date(System.currentTimeMillis() + expirationTime))
            .claim(ROLE, user.role.toString())
            .compact()
    }

    fun getJwtUser(jwtToken: JwtToken): JwtUser {
        val claims = Jwts.parserBuilder()
            .setSigningKey(jwtSecretKey)
            .build()
            .parseClaimsJws(jwtToken.token)
            .body
        return JwtUser(
            userId = claims.subject.toString().toLong(),
            role = claims[ROLE]?.let { UserRole.valueOf(it.toString()) } ?: UserRole.UNKNOWN,
        )
    }

    companion object {
        private val TOKEN_TYPE = "JWT"
        private val TOKEN_ISSUER = "security"
        private val TOKEN_AUDIENCE = "security-all"
        private val ROLE = "role"
    }
}
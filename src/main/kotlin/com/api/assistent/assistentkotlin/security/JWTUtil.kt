package com.api.assistent.assistentkotlin.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

@Component
class JWTUtil {

    @Value("\${jwt.secret}")
    private lateinit var secret : String

    private val expiration = 60000L

    fun generateToken(username : String?) : String {
        return Jwts
                .builder()
                .setSubject(username)
                .setExpiration(Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret.toByteArray())
                .compact()
    }

    fun isTokenValid(token : String) : Boolean {
        val claims = getClaimsToken(token)
        if (claims != null) {
            val username = claims.subject
            val expiration = claims.expiration
            val now = Date(System.currentTimeMillis())
            if (username != null && expiration != null && now.before(expiration)) {
                return true
            }
        }
        return false
    }

    private fun getClaimsToken(token : String) : Claims? {
        return try {
            Jwts.parser().setSigningKey(secret.toByteArray()).parseClaimsJws(token).body
        } catch (e : Exception) {
            null
        }
    }

    fun getUserName(token : String) : String{
        val claims = getClaimsToken(token)
        return claims!!.subject
    }
}
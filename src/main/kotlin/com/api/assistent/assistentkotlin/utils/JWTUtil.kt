package com.api.assistent.assistentkotlin.utils

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*
import javax.print.attribute.standard.JobOriginatingUserName

@Component
class JWTUtil {

    @Value("\${jwt.secret}")
    private lateinit var secret : String
    private val expiration = 60000L

    fun generateToken(userName: String?) : String?{
        return Jwts.builder()
                .setSubject(userName)
                .setExpiration(Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret.toByteArray()).compact()
    }

    fun isValidToken(token : String) : Boolean {
        val claimsToken = getClainsToken(token)
        if (claimsToken != null) {
            val userName = claimsToken.subject
            val expiration = claimsToken.expiration
            val now = Date(System.currentTimeMillis())

            return userName != null && expiration != null && now.before(expiration)
        }
        return false
    }

    private fun getClainsToken(token : String) : Claims? {
        return try {
            Jwts.parser().setSigningKey(secret.toByteArray()).parseClaimsJws(token).body
        } catch (e : Exception) {
            null
        }
    }

    fun getUserName(token : String) : String? = getClainsToken(token)!!.subject
}
package com.api.assistent.assistentkotlin.utils

import com.google.gson.Gson
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.io.IOException
import java.util.*
import javax.servlet.http.HttpServletResponse
import org.apache.logging.log4j.kotlin.logger
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import javax.servlet.http.HttpServletRequest


class TokenServiceAuthentication {

    companion object {

        private val logger = logger()
        private const val EXPIRATION_TIME = 60000 * 60
        private const val SECRET  = "MySecretApp"
        private const val TOKEN_PREFIX =  "Bearer"
        private const val HEADER_STRING = "Authorization"
        private const val  RESET_PASSWORD_PREFIX = "RESET+PREFIX"
        private const val CONTENT_TYPE = "Content-Type"

        fun addAuthentication(response : HttpServletResponse, username : String) {
            val jwt = Jwts.builder()
                    .setSubject(username)
                    .setExpiration(Date(System.currentTimeMillis() + EXPIRATION_TIME    ))
                    .signWith(SignatureAlgorithm.HS512, SECRET)
                    .compact()

            if(username.equals("opt")) {
                writeToken(response, "option ok")
                return
            }

            val values = username.split("/")
            val token = TOKEN_PREFIX.plus(" ").plus(jwt)
            response.addHeader(HEADER_STRING, token)
            response.addHeader(CONTENT_TYPE, "application/json;charset=UTF-8")

            val tokenResponse = TokenResponse()

            tokenResponse.token = token
            tokenResponse.userId = values[1]
            tokenResponse.userName = values[2]
            tokenResponse.isFirstLogin = values[3].toBoolean()

            writeToken(response, Gson().toJson(tokenResponse))
        }

        fun writeToken(response : HttpServletResponse, token : String) {
            try {
                response.characterEncoding = "UTF-8"
                response.writer.write(token)
            } catch(e : IOException) {
                this.logger.error(e)
            }
        }

        fun generateTokenResetPassword(userId : Int) : String {
           return Jwts.builder().setSubject(RESET_PASSWORD_PREFIX + userId.toString())
                    .setExpiration(Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .signWith(SignatureAlgorithm.HS512, SECRET).compact()
        }

        fun validateTokenResetPassword(token : String) : String {
            return Jwts.parser().setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .body
                    .subject.replace(RESET_PASSWORD_PREFIX, "")
        }

        private fun getByToken(token : String) : Authentication? {
            val user : String? = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .body.subject
            return if (user != null) UsernamePasswordAuthenticationToken(user, null, null) else null
        }

        fun getAuthentication(request : HttpServletRequest) : Authentication? {
            val token = request.getHeader(HEADER_STRING)
            return if (token != null) getByToken(token) else null
        }
    }
}
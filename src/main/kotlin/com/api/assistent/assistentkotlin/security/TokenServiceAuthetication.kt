package com.api.assistent.assistentkotlin.security

import com.api.assistent.assistentkotlin.model.TokenResponse
import com.api.assistent.assistentkotlin.utils.*
import com.google.gson.Gson
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.apache.logging.log4j.kotlin.logger
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import java.io.IOException
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class TokenServiceAuthetication {
    companion object {

        val LOGGER = logger()

        fun addAuthentication(res : HttpServletResponse, userName : String) {
            if (userName == "opt") {
                writeToken(res, "option ok")
                return
            }

            val jwt = Jwts.builder().setSubject(userName).
            setExpiration(Date(System.currentTimeMillis() + EXPIRATIONTIME)).signWith(SignatureAlgorithm.HS512, SECRET)

            val values: Array<String> = userName.split("/".toRegex()).toTypedArray()

            val token = "$AUTHORIZATION_VALUE $jwt"

            res.addHeader(AUTHORIZATION,token)
            res.addHeader(CONTENT_TYPE, "application/json;charset=UTF-8")

            Gson().toJson(mountTokenResponse(values, token))

            writeToken(res, token)

        }

        private fun writeToken(res: HttpServletResponse, token : String) {
            try {
                res.characterEncoding = "UTF-8"
                res.writer.write(token)
            } catch(e : IOException) {
                res.writer.write(token)
                LOGGER.error(e.message.toString())
            }
        }

        private fun mountTokenResponse(values : Array<String>, token : String) : TokenResponse {
            val tokenResponse = TokenResponse()
            tokenResponse.token = token
            tokenResponse.userId = values[1]
            tokenResponse.userName = values[2]
            return tokenResponse
        }

       private fun getByToken(token : String) : Authentication? {
           val user = Jwts.parser().setSigningKey(SECRET)
                   .parseClaimsJws(token.replace(AUTHORIZATION_VALUE, ""))
                   .body.subject

           return if (user != null) UsernamePasswordAuthenticationToken(user, null, null) else null

        }

        fun getAuthentication(request : HttpServletRequest)  :Authentication?    {
            val token = request.getHeader(AUTHORIZATION)
            return if(token != null) getByToken(token) else null!!
        }
    }
}
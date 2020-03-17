package com.api.assistent.assistentkotlin.entities

import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull
import kotlin.jvm.Transient

@Entity
@Table(name = "login")
data class LoginEntitie(

        @Id
        @NotNull
        val id : Long? = 0,

        @JoinColumn(name = "id_user")
        @ManyToOne
        var user : UserEntitie? = null,

        @Column(name="data_login")
        var dataLogin : Date? = null

) {

    constructor() : this(null) {

    }
}

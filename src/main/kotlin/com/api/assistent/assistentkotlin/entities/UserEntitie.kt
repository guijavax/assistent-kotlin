package com.api.assistent.assistentkotlin.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "user_assistent")
data class UserEntitie(

        @Id
        @NotNull
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_id_client")
        @SequenceGenerator(name="sequence_id_client", sequenceName = "seq_id_user", initialValue = 1, allocationSize=1)
        val idUser : Long? = 0,

        @OneToOne
        @JoinColumn(name = "id_client")
        val client : ClientEntitie? = null,

        @Column
        @NotNull
        var username : String? = "",

        @Column
        @NotNull
        var password : String = "",

        @Column
        val type : Char? = null,

        @Column
        var email : String = "",

        @OneToMany(mappedBy = "user",cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        @JsonIgnore
        val logins :    List<LoginEntitie>? = emptyList<LoginEntitie>()

) {
    constructor() : this(null) {
    }
}

package com.api.assistent.assistentkotlin.entities

import com.sun.istack.NotNull
import javax.persistence.*

@Entity
@Table(name = "services")
data class ServiceEntitie(

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_id_seq")
        @SequenceGenerator(name = "seq_id_seq", sequenceName = "seq_service")
        var idService : Long? = 0,

        @Column(name = "service_name")
        @NotNull
        var serviceName : String ? = "",

        @Column(name="type_service")
        @OneToOne(mappedBy = "typeService", cascade = [CascadeType.ALL] )
        @NotNull
        var typeService : ServiceTypeEntitie? = null



)
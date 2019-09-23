package com.api.assistent.assistentkotlin.entities

import com.sun.istack.NotNull
import javax.persistence.*

@Entity
@Table(name = "service_type")
data class ServiceTypeEntitie(

         @Id
         var idServiceType: Long? = 0L,

         @JoinColumn
         @OneToOne
         var serviceEntitie : ServiceEntitie? = null,

         @Column(name="type_name")
         @NotNull
         var typeName : String? = ""


 )
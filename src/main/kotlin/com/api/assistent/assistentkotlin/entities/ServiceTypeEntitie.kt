package com.api.assistent.assistentkotlin.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import com.sun.istack.NotNull
import javax.persistence.*

@Entity
@Table(name = "service_type")
data class ServiceTypeEntitie(

         @Id
         var idServiceType: Long? = 0L,


         @OneToMany(mappedBy = "typeService", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
         @JsonIgnore
         var servicesEntitie : List<ServiceEntitie>? = null,

         @Column(name="type_name")
         @NotNull
         var typeName : String? = ""


 )
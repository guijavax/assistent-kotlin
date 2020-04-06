package com.api.assistent.assistentkotlin.entities

import java.math.BigDecimal
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "services")
data class ServiceEntitie (

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_id_seq")
        @SequenceGenerator(name = "seq_id_seq", sequenceName = "seq_service", allocationSize = 1)
        var idService : Long?,

        @Column(name = "service_name")
        @NotNull
        var serviceName : String ? = "",

        @JoinColumn(name = "id_type_service")
        @ManyToOne
        var typeService : ServiceTypeEntitie? = null,

        @Column(name="execution_function")
        @NotNull
        var executionFunction : String? = "",

        @Column(name="service_price")
        @NotNull
        var priceService : Double? = null,

        @Column(name="begin_date")
        var beginDate : Date? = null,

        @Column(name="experation_date")
        var experateDate: Date? = null
) {
        constructor() : this(null, null, null, null, null, null, null) {}
}
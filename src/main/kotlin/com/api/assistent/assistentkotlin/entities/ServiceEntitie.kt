package com.api.assistent.assistentkotlin.entities

import com.sun.istack.NotNull
import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.*

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
        var priceService : BigDecimal? = null,

        @Column(name="begin_date")
        @NotNull
        var beginDate : LocalDate? = null,

        @Column(name="experation_date")
        @NotNull
        var experateDate: LocalDate? = null

)
package com.api.assistent.assistentkotlin.entities

import com.sun.istack.NotNull
import org.hibernate.validator.constraints.Length
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.validation.constraints.Max

@Embeddable
data class AddressClient(
        @Column(name="zip_code")
        val zipCode : Int = 0,

        @Column(name="street")
        @NotNull
        val street : String? = null,

        @Column(name="number")
        @NotNull
        val number : Int? = 0,

        @Column(name="district")
        @NotNull
        val district : String? = null,

        @Column(name="city")
        @NotNull
        val city : String? = null,

        @Column(name="state")
        @NotNull
        @Max(value = 2L)
        val state : String? = null,

        @Column(name="country")
        @NotNull
        val country : String? = null
) {}
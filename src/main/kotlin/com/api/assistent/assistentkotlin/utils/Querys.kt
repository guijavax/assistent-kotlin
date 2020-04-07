package com.api.assistent.assistentkotlin.utils

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import javax.persistence.EntityManagerFactory

internal interface Querys {

    companion object {

       const val selectProductByType : String = """
            SELECT pr.* from products pr
            INNER JOIN type_product tp ON tp.id_type = pr.id_type_product
            WHERE tp.id_type = :type
        """
    }


}
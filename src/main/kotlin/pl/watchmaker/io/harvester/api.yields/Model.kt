package pl.watchmaker.io.harvester.yields

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.time.Year

@Document
data class Yield(
        @Id val id: String? = null,
        val year: Year,
        @DBRef val field: Field,
        @DBRef val cropType: String,
        val expectedYieldInTonnes: Long,
        val actualYieldInTonnes: Long?,
        val expectedPricePerTone: BigDecimal
)

@Document
data class Field(
        @Id val id: String? = null,
        @DBRef val owner: User,
        val sizeInHectares: BigDecimal,
        @DBRef val location: FieldLocation
)

@Document
data class User(
        @Id val email: String,
        val firstName: String,
        val lastName: String,
        val phoneNo: Set<String>,
        val address: Address
)

@Document
data class CropType(
        @Id val code: String,
        val name: String,
        val details: String
)

@Document
data class FieldLocation(
        @Id val id: String? = null,
        val country: String,
        val province: String,
        val county: String,
        val postalCode: String,
        val city: String,
        val roadType: String
)

@Document
data class Address(
        @Id val id: String? = null,
        val country: String,
        val province: String,
        val county: String,
        val postalCode: String,
        val city: String,
        val street: String
)

@Document
data class RoadType(
        @Id val code: String,
        val name: String,
        val description: String
)

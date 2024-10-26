package tymex.test.currencyconverter.models

import com.google.gson.annotations.SerializedName

data class ExchangeRate(
    @SerializedName("base")
    val base: String = "",
    @SerializedName("timestamp")
    val timestamp: Int = 0,
    @SerializedName("rates")
    val rates: Map<String,String> = emptyMap()
)

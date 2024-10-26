package tymex.test.currencyconverter.APIs

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import tymex.test.currencyconverter.models.ExchangeRate

public interface ApiService {
    companion object {
        const val BASE_URL = "https://openexchangerates.org/"
    }
    @GET("api/currencies.json?show_alternative=1")
    fun getSymbols(): Call<Map<String, String>>
    @GET("api/latest.json?")
    fun getRates(@Query("app_id") keyAPI: String ): Call<ExchangeRate>
}
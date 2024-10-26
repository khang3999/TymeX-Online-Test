package tymex.test.currencyconverter.activities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tymex.test.currencyconverter.APIs.ApiService
import tymex.test.currencyconverter.databinding.MainLayoutBinding
import tymex.test.currencyconverter.models.ExchangeRate
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainLayoutBinding
    private lateinit var listCurrencyUnit: ArrayList<String>
    private lateinit var listCountryName: ArrayList<String>
    private lateinit var apiService: ApiService;
    private lateinit var adapterSpinner: ArrayAdapter<String>
    private lateinit var currencyFrom: String
    private lateinit var currencyTo: String
    private lateinit var rates: Map<String, String>
    private lateinit var exchangeRate: ExchangeRate
    private val context = baseContext
    private val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
    // Change this API key if you run out of requests.
    private val API_KEY:String = "064c243112a24b44ae648e5cceb060c3"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listCurrencyUnit = ArrayList()
        listCountryName = ArrayList()
        exchangeRate = ExchangeRate()
        rates = emptyMap()

        // Create and set adapter
        adapterSpinner  = ArrayAdapter( this,androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listCurrencyUnit)
        binding.spinnerSymbolFrom.setAdapter(adapterSpinner)
        binding.spinnerSymbolTo.setAdapter(adapterSpinner)
        // Init Fetch symbols
        getSymbols()
        // Init fetch exChangeRates
        getExchangeRates()
//

        // Listen event input edit text
        binding.edtAmount.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                convertCurrency(currencyFrom, currencyTo)
            }
            override fun afterTextChanged(s: Editable?) {
            }

        })

        // Listen event change item on spinner
        binding.spinnerSymbolFrom.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?,view: View?,position: Int,id: Long) {
                currencyFrom = parent?.getItemAtPosition(position) as String
                binding.currencyFromLabel.setText(listCountryName.get(position) + " ("+ listCurrencyUnit.get(position)+")")
                // Update exchange
                getExchangeRates()
                convertCurrency(currencyFrom, currencyTo)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        binding.spinnerSymbolTo.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?,view: View?,position: Int,id: Long) {
                currencyTo = parent?.getItemAtPosition(position) as String
                binding.currencyToLabel.setText(listCountryName.get(position) + " ("+ listCurrencyUnit.get(position)+")")
                // Update exchange
                getExchangeRates()
                convertCurrency(currencyFrom, currencyTo)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    private fun convertCurrency(currencyFrom: String, currencyTo: String){
        val amount = binding.edtAmount.text.toString().toDoubleOrNull()?:0.0
//        val rateFrom= exchangeRate.rates.get(currencyFrom)?.toString().toDouble()
//        val rateTo = exchangeRate.rates.get(currencyTo)?.toString().toDouble()

        val rateFrom= exchangeRate.rates.get(currencyFrom)?.toDoubleOrNull()
        val rateTo= exchangeRate.rates.get(currencyTo)?.toDoubleOrNull()
        if (rateFrom == null || rateTo == null) {
            binding.rateLabel.setText("Tỷ giá không hợp lệ")
            return
        }
        // Set Result
        val rate = rateTo/rateFrom
        val result = rate * amount
        val roundedAmount = BigDecimal(result).setScale(2, RoundingMode.UP).toDouble()
        binding.resultExchange.setText(roundedAmount.toString())
        // Set rate text
        val tempFormat = BigDecimal(rate).setScale(2, RoundingMode.UP).toDouble()
        val rateString = "1 $currencyFrom = $tempFormat $currencyTo "
        binding.rateLabel.setText(rateString)
    }
//     Define function fetch data exchangeRate
    private fun getExchangeRates(){
        // Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(ApiService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Get ApiService
        apiService = retrofit.create(ApiService::class.java)

        // getRates
        val call: Call<ExchangeRate> = apiService.getRates(API_KEY);

        //Parse data
        call.enqueue(object : Callback<ExchangeRate> {
            override fun onResponse(call: Call<ExchangeRate>, response: Response<ExchangeRate>) {
                if (response.isSuccessful){
                    val exchangeRates = response.body()
                    exchangeRates?.let {
                        exchangeRate = exchangeRates
                        val date = Date(exchangeRates.timestamp.toString().toLong()*1000)
                        binding.timestamp.setText("At: " + dateFormat.format(date))
                    }
                }
            }
            override fun onFailure(call: Call<ExchangeRate>, t: Throwable) {
                // Log
                Log.e("API Error", "Error occurred: ${t.message}", t)
                // Show for client
                Toast.makeText(context, "Network failure!", Toast.LENGTH_SHORT).show()
            }
        })
    }

    // Define function fetch data symbols
    private fun getSymbols(){
        listCurrencyUnit.clear()
        listCountryName.clear()
        // Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(ApiService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        // Get ApiService
        apiService = retrofit.create(ApiService::class.java)
        // getSymbols
        val call: Call<Map<String, String>> = apiService.getSymbols();
        //Parse data
        call.enqueue(object : Callback<Map<String, String>> {
            override fun onResponse(call: Call<Map<String,String>>, response: Response<Map<String, String>>) {
                if (response.isSuccessful){
                    val symbolsList = response.body()
                    symbolsList?.let {
                        listCurrencyUnit.addAll(symbolsList.keys.toList())  // Giả sử bạn có `weathersList` trong `WeatherList`
                        listCountryName.addAll(symbolsList.values.toList())
                        currencyFrom = listCurrencyUnit[0]
                        currencyTo = listCurrencyUnit[0]
                        adapterSpinner.notifyDataSetChanged()
                        binding.spinnerSymbolFrom.setSelection(0)
                        binding.spinnerSymbolTo.setSelection(0)
                    }
                }
            }
            override fun onFailure(call: Call<Map<String, String>>, t: Throwable) {
                // Log
                Log.e("API Error", "Error occurred: ${t.message}", t)

                // Show for client
                Toast.makeText(context, "Network failure!", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
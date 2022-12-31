package com.daniebeler.raadio

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.daniebeler.raadio.interfaces.ApiInterface
import com.daniebeler.raadio.models.Station
import com.daniebeler.raadio.ui.theme.RaadioTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://de1.api.radio-browser.info/json/"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RaadioTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }

        getData()
    }

    private fun getData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getStationsByName()

        retrofitData.enqueue(object : Callback<List<Station>?> {
            override fun onResponse(
                call: Call<List<Station>?>,
                response: Response<List<Station>?>
            ) {
                Log.d("fief res", response.body().toString())
            }

            override fun onFailure(call: Call<List<Station>?>, t: Throwable) {
                Log.d("fief err", t.toString())
            }
        })
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RaadioTheme {
        Greeting("Android")
    }
}
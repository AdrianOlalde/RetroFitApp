package com.example.retrofitapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.retrofitapp.remote.RetrofitBuilder
import com.example.retrofitapp.remote.PokemonEntry
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = RetrofitBuilder.create()
        val response = retrofit.getPokemonById("1")

        response.enqueue(object: Callback<PokemonEntry>{
            override fun onResponse(call: Call<PokemonEntry>, response: Response<PokemonEntry>) {
                Log.d("retrofitResponse", "${response.body()}")
                val resp = response.body()
                if (resp != null){
                    Log.d("retrofitResponse", "Estadisticas")
                    val stats = resp.stats

                        for (state in stats){
                            Log.d("retrofitResponse", "${state.stat_name.stat}: ${state.base_stat}")
                        }
                    Log.d("retrofitResponse", "Tipo")
                    val types = resp.types
                        Log.d("retrofitResponse", "Type: ${types[0].type.name}")
                    val sprite = resp.sprites
                    Log.d("retrofitResponse", "${sprite.sprite}")
                }

            }


            override fun onFailure(call: Call<PokemonEntry>, t: Throwable) {
                Log.w("retrofitResponse", "error: ${t.message}")
            }

        })
    }
}
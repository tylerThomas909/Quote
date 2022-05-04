package com.example.quotes

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import okhttp3.OkHttpClient
import okhttp3.Request
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        quoteCall()
    }
    private fun quoteCall() {
        val textView: TextView = findViewById(R.id.quoteQuery)
        Thread {
            val client = OkHttpClient()
            val url = URL("https://zenquotes.io/api/random")
            val request = Request.Builder()
                .url(url)
                .get()
                .build()
            val response = client.newCall(request).execute()
            val responseBody = response.body!!.string()
            runOnUiThread {
                if (response.isSuccessful){
                    ("Response Body: $responseBody").also { textView.text = it }
                }else{
                    "Error".also { textView.text = it }
                }
            }
        }.start()
    }
}

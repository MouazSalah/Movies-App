package com.paymob.movies.modules.common_views.nointernet

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.paymob.movies.databinding.ActivityNoInternetBinding
import com.paymob.movies.extesnion.isNetworkAvailable

class NoInternetActivity : AppCompatActivity() {

    lateinit var binding: ActivityNoInternetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoInternetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRetry.setOnClickListener {
            if (isNetworkAvailable(this)) {
                val returnIntent = Intent()
                setResult(Activity.RESULT_OK, returnIntent)
                finish()

            } else {
                Toast.makeText(this, "Internet not available. Please try again.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
package com.example.zenv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.zenv.databinding.ActivityMainBinding
import com.example.zenv.databinding.ActivityWellcomeBinding

class WellcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWellcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wellcome)

        binding = ActivityWellcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
package com.example.modelviewproject4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.modelviewproject4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModels: MainActivityViewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        binding.textView.text = viewModels.counter.toString()

        binding.button.setOnClickListener {
            viewModels.increment()
            binding.textView.text = viewModels.counter.toString()
        }
    }
}
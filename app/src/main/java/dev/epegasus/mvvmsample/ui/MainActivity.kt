package dev.epegasus.mvvmsample.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dev.epegasus.mvvmsample.databinding.ActivityMainBinding
import dev.epegasus.mvvmsample.helper.viewmodels.MyViewModel

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // One Way
        //Toast.makeText(this, viewModel.value.toString(), Toast.LENGTH_SHORT).show()
        viewModel.updateValue()
        //Toast.makeText(this, viewModel.value.toString(), Toast.LENGTH_SHORT).show()


        // Two Way
        initObservers()

        binding.btnSubmit.setOnClickListener {
            viewModel.createSafeLoop()
        }
    }

    private fun initObservers() {
        viewModel.numberLiveData.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

}
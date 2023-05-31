package com.applaunch.paginationdemo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.applaunch.paginationdemo.R
import com.applaunch.paginationdemo.databinding.ActivityCameraBinding
import com.applaunch.paginationdemo.pagination.interfaces.AddPhotosListener

class CameraActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCameraBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
package com.applaunch.paginationdemo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.applaunch.paginationdemo.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomIndicatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_indicator)

        val navView: BottomNavigationView = findViewById(R.id.navView)
        val textMessage = findViewById<TextView>(R.id.message)

        navView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> textMessage.setText(R.string.title_home)
                R.id.navigation_dashboard -> textMessage.setText(R.string.title_dashboard)
                R.id.navigation_notifications -> textMessage.setText(R.string.title_notifications)
                else -> null
            } != null
        }
    }

}

package com.applaunch.paginationdemo.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.applaunch.paginationdemo.R
import com.applaunch.paginationdemo.gson.Employee
import com.google.gson.Gson


class GsonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gson)

        /*val employee = Employee("John",30,"john@gmail.com")
        val json = Gson().toJson(employee)
        Log.d("jsonString", "onCreate: $json")*/
        val json = "{\"first_name\":\"John\",\"age\":30,\"mail\":\"john@gmail.com\"}"
        val employee = Gson().fromJson(json, Employee::class.java)
        Log.d("jsonString", "onCreate: $employee")
    }
}
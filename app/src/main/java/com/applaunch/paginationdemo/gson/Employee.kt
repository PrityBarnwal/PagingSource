package com.applaunch.paginationdemo.gson

import com.google.gson.annotations.SerializedName


class Employee(
    @field:SerializedName("first_name")
    private val mFirstName: String,

    @field:SerializedName("age")
    private val mAge: Int,

    @field:SerializedName("mail")
    private val mMail: String
)
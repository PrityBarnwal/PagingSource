package com.applaunch.paginationdemo.pagination.interfaces

import android.view.View
import com.applaunch.paginationdemo.camera.model.Picture
import com.applaunch.paginationdemo.pagination.model.Result

var listenerPlus :((view: View, position:Int, item:Result) -> Unit)? = null
var listenerMinus :((view: View, position:Int, item:Result) -> Unit)? = null
var listener: ((view: View, item: Picture, position: Int) -> Unit)? = null

interface AddPhotosListener {
    fun onPositionClick(position: Int?,size: Int)
}
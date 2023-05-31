package com.applaunch.paginationdemo.navigation

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

val Fragment.handler by lazy { Handler(Looper.getMainLooper()) }

open class BaseFragment<VB : ViewBinding>(
    private val inflate: Inflate<VB>
) : Fragment() {
    private var _binding: VB? = null
    val binding: VB get() = _binding!!
    var fcmToken = ""
    val hasBinding: Boolean get() = _binding != null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)


        binding.initialize()

        return binding.root
    }
    open fun VB.initialize() {
        /* Ignore for now*/
    }
}

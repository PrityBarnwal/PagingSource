package com.applaunch.paginationdemo.navigation

import android.R
import android.widget.ArrayAdapter
import com.applaunch.paginationdemo.databinding.FragmentNotificationBinding

class NotificationFragment : BaseFragment<FragmentNotificationBinding>(FragmentNotificationBinding::inflate) {

    override fun FragmentNotificationBinding.initialize() {
        lvNnotifications.adapter = ArrayAdapter(requireContext(),
            R.layout.simple_list_item_1,getNotifications())
    }
    private fun getNotifications():List<String>{
        val notifications = mutableListOf<String>()

        for(i in 1..20){
            notifications.add("Notification # $i")
        }
        return notifications
    }
}
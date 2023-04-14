package it.localhostsoftware.maps.model

import android.content.Context
import it.localhostsoftware.maps.MobileServices
import it.localhostsoftware.maps.getMobileServices

class ButtCap<C>(c: C) : Cap<C>(c) {
    companion object {
        fun getInstance(c: Context) =
                when (c.getMobileServices()) {
                    MobileServices.GOOGLE -> ButtCap(com.google.android.gms.maps.model.ButtCap())
                    MobileServices.HUAWEI -> ButtCap(com.huawei.hms.maps.model.ButtCap())
                    else -> throw IllegalStateException()
                }
    }
}
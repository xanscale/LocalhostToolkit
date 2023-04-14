package it.localhostsoftware.maps.model

import android.content.Context
import it.localhostsoftware.maps.MobileServices
import it.localhostsoftware.maps.getMobileServices

class RoundCap<C>(c: C) : Cap<C>(c) {
    companion object {
        fun getInstance(c: Context) =
                when (c.getMobileServices()) {
                    MobileServices.GOOGLE -> RoundCap(com.google.android.gms.maps.model.RoundCap())
                    MobileServices.HUAWEI -> RoundCap(com.huawei.hms.maps.model.RoundCap())
                    else -> throw IllegalStateException()
                }
    }
}
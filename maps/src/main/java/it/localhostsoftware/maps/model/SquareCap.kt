package it.localhostsoftware.maps.model

import android.content.Context
import it.localhostsoftware.maps.MobileServices
import it.localhostsoftware.maps.getMobileServices

class SquareCap<C>(c: C) : Cap<C>(c) {
    companion object {
        fun getInstance(context: Context) =
                when (context.getMobileServices()) {
                    MobileServices.GOOGLE -> SquareCap(com.google.android.gms.maps.model.SquareCap())
                    MobileServices.HUAWEI -> SquareCap(com.huawei.hms.maps.model.SquareCap())
                    else -> throw IllegalStateException()
                }
    }
}
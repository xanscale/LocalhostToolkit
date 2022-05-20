package it.localhostsoftware.maps.model

import android.content.Context
import it.localhostsoftware.maps.MobileServices
import it.localhostsoftware.maps.getMobileServices

class PatternItem<PI>(val pi: PI) {
    companion object {
        fun getInstance(c: Context, var1: Int, var2: Float) =
                when (c.getMobileServices()) {
                    MobileServices.GOOGLE -> PatternItem(com.google.android.gms.maps.model.PatternItem(var1, var2))
                    MobileServices.HUAWEI -> PatternItem(com.huawei.hms.maps.model.PatternItem(var1, var2))
                    else -> throw IllegalStateException()
                }
    }
}
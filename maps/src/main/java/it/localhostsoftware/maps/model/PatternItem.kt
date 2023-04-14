package it.localhostsoftware.maps.model

import android.content.Context
import it.localhostsoftware.maps.MobileServices
import it.localhostsoftware.maps.getMobileServices
import it.localhostsoftware.maps.google.model.GooglePatternItem
import it.localhostsoftware.maps.huawei.model.HuaweiPatternItem

abstract class PatternItem<PI>(val pi: PI) {
    companion object {
        fun getInstance(c: Context, var1: Int, var2: Float) =
            when (c.getMobileServices()) {
                MobileServices.GOOGLE -> GooglePatternItem(com.google.android.gms.maps.model.PatternItem(var1, var2))
                MobileServices.HUAWEI -> HuaweiPatternItem(com.huawei.hms.maps.model.PatternItem(var1, var2))
                else -> throw IllegalStateException()
            }
    }
}
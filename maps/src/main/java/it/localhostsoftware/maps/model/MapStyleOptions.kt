package it.localhostsoftware.maps.model

import android.content.Context
import it.localhostsoftware.maps.MobileServices
import it.localhostsoftware.maps.getMobileServices

class MapStyleOptions<MSO>(val mso: MSO) {
    companion object {
        fun getInstance(c: Context, googleJson: String, huaweiJson: String) =
                when (c.getMobileServices()) {
                    MobileServices.GOOGLE -> MapStyleOptions(com.google.android.gms.maps.model.MapStyleOptions(googleJson))
                    MobileServices.HUAWEI -> MapStyleOptions(com.huawei.hms.maps.model.MapStyleOptions(huaweiJson))
                    else -> throw IllegalStateException()
                }

        fun loadRawResourceStyle(c: Context, googleResId: Int, huaweiResId: Int) =
                when (c.getMobileServices()) {
                    MobileServices.GOOGLE -> MapStyleOptions(com.google.android.gms.maps.model.MapStyleOptions.loadRawResourceStyle(c, googleResId))
                    MobileServices.HUAWEI -> MapStyleOptions(com.huawei.hms.maps.model.MapStyleOptions.loadRawResourceStyle(c, huaweiResId))
                    else -> throw IllegalStateException()
                }
    }
}
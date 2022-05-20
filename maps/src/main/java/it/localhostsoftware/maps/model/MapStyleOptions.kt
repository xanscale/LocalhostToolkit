package it.localhostsoftware.maps.model

import android.content.Context
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.huawei.hms.api.HuaweiApiAvailability

class MapStyleOptions<MSO>(val mso: MSO) {
    companion object {
        fun getInstance(context: Context, googleJson: String, huaweiJson: String) =
                if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == ConnectionResult.SUCCESS) MapStyleOptions(com.google.android.gms.maps.model.MapStyleOptions(googleJson))
                else if (HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(context) == com.huawei.hms.api.ConnectionResult.SUCCESS) MapStyleOptions(com.huawei.hms.maps.model.MapStyleOptions(huaweiJson))
                else throw IllegalStateException()

        fun loadRawResourceStyle(context: Context, googleResId: Int, huaweiResId: Int) =
                if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == ConnectionResult.SUCCESS) MapStyleOptions(com.google.android.gms.maps.model.MapStyleOptions.loadRawResourceStyle(context, googleResId))
                else if (HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(context) == com.huawei.hms.api.ConnectionResult.SUCCESS) MapStyleOptions(com.huawei.hms.maps.model.MapStyleOptions.loadRawResourceStyle(context, huaweiResId))
                else throw IllegalStateException()
    }
}
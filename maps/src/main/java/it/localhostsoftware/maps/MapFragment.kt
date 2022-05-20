package it.localhostsoftware.maps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.huawei.hms.api.HuaweiApiAvailability
import com.huawei.hms.maps.HuaweiMap
import it.localhostsoftware.maps.google.GoogleMapOptions
import it.localhostsoftware.maps.huawei.HuaweiMapOptions

class MapFragment : Fragment() {
    companion object {
        const val GOOGLE_MAP_OPTIONS = "GoogleMapOptions"
        const val HUAWEI_MAP_OPTIONS = "HuaweiMapOptions"
        fun newInstance(mapOptions: MapOptions<*>) = MapFragment().apply {
            arguments = Bundle().apply {
                if (mapOptions is GoogleMapOptions) putParcelable(GOOGLE_MAP_OPTIONS, mapOptions.mo)
                else if (mapOptions is HuaweiMapOptions) putParcelable(HUAWEI_MAP_OPTIONS, mapOptions.mo)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_map, container, false)
        childFragmentManager.beginTransaction().replace(R.id.fragmentContainerView,
                if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(requireContext()) == ConnectionResult.SUCCESS)
                    if (arguments != null && requireArguments().containsKey(GOOGLE_MAP_OPTIONS)) SupportMapFragment.newInstance(requireArguments().getParcelable(GOOGLE_MAP_OPTIONS))
                    else SupportMapFragment.newInstance()
                else if (HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(requireContext()) == com.huawei.hms.api.ConnectionResult.SUCCESS)
                    if (arguments != null && requireArguments().containsKey(HUAWEI_MAP_OPTIONS)) com.huawei.hms.maps.SupportMapFragment.newInstance(requireArguments().getParcelable(HUAWEI_MAP_OPTIONS))
                    else com.huawei.hms.maps.SupportMapFragment.newInstance()
                else throw IllegalStateException()
        ).commitAllowingStateLoss()
        return v
    }

    fun getMapAsync(callback: OnMapReadyCallback) {
        childFragmentManager.registerFragmentLifecycleCallbacks(object : FragmentManager.FragmentLifecycleCallbacks() {
            override fun onFragmentViewCreated(fragmentManager: FragmentManager, f: Fragment, view: View, savedInstanceState: Bundle?) {
                childFragmentManager.findFragmentById(R.id.fragmentContainerView).let { f ->
                    if (f is SupportMapFragment) {
                        fragmentManager.unregisterFragmentLifecycleCallbacks(this)
                        f.getMapAsync { googleMap: GoogleMap -> callback.onMapReady(it.localhostsoftware.maps.google.GoogleMap(googleMap)) }
                    } else if (f is com.huawei.hms.maps.SupportMapFragment) {
                        fragmentManager.unregisterFragmentLifecycleCallbacks(this)
                        f.getMapAsync { googleMap: HuaweiMap -> callback.onMapReady(it.localhostsoftware.maps.huawei.HuaweiMap(googleMap)) }
                    }
                }
            }
        }, false)
    }
}
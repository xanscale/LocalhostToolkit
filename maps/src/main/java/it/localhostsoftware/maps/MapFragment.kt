package it.localhostsoftware.maps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import it.localhostsoftware.maps.google.GoogleMap
import it.localhostsoftware.maps.google.GoogleMapOptions
import it.localhostsoftware.maps.huawei.HuaweiMap
import it.localhostsoftware.maps.huawei.HuaweiMapOptions

class MapFragment : Fragment() {
    companion object {
        private const val MAP_OPTIONS = "mapOptions"
        fun newInstance(mapOptions: MapOptions<*, *, *>?) = MapFragment().apply {
            arguments = Bundle().apply {
                if (mapOptions is GoogleMapOptions) putParcelable(MAP_OPTIONS, mapOptions.mo)
                else if (mapOptions is HuaweiMapOptions) putParcelable(MAP_OPTIONS, mapOptions.mo)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_map, container, false)
        childFragmentManager.beginTransaction().replace(
            R.id.fragmentContainerView,
            when (requireContext().getMobileServices()) {
                MobileServices.GOOGLE ->
                    if (arguments != null && requireArguments().containsKey(MAP_OPTIONS))
                        com.google.android.gms.maps.SupportMapFragment.newInstance(requireArguments().getParcelable(MAP_OPTIONS))
                    else com.google.android.gms.maps.SupportMapFragment.newInstance()
                MobileServices.HUAWEI ->
                    if (arguments != null && requireArguments().containsKey(MAP_OPTIONS))
                        com.huawei.hms.maps.SupportMapFragment.newInstance(requireArguments().getParcelable(MAP_OPTIONS))
                    else com.huawei.hms.maps.SupportMapFragment.newInstance()
                else -> throw IllegalStateException()
            }
        ).commitAllowingStateLoss()
        return v
    }

    fun getMapAsync(geomap: (GeoMap<*, *, *, *, *, *, *, *, *, *, *, *, *, *, *, *, *, *, *, *>) -> Unit) {
        childFragmentManager.registerFragmentLifecycleCallbacks(object : FragmentManager.FragmentLifecycleCallbacks() {
            override fun onFragmentViewCreated(fragmentManager: FragmentManager, f: Fragment, view: View, savedInstanceState: Bundle?) {
                childFragmentManager.findFragmentById(R.id.fragmentContainerView).let { mapFragment ->
                    if (mapFragment is com.google.android.gms.maps.SupportMapFragment) {
                        fragmentManager.unregisterFragmentLifecycleCallbacks(this)
                        mapFragment.getMapAsync { geomap(GoogleMap(it)) }
                    } else if (mapFragment is com.huawei.hms.maps.SupportMapFragment) {
                        fragmentManager.unregisterFragmentLifecycleCallbacks(this)
                        mapFragment.getMapAsync { geomap(HuaweiMap(it)) }
                    }
                }
            }
        }, false)
    }
}
package it.localhostsoftware.maps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.BundleCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.gms.maps.GoogleMapOptions
import com.huawei.hms.maps.HuaweiMapOptions
import it.localhostsoftware.maps.google.GoogleMap
import it.localhostsoftware.maps.huawei.HuaweiMap

class MapFragment : Fragment() {
    companion object {
        private const val MAP_OPTIONS = "mapOptions"
        fun newInstance(mapOptions: MapOptions<*, *, *>?) = MapFragment().apply {
            arguments = Bundle().apply {
                mapOptions?.mo?.let { putParcelable(MAP_OPTIONS, it) }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = inflater.inflate(R.layout.fragment_map, container, false).also {
        childFragmentManager.beginTransaction().replace(
            R.id.fragmentContainerView, when (requireContext().getMobileServices()) {
                MobileServices.GOOGLE -> {
                    if (arguments?.containsKey(MAP_OPTIONS) == true) com.google.android.gms.maps.SupportMapFragment.newInstance(BundleCompat.getParcelable(requireArguments(), MAP_OPTIONS, GoogleMapOptions::class.java))
                    else com.google.android.gms.maps.SupportMapFragment.newInstance()
                }

                MobileServices.HUAWEI -> {
                    if (arguments?.containsKey(MAP_OPTIONS) == true) com.huawei.hms.maps.SupportMapFragment.newInstance(BundleCompat.getParcelable(requireArguments(), MAP_OPTIONS, HuaweiMapOptions::class.java))
                    else com.huawei.hms.maps.SupportMapFragment.newInstance()
                }

                else -> throw IllegalStateException()
            }
        ).commitAllowingStateLoss()
    }

    fun getMapAsync(block: (GeoMap<*, *, *, *, *, *, *, *, *, *, *, *, *, *, *, *, *, *, *, *, *>) -> Unit) {
        childFragmentManager.registerFragmentLifecycleCallbacks(object : FragmentManager.FragmentLifecycleCallbacks() {
            override fun onFragmentViewCreated(fragmentManager: FragmentManager, f: Fragment, view: View, savedInstanceState: Bundle?) {
                childFragmentManager.findFragmentById(R.id.fragmentContainerView).let { mapFragment ->
                    if (mapFragment is com.google.android.gms.maps.SupportMapFragment) {
                        fragmentManager.unregisterFragmentLifecycleCallbacks(this)
                        mapFragment.getMapAsync { block(GoogleMap(it)) }
                    } else if (mapFragment is com.huawei.hms.maps.SupportMapFragment) {
                        fragmentManager.unregisterFragmentLifecycleCallbacks(this)
                        mapFragment.getMapAsync { block(HuaweiMap(it)) }
                    }
                }
            }
        }, false)
    }
}
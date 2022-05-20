package it.localhostsoftware.maps;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.common.GoogleApiAvailability;
import com.huawei.hms.api.HuaweiApiAvailability;

import it.localhostsoftware.maps.google.GoogleMap;
import it.localhostsoftware.maps.google.GoogleMapOptions;
import it.localhostsoftware.maps.huawei.HuaweiMap;
import it.localhostsoftware.maps.huawei.HuaweiMapOptions;

public class MapFragment extends Fragment {
    public static final String GOOGLE_MAP_OPTIONS = "GoogleMapOptions";
    public static final String HUAWEI_MAP_OPTIONS = "HuaweiMapOptions";

    public static MapFragment newInstance(MapOptions<?> mapOptions) {
        Bundle args = new Bundle();
        if (mapOptions instanceof GoogleMapOptions)
            args.putParcelable(GOOGLE_MAP_OPTIONS, (com.google.android.gms.maps.GoogleMapOptions) mapOptions.getMapOptions());
        else if (mapOptions instanceof HuaweiMapOptions)
            args.putParcelable(HUAWEI_MAP_OPTIONS, (com.huawei.hms.maps.HuaweiMapOptions) mapOptions.getMapOptions());
        MapFragment fragment = new MapFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map, container, false);
        Fragment fragment;
        if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(requireContext()) == com.google.android.gms.common.ConnectionResult.SUCCESS)
            fragment = getArguments() != null && getArguments().containsKey(GOOGLE_MAP_OPTIONS) ?
                    com.google.android.gms.maps.SupportMapFragment.newInstance(getArguments().getParcelable(GOOGLE_MAP_OPTIONS)) :
                    com.google.android.gms.maps.SupportMapFragment.newInstance();
        else if (HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(requireContext()) == com.huawei.hms.api.ConnectionResult.SUCCESS)
            fragment = getArguments() != null && getArguments().containsKey(HUAWEI_MAP_OPTIONS) ?
                    com.huawei.hms.maps.SupportMapFragment.newInstance(getArguments().getParcelable(HUAWEI_MAP_OPTIONS)) :
                    com.huawei.hms.maps.SupportMapFragment.newInstance();
        else throw new IllegalStateException();
        getChildFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, fragment).commitAllowingStateLoss();
        return v;
    }

    public void getMapAsync(OnMapReadyCallback callback) {
        getChildFragmentManager().registerFragmentLifecycleCallbacks(new FragmentManager.FragmentLifecycleCallbacks() {
            @Override
            public void onFragmentViewCreated(@NonNull FragmentManager fragmentManager, @NonNull Fragment f, @NonNull View view, @Nullable Bundle savedInstanceState) {
                Fragment fragment = getChildFragmentManager().findFragmentById(R.id.fragmentContainerView);
                if (fragment instanceof com.google.android.gms.maps.SupportMapFragment) {
                    fragmentManager.unregisterFragmentLifecycleCallbacks(this);
                    ((com.google.android.gms.maps.SupportMapFragment) fragment).getMapAsync(googleMap -> callback.onMapReady(new GoogleMap(googleMap)));
                } else if (fragment instanceof com.huawei.hms.maps.SupportMapFragment) {
                    fragmentManager.unregisterFragmentLifecycleCallbacks(this);
                    ((com.huawei.hms.maps.SupportMapFragment) fragment).getMapAsync(googleMap -> callback.onMapReady(new HuaweiMap(googleMap)));
                }
            }
        }, false);
    }
}

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
import it.localhostsoftware.maps.huawei.HuaweiMap;

public class MapFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map, container, false);
        Fragment fragment;
        if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(requireContext()) == com.google.android.gms.common.ConnectionResult.SUCCESS)
            fragment = com.google.android.gms.maps.SupportMapFragment.newInstance();
        else if (HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(requireContext()) == com.huawei.hms.api.ConnectionResult.SUCCESS)
            fragment = com.huawei.hms.maps.SupportMapFragment.newInstance();
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

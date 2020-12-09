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
        if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(requireContext()) == com.google.android.gms.common.ConnectionResult.SUCCESS)
            return inflater.inflate(R.layout.fragment_map_google, container, false);
        else if (HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(requireContext()) == com.huawei.hms.api.ConnectionResult.SUCCESS)
            return inflater.inflate(R.layout.fragment_map_huawei, container, false);
        else throw new IllegalStateException();
    }

    public void getMapAsync(OnMapReadyCallback callback) {
        getParentFragmentManager().registerFragmentLifecycleCallbacks(new FragmentManager.FragmentLifecycleCallbacks() {
            @Override
            public void onFragmentViewCreated(@NonNull FragmentManager fragmentManager, @NonNull Fragment f, @NonNull View view, @Nullable Bundle savedInstanceState) {
                Fragment fragment = getChildFragmentManager().findFragmentById(R.id.mapFragmentContainerView);
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

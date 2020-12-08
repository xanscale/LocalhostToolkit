package it.localhostsoftware.maps;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.SupportMapFragment;

import it.localhostsoftware.maps.map.GoogleMap;

public class HeterogeneousMapFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(requireContext()) == ConnectionResult.SUCCESS)
            return inflater.inflate(R.layout.fragment_map_google, container, false);
        else return null;
    }

    public void getMapAsync(OnMapReadyCallback callback) {
        getParentFragmentManager().registerFragmentLifecycleCallbacks(new FragmentManager.FragmentLifecycleCallbacks() {
            @Override
            public void onFragmentViewCreated(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @NonNull View view, @Nullable Bundle savedInstanceState) {
                if (fragment instanceof SupportMapFragment) {
                    fragmentManager.unregisterFragmentLifecycleCallbacks(this);
                    ((SupportMapFragment) fragment).getMapAsync(googleMap -> callback.onMapReady(new GoogleMap(googleMap)));
                }
            }
        }, false);
    }
}

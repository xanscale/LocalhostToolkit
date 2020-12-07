package it.localhostsoftware.maps;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.SupportMapFragment;

import it.localhostsoftware.maps.map.GoogleMap;

public class HeterogeneousMapFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    public void getMapAsync(OnMapReadyCallback callback) {
        ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.fragmentContainerView))
                .getMapAsync(googleMap -> callback.onMapReady(new GoogleMap(googleMap)));
    }
}

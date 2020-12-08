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
    private SupportMapFragment fragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragment = SupportMapFragment.newInstance();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map, container, false);
        getChildFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, fragment).commitAllowingStateLoss();
        return v;
    }

    public void getMapAsync(OnMapReadyCallback callback) {
        fragment.getMapAsync(googleMap -> callback.onMapReady(new GoogleMap(googleMap)));
    }
}

package it.localhostsoftware.maps.google.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import it.localhostsoftware.maps.model.LatLng;
import it.localhostsoftware.maps.model.PatternItem;
import it.localhostsoftware.maps.model.PolygonOptions;

public class GooglePolygonOptions extends PolygonOptions<com.google.android.gms.maps.model.PolygonOptions> {
    public GooglePolygonOptions(com.google.android.gms.maps.model.PolygonOptions polygonOptions) {
        super(polygonOptions);
    }

    @NonNull
    @Override
    public PolygonOptions<?> add(@NonNull LatLng<?> point) {
        getPolygonOptions().add((com.google.android.gms.maps.model.LatLng) point.getLatLng());
        return this;
    }

    @NonNull
    @Override
    public PolygonOptions<?> add(@NonNull LatLng<?>... points) {
        List<com.google.android.gms.maps.model.LatLng> out = new ArrayList<>(points.length);
        for (LatLng<?> value : points)
            out.add((com.google.android.gms.maps.model.LatLng) value.getLatLng());
        getPolygonOptions().add(out.toArray(new com.google.android.gms.maps.model.LatLng[0]));
        return this;
    }

    @NonNull
    @Override
    public PolygonOptions<?> addAll(@NonNull Iterable<LatLng<?>> points) {
        List<com.google.android.gms.maps.model.LatLng> out = new ArrayList<>();
        for (LatLng<?> value : points)
            out.add((com.google.android.gms.maps.model.LatLng) value.getLatLng());
        getPolygonOptions().addAll(out);
        return this;
    }

    @NonNull
    @Override
    public PolygonOptions<?> addHole(@NonNull Iterable<LatLng<?>> points) {
        List<com.google.android.gms.maps.model.LatLng> out = new ArrayList<>();
        for (LatLng<?> value : points)
            out.add((com.google.android.gms.maps.model.LatLng) value.getLatLng());
        getPolygonOptions().addHole(out);
        return this;
    }

    @NonNull
    @Override
    public PolygonOptions<?> strokeWidth(float width) {
        getPolygonOptions().strokeWidth(width);
        return this;
    }

    @NonNull
    @Override
    public PolygonOptions<?> strokeColor(int color) {
        getPolygonOptions().strokeColor(color);
        return this;
    }

    @NonNull
    @Override
    public PolygonOptions<?> strokeJointType(int jointType) {
        getPolygonOptions().strokeJointType(jointType);
        return this;
    }

    @NonNull
    @Override
    public PolygonOptions<?> strokePattern(@Nullable List<PatternItem<?>> pattern) {
        if (pattern == null)
            getPolygonOptions().strokePattern(null);
        else {
            List<com.google.android.gms.maps.model.PatternItem> out = new ArrayList<>(pattern.size());
            for (PatternItem<?> value : pattern)
                out.add((com.google.android.gms.maps.model.PatternItem) value.getPatternItem());
            getPolygonOptions().strokePattern(out);
        }
        return this;
    }

    @NonNull
    @Override
    public PolygonOptions<?> fillColor(int color) {
        getPolygonOptions().fillColor(color);
        return this;
    }

    @NonNull
    @Override
    public PolygonOptions<?> zIndex(float zIndex) {
        getPolygonOptions().zIndex(zIndex);
        return this;
    }

    @NonNull
    @Override
    public PolygonOptions<?> visible(boolean visible) {
        getPolygonOptions().visible(visible);
        return this;
    }

    @NonNull
    @Override
    public PolygonOptions<?> geodesic(boolean geodesic) {
        getPolygonOptions().geodesic(geodesic);
        return this;
    }

    @NonNull
    @Override
    public PolygonOptions<?> clickable(boolean clickable) {
        getPolygonOptions().clickable(clickable);
        return this;
    }

    @NonNull
    @Override
    public List<LatLng<?>> getPoints() {
        List<LatLng<?>> out = new ArrayList<>(getPolygonOptions().getPoints().size());
        for (com.google.android.gms.maps.model.LatLng value : getPolygonOptions().getPoints())
            out.add(new GoogleLatLng(value));
        return out;
    }

    @NonNull
    @Override
    public List<List<LatLng<?>>> getHoles() {
        List<List<LatLng<?>>> out = new ArrayList<>(getPolygonOptions().getHoles().size());
        for (List<com.google.android.gms.maps.model.LatLng> subIn : getPolygonOptions().getHoles()) {
            out.add(new ArrayList<>(subIn.size()));
            for (com.google.android.gms.maps.model.LatLng value : subIn)
                out.get(out.size() - 1).add(new GoogleLatLng(value));
        }
        return out;
    }

    @Override
    public float getStrokeWidth() {
        return getPolygonOptions().getStrokeWidth();
    }

    @Override
    public int getStrokeColor() {
        return getPolygonOptions().getStrokeColor();
    }

    @Override
    public int getStrokeJointType() {
        return getPolygonOptions().getStrokeJointType();
    }

    @Nullable
    @Override
    public List<PatternItem<?>> getStrokePattern() {
        if (getPolygonOptions().getStrokePattern() == null)
            return null;
        else {
            List<PatternItem<?>> out = new ArrayList<>(getPolygonOptions().getStrokePattern().size());
            for (com.google.android.gms.maps.model.PatternItem value : getPolygonOptions().getStrokePattern())
                out.add(new PatternItem<>(value));
            return out;
        }
    }

    @Override
    public int getFillColor() {
        return getPolygonOptions().getFillColor();
    }

    @Override
    public float getZIndex() {
        return getPolygonOptions().getZIndex();
    }

    @Override
    public boolean isVisible() {
        return getPolygonOptions().isVisible();
    }

    @Override
    public boolean isGeodesic() {
        return getPolygonOptions().isGeodesic();
    }

    @Override
    public boolean isClickable() {
        return getPolygonOptions().isClickable();
    }
}

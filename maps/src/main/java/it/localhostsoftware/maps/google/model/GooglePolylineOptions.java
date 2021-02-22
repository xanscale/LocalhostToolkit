package it.localhostsoftware.maps.google.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import it.localhostsoftware.maps.model.Cap;
import it.localhostsoftware.maps.model.LatLng;
import it.localhostsoftware.maps.model.PatternItem;
import it.localhostsoftware.maps.model.PolylineOptions;

public class GooglePolylineOptions extends PolylineOptions<com.google.android.gms.maps.model.PolylineOptions> {
    public GooglePolylineOptions(com.google.android.gms.maps.model.PolylineOptions polylineOptions) {
        super(polylineOptions);
    }

    @Override
    public PolylineOptions<?> add(LatLng<?> var1) {
        getPolylineOptions().add((com.google.android.gms.maps.model.LatLng) var1.getLatLng());
        return this;
    }

    @Override
    public PolylineOptions<?> add(LatLng<?>... var1) {
        for (LatLng<?> value : var1)
            add(value);
        return this;
    }

    @Override
    public PolylineOptions<?> addAll(Iterable<LatLng<?>> var1) {
        for (LatLng<?> value : var1)
            add(value);
        return this;
    }

    @Override
    public PolylineOptions<?> width(float var1) {
        getPolylineOptions().width(var1);
        return this;
    }

    @Override
    public PolylineOptions<?> color(int var1) {
        getPolylineOptions().color(var1);
        return this;
    }

    @Override
    public PolylineOptions<?> startCap(@NonNull Cap<?> var1) {
        getPolylineOptions().startCap((com.google.android.gms.maps.model.Cap) var1.getCap());
        return this;
    }

    @Override
    public PolylineOptions<?> endCap(@NonNull Cap<?> var1) {
        getPolylineOptions().endCap((com.google.android.gms.maps.model.Cap) var1.getCap());
        return this;
    }

    @Override
    public PolylineOptions<?> jointType(int var1) {
        getPolylineOptions().jointType(var1);
        return this;
    }

    @Override
    public PolylineOptions<?> pattern(@Nullable List<PatternItem<?>> var1) {
        if (var1 == null)
            getPolylineOptions().pattern(null);
        else {
            ArrayList<com.google.android.gms.maps.model.PatternItem> values = new ArrayList<>(var1.size());
            for (PatternItem<?> value : var1)
                values.add((com.google.android.gms.maps.model.PatternItem) value.getPatternItem());
            getPolylineOptions().pattern(values);
        }
        return this;
    }

    @Override
    public PolylineOptions<?> zIndex(float var1) {
        getPolylineOptions().zIndex(var1);
        return this;
    }

    @Override
    public PolylineOptions<?> visible(boolean var1) {
        getPolylineOptions().visible(var1);
        return this;
    }

    @Override
    public PolylineOptions<?> geodesic(boolean var1) {
        getPolylineOptions().geodesic(var1);
        return this;
    }

    @Override
    public PolylineOptions<?> clickable(boolean var1) {
        getPolylineOptions().clickable(var1);
        return this;
    }

    @Override
    public List<LatLng<?>> getPoints() {
        ArrayList<LatLng<?>> out = new ArrayList<>(getPolylineOptions().getPoints().size());
        for (com.google.android.gms.maps.model.LatLng latLng : getPolylineOptions().getPoints())
            out.add(new GoogleLatLng(latLng));
        return out;
    }

    @Override
    public float getWidth() {
        return getPolylineOptions().getWidth();
    }

    @Override
    public int getColor() {
        return getPolylineOptions().getColor();
    }

    @NonNull
    @Override
    public Cap<?> getStartCap() {
        return new Cap<>(getPolylineOptions().getStartCap());
    }

    @NonNull
    @Override
    public Cap<?> getEndCap() {
        return new Cap<>(getPolylineOptions().getEndCap());
    }

    @Override
    public int getJointType() {
        return getPolylineOptions().getJointType();
    }

    @Nullable
    @Override
    public List<PatternItem<?>> getPattern() {
        if (getPolylineOptions().getPattern() == null)
            return null;
        else {
            ArrayList<PatternItem<?>> out = new ArrayList<>(getPolylineOptions().getPattern().size());
            for (com.google.android.gms.maps.model.PatternItem value : getPolylineOptions().getPattern())
                out.add(new GooglePatternItem(value));
            return out;
        }
    }

    @Override
    public float getZIndex() {
        return getPolylineOptions().getZIndex();
    }

    @Override
    public boolean isVisible() {
        return getPolylineOptions().isVisible();
    }

    @Override
    public boolean isGeodesic() {
        return getPolylineOptions().isGeodesic();
    }

    @Override
    public boolean isClickable() {
        return getPolylineOptions().isClickable();
    }
}

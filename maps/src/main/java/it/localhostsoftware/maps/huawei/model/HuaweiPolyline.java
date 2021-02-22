package it.localhostsoftware.maps.huawei.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import it.localhostsoftware.maps.model.Cap;
import it.localhostsoftware.maps.model.LatLng;
import it.localhostsoftware.maps.model.PatternItem;
import it.localhostsoftware.maps.model.Polyline;

public class HuaweiPolyline extends Polyline<com.huawei.hms.maps.model.Polyline> {
    public HuaweiPolyline(com.huawei.hms.maps.model.Polyline polyline) {
        super(polyline);
    }

    @Override
    public void remove() {
        getPolyline().remove();
    }

    @Override
    public String getId() {
        return getPolyline().getId();
    }

    @Override
    public void setPoints(List<LatLng<?>> var1) {
        ArrayList<com.huawei.hms.maps.model.LatLng> out = new ArrayList<>(var1.size());
        for (LatLng<?> value : var1)
            out.add((com.huawei.hms.maps.model.LatLng) value.getLatLng());
        getPolyline().setPoints(out);
    }

    @Override
    public List<LatLng<?>> getPoints() {
        ArrayList<LatLng<?>> out = new ArrayList<>(getPolyline().getPoints().size());
        for (com.huawei.hms.maps.model.LatLng value : getPolyline().getPoints())
            out.add(new HuaweiLatLng(value));
        return out;
    }

    @Override
    public void setWidth(float var1) {
        getPolyline().setWidth(var1);
    }

    @Override
    public float getWidth() {
        return getPolyline().getWidth();
    }

    @Override
    public void setColor(int var1) {
        getPolyline().setColor(var1);
    }

    @Override
    public int getColor() {
        return getPolyline().getColor();
    }

    @Override
    public void setStartCap(@NonNull Cap<?> var1) {
        getPolyline().setStartCap((com.huawei.hms.maps.model.Cap) var1.getCap());
    }

    @NonNull
    @Override
    public Cap<?> getStartCap() {
        return new Cap<>(getPolyline().getStartCap());
    }

    @Override
    public void setEndCap(@NonNull Cap<?> var1) {
        getPolyline().setEndCap((com.huawei.hms.maps.model.Cap) var1.getCap());
    }

    @NonNull
    @Override
    public Cap<?> getEndCap() {
        return new Cap<>(getPolyline().getEndCap());
    }

    @Override
    public void setJointType(int var1) {
        getPolyline().setJointType(var1);
    }

    @Override
    public int getJointType() {
        return getPolyline().getJointType();
    }

    @Override
    public void setPattern(@Nullable List<PatternItem<?>> var1) {
        if (var1 == null)
            getPolyline().setPattern(null);
        else {
            ArrayList<com.huawei.hms.maps.model.PatternItem> values = new ArrayList<>(var1.size());
            for (PatternItem<?> value : var1)
                values.add((com.huawei.hms.maps.model.PatternItem) value.getPatternItem());
            getPolyline().setPattern(values);
        }
    }

    @Nullable
    @Override
    public List<PatternItem<?>> getPattern() {
        if (getPolyline().getPattern() == null)
            return null;
        else {
            ArrayList<PatternItem<?>> out = new ArrayList<>(getPolyline().getPattern().size());
            for (com.huawei.hms.maps.model.PatternItem value : getPolyline().getPattern())
                out.add(new HuaweiPatternItem(value));
            return out;
        }
    }

    @Override
    public void setZIndex(float var1) {
        getPolyline().setZIndex(var1);
    }

    @Override
    public float getZIndex() {
        return getPolyline().getZIndex();
    }

    @Override
    public void setVisible(boolean var1) {
        getPolyline().setVisible(var1);
    }

    @Override
    public boolean isVisible() {
        return getPolyline().isVisible();
    }

    @Override
    public void setGeodesic(boolean var1) {
        getPolyline().setGeodesic(var1);
    }

    @Override
    public boolean isGeodesic() {
        return getPolyline().isGeodesic();
    }

    @Override
    public void setClickable(boolean var1) {
        getPolyline().setClickable(var1);
    }

    @Override
    public boolean isClickable() {
        return getPolyline().isClickable();
    }

    @Override
    public void setTag(Object var1) {
        getPolyline().setTag(var1);
    }

    @Nullable
    @Override
    public Object getTag() {
        return getPolyline().getTag();
    }

    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    @Override
    public boolean equals(Object var1) {
        return getPolyline().equals(var1);
    }

    @Override
    public int hashCode() {
        return getPolyline().hashCode();
    }
}

package it.localhostsoftware.maps.huawei.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import it.localhostsoftware.maps.model.LatLng;
import it.localhostsoftware.maps.model.PatternItem;
import it.localhostsoftware.maps.model.Polygon;

public class HuaweiPolygon extends Polygon<com.huawei.hms.maps.model.Polygon> {
    public HuaweiPolygon(com.huawei.hms.maps.model.Polygon polygon) {
        super(polygon);
    }

    @Override
    public void remove() {
        getPolygon().remove();
    }

    @NonNull
    @Override
    public String getId() {
        return getPolygon().getId();
    }

    @Override
    public void setPoints(@NonNull List<LatLng<?>> var1) {
        List<com.huawei.hms.maps.model.LatLng> out = new ArrayList<>(var1.size());
        for (LatLng<?> value : var1)
            out.add((com.huawei.hms.maps.model.LatLng) value.getLatLng());
        getPolygon().setPoints(out);
    }

    @NonNull
    @Override
    public List<LatLng<?>> getPoints() {
        List<LatLng<?>> out = new ArrayList<>(getPolygon().getPoints().size());
        for (com.huawei.hms.maps.model.LatLng value : getPolygon().getPoints())
            out.add(new HuaweiLatLng(value));
        return out;
    }

    @Override
    public void setHoles(@NonNull List<? extends List<LatLng<?>>> var1) {
        List<List<com.huawei.hms.maps.model.LatLng>> out = new ArrayList<>(var1.size());
        for (List<LatLng<?>> subIn : var1) {
            out.add(new ArrayList<>(subIn.size()));
            for (LatLng<?> value : subIn)
                out.get(out.size() - 1).add((com.huawei.hms.maps.model.LatLng) value.getLatLng());
        }
        getPolygon().setHoles(out);
    }

    @NonNull
    @Override
    public List<List<LatLng<?>>> getHoles() {
        List<List<LatLng<?>>> out = new ArrayList<>(getPolygon().getHoles().size());
        for (List<com.huawei.hms.maps.model.LatLng> subIn : getPolygon().getHoles()) {
            out.add(new ArrayList<>(subIn.size()));
            for (com.huawei.hms.maps.model.LatLng value : subIn)
                out.get(out.size() - 1).add(new HuaweiLatLng(value));
        }
        return out;
    }

    @Override
    public void setStrokeWidth(float var1) {
        getPolygon().setStrokeWidth(var1);
    }

    @Override
    public float getStrokeWidth() {
        return getPolygon().getStrokeWidth();
    }

    @Override
    public void setStrokeColor(int var1) {
        getPolygon().setStrokeWidth(var1);
    }

    @Override
    public int getStrokeColor() {
        return getPolygon().getStrokeColor();
    }

    @Override
    public void setStrokeJointType(int var1) {
        getPolygon().setStrokeJointType(var1);
    }

    @Override
    public int getStrokeJointType() {
        return getPolygon().getStrokeJointType();
    }

    @Override
    public void setStrokePattern(@Nullable List<PatternItem<?>> var1) {
        if (var1 == null)
            getPolygon().setStrokePattern(null);
        else {
            List<com.huawei.hms.maps.model.PatternItem> out = new ArrayList<>(var1.size());
            for (PatternItem<?> value : var1)
                out.add((com.huawei.hms.maps.model.PatternItem) value.getPatternItem());
            getPolygon().setStrokePattern(out);
        }
    }

    @Nullable
    @Override
    public List<PatternItem<?>> getStrokePattern() {
        if (getPolygon().getStrokePattern() == null)
            return null;
        else {
            List<PatternItem<?>> out = new ArrayList<>(getPolygon().getStrokePattern().size());
            for (com.huawei.hms.maps.model.PatternItem value : getPolygon().getStrokePattern())
                out.add(new PatternItem<>(value));
            return out;
        }
    }

    @Override
    public void setFillColor(int var1) {
        getPolygon().setFillColor(var1);
    }

    @Override
    public int getFillColor() {
        return getPolygon().getFillColor();
    }

    @Override
    public void setZIndex(float var1) {
        getPolygon().setZIndex(var1);
    }

    @Override
    public float getZIndex() {
        return getPolygon().getZIndex();
    }

    @Override
    public void setVisible(boolean var1) {
        getPolygon().setVisible(var1);
    }

    @Override
    public boolean isVisible() {
        return getPolygon().isVisible();
    }

    @Override
    public void setGeodesic(boolean var1) {
        getPolygon().setGeodesic(var1);
    }

    @Override
    public boolean isGeodesic() {
        return getPolygon().isGeodesic();
    }

    @Override
    public void setClickable(boolean var1) {
        getPolygon().setClickable(var1);
    }

    @Override
    public boolean isClickable() {
        return getPolygon().isClickable();
    }

    @Override
    public void setTag(@Nullable Object var1) {
        getPolygon().setTag(var1);
    }

    @Nullable
    @Override
    public Object getTag() {
        return getPolygon().getTag();
    }
}
package it.localhostsoftware.maps.google.model;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import it.localhostsoftware.maps.model.Circle;
import it.localhostsoftware.maps.model.LatLng;
import it.localhostsoftware.maps.model.PatternItem;

public class GoogleCircle extends Circle<com.google.android.gms.maps.model.Circle> {
    public GoogleCircle(com.google.android.gms.maps.model.Circle circle) {
        super(circle);
    }

    @Override
    public void remove() {
        getCircle().remove();
    }

    @Override
    public String getId() {
        return getCircle().getId();
    }

    @Override
    public void setCenter(LatLng<?> var1) {
        getCircle().setCenter((com.google.android.gms.maps.model.LatLng) var1.getLatLng());
    }

    @Override
    public LatLng<?> getCenter() {
        return new GoogleLatLng(getCircle().getCenter());
    }

    @Override
    public void setRadius(double var1) {
        getCircle().setRadius(var1);
    }

    @Override
    public double getRadius() {
        return getCircle().getRadius();
    }

    @Override
    public void setStrokeWidth(float var1) {
        getCircle().setStrokeWidth(var1);
    }

    @Override
    public float getStrokeWidth() {
        return getCircle().getStrokeWidth();
    }

    @Override
    public void setStrokeColor(int var1) {
        getCircle().setStrokeColor(var1);
    }

    @Override
    public int getStrokeColor() {
        return getCircle().getStrokeColor();
    }

    @Override
    public void setStrokePattern(@Nullable List<PatternItem<?>> var1) {
        if (var1 == null)
            getCircle().setStrokePattern(null);
        else {
            ArrayList<com.google.android.gms.maps.model.PatternItem> values = new ArrayList<>(var1.size());
            for (PatternItem<?> value : var1)
                values.add((com.google.android.gms.maps.model.PatternItem) value.getPatternItem());
            getCircle().setStrokePattern(values);
        }
    }

    @Nullable
    @Override
    public List<PatternItem<?>> getStrokePattern() {
        if (getCircle().getStrokePattern() == null)
            return null;
        else {
            ArrayList<PatternItem<?>> out = new ArrayList<>(getCircle().getStrokePattern().size());
            for (com.google.android.gms.maps.model.PatternItem value : getCircle().getStrokePattern())
                out.add(new PatternItem<>(value));
            return out;
        }
    }

    @Override
    public void setFillColor(int var1) {
        getCircle().setFillColor(var1);
    }

    @Override
    public int getFillColor() {
        return getCircle().getFillColor();
    }

    @Override
    public void setZIndex(float var1) {
        getCircle().setZIndex(var1);
    }

    @Override
    public float getZIndex() {
        return getCircle().getZIndex();
    }

    @Override
    public void setVisible(boolean var1) {
        getCircle().setVisible(var1);
    }

    @Override
    public boolean isVisible() {
        return getCircle().isVisible();
    }

    @Override
    public void setClickable(boolean var1) {
        getCircle().setClickable(var1);
    }

    @Override
    public boolean isClickable() {
        return getCircle().isClickable();
    }

    @Override
    public void setTag(@Nullable Object var1) {
        getCircle().setTag(var1);
    }

    @Nullable
    @Override
    public Object getTag() {
        return getCircle().getTag();
    }
}

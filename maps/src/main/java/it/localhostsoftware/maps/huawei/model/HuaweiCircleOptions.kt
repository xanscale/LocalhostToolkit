package it.localhostsoftware.maps.huawei.model;


import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import it.localhostsoftware.maps.model.CircleOptions;
import it.localhostsoftware.maps.model.LatLng;
import it.localhostsoftware.maps.model.PatternItem;

public class HuaweiCircleOptions extends CircleOptions<com.huawei.hms.maps.model.CircleOptions> {
    public HuaweiCircleOptions(com.huawei.hms.maps.model.CircleOptions circleOptions) {
        super(circleOptions);
    }

    @Override
    public CircleOptions<?> center(LatLng<?> var1) {
        getCircleOptions().center((com.huawei.hms.maps.model.LatLng) var1.getLatLng());
        return this;
    }

    @Override
    public CircleOptions<?> radius(double var1) {
        getCircleOptions().radius(var1);
        return this;
    }

    @Override
    public CircleOptions<?> strokeWidth(float var1) {
        getCircleOptions().strokeWidth(var1);
        return this;
    }

    @Override
    public CircleOptions<?> strokeColor(int var1) {
        getCircleOptions().strokeColor(var1);
        return this;
    }

    @Override
    public CircleOptions<?> strokePattern(@Nullable List<PatternItem<?>> var1) {
        if (var1 == null)
            getCircleOptions().strokePattern(null);
        else {
            ArrayList<com.huawei.hms.maps.model.PatternItem> values = new ArrayList<>(var1.size());
            for (PatternItem<?> value : var1)
                values.add((com.huawei.hms.maps.model.PatternItem) value.getPatternItem());
            getCircleOptions().strokePattern(values);
        }
        return this;
    }

    @Override
    public CircleOptions<?> fillColor(int var1) {
        getCircleOptions().fillColor(var1);
        return this;
    }

    @Override
    public CircleOptions<?> zIndex(float var1) {
        getCircleOptions().zIndex(var1);
        return this;
    }

    @Override
    public CircleOptions<?> visible(boolean var1) {
        getCircleOptions().visible(var1);
        return this;
    }

    @Override
    public CircleOptions<?> clickable(boolean var1) {
        getCircleOptions().clickable(var1);
        return this;
    }

    @Override
    public LatLng<?> getCenter() {
        return new HuaweiLatLng(getCircleOptions().getCenter());
    }

    @Override
    public double getRadius() {
        return getCircleOptions().getRadius();
    }

    @Override
    public float getStrokeWidth() {
        return getCircleOptions().getStrokeWidth();
    }

    @Override
    public int getStrokeColor() {
        return getCircleOptions().getStrokeColor();
    }

    @Nullable
    @Override
    public List<PatternItem<?>> getStrokePattern() {
        if (getCircleOptions().getStrokePattern() == null)
            return null;
        else {
            ArrayList<PatternItem<?>> out = new ArrayList<>(getCircleOptions().getStrokePattern().size());
            for (com.huawei.hms.maps.model.PatternItem value : getCircleOptions().getStrokePattern())
                out.add(new PatternItem<>(value));
            return out;
        }
    }

    @Override
    public int getFillColor() {
        return getCircleOptions().getFillColor();
    }

    @Override
    public float getZIndex() {
        return getCircleOptions().getZIndex();
    }

    @Override
    public boolean isVisible() {
        return getCircleOptions().isVisible();
    }

    @Override
    public boolean isClickable() {
        return getCircleOptions().isClickable();
    }
}

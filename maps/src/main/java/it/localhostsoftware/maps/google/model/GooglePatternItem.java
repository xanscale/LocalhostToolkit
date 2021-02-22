package it.localhostsoftware.maps.google.model;

import it.localhostsoftware.maps.model.PatternItem;

public class GooglePatternItem extends PatternItem<com.google.android.gms.maps.model.PatternItem> {
    public GooglePatternItem(com.google.android.gms.maps.model.PatternItem patternItem) {
        super(patternItem);
    }
}

package localhost.toolkit.lang;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Version implements Comparable<Version> {
    private final ArrayList<Integer> values;

    public Version(String value) {
        String[] parts = value.split("\\.");
        this.values = new ArrayList<>(parts.length);
        for (String part : parts)
            try {
                this.values.add(Integer.parseInt(part));
            } catch (Exception e) {
                this.values.add(0);
            }
    }

    public Integer getVal(int pos) {
        return pos < values.size() ? values.get(pos) : 0;
    }

    @Override
    public int compareTo(@NonNull Version o) {
        for (int i = 0; i < Math.max(values.size(), o.values.size()); i++) {
            int compare = getVal(i).compareTo(o.getVal(i));
            if (compare != 0)
                return compare;
        }
        return 0;
    }

    @NonNull
    @Override
    public String toString() {
        return TextUtils.join(".", values);
    }
}
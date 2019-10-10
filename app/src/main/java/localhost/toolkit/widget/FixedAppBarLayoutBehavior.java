package localhost.toolkit.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;

import com.google.android.material.appbar.AppBarLayout;

/**
 *     <com.google.android.material.appbar.AppBarLayout
 *         app:layout_behavior="localhost.toolkit.widget.FixedAppBarLayoutBehavior">
 */
public class FixedAppBarLayoutBehavior extends AppBarLayout.Behavior {

    public FixedAppBarLayoutBehavior() {
        setDragCallback();
    }

    public FixedAppBarLayoutBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        setDragCallback();
    }

    private void setDragCallback() {
        setDragCallback(new BaseDragCallback() {
            @Override
            public boolean canDrag(@NonNull AppBarLayout appBarLayout) {
                return false;
            }
        });
    }
}

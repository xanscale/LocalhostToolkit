/*
 * Copyright 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package localhost.toolkit.widget.recyclerview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import kotlin.math.roundToInt

class MiddleDividerItemDecoration(context: Context, private val orientation: Int) : ItemDecoration() {
    companion object {
        const val HORIZONTAL = LinearLayout.HORIZONTAL
        const val VERTICAL = LinearLayout.VERTICAL
        const val ALL = 2
        private const val TAG = "DividerItem"
        private val ATTRS = intArrayOf(android.R.attr.listDivider)
    }

    private val mBounds = Rect()
    var drawable: Drawable
    var offset = 0

    init {
        context.obtainStyledAttributes(ATTRS).let {
            it.getDrawable(0).let { d ->
                checkNotNull(d) { "@android:attr/listDivider was not set in the theme used for this MiddleDividerItemDecoration. Please set that attribute all call setDrawable()" }
                drawable = d
            }
            it.recycle()
        }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (parent.layoutManager == null) return
        when (orientation) {
            ALL -> {
                drawVertical(c, parent)
                drawHorizontal(c, parent)
            }
            VERTICAL -> drawVertical(c, parent)
            else -> drawHorizontal(c, parent)
        }
    }

    private fun drawVertical(canvas: Canvas, parent: RecyclerView) {
        canvas.save()
        val left: Int
        val right: Int
        if (parent.clipToPadding) {
            left = parent.paddingLeft
            right = parent.width - parent.paddingRight
            canvas.clipRect(left, parent.paddingTop, right, parent.height - parent.paddingBottom)
        } else {
            left = 0
            right = parent.width
        }
        var childCount = parent.childCount
        (parent.layoutManager as? GridLayoutManager)?.let {
            var leftItems = childCount % it.spanCount
            if (leftItems == 0) leftItems = it.spanCount
            childCount -= leftItems
        }
        for (i in 0 until childCount - 1) {
            val child = parent.getChildAt(i)
            val position = parent.getChildAdapterPosition(child)
            if (position >= offset && position < (parent.adapter?.itemCount ?: 0) - 1 - offset) {
                parent.getDecoratedBoundsWithMargins(child, mBounds)
                val bottom = mBounds.bottom + child.translationY.roundToInt()
                val top = bottom - drawable.intrinsicHeight
                drawable.setBounds(left, top, right, bottom)
                drawable.draw(canvas)
            }
        }
        canvas.restore()
    }

    private fun drawHorizontal(canvas: Canvas, parent: RecyclerView) {
        canvas.save()
        val top: Int
        val bottom: Int
        if (parent.clipToPadding) {
            top = parent.paddingTop
            bottom = parent.height - parent.paddingBottom
            canvas.clipRect(parent.paddingLeft, top, parent.width - parent.paddingRight, bottom)
        } else {
            top = 0
            bottom = parent.height
        }
        var childCount = parent.childCount
        (parent.layoutManager as? GridLayoutManager)?.let {
            childCount = it.spanCount
        }
        for (i in 0 until childCount - 1) {
            val child = parent.getChildAt(i)
            val position = parent.getChildAdapterPosition(child)
            if (position >= offset && position < (parent.adapter?.itemCount ?: 0) - 1 - offset) {
                parent.getDecoratedBoundsWithMargins(child, mBounds)
                val right = mBounds.right + child.translationX.roundToInt()
                val left = right - drawable.intrinsicWidth
                drawable.setBounds(left, top, right, bottom)
                drawable.draw(canvas)
            }
        }
        canvas.restore()
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        if (position < offset || position >= (parent.adapter?.itemCount ?: 0) - 1 - offset)
            outRect.setEmpty()
        else
            outRect.set(0, 0,
                    if (orientation == VERTICAL) 0 else drawable.intrinsicWidth,
                    if (orientation == HORIZONTAL) 0 else drawable.intrinsicHeight
            )
    }
}
package com.cyriltheandroid.leboncv.customview

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import com.cyriltheandroid.leboncv.R

class FilterToggleButton(context: Context, attrs: AttributeSet) :
    androidx.appcompat.widget.AppCompatButton(context, attrs) {

    var select: Boolean = false
    private var selectedBackground: Drawable? = null
    private var unselectedBackground: Drawable? = null
    private var selectedTextTint: Int? = null
    private var unselectedTextTint: Int? = null

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.FilterToggleButton,
            0, 0
        ).apply {
            try {
                select = getBoolean(R.styleable.FilterToggleButton_isSelected, false)
                selectedBackground = getDrawable(R.styleable.FilterToggleButton_selectedBackground)
                unselectedBackground =
                    getDrawable(R.styleable.FilterToggleButton_unselectedBackground)
                selectedTextTint =
                    getColor(R.styleable.FilterToggleButton_selectedTextTint, Color.BLACK)
                unselectedTextTint =
                    getColor(R.styleable.FilterToggleButton_unselectedTextTint, Color.BLACK)
                setButtonSelected(select)
            } finally {
                recycle()
            }
        }
    }

    private val simpleGestureListener = object : GestureDetector.SimpleOnGestureListener() {
        override fun onDown(e: MotionEvent): Boolean {
            return true
        }
    }

    private val detector: GestureDetector = GestureDetector(context, simpleGestureListener)

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return detector.onTouchEvent(event).let { result ->
            if (!result) {
                if (event.action == MotionEvent.ACTION_UP) {
                    setButtonSelected(true)
                    performClick()
                    true
                } else false
            } else true
        }
    }

    override fun performClick(): Boolean {
        return super.performClick()
    }

    fun setButtonSelected(select: Boolean) {
        this.select = select
        background = if (select) selectedBackground else unselectedBackground
        val textTint = if (select) selectedTextTint else unselectedTextTint
        textTint?.let {
            setTextColor(it)
        }
        invalidate()
        requestLayout()
    }
}
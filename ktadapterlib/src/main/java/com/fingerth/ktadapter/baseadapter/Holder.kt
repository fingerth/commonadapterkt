package com.fingerth.ktadapter.baseadapter

import android.content.Context
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import com.fingerth.ktadapter.R
import com.fingerth.ktadapter.utils.BaseHolder

class Holder(context: Context, parent: ViewGroup?, layoutId: Int, private val mPosition: Int) :
    BaseHolder {
    private val mViews = SparseArray<View>()
    private val mConvertView by lazy {
        LayoutInflater.from(context).inflate(layoutId, parent, false)
    }

    init {
        mConvertView.setTag(R.id.fingerth_holder_id, this)
    }

    fun getConvertView(): View = mConvertView
    fun getPosition(): Int = mPosition

    override fun <T : View> getView(@IdRes viewId: Int): T {
        var view = mViews.get(viewId)
        if (view == null) {
            view = mConvertView.findViewById(viewId)
            mViews.put(viewId, view)
        }
        return view as T
    }

    override fun setText(viewId: Int, text: String) =
        getView<TextView>(viewId).setText(text)


    override fun setImageResource(viewId: Int, drawableId: Int) =
        getView<ImageView>(viewId).setImageResource(drawableId)

    companion object {
        /**
         * 拿到ViewHolder对象
         */
        @JvmStatic
        fun getHolder(
            context: Context,
            convertView: View?,
            parent: ViewGroup?,
            @LayoutRes layoutId: Int,
            position: Int
        ): Holder {
            return if (convertView == null) {
                Holder(context, parent, layoutId, position)
            } else convertView.getTag(R.id.fingerth_holder_id) as Holder
        }
    }


}
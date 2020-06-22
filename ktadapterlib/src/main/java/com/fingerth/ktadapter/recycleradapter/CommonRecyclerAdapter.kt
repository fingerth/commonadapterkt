package com.fingerth.ktadapter.recycleradapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

abstract class CommonRecyclerAdapter<T>(
    val context: Context,
    val mDataList: List<T>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val TYPE_HEADER = 0x00
    private val TYPE_NORMAL = 0x11
    private val TYPE_FOOT = 0x22

    private var mHeaderView: View? = null
    private var mFootView: View? = null

    override fun getItemCount(): Int {
        return if (mHeaderView == null) {
            if (mFootView == null) {
                mDataList.size
            } else {
                mDataList.size + 1
            }
        } else {
            if (mFootView == null) {
                mDataList.size + 1
            } else {
                mDataList.size + 2
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (mHeaderView != null && position == 0) {
            return TYPE_HEADER
        } else if (mFootView != null && position == itemCount - 1) {
            return TYPE_FOOT
        }
        val pos = if (mHeaderView != null) {
            position - 1
        } else {
            position
        }
        return itemViewType(pos)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (mHeaderView != null && viewType == TYPE_HEADER) {
            Holder(mHeaderView!!)
        } else if (mFootView != null && viewType == TYPE_FOOT) {
            Holder(mFootView!!)
        } else {
            Holder(
                LayoutInflater.from(parent.context).inflate(setLayoutId(viewType), parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (mHeaderView != null && holder.itemViewType == TYPE_HEADER) {
            return
        }
        if (mFootView != null && holder.itemViewType == TYPE_FOOT) {
            return
        }
        val pos: Int = if (mHeaderView == null) holder.layoutPosition else holder.layoutPosition - 1
        val data: T = mDataList[pos]
        onBind(holder as Holder, pos, data)
        if (mListener != null) {
            holder.itemView.setOnClickListener(View.OnClickListener {
                mListener?.onItemClick(pos, data)
            })
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        val manager = recyclerView.layoutManager
        if (manager is GridLayoutManager) {
            val gridManager: GridLayoutManager = manager
            gridManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if (((position == 0 && mHeaderView != null) || (mDataList.size > 1 && position == mDataList.size - 1 && mFootView != null)) && getItemViewType(
                            position
                        ) != TYPE_NORMAL
                    ) gridManager.spanCount else 1
                }
            }
        }
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        super.onViewAttachedToWindow(holder)
        val lp = holder.itemView.layoutParams
        if (lp != null && lp is StaggeredGridLayoutManager.LayoutParams && holder.layoutPosition == 0) {
            val p: StaggeredGridLayoutManager.LayoutParams = lp
            p.isFullSpan = true
        }
    }

    open fun itemViewType(position: Int): Int = TYPE_NORMAL

    @LayoutRes
    abstract fun setLayoutId(viewType: Int): Int
    abstract fun onBind(holder: Holder, RealPosition: Int, data: T)


    private var mListener: OnItemClickListener<T>? = null

    fun setOnItemClickListener(listener: OnItemClickListener<T>) {
        mListener = listener
    }

    fun setHeaderView(headerView: View) {
        mHeaderView = headerView
        notifyItemInserted(0)
    }

    fun getHeaderView(): View? = mHeaderView

    fun setFootView(mFootView: View?) {
        //沒有數據，不顯示FootView
        if (mDataList.isNullOrEmpty()) {
            this.mFootView = null
        } else {
            this.mFootView = mFootView
            notifyItemInserted(itemCount - 1)
        }
    }

    fun setNoFootView() = run { mFootView = null }


    fun getFootView(): View? = mFootView


    interface OnItemClickListener<T> {
        fun onItemClick(position: Int, data: T)
    }
}
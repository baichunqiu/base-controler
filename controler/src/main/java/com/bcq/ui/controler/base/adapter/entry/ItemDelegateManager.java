package com.bcq.ui.controler.base.adapter.entry;

import android.support.v4.util.SparseArrayCompat;

import com.bcq.ui.controler.base.adapter.ViewHolder;


/**
 * @author: BaiCQ
 * @createTime: 2017/2/26 15:10
 * @className: ItemDelegateManager
 * @Description: 复用控件 ItemDelegate的管理类
 */
public class ItemDelegateManager<T> {

    /**
     * 类似但性能优于HashMap<Intager,Object> 此处 key：viewType Value：convertView （ItemDelegate）
     */
    SparseArrayCompat<ItemDelegate<T>> delegates = new SparseArrayCompat();

    public int getItemDelegateCount() {
        return delegates.size();
    }

    /**
     * 添加
     * @param delegate
     * @return
     */
    public ItemDelegateManager<T> addDelegate(ItemDelegate<T> delegate) {
        int viewType = delegates.size();
        if (delegate != null) {
            delegates.put(viewType, delegate);
            viewType++;
        }
        return this;
    }

    /**
     * 添加
     * @param viewType view类型
     * @param delegate
     * @return
     */
    public ItemDelegateManager<T> addDelegate(int viewType, ItemDelegate<T> delegate) {
        if (delegates.get(viewType) != null) {//该类型已存在
            throw new IllegalArgumentException(
                    "An ItemDelegate is already registered for the viewType = "
                            + viewType
                            + ". Already registered ItemDelegate is "
                            + delegates.get(viewType));
        }
        delegates.put(viewType, delegate);
        return this;
    }

    /**
     * 移除
     * @param delegate
     * @return
     */
    public ItemDelegateManager<T> removeDelegate(ItemDelegate<T> delegate) {
        if (delegate == null) {
            throw new NullPointerException("ItemViewDelegate is null");
        }
        int indexToRemove = delegates.indexOfValue(delegate);

        if (indexToRemove >= 0) {
            delegates.removeAt(indexToRemove);
        }
        return this;
    }

    /**
     * 移除 按指定类型
     * @param itemType
     * @return
     */
    public ItemDelegateManager<T> removeDelegate(int itemType) {
        int indexToRemove = delegates.indexOfKey(itemType);

        if (indexToRemove >= 0) {
            delegates.removeAt(indexToRemove);
        }
        return this;
    }

    /**
     * 获取view类型
     * @param item
     * @param position
     * @return
     */
    public int getItemViewType(T item, int position) {
        int delegatesCount = delegates.size();
        for (int i = delegatesCount - 1; i >= 0; i--) {
            ItemDelegate<T> delegate = delegates.valueAt(i);
            if (delegate.isForViewType(item, position)) {
                return delegates.keyAt(i);
            }
        }
        throw new IllegalArgumentException(
                "No ItemDelegate added that matches position=" + position + " in data source");
    }

    /**
     * 复用
     * @param holder ViewHolder
     * @param item 数据
     * @param position 位置
     */
    public void convert(ViewHolder holder, T item, int position) {
        int delegatesCount = delegates.size();
        for (int i = 0; i < delegatesCount; i++) {
            ItemDelegate<T> delegate = delegates.valueAt(i);

            if (delegate.isForViewType(item, position)) {
                delegate.convert(holder, item, position);
                return;
            }
        }
        throw new IllegalArgumentException(
                "No ItemDelegateManager added that matches position=" + position + " in data source");
    }


    /**
     * 获取指定类型的id
     * @param viewType
     * @return
     */
    public int getItemLayoutId(int viewType) {
        return delegates.get(viewType).getItemViewLayoutId();
    }

    public int getItemViewType(ItemDelegate itemDelegate) {
        return delegates.indexOfValue(itemDelegate);
    }

    public ItemDelegate getItemDelegate(T item, int position) {
        int delegatesCount = delegates.size();
        for (int i = delegatesCount - 1; i >= 0; i--) {
            ItemDelegate<T> delegate = delegates.valueAt(i);
            if (delegate.isForViewType(item, position)) {
                return delegate;
            }
        }
        throw new IllegalArgumentException(
                "No ItemDelegate added that matches position=" + position + " in data source");
    }

    public int getItemLayoutId(T item, int position) {
        return getItemDelegate(item, position).getItemViewLayoutId();
    }
}

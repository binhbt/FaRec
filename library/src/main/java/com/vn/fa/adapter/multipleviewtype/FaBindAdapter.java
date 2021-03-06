package com.vn.fa.adapter.multipleviewtype;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by binhbt on 7/21/2016.
 */
public class FaBindAdapter extends ListBindAdapter {

    public FaBindAdapter() {
    }
    public FaBindAdapter listData(List<DataBinder> dataSet){
        if (dataSet == null){
            dataSet = new ArrayList<>();
        }
        this.mBinderList = dataSet;
        return this;
    }

    public List<DataBinder> getBinderList(){
        return this.mBinderList;
    }
    public void setBinderList(List<DataBinder> dataSet){
        if (dataSet == null){
            dataSet = new ArrayList<>();
        }
        this.mBinderList = dataSet;
    }
    public void addDataObject(IViewBinder object, boolean isAdapterAttached){
        FaDataBinder iViewBinder = (FaDataBinder) object.getViewBinder();
        if (isAdapterAttached){
            iViewBinder.adapter(this);
        }
        if (object != null){
            add(iViewBinder);
        }
    }
    public void addDataObject(IViewBinder object){
        FaDataBinder iViewBinder = (FaDataBinder) object.getViewBinder();
        if (object != null){
            add(iViewBinder);
        }
    }
    public void addAllDataObject(List<IViewBinder> dataset){
        if (dataset != null){
            addAll(convertDataToViewBinder(dataset));
        }
    }
    public void addAllDataObject(List<IViewBinder> dataset, boolean isAdapterAttached){
        if (dataset != null){
            addAll(convertDataToViewBinder(dataset, isAdapterAttached));
        }
    }
    public void insertDataObject(IViewBinder object, int position){
        FaDataBinder iViewBinder = (FaDataBinder) object.getViewBinder();
        if (object != null){
            insert(iViewBinder,position);
        }
    }
    public void insertDataObject(IViewBinder object, int position, boolean isAdapterAttached){
        FaDataBinder iViewBinder = (FaDataBinder) object.getViewBinder();
        if (isAdapterAttached){
            iViewBinder.adapter(this);
        }
        if (object != null){
            insert(iViewBinder,position);
        }
    }
    public void add(DataBinder binder) {
        insert(binder, mBinderList.size());
    }

    public void insert(DataBinder binder, int position) {
        mBinderList.add(position, binder);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        mBinderList.remove(position);
        notifyItemRemoved(position);
    }

    public void clear() {
        int size = mBinderList.size();
        mBinderList.clear();
        notifyItemRangeRemoved(0, size);
    }

    public void addAll(List<DataBinder> dataSet) {
        int startIndex = mBinderList.size();
        addAllBinder(dataSet);
        notifyItemRangeInserted(startIndex, dataSet.size());
    }
    public static List<DataBinder> convertDataToViewBinder(List<IViewBinder> dataset){
        if (dataset == null) throw new IllegalArgumentException("List data can not null!");
        if (dataset.size() ==0) return new ArrayList<>();
        List<DataBinder> list = new ArrayList<>();
        for (IViewBinder viewBinder: dataset) {
            list.add(viewBinder.getViewBinder());
        }
        return list;
    }
    public List<DataBinder> convertDataToViewBinder(List<IViewBinder> dataset, boolean isAdapterAttached){
        if (dataset == null) throw new IllegalArgumentException("List data can not null!");
        if (dataset.size() ==0) return new ArrayList<>();
        List<DataBinder> list = new ArrayList<>();
        for (IViewBinder viewBinder: dataset) {
            FaDataBinder iViewBinder = (FaDataBinder) viewBinder.getViewBinder();
            if (isAdapterAttached){
                iViewBinder.adapter(this);
            }
            list.add(iViewBinder);
        }
        return list;
    }
    public DataBinder getItemView(int position){
        if (mBinderList != null && mBinderList.size() >position){
            return mBinderList.get(position);
        }
        return null;
    }
    public Object getItemData(int position){
        return ((FaDataBinder)getItemView(position)).getData();
    }
    //Fix position bug
    @Override
    public int getBinderPosition(int position){
        return position;
    }

}
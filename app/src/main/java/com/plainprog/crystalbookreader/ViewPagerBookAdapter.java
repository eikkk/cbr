package com.plainprog.crystalbookreader;

import android.content.Context;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

public class ViewPagerBookAdapter extends PagerAdapter {
    Pagination pagination;
    Context context;
    int textSize;
    public ViewPagerBookAdapter(Context context,Pagination pagination){
        this.pagination = pagination;
        this.context = context;
    }
    @Override
    public int getCount() {
        return pagination.getCount();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        PageView PV;
        TextPaintCollection paints = pagination.getPaints();
        PV = new PageView(context, paints,pagination.getPages().get(position), pagination.getPaddings(), pagination.getDisplayDimensions());
        ViewGroup.LayoutParams parameters = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        PV.setLayoutParams(parameters);
        PV.setTag(position);
        container.addView(PV);
        return PV;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        View item = (View)object;
        int position = (int)((View) object).getTag();
        return position;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //super.destroyItem(container, position, object);
        ViewPager viewPager = (ViewPager)container;
        viewPager.removeView((View)object);
    }
}

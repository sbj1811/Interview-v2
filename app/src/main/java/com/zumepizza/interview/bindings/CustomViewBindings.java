package com.zumepizza.interview.bindings;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.zumepizza.interview.ui.main.MainActivity;

import java.util.List;

public class CustomViewBindings {
    @BindingAdapter({"handler"})
    public static void bindViewPagerAdapter(final ViewPager view, final MainActivity activity) {
        final FragmentSelectAdapter adapter = new FragmentSelectAdapter(activity.getSupportFragmentManager(), view.getContext(), 4);
        view.setAdapter(adapter);
    }

    @BindingAdapter({"pager"})
    public static void bindViewPagerTabs(final TabLayout view, final ViewPager pagerView) {
        view.setupWithViewPager(pagerView, true);
    }

    @BindingAdapter("app:setAdapter")
    public static void bindRecyclerViewAdapter(RecyclerView recyclerView, RecyclerView.Adapter<?> adapter) {
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(adapter);
    }


    @BindingAdapter("setToppingAdapter")
    public static void bindToppingRecyclerViewAdapter(RecyclerView recyclerView, RecyclerView.Adapter<?> adapter) {
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 2));
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter("menuImage")
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .into(view);
    }

    @BindingAdapter("detailsImage")
    public static void loadDetailsImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .into(view);
    }

    @BindingAdapter("createView")
    public static void createTextView(LinearLayout linearLayout, List<TextView> views) {
        linearLayout.removeAllViews();
        for (TextView view : views
        ) {
            linearLayout.addView(view);
        }
    }


}

package com.lemon95.ymtv.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lemon95.ymtv.R;
import com.lemon95.ymtv.bean.VideoSearchList;
import com.lemon95.ymtv.common.AppConstant;
import com.lemon95.ymtv.utils.StringUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by WXT on 2016/7/19.
 */
public class GridViewAdapter extends BaseAdapter {

    List<VideoSearchList.Data.VideoBriefs> videoBriefsList;
    private Context context;
    private DisplayImageOptions options;

    public GridViewAdapter(List<VideoSearchList.Data.VideoBriefs> videoBriefsList,Context context) {
        this.videoBriefsList = videoBriefsList;
        this.context = context;
        // 使用DisplayImageOptions.Builder()创建DisplayImageOptions
        options = new DisplayImageOptions.Builder()
                .showStubImage(R.drawable.lemon_details_small_def)          // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.drawable.lemon_details_small_def)  // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.lemon_details_small_def)       // 设置图片加载或解码过程中发生错误显示的图片
                .build();                                   // 创建配置过得DisplayImageOption对象
    }

    @Override
    public int getCount() {
        return videoBriefsList.size();
    }

    @Override
    public Object getItem(int position) {
        return videoBriefsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = View.inflate(context, R.layout.item_gridview, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        VideoSearchList.Data.VideoBriefs videoBriefs = videoBriefsList.get(position);
        ImageLoader.getInstance().displayImage(AppConstant.RESOURCE + videoBriefs.getPicturePath(),holder.lemon_grid_img,options);
        holder.lemon_grid_textView.setText(videoBriefs.getVideoName());
        String title = videoBriefs.getTitle();
        if (StringUtils.isNotBlank(title)) {
            holder.lemon_title.setText(title);
            holder.lemon_title.setVisibility(View.VISIBLE);
        } else {
            holder.lemon_title.setVisibility(View.GONE);
        }
        return view;
    }

    private static class ViewHolder {
        TextView lemon_grid_textView;
        TextView lemon_title;
        ImageView lemon_grid_img;
        public ViewHolder(View view) {
            lemon_grid_textView = (TextView) view.findViewById(R.id.lemon_grid_textView);
            lemon_title = (TextView) view.findViewById(R.id.lemon_title);
            lemon_grid_img = (ImageView) view.findViewById(R.id.lemon_grid_img);
        }
    }
}

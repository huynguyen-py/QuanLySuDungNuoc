package com.example.ql_sudungnuoc.xuly;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ql_sudungnuoc.R;

public class ImageTextAdapter extends BaseAdapter {
    private Activity context;
    private int layoutId;
    private Integer[] Images;
    private String[] Texts;

    public ImageTextAdapter(Activity context, int layoutId, Integer[] images, String[] texts) {
        this.context = context;
        this.layoutId = layoutId;
        Images = images;
        Texts = texts;
    }
    public void setDataSource(Integer[] images, String[] texts){
        Images = images;
        Texts = texts;
    }

    @Override
    public int getCount() {
        return Images.length;
    }

    @Override
    public Object getItem(int position) {
        return Images[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class Holder{
        TextView txtText;
        ImageView imgImage;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater  inflater = context.getLayoutInflater();
        convertView = inflater.inflate(layoutId, parent, false);
        Holder holder = new Holder();
        holder.txtText = (TextView) convertView.findViewById(R.id.txt_Text);
        holder.imgImage = (ImageView) convertView.findViewById(R.id.img_Image);
        convertView.setTag(holder);
        holder.txtText.setText(Texts[position]);
        holder.imgImage.setImageResource(Images[position]);
        return convertView;
    }
}

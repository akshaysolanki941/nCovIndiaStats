package com.akshay.ncovindiastats.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akshay.ncovindiastats.R;
import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterVH> {

    private Context context;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout_item, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, int position) {
        viewHolder.textViewDescription.setText("This is slider item " + position);

        switch (position) {
            case 0:
                Glide.with(context).load(R.drawable.image1).into(viewHolder.imageViewBackground);
                break;
            case 1:
                Glide.with(context).load(R.drawable.image2).into(viewHolder.imageViewBackground);
                break;
            case 2:
                Glide.with(context).load(R.drawable.image3).into(viewHolder.imageViewBackground);
                break;
            case 3:
                Glide.with(context).load(R.drawable.image4).into(viewHolder.imageViewBackground);
                break;
            case 4:
                Glide.with(context).load(R.drawable.image5).into(viewHolder.imageViewBackground);
                break;
            case 5:
                Glide.with(context).load(R.drawable.image6).into(viewHolder.imageViewBackground);
                break;
        }
    }

    @Override
    public int getCount() {
        return 6;
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;
        TextView textViewDescription;

        private SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider);
            this.itemView = itemView;
        }
    }
}

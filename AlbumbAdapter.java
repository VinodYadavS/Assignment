package com.bluejeans.assignment.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bluejeans.assignment.R;
import com.bluejeans.assignment.databinding.ImageItemBinding;
import com.bluejeans.assignment.model.Image;
import com.bluejeans.assignment.ui.DetailsActivity;
import com.bluejeans.assignment.utils.CustomClickListener;

import java.util.ArrayList;
/**
 * Created by vinod on 23/07/20.
 */

public class AlbumbAdapter extends RecyclerView.Adapter<AlbumbAdapter.ImageViewHolder> implements CustomClickListener {

    private ArrayList<Image> images;
    private Context context;

    public AlbumbAdapter(ArrayList<Image> images, Context context) {
        this.images = images;
        this.context = context;
    }


    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //Binding itemLayout
        ImageItemBinding imageItemBinding =
                DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                        R.layout.image_item, viewGroup, false);
        return new ImageViewHolder(imageItemBinding);
    }
    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder imageViewHolder, int i) {
        //Getting image of current position
        Image currentImage = images.get(i);

        //Binding the currentImage
        imageViewHolder.imageListItemBinding.setImageModel(currentImage);

        //Set the itemClickListener
        imageViewHolder.imageListItemBinding.setItemClickListener(this);

    }
    @Override
    public int getItemCount() {
        //Return Itemcount as Arraysize
        if (images != null) {
            return images.size();
        } else {
            return 0;
        }
    }
    public void setImages(ArrayList<Image> employees) {
        this.images = employees;
        notifyDataSetChanged();
    }

    @Override
    public void cardClicked(Image image) {
        //Sending setected image to DetailsActivity
        Intent intent=new Intent(context, DetailsActivity.class);
        intent.putExtra("image",image);
        context.startActivity(intent);
    }

    // CustomViewHolder class
    class ImageViewHolder extends RecyclerView.ViewHolder {
        private ImageItemBinding imageListItemBinding;

        public ImageViewHolder(@NonNull ImageItemBinding imageListItemBinding) {
            super(imageListItemBinding.getRoot());
            this.imageListItemBinding = imageListItemBinding;
        }
    }
}

package com.mobileapps.flickr.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.mobileapps.flickr.DialogMin;
import com.mobileapps.flickr.DialogsOpc;
import com.mobileapps.flickr.R;
import com.mobileapps.flickr.model.Item;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder>
{
    List<Item> images;
    Context context;

    public ImagesAdapter(List<Item> images, Context context) {
        this.images = images;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.image_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder,final int position)
    {

        String title = images.get(position).getTitle();
        if(title.length()>14)
            title = title.substring(0,14);
        holder.tvTitle.setText(title);
        final String author = images.get(position).getAuthor().split(" ")[1];
        holder.tvAuthor.setText(author);


        final String urlPicture = images.get(position).getMedia().getM();
        Picasso.with(context)
                .load(urlPicture)
                .into(holder.imgPicture);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {


            @Override
            public boolean onLongClick(View view) {


                DialogFragment fragment = new DialogsOpc(context,images.get(position).getMedia().getM(),author); //where MyFragment is my fragment I want to show
                //DialogFragment fragment = new DialogMin();
                fragment.setCancelable(true);
                fragment.show(((FragmentActivity)context).getSupportFragmentManager(), "opcPicker");


                /*

                Intent intent = new Intent(context, FullScreenActivity.class);
                intent.putExtra("picture",images.get(position).getMedia().getM());
                context.startActivity(intent);*/

                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imgPicture;
        TextView  tvTitle;
        TextView  tvAuthor;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPicture = itemView.findViewById(R.id.imgPicture);
            tvTitle    = itemView.findViewById(R.id.tvTitle);
            tvAuthor   = itemView.findViewById(R.id.tvAuthor);
        }
    }
}

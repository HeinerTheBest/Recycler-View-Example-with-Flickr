package com.mobileapps.flickr;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.mobileapps.flickr.activities.FullScreenActivity;
import com.squareup.picasso.Picasso;

public class DialogsOpc extends DialogFragment
{
    Context context;
    String picture;
    String title;

    public DialogsOpc(Context context, String picture,String title) {
        this.context = context;
        this.picture = picture;
        this.title = title;
    }

    String[] opc = {"Show full image","Show small image"};

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("What do you want to do?")
                .setItems(opc, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        switch (which)
                        {
                            case 0:
                                Intent intent = new Intent(context, FullScreenActivity.class);
                                intent.putExtra("picture",picture);
                                context.startActivity(intent);
                                dismiss();
                                break;

                            case 1:
                                AlertDialog.Builder ImageDialog = new AlertDialog.Builder(context);
                                ImageDialog.setTitle(title);
                                ImageView showImage = new ImageView(context);
                                Picasso.with(context)
                                        .load(picture)
                                        .into(showImage);
                                ImageDialog.setView(showImage);

                                ImageDialog.setNegativeButton("ok", new DialogInterface.OnClickListener()
                                {
                                    public void onClick(DialogInterface arg0, int arg1)
                                    {
                                    }
                                });
                                ImageDialog.show();
                                dismiss();
                                break;
                        }
                    }
                });
        return builder.create();
    }





}

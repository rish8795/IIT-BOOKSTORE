package com.cs442.rshah92.bookapp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cs442.rshah92.bookapp.Database.BookDetails;
import com.cs442.rshah92.bookapp.Database.Utils;

/**
 * Created by rish8795 on 12/9/2016.
 */

public class SearchCursorAdaptor extends CursorAdapter{

    public SearchCursorAdaptor(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.item_layout, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        String fromFieldNames = cursor.getString(cursor.getColumnIndex(BookDetails.BOOKNAME));

        TextView nameView = (TextView) view.findViewById(R.id.bookName);

        nameView.setText(fromFieldNames);

        String a_name = cursor.getString(cursor.getColumnIndex(BookDetails.AUTHOR));

        TextView a_view = (TextView) view.findViewById(R.id.bookAuthor);

        a_view.setText(a_name);

        String quan = cursor.getString(cursor.getColumnIndex(BookDetails.QUANT));

        TextView quan_view = (TextView) view.findViewById(R.id.quantityy);

        quan_view.setText(quan);

        ImageView storeImageView = (ImageView) view.findViewById(R.id.imagee);

        byte[] image;
        image=cursor.getBlob(cursor.getColumnIndex(BookDetails.IMG));
        storeImageView.setImageBitmap(Utils.getImage(image));




    }
}

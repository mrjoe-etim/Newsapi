package sample.meepo.nekocode.joe.news;


import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.media.CamcorderProfile.get;

public class GridAdapter extends ArrayAdapter<news_model> {

     private Context mContext;
    private LayoutInflater mLayoutInflater;
    private String append = null;
    private ArrayList<news_model> mNews_models = new ArrayList<>();
    private int LAYOUT_RESOURSE;

    public GridAdapter(Context context,int layout_resourse, ArrayList<news_model> models){
        super(context,layout_resourse,models);
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mNews_models = models;
        LAYOUT_RESOURSE=layout_resourse;
    }

    public static class ViewHolder{

        ImageView mImageView;
        TextView mName;
        TextView mDescription;

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if(convertView==null){
            convertView = mLayoutInflater.inflate(LAYOUT_RESOURSE,parent,false);
            viewHolder.mImageView = (ImageView) convertView.findViewById(R.id.Image);
            viewHolder.mDescription= (TextView) convertView.findViewById(R.id.title);
            viewHolder.mName =(TextView)convertView.findViewById(R.id.name);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

       String name      = mNews_models.get(position).getName();
       String title     = mNews_models.get(position).getTitle();
       String Image_url = mNews_models.get(position).getImageUrl();

        viewHolder.mName.setText(name);
        viewHolder.mDescription.setText(title);

        UniversalImageLoader.setImage(Image_url,viewHolder.mImageView,null,append);

        return convertView;
    }
}

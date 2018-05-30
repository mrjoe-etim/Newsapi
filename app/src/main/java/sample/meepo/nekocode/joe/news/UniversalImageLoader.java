package sample.meepo.nekocode.joe.news;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;


public class UniversalImageLoader {

    public static final int DEFAULT_IMAGE = R.mipmap.ic_launcher;

    private Context mContext;

    public UniversalImageLoader(Context context) {
        mContext = context;
    }

    public ImageLoaderConfiguration getConfig(){

        DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder()
                 .showImageOnLoading(DEFAULT_IMAGE)
                 .showImageForEmptyUri(DEFAULT_IMAGE)
                 .showImageOnFail(DEFAULT_IMAGE)
                 .cacheOnDisk(true).cacheInMemory(true)
                 .imageScaleType(ImageScaleType.EXACTLY)
                 .displayer(new SimpleBitmapDisplayer()).build();

        ImageLoaderConfiguration imageLoaderConfiguration = new ImageLoaderConfiguration.Builder(mContext)
                 .defaultDisplayImageOptions(displayImageOptions).memoryCache(new WeakMemoryCache())
                 .diskCacheSize(100 * 1024 * 1024).build();

        return imageLoaderConfiguration;
    }

    public static void setImage(String imageUrls, ImageView imageView , final ProgressBar progressBar, String stringToAppend){

                 ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(imageUrls, imageView, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) {
                if(progressBar!=null){
                    progressBar.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {
                if(progressBar!=null){
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                if(progressBar!=null){
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onLoadingCancelled(String s, View view) {
                if(progressBar!=null){
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

    }


}

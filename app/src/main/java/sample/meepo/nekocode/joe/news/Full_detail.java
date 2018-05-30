package sample.meepo.nekocode.joe.news;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import static android.R.attr.author;
import static android.R.attr.description;

public class Full_detail  extends AppCompatActivity {

    private TextView mDescription,mName,mAuthor,mWebUrl,mTitle;
    private ImageView mImageView;
    private String description,weburl,author,name,title,ImageUrls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_details);

        Intent intent = getIntent();

        description = intent.getStringExtra("description");
        weburl      = intent.getStringExtra("webURL");
        author      = intent.getStringExtra("author");
        name        = intent.getStringExtra("name");
        title       = intent.getStringExtra("title");
        ImageUrls   = intent.getStringExtra("ImageUrls");

        init();

    }

    private void init(){

        mDescription = (TextView) findViewById(R.id.description);
        mName = (TextView) findViewById(R.id.name);
        mAuthor=(TextView)findViewById(R.id.Author);
        mWebUrl=(TextView)findViewById(R.id.website);
        mTitle =(TextView)findViewById(R.id.title);
        mImageView = (ImageView) findViewById(R.id.imageFullDetails);

        mDescription.setText(description);
        mWebUrl.setText(weburl);
        mAuthor.setText(author);
        mWebUrl.setText(weburl);
        mTitle.setText(title);

        UniversalImageLoader.setImage(ImageUrls,mImageView,null,"");

        mWebUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(weburl));
                startActivity(intent);
            }
        });
    }
}

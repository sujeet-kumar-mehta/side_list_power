package in.sample.saltlistpower;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import in.sample.saltlistpower.model.ItemModel;

public class DetailActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ItemModel itemModel = getIntent().getParcelableExtra(MainActivity.ITEM);

        if (itemModel == null) {
            finish();
            return;
        }
        initUi(itemModel);
    }

    private void initUi(ItemModel itemModel) {
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("");
        ImageView imageView = (ImageView) findViewById(R.id.imageVIew);
        Glide.with(this).load(itemModel.getImage()).centerCrop().into(imageView);
        TextView titleTextView = (TextView) findViewById(R.id.title_text_view);
        titleTextView.setText(itemModel.getTitle());

        TextView descriptionTextView = (TextView) findViewById(R.id.subtitle_text_view);
        descriptionTextView.setText(itemModel.getDescription());
    }


}

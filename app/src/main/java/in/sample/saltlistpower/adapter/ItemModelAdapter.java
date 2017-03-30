package in.sample.saltlistpower.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import in.sample.saltlistpower.DetailActivity;
import in.sample.saltlistpower.MainActivity;
import in.sample.saltlistpower.R;
import in.sample.saltlistpower.model.ItemModel;

public class ItemModelAdapter extends RecyclerView.Adapter<ItemModelAdapter.ItemViewHolder> {


    private Context context;
    private List<ItemModel> itemModelList;

    public void setItemModelList(List<ItemModel> itemModelList) {
        this.itemModelList = itemModelList;
    }

    public ItemModelAdapter(List<ItemModel> itemModels, Context context) {
        this.context = context;
        this.itemModelList = itemModels;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_list, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.setUi(itemModelList.get(position));
    }

    @Override
    public int getItemCount() {

        return itemModelList != null ? itemModelList.size() : 0;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView titleTextView;
        TextView subtitleTextView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            titleTextView = (TextView) itemView.findViewById(R.id.title_text_view);
            subtitleTextView = (TextView) itemView.findViewById(R.id.subtitle_text_view);
        }

        public void setUi(final ItemModel model) {
            titleTextView.setText(model.getTitle());
            subtitleTextView.setText(model.getDescription());
            Glide.with(itemView.getContext()).load(model.getImage()).dontAnimate().into(imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra(MainActivity.ITEM, model);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }

    }
}

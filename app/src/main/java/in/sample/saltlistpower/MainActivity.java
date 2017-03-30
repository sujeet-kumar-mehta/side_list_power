package in.sample.saltlistpower;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import in.sample.saltlistpower.adapter.ItemModelAdapter;
import in.sample.saltlistpower.api.ApiClient;
import in.sample.saltlistpower.api.ApiInterface;
import in.sample.saltlistpower.data.DatabaseHandler;
import in.sample.saltlistpower.model.ItemModel;
import in.sample.saltlistpower.utils.DataUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final String ITEM ="item";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        final DatabaseHandler databaseHandler = new DatabaseHandler(getApplicationContext());

        List<ItemModel> itemModelList = databaseHandler.getItemModelList();

        final ItemModelAdapter itemModelAdapter = new ItemModelAdapter(itemModelList, getApplicationContext());

        if (itemModelList == null || (itemModelList != null && itemModelList.size() == 0)) {

            progressBar.setVisibility(View.VISIBLE);

            if (!DataUtils.isNetworkAvailable(getApplicationContext())) {
                Toast.makeText(getApplicationContext(), R.string.no_network, Toast.LENGTH_SHORT).show();
                return;
            }
            ApiInterface apiClient = ApiClient.getClient().create(ApiInterface.class);

            Call<List<ItemModel>> listCall = apiClient.getListItems();
            listCall.enqueue(new Callback<List<ItemModel>>() {
                @Override
                public void onResponse(Call<List<ItemModel>> call, Response<List<ItemModel>> response) {
                    progressBar.setVisibility(View.GONE);
                    List<ItemModel> itemModelList = response.body();

                    itemModelAdapter.setItemModelList(itemModelList);
                    itemModelAdapter.notifyDataSetChanged();

                    databaseHandler.addItemModelList(itemModelList);
                }

                @Override
                public void onFailure(Call<List<ItemModel>> call, Throwable t) {

                }
            });
        }

        recyclerView.setAdapter(itemModelAdapter);
    }

}

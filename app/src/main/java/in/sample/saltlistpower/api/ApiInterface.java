package in.sample.saltlistpower.api;

import java.util.List;

import in.sample.saltlistpower.model.ItemModel;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Api Interface
 */
public interface ApiInterface {

    /**
     * Get ALl the items
     *
     * @return List<ItemModel>
     */
    @GET("items.json")
    Call<List<ItemModel>> getListItems();
}

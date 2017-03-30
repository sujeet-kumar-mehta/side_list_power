package in.sample.saltlistpower;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import in.sample.saltlistpower.model.ItemModel;
import in.sample.saltlistpower.utils.MockModelsUtil;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {
    }


    @Test
    public void testItemsShowAndAreScrollableInFeed() {
        List<Integer> ItemModelIdList = MockModelsUtil.createMockItemModelIdList(20);
        List<ItemModel> ItemModelList = new ArrayList<>();
        for (int id : ItemModelIdList) {
            ItemModelList.add(MockModelsUtil.createMockItems());
        }

        mActivityTestRule.launchActivity(null);

        checkItemModelsDisplayOnRecyclerView(ItemModelList);
    }

    private void checkItemModelsDisplayOnRecyclerView(List<ItemModel> ItemModelsToCheck) {
        for (int i = 0; i < ItemModelsToCheck.size(); i++) {

            checkItemModelDisplays(ItemModelsToCheck.get(i));
        }
    }

    private void checkItemModelDisplays(ItemModel ItemModel) {
        onView(withText(ItemModel.getTitle()))
                .check(matches(isDisplayed()));
    }


}

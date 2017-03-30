package in.sample.saltlistpower.utils;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import in.sample.saltlistpower.model.ItemModel;

public class MockModelsUtil {


    public static String generateRandomString() {
        return UUID.randomUUID().toString();
    }

    public static int generateRandomInt() {
        return new Random().nextInt(80 - 65) + 65;
    }

    public static ItemModel createMockItems() {
        ItemModel model = new ItemModel();
        model.setID(generateRandomInt());
        model.setTitle(generateRandomString());
        return model;
    }



    public static List<Integer> createMockItemModelIdList(int count) {
        List<Integer> idList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            idList.add(generateRandomInt());
        }
        return idList;
    }

}
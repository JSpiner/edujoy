package net.jspiner.edujoy.ingredients;

public class FoodItem {

    public String filePath;
    public String name;
    public int kcal;

    public FoodItem(String filePath, String name, int kcal){
        this.filePath = filePath;
        this.name = name;
        this.kcal = kcal;
    }

}

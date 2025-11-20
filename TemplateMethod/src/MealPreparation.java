abstract class MealPreparation {

    public final void prepareMeal() {
        gatherIngredients();
        prepareIngredients();
        cookOrAssemble();
        serve();
    }

    abstract void gatherIngredients();
    abstract void prepareIngredients();
    abstract void cookOrAssemble();

    void serve() {
        System.out.println("Serving the meal on a plate.");
    }
}

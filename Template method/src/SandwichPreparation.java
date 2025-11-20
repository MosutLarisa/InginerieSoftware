class SandwichPreparation extends MealPreparation {

    @Override
    void gatherIngredients() {
        System.out.println("Gathering bread, cheese, ham, veggies.");
    }

    @Override
    void prepareIngredients() {
        System.out.println("Slicing veggies and cheese.");
    }

    @Override
    void cookOrAssemble() {
        System.out.println("Assembling sandwich layers.");
    }
}

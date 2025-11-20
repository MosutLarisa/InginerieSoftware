class SaladPreparation extends MealPreparation {

    @Override
    void gatherIngredients() {
        System.out.println("Gathering lettuce, tomatoes, cucumbers, dressing.");
    }

    @Override
    void prepareIngredients() {
        System.out.println("Washing and chopping vegetables.");
    }

    @Override
    void cookOrAssemble() {
        System.out.println("Mixing vegetables and adding dressing.");
    }
}

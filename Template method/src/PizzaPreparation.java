class PizzaPreparation extends MealPreparation {

    @Override
    void gatherIngredients() {
        System.out.println("Gathering dough, tomato sauce, cheese, toppings.");
    }

    @Override
    void prepareIngredients() {
        System.out.println("Rolling dough, cutting toppings.");
    }

    @Override
    void cookOrAssemble() {
        System.out.println("Assembling pizza and putting it in the oven.");
    }
}

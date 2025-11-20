public class Main {
    public static void main(String[] args) {

        System.out.println("-Preparing Sandwich");
        MealPreparation sandwich = new SandwichPreparation();
        sandwich.prepareMeal();

        System.out.println("\n-Preparing Pizza");
        MealPreparation pizza = new PizzaPreparation();
        pizza.prepareMeal();

        System.out.println("\n-Preparing Salad");
        MealPreparation salad = new SaladPreparation();
        salad.prepareMeal();
    }
}

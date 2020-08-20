package jovanka_milosevic_a1;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * This class will be used to order a pizza
 *
 * @author Jovanka Milosevic
 */
public class Jovanka_Milosevic_A1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        System.out.println("***Pizza Ordering Application***");

        Scanner input = new Scanner(System.in);
        do {
            int customerChoice = chooseAction(input);

            if (customerChoice == 1) {
                System.out.println("Enter your name: ");
            } else if (customerChoice == 2) {
                System.out.println("Thank you for using our app.");
                break;
            }

            // user's name
            String usersName = enterUserName(input);

            // user's phone no.
            System.out.println("Enter your phone number (xxx xxx-xxxx or xxx xxx xxxx): ");
            String phoneNum = enterPhoneNumber(input);

            // no. of pizzas
            int numberOfPizzas = enterNumberOfPizzas(input);

            // creating pizza objects and an array of pizza objects
            Pizza[] pizzas = new Pizza[numberOfPizzas];
            for (int i = 0; i < pizzas.length; i++) {
                System.out.printf("Pizza %d of %d. Enter size:\n"
                        + "1 - for small\n"
                        + "2 - for medium\n"
                        + "3 - for large\n", i + 1, pizzas.length);
                int pizzaSize = enterPizzaSize(input);
                int numberOfToppings = enterNumberOfToppings(input);
                String[] toppings = new String[numberOfToppings];
                for (int j = 0; j < toppings.length; j++) {
                    System.out.printf("Enter topping %d: ", j + 1);
                    toppings[j] = enterToppingName(input);
                }
                Pizza pizzaP = new Pizza(pizzaSize, toppings);
                pizzas[i] = pizzaP;
            }

            //try-catch block to check if user's input for the type of end action is valid, and to handle input errors
            while (true) {
                try {
                    System.out.println("What do you want to do?\n"
                            + "1. View your order\n"
                            + "2. Complete your order");
                    int orderSelection = input.nextInt();
                    input.nextLine();
                    if (orderSelection == 1) {
                        System.out.printf("Name: %s\n", usersName);
                        System.out.printf("Phone number: %s\n", phoneNum);
                        System.out.printf("There are %d pizzas in your order.\n", numberOfPizzas);
                        for (Pizza pizza : pizzas) {
                            System.out.printf("%s with %d toppings\n", pizza.getSizeString(), pizza.getToppings().length);
                            for (String topping : pizza.getToppings()) {
                                System.out.printf("- %s\n", topping);
                            }
                        }
                    } else if (orderSelection == 2) {
                        System.out.printf("Hi %s, your order of %d pizza(s) will be ready "
                                + "for pickup in %d minutes.\n", usersName, numberOfPizzas, getDeliveryTime());
                        break;
                    } else {
                        System.out.println("Please enter 1 or 2: ");
                    }
                } catch (InputMismatchException ex) {
                    System.out.println("Please, enter a number: ");
                    input.nextLine();
                }
            }

        } while (true);
    }

    /**
     * A method that returns user's name
     * @param input user name
     * @return used to return user's name
     */
    private static String enterUserName(Scanner input) {
        while (true) {
            String name = input.nextLine();
            int nameLength = name.length();
            if (nameLength > 2) {
                return name;
            } else {
                System.out.println("Please enter your full name: ");
            }
        }
    }

    /**
     * A method that returns entered number of pizzas
     * @param input number of pizzas
     * @return used to return number of pizzas
     */ 
    private static int enterNumberOfPizzas(Scanner input) {
        // no. of pizzas
        System.out.println("How many pizzas will you be ordering?");
        while (true) {
            try {
                int numberOfPizzas = input.nextInt();
                input.nextLine();
                if (numberOfPizzas < 1) {
                    System.out.println("Please, enter a valid number of Pizzas(at least one Pizza): ");
                } else {
                    return numberOfPizzas;
                }
            } catch (InputMismatchException ex) {
                System.out.println("Please, enter a number: ");
                input.nextLine();
            }
        }
    }

    /**
     * A method that is used to ask the user to choose what he wants to do at beginning
     * @param input number for user's choice
     * @return used to return number for user's choice
     */
    private static int chooseAction(Scanner input) {
        System.out.println("What do you want to do? (enter the number of the offered options)\n"
                + "1. Create a new order\n"
                + "2. Exit application");
        //try-catch block to check if user's input for the type of action is valid, and to handle input errors
        while (true) {
            try {
                int customerChoice = input.nextInt();
                input.nextLine();
                if (customerChoice < 1 || customerChoice > 2) {
                    System.out.println("Please enter 1 or 2: ");
                } else {
                    return customerChoice;
                }
            } catch (InputMismatchException ex) {
                System.out.println("Please, enter a number: ");
                input.nextLine();
            }
        }
    }

    /**
     * A method that return user's phone if it's valid, or that is used to ask him to input the valid one
     * @param input user's phone number
     * @return used to return user's phone number
     */
    private static String enterPhoneNumber(Scanner input) {

        while (true) {
            String userPhone = input.nextLine();

            String userPhoneCleaned = userPhone.trim();

            String regexPhone = "\\d{3} \\d{3}[ -]\\d{4}";

            if (userPhoneCleaned.matches(regexPhone)) {
                return userPhoneCleaned;
            } else {
                System.out.println("Enter valid phone number: ");
            }
        }

    }

    /**
     * A method that return pizza's size, or that is used to ask user enter a valid value
     * @param input number for pizza size
     * @return used to return number for pizza size
     */
    private static int enterPizzaSize(Scanner input) {

        while (true) {
            try {

                int pizzaSize = input.nextInt();
                input.nextLine();
                if (pizzaSize < 1 || pizzaSize > 3) {
                    System.out.println("Please, enter a valid number for pizza size (1, 2, or 3): ");
                } else {
                    return pizzaSize;
                }

            } catch (InputMismatchException ex) {
                System.out.println("Please, enter a number: ");
                input.nextLine();
            }
        }

    }

    /**
     * A method that return number of toppings, or that is used ask for valid input
     * @param input number of toppings 
     * @return used to return number of toppings 
     */
    private static int enterNumberOfToppings(Scanner input) {
        System.out.println("How many toppings?");
        while (true) {
            try {

                int numOfToppings = input.nextInt();
                input.nextLine();
                if (numOfToppings < 0) {
                    System.out.println("Please, enter a valid number of Pizzas(not negative numbers): ");
                } else {
                    return numOfToppings;
                }

            } catch (InputMismatchException ex) {
                System.out.println("Please, enter a number: ");
                input.nextLine();
            }
        }
    }

    /**
     * A method that returns topping name if the input is valid, or that is used to asks for valid input
     * @param input name of the toppings
     * @return used to return name of the toppings
     */
    private static String enterToppingName(Scanner input) {
        while (true) {
            String toppingName = input.nextLine();
            int toppingLength = toppingName.length();
            if (toppingLength > 2) {
                return toppingName;
            } else {
                System.out.println("Enter valid topping name: ");
            }
        }
    }

    /**
     * A method that returns random numbers between 15 and 30 (number of minutes to wait)
     * @return used to return random number
     */
    private static int getDeliveryTime() {
        Random rand = new Random();
        int numMin = rand.nextInt(15) + 15;
        return numMin;
    }

}

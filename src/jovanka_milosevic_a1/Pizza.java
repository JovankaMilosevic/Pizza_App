package jovanka_milosevic_a1;

/**
 * This class represents different types of pizza we can order
 *
 * @author Jovanka Milosevic
 */
public class Pizza {

    private String[] toppings;
    private int size;

    public Pizza(int size, String[] toppings) {
        this.size = size;
        this.toppings = toppings;
    }

    public String[] getToppings() {
        return toppings;
    }

    public int getSize() {
        return size;
    }

    public void setToppings(String[] toppings) {
        this.toppings = toppings;
    }

    public void setSize(int size) {
        this.size = size;
    }

    /**
     * A method that output the choice of the pizza size as a string
     * @return
     */
    
    public String getSizeString() {
        int sizeInt = getSize();
        String sizeString = "";

        switch (sizeInt) {
            case 1:
                sizeString = "Small pizza";
                break;
            case 2:
                sizeString = "Medium pizza";
                break;
            case 3:
                sizeString = "Large pizza";
                break;
            default:
                System.out.println("Enter a number 1-3: ");
        }
        return sizeString;
    }
}

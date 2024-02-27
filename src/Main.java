public class Main {
    public static void main(String[] args) {
        ToyShop toyShop = new ToyShop("1 2 конструктор", "2 2 робот", "3 6 кукла");
        toyShop.writeSelectedToOutput(10, "output.txt");
    }
}
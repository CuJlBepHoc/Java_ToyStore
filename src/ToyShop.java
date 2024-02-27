import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.PriorityQueue;

public class ToyShop {
    private final PriorityQueue<Toy> queue;

    public ToyShop(String toy1, String toy2, String toy3) {
        queue = new PriorityQueue<>((t1, t2) -> Integer.compare(t2.getWeight(), t1.getWeight()));

        addToQueueFromString(toy1);
        addToQueueFromString(toy2);
        addToQueueFromString(toy3);
    }

    public ToyShop(List<String> toyList) {
        queue = new PriorityQueue<>((t1, t2) -> Integer.compare(t2.getWeight(), t1.getWeight()));

        for (String toyString : toyList) {
            addToQueueFromString(toyString);
        }
    }

    private void addToQueueFromString(String toyString) {
        String[] toyData = toyString.split(" ");
        if (toyData.length == 3) {
            int id = Integer.parseInt(toyData[0]);
            int weight = Integer.parseInt(toyData[1]);
            String name = toyData[2];
            queue.add(new Toy(id, name, weight));
        } else {
            System.out.println("Invalid toy data: " + toyString);
        }
    }

    public void addToQueue(Toy toy) {
        queue.add(toy);
    }

    public void writeSelectedToOutput(int times, String path) {
        try (FileWriter writer = new FileWriter(path)) {
            for (int i = 0; i < times; i++) {
                double random = Math.random();
                if (random <= 0.2) {
                    if (!queue.isEmpty())
                        writer.write(queue.peek().getId() + "\n");
                } else if (random <= 0.4) {
                    Toy[] toys = queue.toArray(new Toy[0]);
                    if (toys.length > 1)
                        writer.write(toys[0].getId() + "\n");
                } else {
                    Toy[] toys = queue.toArray(new Toy[0]);
                    if (toys.length > 2)
                        writer.write(toys[1].getId() + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}

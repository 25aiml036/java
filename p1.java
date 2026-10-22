import java.util.Scanner;

class Item {
  private final String name;
  private final int price;

  Item(String name, int price) {
    this.name = name;
    this.price = price;
  }

  public String getName() {
    return name;
  }

  public int getPrice() {
    return price;
  }

  public static Item findItem(char code) {
    return switch (code) {
      case 'T' -> new Item("Tea", 10);
      case 'C' -> new Item("Coffee", 15);
      case 'S' -> new Item("Samosa", 12);
      case 'P' -> new Item("Puff", 20);
      default -> null;
    };
  }
}

class Order {
  private static int nextToken = 101;
  private static int totalOrders = 0;
  private static int totalSales = 0;

  private final int tokenNo;
  private final Item item;
  private final int quantity;

  public Order(Item item, int quantity) {
    this.tokenNo = nextToken++;
    this.item = item;
    this.quantity = quantity;
    totalOrders++;
    totalSales += item.getPrice() * quantity;
  }

  public int getAmount() {
    return item.getPrice() * quantity;
  }

  public static int getTotalOrders() {
    return totalOrders;
  }

  public static int getTotalSales() {
    return totalSales;
  }

  @Override
  public String toString() {
    return "Token " + tokenNo + ": " + item.getName() + " x " + quantity + " = " + getAmount();
  }
}

public class p1 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    scanner.nextLine(); 

    for (int i = 0; i < n; i++) {
      String[] input = scanner.nextLine().split(" ");
      char code = input[0].charAt(0);
      int quantity = Integer.parseInt(input[1]);

      if (quantity > 0) {
        Item item = Item.findItem(code);
        if (item != null) {
          Order order = new Order(item, quantity);
          System.out.println(order);
        } else {
          System.out.println("Invalid order: " + code + " " + quantity);
        }
      } else {
        System.out.println("Invalid order: " + code + " " + quantity);
      }
    }

    System.out.println("Total orders: " + Order.getTotalOrders() + ", Total sales: " + Order.getTotalSales());
  }
}

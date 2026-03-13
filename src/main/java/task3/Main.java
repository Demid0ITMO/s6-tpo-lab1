package task3;

import task3.item.CornFlakes;
import task3.item.DentrassiUnderwear;
import task3.item.Item;
import task3.item.ScvorshneineMatrass;
import task3.person.Arthur;
import task3.person.Ford;

public class Main {
  public static void main(String[] args) {
    Ford f = new Ford();
    Arthur a = new Arthur();

    System.out.println(
      f.getName() + 
      (f.getVial() == null ? "лох" : 
      " имеет " + f.getVial().toString() +
      (f.getVial().getFish() == null ? " пустой ": " с " + f.getVial().getFish().toString())
      )
    );

    System.out.println(a.lookAt(f) + (a.isBlinking() ? " моргая " : " пересохшими глазами "));

    System.out.println(f.offerFishToEar(a));

    Item[] items = {
      new DentrassiUnderwear(),
      new CornFlakes(),
      new ScvorshneineMatrass(),
    };

    for (Item i : items) {
      System.out.println("когда " + a.lookAt(i) + ", уверенность: " + a.getConfidence());
    }
  }
}

package Zadanie1;

public class Javalab1Obrot {
  public static void main(String[] args) {

    // Punkt p1 = Punkt.E_X;
    Transformacja x = new Skalowanie(3, 3);
    Transformacja y = new Translacja(3, 3);
    Transformacja[] tab = { x, y };
    Punkt p = new Punkt(3, 2);
    Transformacja zloz = new ZlozenieTransformacji(tab);
    System.out.println(p);
    p = zloz.transformuj(p);
    System.out.println(p);
  }
}

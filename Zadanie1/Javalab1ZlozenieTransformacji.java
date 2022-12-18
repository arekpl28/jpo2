package Zadanie1;

public class Javalab1ZlozenieTransformacji {
  public static void main(String[] args) {

    Transformacja[] transformacje = { new Skalowanie(2, 2), new Translacja(3, 3) };

    Punkt p1 = Punkt.E_X;
    Transformacja tr = new ZlozenieTransformacji(transformacje);
    ZlozenieTransformacji odwrotne;
    Punkt p2 = tr.transformuj(p1);
    ZlozenieTransformacji zlozenie = new ZlozenieTransformacji(transformacje);
    try {
      odwrotne = (ZlozenieTransformacji) zlozenie.getTransformacjaOdwrotna();
      System.out.println(odwrotne);
    }

    catch (BrakTransformacjiOdwrotnejException ex) {
      ex.printStackTrace();
    }
    System.out.println();

    System.out.println(zlozenie);

    System.out.println(p2);

  }
}

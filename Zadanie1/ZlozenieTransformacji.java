package Zadanie1;

public class ZlozenieTransformacji implements Transformacja {
  private Transformacja tab[];

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

  public ZlozenieTransformacji(Transformacja tab[]) {
    this.tab = new Transformacja[tab.length];
    for (int i = 0; i < tab.length; i++) {
      if (tab[i] instanceof Translacja) {
        Translacja temp = (Translacja) tab[i];
        this.tab[i] = new Translacja(temp.getdX(), temp.getdY());
      } else if (tab[i] instanceof Skalowanie) {
        Skalowanie temp = (Skalowanie) tab[i];
        this.tab[i] = new Skalowanie(temp.getSkalaX(), temp.getSkalaY());
      } else if (tab[i] instanceof Obrot) {
        Obrot temp = (Obrot) tab[i];
        this.tab[i] = new Obrot(temp.getAlfa());
      } else if (tab[i] instanceof ZlozenieTransformacji) {
        ZlozenieTransformacji temp = (ZlozenieTransformacji) tab[i];
        this.tab[i] = new ZlozenieTransformacji(temp);
      }
    }
  }

  public ZlozenieTransformacji(ZlozenieTransformacji zlozenie) { // copy constructor
    this.tab = new Transformacja[zlozenie.tab.length];
    for (int i = 0; i < tab.length; i++) {
      if (zlozenie.tab[i] instanceof Translacja) {
        Translacja temp = (Translacja) zlozenie.tab[i];
        this.tab[i] = new Translacja(temp.getdX(), temp.getdY());
      } else if (zlozenie.tab[i] instanceof Skalowanie) {
        Skalowanie temp = (Skalowanie) zlozenie.tab[i];
        this.tab[i] = new Skalowanie(temp.getSkalaX(), temp.getSkalaY());
      } else if (zlozenie.tab[i] instanceof Obrot) {
        Obrot temp = (Obrot) zlozenie.tab[i];
        this.tab[i] = new Obrot(temp.getAlfa());
      } else if (zlozenie.tab[i] instanceof ZlozenieTransformacji) {
        ZlozenieTransformacji temp = (ZlozenieTransformacji) zlozenie.tab[i]; // fajna rekurencja :D
        this.tab[i] = new ZlozenieTransformacji(temp);
      }
    }
  }

  public Transformacja[] getTransformacja() {
    return this.tab;
  }

  @Override
  public Punkt transformuj(Punkt p) {
    for (int i = 0; i < tab.length; i++) {
      p = tab[i].transformuj(p);
    }
    return p;
  }

  @Override
  public Transformacja getTransformacjaOdwrotna() throws BrakTransformacjiOdwrotnejException {
    Transformacja temp[] = new Transformacja[this.tab.length];
    for (int i = 0; i < tab.length; i++) {
      try {

        temp[i] = this.tab[i].getTransformacjaOdwrotna(); // moze nastapic rekurencja
      } catch (BrakTransformacjiOdwrotnejException ex) {
        throw ex;
      }
    }
    return new ZlozenieTransformacji(temp);
  }

  @Override
  public String toString() {
    String return_string = "ZlozenieTransformacji: \n";
    for (int i = 0; i < this.tab.length; i++) {
      return_string += tab[i].toString();
    }
    return return_string;
  }

}

package ZadanieAdwance2.biegpolesie;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class OstatniaTura implements ElementTrasy {
  private final Komentator komentator;
  private final Set<Uczestnik> uczestnicyWOstatnijTurze = new HashSet<>();
  private final Set<Uczestnik> uczestnicyWBierzacejTurze = new HashSet<>();;

  OstatniaTura(Komentator komentator) {
    this.komentator = komentator;
  }

  @Override
  public Iterable<Uczestnik> getUczestnicy() {
    return uczestnicyWOstatnijTurze;
  }

  @Override
  public void dodajUczestnika(Uczestnik u) {
    uczestnicyWOstatnijTurze.add(u);
    uczestnicyWBierzacejTurze.add(u);
  }

  @Override
  public void usunUczestnika(Uczestnik u) {
    uczestnicyWOstatnijTurze.remove(u);
  }

  @Override
  public int getLiczbaUczestnikowNaTrasie() {
    return uczestnicyWOstatnijTurze.size();
  }

  void sprawdzenieMety(List<ElementTrasy> elementyTrasy) {
    ElementTrasy ostatniElementTrasy = wezOstatniElementTrasy(elementyTrasy);
    przypisanieUczestnikowDoTury(ostatniElementTrasy);
    czyUczestnicyPokonaliOstaniElement();
    nowaTura();
  }

  private void przypisanieUczestnikowDoTury(ElementTrasy ostatniElementTrasy) {
    for (Uczestnik u : ostatniElementTrasy.getUczestnicy()) {
      dodajUczestnika(u);
    }
  }

  private void czyUczestnicyPokonaliOstaniElement() {
    if (getLiczbaUczestnikowNaTrasie() != uczestnicyWBierzacejTurze.size()) {
      Set<Uczestnik> uczestnicyNaMecie = new HashSet<>(uczestnicyWOstatnijTurze);
      uczestnicyNaMecie.removeAll(uczestnicyWBierzacejTurze);
      uczestnicyNaMecie.forEach(this::metaDlaUczestnika);
    }
  }

  private ElementTrasy wezOstatniElementTrasy(List<ElementTrasy> elementyTrasy) {
    return elementyTrasy.get(elementyTrasy.size() - 1);
  }

  private void metaDlaUczestnika(Uczestnik uczestnik) {
    komentator.relacjonujUczestnikaNaMecie(uczestnik);
    usunUczestnika(uczestnik);
  }

  private void nowaTura() {
    uczestnicyWBierzacejTurze.clear();
  }
}

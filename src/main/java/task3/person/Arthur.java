package task3.person;

import task3.item.CornFlakes;

public class Arthur extends Person {
    private int confidence = 50;
    private boolean blinking = false;

    public Arthur() {
        super("Артур", "Земля");
    }

    public String lookAt(Object object) {
        blink();

        if (object instanceof CornFlakes) {
            confidence += 10;
        }

        return getName() + " смотрит на " + object.toString();
    }

    private void blink() {
        blinking = true;
    }

    public boolean isBlinking() { return blinking; }
    public void resetBlink() { blinking = false; }

    public int getConfidence() { return confidence; }

}
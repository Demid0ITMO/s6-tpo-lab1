package task3.person;

import task3.fish.YellowFish;
import task3.item.GlassVial;

public class Ford extends Person {
    private GlassVial vial;

    public Ford() {
        super("Форд", "Бетельгейзе");
        this.vial = new GlassVial(new YellowFish());
    }

    public GlassVial getVial() { return vial; }

    public String offerFishToEar(Person person) {
        String pname = person.getName() != null ? person.getName() : "кому-то";
        return 
        (vial != null && vial.getFish() != null ? 
        getName() + " предложил " + pname + " засунуть " + vial.getFish().toString() + " в ухо" : 
        getName() + " нечего предложить " + pname
        );
    }
}
package task3.item;

import task3.fish.Fish;

public class GlassVial extends Item {
    private Fish fish;

    public GlassVial(Fish fish) {
        super("стеклянный флакон");
        this.fish = fish;
    }

    public Fish getFish() { return fish; }
}
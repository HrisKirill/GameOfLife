package khrystoforov;

import lombok.Getter;

@Getter
public class World {

    Generation currentGeneration;

    private final int maxGeneration;

    public World(boolean[] data, int maxGeneration, int height, int width) {
        this.currentGeneration = new Generation(data, height, width);
        this.maxGeneration = maxGeneration;
    }

    public void updateGeneration() {
        this.currentGeneration = new Generation(currentGeneration);
    }

}
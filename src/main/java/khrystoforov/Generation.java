package khrystoforov;

import lombok.Getter;

@Getter
public class Generation {

    private final boolean alive = true;
    private final boolean dead = false;
    private final int height;
    private final int width;
    private final boolean[][] cells;


    /**
     * Constructor with params.
     *
     * @param data   - array with data
     * @param height - height
     * @param width  - width
     */
    public Generation(boolean[] data, int height, int width) {
        this.width = width;
        this.height = height;
        this.cells = new boolean[width][height];
        int count = 0;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cells[i][j] = data[count++];
            }
        }


    }

    /**
     * Constructs a generation according to the previous Generation's state and to the Game of Life's
     * rules.
     *
     * @param previousGeneration - previous generation
     */
    public Generation(Generation previousGeneration) {
        this.width = previousGeneration.getWidth();
        this.height = previousGeneration.getHeight();
        this.cells = new boolean[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                this.cells[i][j] = previousGeneration.nextGenCellVal(i, j);
            }
        }
    }

    /**
     * Check element is alive.
     *
     * @param cellValue - value
     * @return X if cellValue represents alive cell, O if represents a dead cell.
     */
    public boolean isAlive(boolean cellValue) {
        return cellValue;
    }

    /**
     * Check element is alive.
     *
     * @param row - row
     * @param col - column
     * @return X if cell[row][col] is alive, O if it is a dead cell.
     */
    private boolean isAlive(int row, int col) {
        return this.cells[row][col];
    }

    /**
     * Get alive or dead element.
     *
     * @param row - row
     * @param col - column
     * @return X if cells[row][col] is alive in the next Generation, O if dead.
     */
    public boolean nextGenCellVal(int row, int col) {
        int aliveNeighbors = numberOfAliveNeighbors(row, col);
        boolean nextVal;
        if (isAlive(cells[row][col])) {
            if ((aliveNeighbors == 2) || (aliveNeighbors == 3)) {
                nextVal = alive;
            } else {
                nextVal = dead;
            }
        } else {
            if (aliveNeighbors == 3) {
                nextVal = alive;
            } else {
                nextVal = dead;
            }
        }
        return nextVal;
    }

    /**
     * Get number of alive neighbors.
     *
     * @param row - row
     * @param col - column
     * @return The number of alive neighbors of cells[row][col].
     */
    public int numberOfAliveNeighbors(int row, int col) {
        int counter = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                int neighborRow = cycleCoordinate(row + i, width);
                int neighborCol = cycleCoordinate(col + j, height);
                if (isAlive(neighborRow, neighborCol)) {
                    counter++;
                }
            }
        }

        return counter;
    }

    private int cycleCoordinate(int coordinate, int size) {
        return (coordinate + size) % size;
    }
}
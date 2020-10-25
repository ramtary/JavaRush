package com.javarush.task.task35.task3513;

import java.util.*;

public class Model {
    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
    private Stack<Tile[][]> previousStates = new Stack();
    private Stack<Integer> previousScores = new Stack();
    private boolean isSaveNeeded = true;

    int score;
    int maxTile;

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    public Model() {
        score = 0;
        maxTile = 0;
        resetGameTiles();
    }

    public void saveState(Tile[][] gameState) {
        Tile[][] saveState = new Tile[gameState.length][gameState[0].length];
        for (int i = 0; i < gameState.length; i++) {
            for (int j = 0; j < gameState[0].length; j++) {
                saveState[i][j] = new Tile(gameState[i][j].getValue());
            }
        }
        previousStates.push(saveState);
        int scoreToSave = score;
        previousScores.push(scoreToSave);
        isSaveNeeded = false;
    }

    public void rollback() {
        if (!previousStates.isEmpty() && !previousScores.isEmpty()) {
            gameTiles = previousStates.pop();
            score = previousScores.pop();
        }
    }

    void resetGameTiles() {
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[i].length; j++) {
                gameTiles[i][j] = new Tile();
            }
        }
        addTile();
        addTile();
    }

    public void left() {
        if (isSaveNeeded) saveState(gameTiles);
        boolean hasChanged = false;
        for(int i = 0; i < gameTiles.length; i++) {
            if(compressTiles(gameTiles[i]) | mergeTiles(gameTiles[i])) {
                hasChanged = true;
            }
        }
        if(hasChanged) {
            addTile();
            isSaveNeeded = true;
        }
    }

    public void right() {
        saveState(gameTiles);
        rotate();
        rotate();
        left();
        rotate();
        rotate();
    }

    public void up() {
        saveState(gameTiles);
        rotate();
        left();
        rotate();
        rotate();
        rotate();
    }

    public void down() {
        saveState(gameTiles);
        rotate();
        rotate();
        rotate();
        left();
        rotate();
    }

    public void randomMove() {
        int n = ((int) (Math.random() * 100)) % 4;
        switch (n) {
            case 0:
                left();
                break;
            case 1:
                right();
                break;
            case 2:
                up();
                break;
            case 3:
                down();
                break;
        }
    }

    public boolean hasBoardChanged() {
        int weighNow = 0;
        int weightPrev = 0;
        Tile[][] prevGame = previousStates.peek();
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[0].length; j++) {
                weighNow += gameTiles[i][j].getValue();
                weightPrev += prevGame[i][j].getValue();
            }
        }
        return weighNow != weightPrev;
    }

    public MoveEfficiency getMoveEfficiency(Move move) {
        MoveEfficiency moveEfficiency;
        move.move();
        if (hasBoardChanged()) moveEfficiency = new MoveEfficiency(getEmptyTiles().size(), score, move);
        else moveEfficiency = new MoveEfficiency(-1, 0, move);
        rollback();

        return moveEfficiency;
    }

    public void autoMove() {
        PriorityQueue<MoveEfficiency> fourMoves = new PriorityQueue<MoveEfficiency>(4, Collections.reverseOrder());
        fourMoves.add(getMoveEfficiency(this::left));
        fourMoves.add(getMoveEfficiency(this::right));
        fourMoves.add(getMoveEfficiency(this::up));
        fourMoves.add(getMoveEfficiency(this::down));

        Move move = fourMoves.peek().getMove();
        move.move();
    }

    private void addTile() {
        List<Tile> emptyTiles = getEmptyTiles();
        if (emptyTiles.size() > 0) {
            emptyTiles.get((int) (Math.random() * emptyTiles.size())).value = (Math.random() < 0.9) ? 2 : 4;
        }
    }

    private List<Tile> getEmptyTiles() {
        List<Tile> result = new ArrayList<>();
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[i].length; j++) {
                if (gameTiles[i][j].isEmpty()) {
                    result.add(gameTiles[i][j]);
                }
            }
        }
        return result;
    }

    private boolean mergeTiles(Tile[] tiles) {
        boolean isMerged = false;
        for(int i = 0; i < tiles.length - 1; i++) {
            if(tiles[i].value == tiles[i + 1].value && tiles[i].value != 0) {
                tiles[i].value *= 2;
                isMerged = true;
                if (tiles[i].value > maxTile) {
                    maxTile = tiles[i].value;
                }
                score += tiles[i].value;
                tiles[i + 1] = new Tile();
                i++;
            }
        }
        if (isMerged) {
            compressTiles(tiles);
        }
        return isMerged;
    }

    private boolean compressTiles(Tile[] tiles) {
        boolean isCompressed = false;
        int index = 0;
        for(int i = 0; i < tiles.length; i++) {
            if(tiles[i].value == 0) continue;
            if(index == i) {
                index++;
                continue;
            }
            Tile tmp = tiles[index];
            tiles[index] = tiles[i];
            index++;
            tiles[i] = tmp;
            isCompressed = true;
        }
        return isCompressed;
    }

    private void rotate() {
        int len = FIELD_WIDTH;
        for (int k = 0; k < len / 2; k++) // border -> center
        {
            for (int j = k; j < len - 1 - k; j++) // left -> right
            {
                Tile tmp = gameTiles[k][j];
                gameTiles[k][j] = gameTiles[j][len - 1 - k];
                gameTiles[j][len - 1 - k] = gameTiles[len - 1 - k][len - 1 - j];
                gameTiles[len - 1 - k][len - 1 - j] = gameTiles[len - 1 - j][k];
                gameTiles[len - 1 - j][k] = tmp;
            }
        }
    }

    public boolean canMove() {
        if (!getEmptyTiles().isEmpty())
            return true;
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 1; j < gameTiles.length; j++) {
                if (gameTiles[i][j].value == gameTiles[i][j - 1].value)
                    return true;
            }
        }
        for (int j = 0; j < gameTiles.length; j++) {
            for (int i = 1; i < gameTiles.length; i++) {
                if (gameTiles[i][j].value == gameTiles[i - 1][j].value)
                    return true;
            }
        }
        return false;
    }


}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

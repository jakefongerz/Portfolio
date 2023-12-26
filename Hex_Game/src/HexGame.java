// Jacob Fonger
// A02316298

import java.util.ArrayList;

public class HexGame {
    private final int numOfHex;
    private final int size;
    DisjointSet board;
    private final int TOP_EDGE;
    private final int BOTTOM_EDGE;
    private final int LEFT_EDGE;
    private final int RIGHT_EDGE;
    ArrayList<Integer> blueMoves = new ArrayList<>();
    ArrayList<Integer> redMoves = new ArrayList<>();


    public HexGame(int size){
        this.size = size; // length/height
        numOfHex = (int) Math.pow(size, 2); // total number of nodes
        board = new DisjointSet(numOfHex + 4);
        TOP_EDGE = numOfHex + 3;
        BOTTOM_EDGE = numOfHex + 2;
        LEFT_EDGE = numOfHex + 1;
        RIGHT_EDGE = numOfHex;
        blueMoves.add(LEFT_EDGE);
        blueMoves.add(RIGHT_EDGE);
        redMoves.add(TOP_EDGE);
        redMoves.add(BOTTOM_EDGE);
    }

    public boolean playBlue(int position, boolean displayNeighbors) {
        position = position - 1;
        ArrayList<Integer> neighbors = getNeighborsBlue(position);

        if (displayNeighbors) {
            String neighborStr = "Cell " + (position +1) + ": [";
            for (int neighbor : neighbors) {
                neighborStr += neighbor + 1 + " ";
            }
            System.out.println(neighborStr + "]");
        }
        if (isOccupied(position)){
            return false;
        }
        blueMoves.add(position);
        for (int neighbor : neighbors){
            if (blueMoves.contains(neighbor)){
                board.union(neighbor, position);
            }
        }

        //TODO: write some stuff
        return board.find(LEFT_EDGE) == board.find(RIGHT_EDGE);
    }

    public boolean playRed(int position, boolean displayNeighbors){
        position = position - 1;
        ArrayList<Integer> neighbors = getNeighborsRed(position);
        if (displayNeighbors) {
            String neighborStr = "Cell " + (position +1) + ": [";
            for (int neighbor : neighbors) {
                neighborStr += neighbor + 1 + " ";
            }
            System.out.println(neighborStr + "]");
        }
        if (isOccupied(position)){
            return false;
        }
        redMoves.add(position);
        for (int neighbor : neighbors){
            if (redMoves.contains(neighbor)) {
                board.union(neighbor, position);
            }
        }

        //TODO: write some more stuff
        return board.find(TOP_EDGE) == board.find(BOTTOM_EDGE);
    }

    private boolean isOccupied(int position) {
    return (blueMoves.contains(position) || redMoves.contains(position));
        }

    private ArrayList<Integer> getNeighborsBlue(int position) {
        ArrayList<Integer> neighbors = new ArrayList<>();
        // if on left side
        if ((position) % size == 0 || position == 0){
            neighbors.add(LEFT_EDGE);
            neighbors.add(position + 1);
            if (position == 0){ // if top left corner
                neighbors.add(position + size);
            }
            else if (position == numOfHex - size){ // bottom left corner
                neighbors.add(position - size);
                neighbors.add(position - size + 1);
            }
            else { // just on the side but not the corners
                neighbors.add(position - size);
                neighbors.add(position - size + 1);
                neighbors.add(position + size);
            }

        }
        else if ((position +1) % size == 0){ // if on Right edge
            neighbors.add(RIGHT_EDGE);
            neighbors.add(position - 1);
            if (position == numOfHex -1) { // if top right corner
                neighbors.add(position - size);
            }
            else if (position == size - 1){ // if bottom right corner
                neighbors.add(position + size);
                neighbors.add(position + size -1);
            }
            else { // just on right side
                neighbors.add(position + size);
                neighbors.add(position + size -1);
                neighbors.add(position - size);
            }
        }
        else { // if not on right or left edges
            neighbors.add(position -1);
            neighbors.add(position + 1);
            if (position < size ){ // touches top
                neighbors.add(position + size);
                neighbors.add(position + size - 1);
            }
            else if (position >= numOfHex - size){ // touches bottom
                neighbors.add(position - size);
                neighbors.add(position - size + 1);
            }
            else { // touches no sides
                neighbors.add(position - size);
                neighbors.add(position - size + 1);
                neighbors.add(position + size);
                neighbors.add(position + size - 1);
            }
        }
        return neighbors;
    }


    private ArrayList<Integer> getNeighborsRed(int position) {
        ArrayList<Integer> neighbors = new ArrayList<>();
        if (position < size){ // top
            neighbors.add(TOP_EDGE);
            neighbors.add(position + size);

            if (position != 0) { // if not left corner
            neighbors.add(position - 1);
            neighbors.add((position + size - 1));

            }
            if (position != size - 1) { // if not right corner
            neighbors.add(position + 1);
            }
        }

        else if (position >= numOfHex - size) { // bottom
            neighbors.add(BOTTOM_EDGE);
            neighbors.add(position - size);
            if (position != numOfHex - size) { // if not left corner
                //neighbors.add(position - size - 1);
                neighbors.add(position - 1);
            }
            if (position != numOfHex - 1){ //if not right corner
                neighbors.add(position - size + 1);
                neighbors.add(position + 1);
            }

        }
        else { // for non-corner nodes

            // if not on left side
            if ((position + 1) % size != 0) {
                neighbors.add(position + 1);
                neighbors.add(position - size + 1);
            }
            // if not on right side
            if (position % size != 0) neighbors.add(position - 1);
            neighbors.add(position - size);

            neighbors.add((position + size - 1));
            neighbors.add(position + size);

        }
        return neighbors;
    }

    public int getSize(){
    return size;
    }


}

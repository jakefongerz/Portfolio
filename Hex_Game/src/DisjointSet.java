// Jacob Fonger
// A02316298

public class DisjointSet {
    private final int[] s;
    public DisjointSet(int size){
        s = new int[size];

        for (int i = 0; i < size; i++){
            s[i] = -1;
        }
    }




    public int find(int node){
        if (node >= s.length || node < 0) {
            System.out.println("ERROR: Invalid node given to find method : "  + node + " is not within bounds");
            System.exit(1);
        }
        return doFind(node);
    }

    private int doFind(int node){
        if (s[node] < 0) {
            return node;
        }
        else {
            return s[node] = doFind(s[node]);
        }
    }



    // check that each node is the root
    public void union(int node1, int node2) {
        node1 = find(node1);
        node2 = find(node2);
        if (node1 != node2) { // if the two nodes are in the same tree do nothing
            doUnion(node1, node2);
        }
    }

    private void doUnion(int node1, int node2){
        if (s[node2] < s[node1]) {
            // root2 is larger, because it is more negative
            s[node2] += s[node1];   // add the size from root1 to root2
            s[node1] = node2;       // Make root2 new root
        }
        else {
            // root1 is equal or larger
            s[node1] += s[node2];   // add the size from root2 to root1
            s[node2] = node1;       // Make root1 new root
        }
    }

    public String toString() {
        String str = "";
        for (int i = 0; i < s.length; i++) {
            str += i + " : " + s[i] + "\n";
        }
        return str;
    }
}
public class DisjointSetTest {
    public static void main(String[] args){
        DisjointSet djs = new DisjointSet(8);
        System.out.println("find 6:" + djs.find(6));
        djs.union(2, 5);
        djs.union(5, 6);
        djs.union(0, 7);
        System.out.println(djs);
        djs.union(0, 2);
        System.out.println(djs);

        System.out.println("find 7 : " + djs.find(7));
        System.out.println(djs);

    }
}

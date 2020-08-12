public class UnionFind {

    // TODO - Add instance variables?
    // store the parent of vertices
    private int[] parent;
    // store the size of the sets each vertices belong to
    private int[] sizeOfparent;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        // TODO
        parent = new int[n];
        sizeOfparent = new int[n];
        //all vertices are in disjoint sets
        // all vertices have size of 1
        for (int k=0 ; k<n ; k++){
            parent[k] = -1;
            sizeOfparent[k] = 1;
        }

    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        // TODO
        if (vertex<0 || vertex>=parent.length){
            throw new IllegalArgumentException("vertex out of bound");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        // TODO
        validate(v1);
        return sizeOfparent[find(v1)];
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        // TODO
        validate(v1);
        return parent[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO
        validate(v1);
        validate(v2);
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        // TODO
        validate(v1);
        validate(v2);
        if (connected(v1 , v2)){
            return;
        }
        //If the sizes of the sets are equal, tie break by connecting v1's root to v2's root
        if (sizeOf(v1) > sizeOf(v2)){
            parent[find(v2)] = find(v1);
            //The size of new disjoint set equals to the addition of two separated set
            sizeOfparent[find(v1)] = sizeOf(v1) + sizeOf(v2);
        }else {
            parent[find(v1)] = find(v2);
            sizeOfparent[find(v2)] = sizeOf(v1) + sizeOf(v2);
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        // TODO
        int temp = vertex;
        while (parent(temp) >= 0){
            temp = parent(temp);
        }
        //path-compression
        if (temp == vertex){
            //do nothing
        }else {
            parent[vertex] = temp;
        }
        return temp;
    }

}

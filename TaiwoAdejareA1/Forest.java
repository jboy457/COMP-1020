public class Forest {
    private final int TREE_MAX = 100;

    // Forest Attributes.
    private String name;
    private String[] trees;
    private int treeCount;

    /**
     * Forest Constructor
     * @paramters {name}
     * @return Forest Object
     */
    public Forest(String name) {
        this.name = name;
        this.trees = new String[TREE_MAX];
    }

    public String getName() {
        return this.name;
    }

    public String[] getTrees() {
        return this.trees;
    }

    public int getTreeCount() {
        return this.treeCount;
    }

    public int getTreeMax() {
        return this.TREE_MAX;
    }

    public String toString() {
        return "Forest{name='" + this.name +"', numTrees=" + this.treeCount + "}";
    }

    /**
     * addTree - Add tree to forest object.
     * @paramters {String treeName}
     * @return response.
     */
    public String addTree(String treeName) {
        String response;
        if(this.treeCount < TREE_MAX) {
            this.trees[this.treeCount] = treeName;
            this.treeCount++;
            response = treeName + " added";
        } else {
            response = this.name + " has reached it maximum number of " + TREE_MAX + " trees.";
        }

        return response;
    }
}

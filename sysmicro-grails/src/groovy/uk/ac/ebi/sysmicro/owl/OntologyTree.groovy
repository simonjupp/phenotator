package uk.ac.ebi.sysmicro.owl

/**
 *
 * @author Simon Jupp
 * @date 14/05/2013
 * Functional Genomics Group EMBL-EBI
 *
 */
class OntologyTree {

    private TreeNode root;
    private HashMap<String, TreeNode> allNodes;

    public OntologyTree () {
        allNodes = new HashMap<String, TreeNode>();
    }

    public Set<TreeNode> getAllNodes( ) {
        return allNodes.values();
    }


    public TreeNode getRoot () {
        return root;
    }

    public TreeNode setRoot (TreeNode node) {
        this.root = node;
    }


    public TreeNode getNode (String id, String uri, String label) {
        if (allNodes.containsKey(id)) {
            return allNodes.get(id);
        }
        else {
            TreeNode node = new TreeNode(id, uri, label);
            allNodes.put(id, node);
            return node;
        }
    }

}

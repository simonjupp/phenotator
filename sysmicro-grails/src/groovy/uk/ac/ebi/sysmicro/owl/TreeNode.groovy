package uk.ac.ebi.sysmicro.owl

/**
 * 
 * @author Simon Jupp
 * @date 14/05/2013
 * Functional Genomics Group EMBL-EBI
 *
 */
class TreeNode {

    def String id;
    def String uri;
    def String label;

    def Set<TreeNode> children = new HashSet<TreeNode>();
    def Set<TreeNode> siblings = new HashSet<TreeNode>();

    TreeNode(String id, String uri, String label) {
        this.id = id
        this.uri = uri
        this.label = label
    }

    public Set<TreeNode> getChildren() {
        return children;
    }

    public Set<TreeNode> getSiblings() {
        return siblings;
    }

    def getEquivLabel () {
        String label = label;
        for (TreeNode sib : siblings) {
            label += " = " + sib.getLabel();
        }
        return label;
    }

}

import java.util.ArrayList;
import java.util.List;

class NodeSpace {
    private List<Node> nextNodes = new ArrayList<>();

    public NodeSpace(Node initialNode) {
        findSpace(initialNode);
    }

    private void findSpace(Node node) {
        actionOnJug(
                Node.emptyA(node),
                Node.emptyB(node),
                Node.fillA(node),
                Node.fillB(node),
                Node.fillAToB(node),
                Node.fillBToA(node));
    }

    public void actionOnJug(Node... nodes) {
        for (Node node : nodes) {
            if (node.isValid(node)) {
                nextNodes.add(node);
            }
        }

    }

    public List<Node> getNextNodes() {
        return nextNodes;
    }
}

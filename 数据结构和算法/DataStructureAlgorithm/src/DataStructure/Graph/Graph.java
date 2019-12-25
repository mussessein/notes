package DataStructure.Graph;

/**
 * 无向图
 */
public class Graph {

    private int V;  // 顶点数目
    private int E;  // 边的数目
    // 邻接表，保存权值信息
    private int[] adj;

    public Graph(int V) {
        this.V = V;
        this.E = 0;
        for (int i = 0; i < V; i++) {

        }
    }

}

class Edge {
    int pos;
    int height;
    boolean isStart;
    Edge(int pos, int height, boolean isStart) {
        this.pos = pos;
        this.height = height;
        this.isStart = isStart;
    }
}
class ComparatorEdge implements Comparator<Edge> {
    public int compare(Edge x, Edge y) {
        if(x.pos != y.pos) {
            return x.pos - y.pos;
        }
        else if(x.isStart && y.isStart) {
            return y.height - x.height;
        }
        else if(!x.isStart && !y.isStart) {
            return x.height - y.height;
        }
        return x.isStart ? -1 : 1;
    }
}
class ComparatorMax implements Comparator<Integer> {
    public int compare(Integer x, Integer y) {
        return y - x;
    }
}
public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    public ArrayList<ArrayList<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(buildings == null || buildings.length == 0) return res;
        
        ArrayList<Edge> edges = new ArrayList<Edge>();
        
        for(int[] building : buildings) {
            edges.add(new Edge(building[0], building[2], true));
            edges.add(new Edge(building[1], building[2], false));
        }
        Collections.sort(edges, new ComparatorEdge());
        
        Queue<Integer> heap = new PriorityQueue<Integer>(buildings.length, 
                                                        new ComparatorMax());
        for(Edge edge : edges) {
            if(edge.isStart) {
                if(heap.isEmpty() || edge.height > heap.peek()) {
                    ArrayList<Integer> now = new ArrayList<Integer>();
                    now.add(edge.pos);
                    now.add(edge.height);
                    res.add(now);
                }
                heap.add(edge.height);
            }
            else {
                heap.remove(edge.height);
                if(heap.isEmpty()) {
                    ArrayList<Integer> now = new ArrayList<Integer>();
                    now.add(edge.pos);
                    now.add(0);
                    res.add(now);
                }
                else if(edge.height > heap.peek()) {
                    ArrayList<Integer> now = new ArrayList<Integer>();
                    now.add(edge.pos);
                    now.add(heap.peek());
                    res.add(now);
                }
            }
        }
        return output(res);
    }
    
    public ArrayList<ArrayList<Integer>> output(ArrayList<ArrayList<Integer>> res) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<ArrayList<Integer>>();
        if (res.size() > 0) {
            int pre = res.get(0).get(0);
            int height = res.get(0).get(1);
            for (int i = 1; i < res.size(); i++) {
                ArrayList<Integer> now = new ArrayList<Integer>();
                int id = res.get(i).get(0);
                if (height > 0) {
                    now.add(pre);
                    now.add(id);
                    now.add(height);
                    ans.add(now);
                }
                pre = id;
                height = res.get(i).get(1);
            }
        }
        return ans;
    }
}


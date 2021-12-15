
public  class TreeNode <T> {
 
	public Location loc;
	public List<T> data;
	public TreeNode <T> child1, child2, child3, child4;
	
	public TreeNode(Location loc, T data) {
		this.loc = loc;
		this.data = new LinkedList<T>();
		this.data.insert(data);
		child1 = child2 = child3 = child4 = null;
	}
	
	
}

public class TreeLocator<T> implements Locator<T> {
	private TreeNode <T> root;
	private TreeNode <T> current;

	
	public TreeLocator() {
		root = null ;
		current= null;
	}
	@Override
	public int add(T e, Location loc) {
		TreeNode<T> p = root;
		TreeNode<T> q = root;
	    int n=0;

		
		TreeNode<T> s = new TreeNode<T>(loc,e);
		if (root == null) {
			root = current = s;
			return n;
		} 
		
			while(p != null) {
				n++;
				q = p;
				if(loc.x == p.loc.x && loc.y == p.loc.y) {
					p.data.insert(e);
					return n;
				}
				else if(loc.x < p.loc.x && loc.y <= p.loc.y)
					p = p.child1; 
				else if(loc.x <= p.loc.x && loc.y > p.loc.y)
					p=p.child2;
				else if(loc.x > p.loc.x && loc.y >= p.loc.y)
					p=p.child3;
				else 
					p=p.child4;	
			}
			
			if(loc.x < q.loc.x && loc.y <= q.loc.y)
				q.child1= s; 
			else if(loc.x <= q.loc.x && loc.y > q.loc.y)
				q.child2= s; 
			else if(loc.x > q.loc.x && loc.y >= q.loc.y)
				q.child3= s; 
			else if(loc.x >= q.loc.x && loc.y < q.loc.y)
				q.child4= s; 
			
		return n;
		
		}
	

	@Override
	public Pair<List<T>, Integer> get(Location loc){
		TreeNode<T> p = root;
		int n=0;
		if(root == null)
			return new Pair<List<T>,Integer>(new LinkedList<T>(),n);
		
		while(p != null) {
			n++;
			if(p.loc.x == loc.x && p.loc.y == loc.y) {
				return new Pair<List<T>,Integer>(p.data,n);
			}
			else if(loc.x < p.loc.x && loc.y <= p.loc.y)
				p = p.child1; 
			else if(loc.x <= p.loc.x && loc.y > p.loc.y)
				p=p.child2;
			else if(loc.x > p.loc.x && loc.y >= p.loc.y)
				p=p.child3;
			else if(loc.x >= p.loc.x && loc.y < p.loc.y)
				p=p.child4;
		}
		
		return new Pair<List<T>,Integer>(new LinkedList<T>(),n);
	}

	@Override
	public Pair<Boolean, Integer> remove(T e, Location loc) {
		TreeNode<T> p = root;
		int n=0;
		if(root == null)
			return new Pair<Boolean,Integer>(false,n);
		
		while(p != null) {
			n++;
			if(p.loc.x == loc.x && p.loc.y == loc.y) {
				if(p.data.empty()) {
					return new Pair<Boolean,Integer>(false,n);
				}
				p.data.findFirst();
				while(!p.data.last()) {
					if(p.data.retrieve().equals(e)) {
						p.data.remove();
						return new Pair<Boolean,Integer>(true,n);
					}
					else 
						p.data.findNext();
				}
					if(p.data.retrieve().equals(e)) {
						p.data.remove();
						return new Pair<Boolean,Integer>(true,n);
					}
				
				return new Pair<Boolean,Integer>(false,n);
			}
			else if(loc.x < p.loc.x && loc.y <= p.loc.y)
				p = p.child1; 
			else if(loc.x <= p.loc.x && loc.y > p.loc.y)
				p=p.child2;
			else if(loc.x > p.loc.x && loc.y >= p.loc.y)
				p=p.child3;
			else if(loc.x >= p.loc.x && loc.y < p.loc.y)
				p=p.child4;
		}
		
		return new Pair<Boolean,Integer>(false,n);
	}
	

	@Override
	public List<Pair<Location, List<T>>> getAll() {
		List<Pair<Location, List<T>>> l = new LinkedList<Pair<Location, List<T>>>();
		if(root == null)
			return l;
		Collect(root, l);
		return l;
		
	}
	
	private void Collect(TreeNode<T> p,List<Pair<Location, List<T>>> l1) {
		if(p == null)
			return;
		else {
			l1.insert(new Pair<Location, List<T>>(p.loc, p.data));
			Collect(p.child1, l1);
			Collect(p.child2, l1);
			Collect(p.child3, l1);
			Collect(p.child4, l1);	

		}
	}

	@Override
	public Pair<List<Pair<Location, List<T>>>, Integer> inRange(Location lowerLeft, Location upperRight) {
		Pair<List<Pair<Location, List<T>>>, Integer> s = new Pair<List<Pair<Location, List<T>>>, Integer>(new LinkedList<Pair<Location,List<T>>>(), 0);
		if(root == null)
			return s;
		inRange_helper(root,s,lowerLeft.x,lowerLeft.y,upperRight.x,upperRight.y);
		return s;
		
		
	}
	
	private void inRange_helper(TreeNode<T> p, Pair<List<Pair<Location, List<T>>>, Integer> s,int x1, int y1, int x2, int y2) {
		if(p == null)
			return;
		else{
			s.second++;
			if(p.loc.x >= x1 && p.loc.x <= x2 && p.loc.y >= y1 && p.loc.y <= y2) {
				s.first.insert(new Pair<Location, List<T>>(p.loc,p.data));
				inRange_helper(p.child1, s, x1,y1,x2,y2);
				inRange_helper(p.child2, s, x1,y1,x2,y2);
				inRange_helper(p.child3, s, x1,y1,x2,y2);
				inRange_helper(p.child4, s, x1,y1,x2,y2);

			}
			else if(x2<p.loc.x && y2<=p.loc.y) {
				inRange_helper(p.child1, s, x1,y1,x2,y2);

			}
			else if(x1>p.loc.x && y1>=p.loc.y) {
				inRange_helper(p.child3, s, x1,y1,x2,y2);

			}
			else if(x1>=p.loc.x && y1<p.loc.y) {
				inRange_helper(p.child4, s, x1,y1,x2,y2);

			}
			
			else if(x2<=p.loc.x && y1>p.loc.y) {
				inRange_helper(p.child2, s, x1,y1,x2,y2);

			}

			
			else if(y2 < p.loc.y) {
				inRange_helper(p.child1, s, x1,y1,x2,y2);
				inRange_helper(p.child4, s, x1,y1,x2,y2);

			}
			else if(x2 < p.loc.x) {
				inRange_helper(p.child1, s, x1,y1,x2,y2);
				inRange_helper(p.child2, s, x1,y1,x2,y2);

			}
			else if(x1 > p.loc.x) {
				inRange_helper(p.child2, s, x1,y1,x2,y2);
				inRange_helper(p.child3, s, x1,y1,x2,y2);

			}
			else if(y1 > p.loc.y) {
				inRange_helper(p.child2, s, x1,y1,x2,y2);
				inRange_helper(p.child3, s, x1,y1,x2,y2);

			}
	
			
		}
					
	}
	
}

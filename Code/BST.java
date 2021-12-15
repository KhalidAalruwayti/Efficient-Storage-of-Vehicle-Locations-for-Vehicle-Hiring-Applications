public class BST<K extends Comparable<K>, T> implements Map<K, T> {
	
	private BSTNode <K,T> root, current;
	
	public BST() {
		root = current = null;
	}
	@Override
	public boolean empty() {
		return root == null;

	}

	@Override
	public boolean full() {
		return false;

	}

	@Override
	public T retrieve() {
		return current.data;

	}

	@Override
	public void update(T e) {
		current.data = e;

	}

	@Override
	public Pair<Boolean, Integer> find(K key) {
		BSTNode<K,T> p = root;
		int n=0;
		if(empty())
			return new Pair<Boolean, Integer>(false,n);
		
		while(p != null) {
			n++;

			if(key.compareTo(p.key) == 0) {
				current = p;
				return new Pair<Boolean, Integer>(true,n);
			}
			else if(key.compareTo(p.key) < 0)
				p = p.left;
			else
				p = p.right;
		}
		
		return new Pair<Boolean, Integer>(false,n);
	
	}

	@Override
	public Pair<Boolean, Integer> insert(K key, T data) {
		BSTNode<K,T> p = root;
		BSTNode<K,T> q = root;

	    int n=0;

		if(find(key).first) {
			return new Pair<Boolean, Integer>(false,find(key).second);
		}
		
		BSTNode<K,T> s = new BSTNode<K,T>(key, data);
		if (empty()) {
			root = current = s;
			return new Pair<Boolean, Integer>(true,n);
		}
		else {
			n++;
			if (key.compareTo(p.key) < 0)
				p = p.left;
			else
				p = p.right;

			while(p != null) {
				n++;
				if (key.compareTo(p.key) < 0) 
				p = p.left;
				else 
				p = p.right;
				if (key.compareTo(q.key) < 0) 
					q = q.left;
					else 
					q = q.right;
				
			}
			if(key.compareTo(q.key) < 0) 
				q.left = s;
			else
				q.right = s;
			
			return new Pair<Boolean, Integer>(true,n);
		
			}
	}

	@Override
	public Pair<Boolean, Integer> remove(K key) {
		K k1 =key;
		BSTNode<K,T> p = root;
		BSTNode<K,T> q = null;
		int n=0;
		while(p != null) {
			n++;
			if(k1.compareTo(p.key) < 0) {
				q = p;
				p = p.left;
			}
			else if(k1.compareTo(p.key) > 0) {
				q = p;
				p = p.right;
			}
			else {
				if((p.left != null) && (p.right !=null)) {
					BSTNode<K,T> min =p.right;
					q = p;
					while(min.left != null) {
						q = min;
						min = min.left;
					}
					p.key = min.key;
					p.data = min.data;
					k1 =min.key;
					p = min;
				}
				if(p.left != null) {
					p=p.left;
				}
				else {
					p = p.right;
				}
				if( q == null) {
					root = p;
				} else {
					if(k1.compareTo(q.key) < 0) {
						q.left = p;
					} else {
						q.right = p;
					}
				}
				current = root;
				return new Pair<Boolean, Integer>(true,n);
			}
		}
		return new Pair<Boolean, Integer>(false,n);
	}

	@Override
	public List<K> getAll() {
		List<K> l = new LinkedList<K>();
		getAll_helper(root,l);
		return l;
		
	}
	private void getAll_helper(BSTNode<K,T> p,List<K>l) {
		if(p == null)
			return;
		else {
			getAll_helper(p.left,l);
			l.insert(p.key);
			getAll_helper(p.right,l);

		}
	}	
}

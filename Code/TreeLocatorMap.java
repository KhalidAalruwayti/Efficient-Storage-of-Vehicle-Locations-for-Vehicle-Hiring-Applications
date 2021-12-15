
public class TreeLocatorMap<K extends Comparable<K>> implements LocatorMap<K> {
	
	private Map <K, Location> M;
	private Locator<K> L;
	
	public TreeLocatorMap() {
		M= new BST <K, Location>();
		L=new TreeLocator<K>();
	}
	@Override
	public Map<K, Location> getMap() {
		return M;

	}

	@Override
	public Locator<K> getLocator() {
		return L;

	}

	@Override
	public Pair<Boolean, Integer> add(K k, Location loc) {
		Pair<Boolean, Integer> s =M.insert(k, loc);
		if(s.first==true) {
			L.add(k, loc);
		}
			
		return s;
	}

	@Override
	public Pair<Boolean, Integer> move(K k, Location loc) {
		Pair <Boolean, Integer> s =M.find(k);
		if(s.first==true) {
			L.remove(k, M.retrieve());
			L.add(k, loc);
			M.update(loc);
		}
		return s;
	}

	@Override
	public Pair<Location, Integer> getLoc(K k) {
		Pair<Boolean, Integer> s=M.find(k);
		if(s.first == true) {
			return new Pair <Location, Integer>(M.retrieve(),s.second);
		}
		else
			return new Pair <Location, Integer>(null,s.second);
	}

	@Override
	public Pair<Boolean, Integer> remove(K k) {
		Pair<Boolean, Integer> s=M.find(k);
		if(s.first==true) {
			L.remove(k, M.retrieve());
			M.remove(k);
		}
		return s;
	}

	@Override
	public List<K> getAll() {
		return M.getAll();

	}

	@Override
	public Pair<List<K>, Integer> getInRange(Location lowerLeft, Location upperRight) {
		
		Pair<List<Pair<Location, List<K>>>, Integer> get=L.inRange(lowerLeft, upperRight);
		List<Pair<Location, List<K>>> compound= get.first;
		List<K> compoundKeys=new LinkedList<K>();
		if(!compound.empty()) {
			compound.findFirst();
			while(!compound.last()) {
				doADD(compoundKeys, compound.retrieve().second);
				compound.findNext();
			}
			doADD(compoundKeys,compound.retrieve().second);
			}
		Pair<List<K>, Integer> get1=new Pair<List<K>, Integer>(compoundKeys,get.second);
				return get1;
	}
	
	private boolean found(List<K> list, K e) {
		if(list.empty())
			return false;
		while(!list.last()) {
			if(list.retrieve().compareTo(e)==0)
				return true;
			list.findNext();
		}
		if(list.retrieve().compareTo(e)==0)
			return true;
		return false;
	}
	
	private void doADD(List<K>f, List<K>s) {
		if(s.empty())
			return;
		s.findFirst();
		while(!s.last()) {
			if(!found(f,s.retrieve())) 
				f.insert(s.retrieve());
			s.findNext();
		}
		if(!found(f,s.retrieve())) 
			f.insert(s.retrieve());
		s.findNext();	
			
	}

}

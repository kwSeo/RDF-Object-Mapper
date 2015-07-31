package team.afgm.rdfom.context;

import java.util.ArrayList;
import java.util.List;

import com.hp.hpl.jena.sparql.function.library.namespace;

public class Namespaces{
	
	private List<Namespace> namespaces;

	public Namespaces() {
		this.namespaces = new ArrayList<Namespace>();
	}

	public Namespaces(List<Namespace> namespaces){
		this.namespaces = namespaces;
	}
	
	public List<Namespace> getNamespaces() {
		return namespaces;
	}

	public void setNamespaces(List<Namespace> namespaces) {
		this.namespaces = namespaces;
	}
	
	public Namespace getNamespace(int index){
		return namespaces.get(index);
	}
}

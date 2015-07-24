package team.afgm.rdfom.context;

import java.util.ArrayList;
import java.util.List;

import com.hp.hpl.jena.sparql.function.library.namespace;

public class NamespacesImpl implements Namespaces {
	
	private List<Namespace> namespaces;

	public NamespacesImpl() {
		this.namespaces = new ArrayList<Namespace>();
	}

	@Override
	public List<Namespace> getNamespace() {
		return namespaces;
	}

	@Override
	public void setNamespace(List<Namespace> namespaces) {
		this.namespaces = namespaces;
	}

}

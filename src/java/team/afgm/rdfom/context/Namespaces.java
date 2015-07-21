package team.afgm.rdfom.context;

import java.util.List;

import com.hp.hpl.jena.sparql.function.library.namespace;

public interface Namespaces {
	public List<Namespace> getNamespace();
	public void setNamespace(List<Namespace> namespaces);
}

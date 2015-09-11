package team.afgm.rdfom.session;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class StreamMaker {
	private ClassLoader classLoaders[] = {
			ClassLoader.getSystemClassLoader(),
			new DirClassLoader()
	};
	
	public InputStream getResourceAsStream(String path){
		for(ClassLoader classLoader : classLoaders){
			InputStream stream = classLoader.getResourceAsStream(path);
			if(stream != null)
				return stream;
		}
		
		throw new RuntimeException("Error getting Resource as stream. " + path);
	}
	
	public Reader getResourceAsReader(String path){
		InputStream stream = getResourceAsStream(path);
		InputStreamReader reader = new InputStreamReader(stream);
		
		return reader;
	}
	
	public BufferedReader getResourceAsBufferedReader(String path){
		Reader reader = getResourceAsReader(path);
		BufferedReader bufferedReader = new BufferedReader(reader);
		
		return bufferedReader;
	}
}

class DirClassLoader extends ClassLoader{
	@Override
	public InputStream getResourceAsStream(String path){
		try{
			File file = new File(path);
			FileInputStream stream = new FileInputStream(file);
			
			return stream;
			
		}catch(Exception e){
			e.printStackTrace(System.err);
			throw new RuntimeException("Error, not found path. " + e.getMessage());
		}
	}
}

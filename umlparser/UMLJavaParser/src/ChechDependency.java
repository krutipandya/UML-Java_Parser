/**
 * 
 */


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.type.ReferenceType;
import com.github.javaparser.ast.type.Type;

/**
 * @author Kruti
 *
 */
public class ChechDependency {
	
		
		private String interfaceName="";
		private String className="";
		private MethodDeclaration md;
		private ConstructorDeclaration cd;
		private static ConcurrentHashMap<String,String> usesMap = new ConcurrentHashMap<String,String>();
		private static ConcurrentHashMap<String,String> usessMap = new ConcurrentHashMap<String,String>();
		
		public ChechDependency(String className,MethodDeclaration md)
		{
			this.className=className;
			this.md=md;
		
		}
		public ChechDependency(String className,ConstructorDeclaration cd)
		{
			this.className=className;
			this.cd=cd;
		
		}
		public String getInterfaceName() {
			return interfaceName;
		}
		
		public void setInterfaceName(String interfaceName) {
			this.interfaceName = interfaceName;
		}
		
		public String getClassName() {
			return className;
		}
		
		public void setClassName(String className) {
			this.className = className;
		}
		
		public ConcurrentHashMap<String, String> checkDependency(ArrayList<String> interfaceList)
		{
			int count=1;
			List<Parameter> list = md.getParameters();
			if(!list.isEmpty())
			{
				for(int i=0;i<list.size();i++)
				{
					Type temp = list.get(i).getType();
					if(temp instanceof ReferenceType && interfaceList.contains(temp.toString()))
					{
						if(!usesMap.containsKey(temp))
						{
							usesMap.put(temp.toString(),className);
						}
					}
				}
			}
			System.out.println(usesMap);
			return usesMap;
		}
		public ConcurrentHashMap<String, String> checkCDependency(ArrayList<String> interfaceList)
		{
			int count=1;
			List<Parameter> list = cd.getParameters();
			if(!list.isEmpty())
			{
				for(int i=0;i<list.size();i++)
				{
					Type temp = list.get(i).getType();
					if(temp instanceof ReferenceType && interfaceList.contains(temp.toString()))
					{
					
						System.out.println("temp value"+temp);
						System.out.println("before"+usessMap);
						usessMap.put(className,temp.toString());
						System.out.println("after "+usessMap);
						
					}
				}
			}
			System.out.println(usessMap);
			return usessMap;
		}

}

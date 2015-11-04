/**
 * 
 */


import java.io.File;

/**
 * @author Kruti
 *
 */

import java.io.FileInputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.ModifierSet;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.PrimitiveType;
import com.github.javaparser.ast.type.PrimitiveType.Primitive;
import com.github.javaparser.ast.type.ReferenceType;
import com.github.javaparser.ast.type.Type;
/*
 *  [A|-x:int;-y:int(*)]1-0..*[B],
	[A]-1[C],
	[A]-*[D]
 */
public class Application {
	
	/*private static String filePath = "D:/projects/JavaParser/src/main/java/javaparser/";
	private String baseURL = "http://yuml.me/diagram/scruffy/class/";
	private String submitURL = baseURL;
	private String myURL = submitURL;
	private String[] primitives = {"byte","short","int","long","float","double","boolean","char",
			"Byte","Short","Int","Long","Float","Double","Boolean","Char"};
	private String[] referenceTypes = {"Collection"};
	
	
	public static void main(String[] args)
	{
		Application app = new Application();
		app.umlParser();
	}
	public void umlParser()
	{
		Class c;
		try {
			c = Class.forName("javaparser.A");
			String classname=(String)c.getName();
			String temp = classname.split("\\.")[1];
			File folder = new File(filePath);
			File[] listOfFiles = folder.listFiles();

			for (File file : listOfFiles) {
			    if (file.isFile()) {
			        System.out.println(file.getName().split("\\.")[0]);
			    }
			}
			
		} catch (ClassNotFoundException e1) {
			
			e1.printStackTrace();
		}
		
		try
		{
			FileInputStream inputStream = new FileInputStream(filePath);
			
			CompilationUnit cu = JavaParser.parse(inputStream);
			List<Node> cuChildNodes = cu.getChildrenNodes();
			String className="";
			for(Node cuChildNode : cuChildNodes)
			{
				if(cuChildNode instanceof ClassOrInterfaceDeclaration)
				{
					ClassOrInterfaceDeclaration cid = (ClassOrInterfaceDeclaration)cuChildNode;
					if(cid.isInterface())
					{
					List<ClassOrInterfaceType> impl = cid.getImplements();
					if(impl.isEmpty())
					{
						return;
					}
					else
					{
						Iterator it = impl.iterator();
						while(it.hasNext()&& impl.size()>=1)
						{
							submitURL +="<<"+it.next().toString()+">>;";
						}
					}
					
					}
					else
					{
						
						System.out.println(cid.getName()+" class name");
						submitURL += "[" + cid.getName()+"|";
						className = cid.getName();
					}
				}
				else if(cuChildNode instanceof PackageDeclaration)
				{
					PackageDeclaration pd = (PackageDeclaration)cuChildNode;
				}
				else if(cuChildNode instanceof ImportDeclaration)
				{
					ImportDeclaration ide = (ImportDeclaration)cuChildNode;
				}
			}
			List<TypeDeclaration> listOfTypeDeclarations = cu.getTypes();
			myURL = baseURL;
			for(TypeDeclaration id : listOfTypeDeclarations)
			{
				List<BodyDeclaration> listOfBodyDeclarations = id.getMembers();

				for(int j=0 ; j<listOfBodyDeclarations.size() ; j++)
				{
					
						myURL += "["+className;
					
					BodyDeclaration bd = listOfBodyDeclarations.get(j);
					
					if(bd instanceof FieldDeclaration)
					{
						myURL = myURL + "|";
						FieldDeclaration fd = (FieldDeclaration)bd;

						int modifiers = fd.getModifiers();
						String submitURLVariable = "",submitURLModifier="";
						boolean primitiveMatch = false;

						switch(modifiers)
						{
						case ModifierSet.PRIVATE:
							System.out.println("Variable is declared private");
							submitURLModifier = "-";
							break;
						case ModifierSet.PUBLIC:
							System.out.println("Variable is declared public");
							submitURLModifier = "+";
							break;
						case ModifierSet.PROTECTED:
							System.out.println("Variable is declared protected");
							submitURLModifier = "~";
							break;
						}
						List<Node> listNodes =  fd.getChildrenNodes();
						
						for(Node n : listNodes)
						{
							if(n instanceof ReferenceType)
							{
								ReferenceType rt = (ReferenceType)n;
								System.out.println(rt.getType().toString()+" reference type");
								Type rType = rt.getType();
								String strClass = rt.toString();
								if(strClass.contains("Collection"))
								{
								strClass = rt.toString().replaceFirst("Collection<","");
								strClass = strClass.replace(">", "");
								}
								
								System.out.println();
								for(String str : primitives)
								{
									if(rType.toString().contains(str))
									{ 
										submitURLVariable += rType.toString()+"(*)";
										System.out.println(submitURLVariable+" submitURLVariable");
										primitiveMatch = true;
										break;
									}
									
								}
								
								if(!primitiveMatch)
								{
									myURL = myURL + "1-*" + strClass;
									myURL = myURL + ",";
								}
								
								
								
								
							}
							else if(n instanceof PrimitiveType)
							{
								PrimitiveType pt = (PrimitiveType)n;
								System.out.println(pt.toString()+" primitive type");
								primitiveMatch = true;
								submitURLVariable += pt.toString();
							}
							else if(n instanceof VariableDeclarator)
							{
								VariableDeclarator vd = (VariableDeclarator)n;
								System.out.println(vd.toString()+" variable declartor");
								if(primitiveMatch)
								{
									submitURL += submitURLModifier+vd.toString()+":"+submitURLVariable+";";
									myURL += submitURLModifier+vd.toString()+":"+submitURLVariable+";";
								}
								else
								{
									
								}
								
							}
							//myURL +="],";
						}
					}
					else if(bd instanceof MethodDeclaration)
					{
						myURL = myURL + "|";
						MethodDeclaration md = (MethodDeclaration)bd;
						System.out.println(md.getName()+ ""+ md.getParameters() +"is the method name");


					}
				}
				
			}
		}
		catch(Exception e)
		{

		}
		
		
		System.out.println(submitURL);
		System.out.println(myURL);
	}*/
}

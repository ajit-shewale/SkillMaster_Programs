package fileSplitter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileSplitter
{

	public static void main(String[] args){
        
		File file=new File("C:\\Users\\ajits\\OneDrive\\Desktop\\work\\cert_chain.pem");
		splitFile(file);
	}

	private static void splitFile(File file) {
		try {  
			FileReader fr=new FileReader(file);     
			BufferedReader br=new BufferedReader(fr);  
			String line;
			Boolean flag = false;
			StringBuffer sb=new StringBuffer(); 
			while((line=br.readLine())!=null) {
					if(line.equalsIgnoreCase("-----END CERTIFICATE-----") && flag.equals(false)) {
						sb.append(line); 
						//file create method.
						generateFile(sb.toString(),"cert");
						sb.setLength(0);
						flag=true;
					}else {
						sb.append(line);      
						sb.append("\n"); 
					}
				}
			if(flag = true) {
				generateFile(sb.toString(),"chain");
			}
			fr.close();     

		} catch(IOException e) {  
				e.printStackTrace();  
		} 
	}
	
	private static void generateFile(String sb , String fileName)
	{
		String path = "C:\\Users\\ajits\\OneDrive\\Desktop\\work\\certificates";
		try {
		      FileWriter myWriter = new FileWriter(path.concat("\\").concat(fileName).concat(".pem"));
		      myWriter.write(sb.toString());
		      myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
	}  
	
	


}

package fileSplitter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileSplitter
{

	public static void main(String[] args){

		System.out.println("Please Provide path of input pem file :");
		Scanner scanner = new Scanner(System.in);
		String inputFile = scanner.nextLine();  		//eg.path - C:\\Users\\ajits\\OneDrive\\Desktop\\work\\cert_chain.pem
		File file=new File(inputFile);
		if(file!=null && file.exists() == true ) {
			if(checkFileExtension(file.getName())) {
				splitFile(file);
			}else {
				System.out.println("Input file is not PEM file. Please provide '.pem' files only.");
			}
		}else {
			System.out.println("Given file does not Exists !!");
		}
		
	}

	private static void splitFile(File file) {
		try {  
			FileReader fr=new FileReader(file);     
			BufferedReader br=new BufferedReader(fr);  
			String line;
			Boolean flag = false;
			StringBuffer stringBuffer=new StringBuffer(); 
			while((line=br.readLine())!=null) {
					if(line.equalsIgnoreCase("-----END CERTIFICATE-----") && flag.equals(false)) {
						stringBuffer.append(line); 
						//file create method.
						generateFile(stringBuffer.toString(),"cert");
						stringBuffer.setLength(0);
						flag=true;
					}else {
						stringBuffer.append(line);      
						stringBuffer.append("\n"); 
					}
				}
			if(flag = true) {
				generateFile(stringBuffer.toString(),"chain");
			}
			fr.close();     

		} catch(IOException e) {  
				e.printStackTrace();  
		} 
	}
	
	private static void generateFile(String dataString , String fileName){
		String outputPath = "C:\\Users\\ajits\\OneDrive\\Desktop\\work\\certificates";
		File outputfile = new File( outputPath.concat("\\").concat(fileName).concat(".pem"));
		if(outputfile.exists() != true ) {
		try {
		      FileWriter myWriter = new FileWriter(outputfile);
		      myWriter.write(dataString);
		      myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		}else{
			System.out.println("Output file Already exists!!");
		}
	}  
	
	private static boolean checkFileExtension(String fileName){
		int Index = fileName.lastIndexOf('.');
		String extension = "";
		if (Index > 0) {
		    extension = fileName.substring(Index + 1);
		}
		
		return extension.equals("pem")? true: false;
	}  


}

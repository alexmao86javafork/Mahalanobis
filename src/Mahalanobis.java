import java.io.FileReader;
import java.util.List;

import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.correlation.Covariance;

import au.com.bytecode.opencsv.*;
public class Mahalanobis {
	
	public  double[][] readAllDataAtOnce(String file)
	{
		 try {
			 	
		        FileReader filereader = new FileReader(file);
		 		 CSVReader csvReader = new CSVReader(filereader);
		        List<String[]> allData = csvReader.readAll();
		        int row = allData.size();
		        int col = allData.get(0).length;
		        double data[][] = new double[row][col];
		        for(int i=0;i<row;i++)
		        {
		        	for(int j=0;j<col;j++)
		        	{
		        		data[i][j]=Double.parseDouble(allData.get(i)[j]);
		        	}
		        }
		        csvReader.close();
		        
				return data;
		    }
		    catch (Exception e) {
		        e.printStackTrace();
		    }
		 return null;
	}
	
	public double[][] getCovarianceMatrix(double m[][])
	{
		Covariance covariance= new Covariance(m);
		RealMatrix realMatrix =  covariance.getCovarianceMatrix();
		return realMatrix.getData();
	}

}





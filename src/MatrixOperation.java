import java.util.List;

public class MatrixOperation {
	
	public double[][] subtract(double m1[][],double m2[][])
	{
		if(m1.length!=m2.length||(m1[0].length!=m2[0].length))
		{
			System.out.println("Incompatible Matrix sizes");
		}
		double ans[][]= new double[m1.length][m1[0].length];
		
		for(int i=0;i<m1.length;i++)
		{
			for(int j=0;j<m1[0].length;j++)
			{
				ans[i][j]=m1[i][j]-m2[i][j];
			}
		}
		return ans;
	}
	
	public double[][] addition(double m1[][],double m2[][])
	{
		if(m1.length!=m2.length||(m1[0].length!=m2[0].length))
		{
			System.out.println("Incompatible Matrix sizes");
		}
		double ans[][]= new double[m1.length][m1[0].length];
		
		for(int i=0;i<m1.length;i++)
		{
			for(int j=0;j<m1[0].length;j++)
			{
				ans[i][j]=m1[i][j]+m2[i][j];
			}
		}
		return ans;
	}
	
	public double[][] sqrt(double m[][])
	{
		
		double ans[][]= new double[m.length][m[0].length];
		
		for(int i=0;i<m.length;i++)
		{
			for(int j=0;j<m[0].length;j++)
			{
				ans[i][j]=Math.sqrt(m[i][j]);
			}
		}
		return ans;
	}
	public double[][] getArrayFromList(List<List<Double >> list)
	{
		int r = list.size(),c=list.get(0).size();
		double ans[][] = new double[r][c];
		for(int i=0;i<r;i++)
		{
			for(int j=0;j<c;j++)
			{
				ans[i][j]=list.get(i).get(j);
			}
		}
		return ans;
	}
	
	public double[][] mean(double m[][],int axis)
	{
		double mean[][];
		if(axis ==0)
		{
			mean=new double[1][m[0].length];
			for(int i=0;i<m[0].length;i++)
			{
				double sum=0;
				for(int j=0;j<m.length;j++)
				{
					sum= sum+m[j][i];
				}
				mean[0][i]=sum/m.length;
			}
			
			
		}
		else
		{
			mean = new double[m.length][1];

			for(int i=0;i<m.length;i++)
			{
				double sum=0;
				for(int j=0;j<m[0].length;j++)
				{
					sum=sum+m[i][j];
				}
				mean[i][0] = sum/m[0].length;
			}
		}
		return mean;
	}
	
	public double[][] multiply(double m1[][],double m2[][])
	{
		int r1 =m1.length,c1=m1[0].length;
		int r2= m2.length,c2=m2[0].length;

		if(c1!=r2)
		{
			System.out.println("Incompatible Matrices for multiplication");
		}
		double ans[][]= new double[r1][c2];
		
		for(int i = 0; i < r1; i++) {
            for (int j = 0; j < c2; j++) {
                for (int k = 0; k < c1; k++) {
                    ans[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
		return ans;
	}
	public double[][] transpose(double m[][])
	{
		int r=m.length,c=m[0].length;
		
		double ans[][] =new double[c][r];
		for(int i=0;i<r;i++)
		{
			for(int j=0;j<c;j++)
			{
				ans[j][i] = m[i][j];
			}
		}
		return ans;
	}
	
	void print(double m[][])
	{
		
		int r= m.length,c=m[0].length;
		for(int i=0;i<r;i++)
		{
			for(int j=0;j<c;j++)
			{
				System.out.print(m[i][j]+" ");
			}
			System.out.println("");
		}
		
		System.out.println("");
		System.out.println("");

	}
	
	 public  double[][] invert(double a[][]){
        int n = a.length;
        double x[][] = new double[n][n];
        double b[][] = new double[n][n];
        int index[] = new int[n];
        for (int i=0; i<n; ++i) 
            b[i][i] = 1;
 
        gaussian(a, index);
 
        for (int i=0; i<n-1; ++i)
            for (int j=i+1; j<n; ++j)
                for (int k=0; k<n; ++k)
                    b[index[j]][k]
                    	    -= a[index[j]][i]*b[index[i]][k];
 
        for (int i=0; i<n; ++i) 
        {
            x[n-1][i] = b[index[n-1]][i]/a[index[n-1]][n-1];
            for (int j=n-2; j>=0; --j) 
            {
                x[j][i] = b[index[j]][i];
                for (int k=j+1; k<n; ++k) 
                {
                    x[j][i] -= a[index[j]][k]*x[k][i];
                }
                x[j][i] /= a[index[j]][j];
            }
        }
        return x;
	   }
	 
	 
	    public static void gaussian(double a[][], int index[]) 
	    {
	        int n = index.length;
	        double c[] = new double[n];
	 
	        for (int i=0; i<n; ++i) 
	            index[i] = i;
	 
	        for (int i=0; i<n; ++i) 
	        {
	            double c1 = 0;
	            for (int j=0; j<n; ++j) 
	            {
	                double c0 = Math.abs(a[i][j]);
	                if (c0 > c1) c1 = c0;
	            }
	            c[i] = c1;
	        }
	 
	        int k = 0;
	        for (int j=0; j<n-1; ++j) 
	        {
	            double pi1 = 0;
	            for (int i=j; i<n; ++i) 
	            {
	                double pi0 = Math.abs(a[index[i]][j]);
	                pi0 /= c[index[i]];
	                if (pi0 > pi1) 
	                {
	                    pi1 = pi0;
	                    k = i;
	                }
	            }
	 
	            int itmp = index[j];
	            index[j] = index[k];
	            index[k] = itmp;
	            for (int i=j+1; i<n; ++i) 	
	            {
	                double pj = a[index[i]][j]/a[index[j]][j];
	 
	                a[index[i]][j] = pj;
	 
	                for (int l=j+1; l<n; ++l)
	                    a[index[i]][l] -= pj*a[index[j]][l];
	            }
	        }
	    }
	

}

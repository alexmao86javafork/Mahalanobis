import java.util.Scanner;

public class Project {

	public static void main(String[] args) {
	
		int choice;
		MahalanobisDistance mahalanobisDistance = new MahalanobisDistance(args[0]);
		System.out.println("File = "+ args[0]);
		System.out.println("Enter 1 for Distance and 2 for DissimilarityMeasure");
		
		Scanner scan = new Scanner(System.in);
		choice =scan.nextInt();
		if(choice==1)
		{
			System.out.println("Enter point x");
			double x[]=  new double[3];
			for(int i=0;i<3;i++)
			{
				x[i]= scan.nextDouble();
			}
			System.out.println("Distance = "+mahalanobisDistance.getDistance(x));
		}
		else
		{
			double x[]=  new double[3];
			double y[]=  new double[3];
			System.out.println("Enter point x");
			for(int i=0;i<3;i++)
			{
				x[i]= scan.nextDouble();
			}
			System.out.println("Enter point y");

			for(int i=0;i<3;i++)
			{
				y[i]= scan.nextDouble();
			}
			System.out.println("DissimilarityMeasure = "+mahalanobisDistance.getDissimilarityMeasure(x, y));

			
		}
		scan.close();
		
	}

}

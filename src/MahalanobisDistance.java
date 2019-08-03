import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MahalanobisDistance {

	private List<List<Double>>covariance ;
	private List<List<Double>>mean ;

	public MahalanobisDistance()
	{
		
	}
	public MahalanobisDistance(String file)
	{
		MatrixOperation matrixOperation = new MatrixOperation();
		Mahalanobis mahalanobis =new Mahalanobis();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		URL location = classLoader.getResource(file);
		File filelocation = new File(location.getPath());
		//读取原始数据
		double data[][]= mahalanobis.readAllDataAtOnce(filelocation.toString());
		//计算协方差矩阵
		double covarianceMatrix[][] = mahalanobis.getCovarianceMatrix(data);
		//计算平均值矩阵
		double meanMatrix[][] = matrixOperation.mean(data,0);
		
		System.out.println("*****************************Printing Data*****************************");
		matrixOperation.print(data);
		System.out.println("*****************************Printing Mean*****************************");
		matrixOperation.print(meanMatrix);
		System.out.println("*****************************Printing Covariance*****************************");
		matrixOperation.print(covarianceMatrix);
		covariance = new ArrayList<>();
		for(int i=0;i<covarianceMatrix.length;i++)
		{
			List<Double>list= new ArrayList<>();
			for(int j=0;j<covarianceMatrix[0].length;j++)
			{
				list.add(covarianceMatrix[i][j]);
			}
			covariance.add(list);
		}
		
		mean = new ArrayList<>();
		for(int i=0;i<meanMatrix.length;i++)
		{
			List<Double>list= new ArrayList<>();
			for(int j=0;j<meanMatrix[0].length;j++)
			{
				list.add(meanMatrix[i][j]);
			}
			mean.add(list);
		}
		
	}
	/**
	 * 给定两个样板，基于当前样板的统计特征，计算两个样本的差异
	 * @param v1
	 * @param v2
	 * @return
	 */
	public double getDissimilarityMeasure(double v1[],double v2[])
	{
		double m1[][] = new double [1][v1.length];
		for(int i=0;i<v1.length;i++) {
			m1[0][i]=v1[i];
		}
		
		double m2[][] = new double [1][v2.length];
		for(int i=0;i<v2.length;i++) {
			m1[0][i]=v2[i];
		}
		
		MatrixOperation matrixOperation = new MatrixOperation();
		double squaredAnswer[][] = matrixOperation.multiply(
				matrixOperation.multiply(matrixOperation.subtract(m1, m2), matrixOperation.invert(matrixOperation.getArrayFromList(covariance))),
				
				matrixOperation.transpose(matrixOperation.subtract(m1, m2)));
		double ans = Math.sqrt(squaredAnswer[0][0]);
		return ans;
	}
	/**
	 * 给定一个多维数组的样本，计算它和本样板的距离
	 * @param v
	 * @return
	 */
	public double getDistance(double v[])
	{
		double m1[][] = new double [1][v.length];
		for(int i=0;i<v.length;i++) {
			m1[0][i]=v[i];
		}
		MatrixOperation matrixOperation = new MatrixOperation();
		matrixOperation.print(matrixOperation.getArrayFromList(mean));
		double squaredAnswer[][] = matrixOperation.multiply(matrixOperation.multiply(matrixOperation.subtract(m1, matrixOperation.getArrayFromList(mean)), matrixOperation.invert(matrixOperation.getArrayFromList(covariance))),matrixOperation.transpose(matrixOperation.subtract(m1, matrixOperation.getArrayFromList(mean))));
		double ans = Math.sqrt(squaredAnswer[0][0]);
		return ans;
	}
}

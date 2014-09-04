import java.util.Scanner;
import java.util.InputMismatchException;

class RowsAndColsMismatchException extends Exception{
	public String toString()
	{
		return "Rows and Cols do not match";
	}
}

class CheckRowsAndCols{
	static boolean equateRowsAndCols(int a[][], int b[][]) throws RowsAndColsMismatchException
	{
		if(a.length != b.length || a[0].length != b[0].length)							// exception checker for addition
		{
			throw new RowsAndColsMismatchException();
		}
		else
			return true;
	}

	static boolean equateRowsAndCols(int col, int row) throws RowsAndColsMismatchException
	{
		if(col != row)																// exception checker for multiplication and trace and norm
		{
			throw new RowsAndColsMismatchException();
		}
		else
			return true;
	}
}

class CheckInteger{
	static int chkInt()
	{	
		int value= 0;
		Scanner num = new Scanner(System.in);
		boolean flag = true;
		do
		{
			try
			{
				System.out.print("Value: ");			//exception checker for input integer mismatch
				value = num.nextInt();
				flag = true;
			}
			catch(InputMismatchException e)
			{
				System.out.println("Exception: "+e+"\nPlease enter integer values.");
				flag = false;
				num.next();
			}
		}while(!flag);

		return value;
	}
}


// ****exception ends******

// ***program starts******

class Matrix{
	int mat[][];
	int rows, cols;

	void inputMatrix()
	{
		Scanner num = new Scanner(System.in);
		System.out.print("Enter number of rows ");
		rows = CheckInteger.chkInt();
		System.out.print("Enter number of cols ");
		cols = CheckInteger.chkInt();

		mat = new int[rows][cols];
		for(int i=0;i<rows;i++)
			for(int j=0;j<cols;j++)
				mat[i][j] = CheckInteger.chkInt();
	}

	void add(int a[][], int b[][])
	{
		int i, j;
		mat = new int[a.length][a[0].length];

		rows = a.length;
		cols = a[0].length;
		for(i=0;i<rows;i++)
			for(j=0;j<cols;j++)
				mat[i][j] = a[i][j] + b[i][j];
	}

	void multiply(int a[][], int b[][])
	{
		int i, j, n, k;
		n = b.length;
		rows = a.length;
		cols = b[0].length; 
		mat = new int[rows][cols];
	
		for(i=0;i<rows;i++)
			for(j=0;j<cols;j++)
				for(k=0;k<n;k++)
					mat[i][j] = mat[i][j] + a[i][k] * b[k][j];
	}

	void trace(int a[][])
	{
		int t=0; 
		for(int i=0;i<a.length;i++)	
			t = t + a[i][i];

		System.out.println("Trace: " + t);			
	}

	void transpose(int a[][])
	{
		int i, j;
		rows = a.length;
		cols = a[0].length;
		mat = new int[rows][cols];

		for(i=0;i<rows;i++)
			for(j=0;j<cols;j++)
				mat[j][i] = a[i][j];
	}

	void display()
	{
		int i,j;
		System.out.println("Matrix: ");
		for(i=0;i<rows;i++)
		{
			for(j=0;j<cols;j++)
				System.out.print(mat[i][j]+" ");

			System.out.println("");
		}
	}
}



public class MatrixExceptionHandling{
	public static void main(String args[]){

		Scanner num = new Scanner(System.in);
		char choice = 'y';
		Matrix a = new Matrix();
		Matrix b = new Matrix();
		Matrix c = new Matrix();
		CheckRowsAndCols err = new CheckRowsAndCols();

		do
		{
			System.out.println("***Choose an operation: ***");
			System.out.println("1. Add two matrices\n2. Multiply two matrices\n3. Trace of a matrix\n4. Transpose of a matrix\n5. Norm of a matrix\n");
			System.out.print("Enter choice: ");

			int ch = CheckInteger.chkInt();
			switch(ch)
			{
				case 1: a.inputMatrix();
						b.inputMatrix();
						try
						{
							err.equateRowsAndCols(a.mat, b.mat);
							c.add(a.mat, b.mat);
							c.display();
						}
						catch(RowsAndColsMismatchException e)
						{
							System.out.println("Exception: "+ e);
						}
						break;

				case 2: a.inputMatrix();
						b.inputMatrix();
						try
						{
							err.equateRowsAndCols(a.mat[0].length, b.mat.length);
							c.multiply(a.mat, b.mat);
							c.display();
						}
						catch(RowsAndColsMismatchException e)
						{
							System.out.println("Exception: "+ e + "\nStatement: Multiplication can only be performed when, cols of matrix A == rows of matrix B.");
						}
						break;

				case 3: a.inputMatrix();
						try
						{
							err.equateRowsAndCols(a.mat[0].length, a.mat.length);
							c.trace(a.mat);
						}
						catch(RowsAndColsMismatchException e)
						{
							System.out.println("Exception: "+ e + "\nStatement: Trace can only be performed when, cols of matrix A == rows of matrix A.");
						}
						break;


				case 4: a.inputMatrix();
						c.transpose(a.mat);
						c.display();
						break;

				case 5: a.inputMatrix();
						try
						{
							err.equateRowsAndCols(a.mat[0].length, a.mat.length);
							b.transpose(a.mat);
							c.multiply(a.mat, b.mat);
							c.display();
						}
						catch(RowsAndColsMismatchException e)
						{
							System.out.println("Exception: "+ e + "\nStatement: Norm can only be performed when, cols of matrix A == rows of matrix A.");
						}
						break;
			}
			
			boolean flag = true;
			do
			{
				System.out.print("Do you want to continue (Y/N)?: ");
				choice = num.next().charAt(0);
				if(choice == 'y' || choice == 'Y' || choice == 'n' || choice == 'N')
					flag = false;
			}while(flag);
		}while((choice == 'y') || (choice == 'Y'));

	}
}
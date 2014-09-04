import java.util.Scanner;
import java.lang.Math;
import java.util.InputMismatchException;

class TriangleSidesException extends Exception{														// exception to check triangle posiblity
	public String toString()
	{
		return "Sides of the triangle are not proper to construct a triangle";
	}
}

class CheckTriangleSides{
	static boolean trianglePosiblity(int side1, int side2, int side3) throws TriangleSidesException
	{
		if(side1 + side2 <= side3)
			throw new TriangleSidesException();
		else if(side2 + side3 <= side1)
			throw new TriangleSidesException();
		else if(side3 + side1 <= side2)
			throw new TriangleSidesException();
		else
			return true;
	}
}

class NegativeSideException extends Exception{														// exception to check positive sides
	public String toString()
	{
		return "Side is negative. Please enter positive side";
	}
}

class EvaluateSides{
	static boolean positiveSideCheck(int side) throws NegativeSideException
	{
		if(side <= 0)
			throw new NegativeSideException();
		else
			return true;
	}
}

class CheckSide{
	static int chkSide()
	{	
		int value= 0;
		Scanner num = new Scanner(System.in);
		boolean flag = true;
		do
		{
			try
			{
				System.out.print("Value: ");															// call exception checker for input integer mismatch
				value = num.nextInt();																	// call exception checker for input positive mismatch
				EvaluateSides.positiveSideCheck(value);
				flag = true;
			}
			catch(InputMismatchException e)
			{
				System.out.println("Exception: "+e+"\nPlease enter integer values.");
				flag = false;
				num.next();
			}
			catch(NegativeSideException e)
			{
				System.out.println("Exception: "+e+"\nPlease enter positive value.");
				flag = false;
				//num.next();
			}
		}while(!flag);

		return value;
	}
}

// ****exception ends******

// ***program starts******

interface Shape{

	public double area();

}

class Triangle implements Shape{
	int a;
	int b;
	int c;

	Triangle()
	{

	}
	Triangle(int a, int b, int c)
	{
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public double area()
	{
		double p;
		double area;
		p = (double)(a+b+c)/2;
		area = Math.sqrt(p*(p-a)*(p-b)*(p-c));
		return area;
	}

	public double perimeter()
	{
		double perimeter;
		perimeter = a+b+c;
		return perimeter;
	}

}

class Rectangle implements Shape{
	int l;
	int b;

	Rectangle()
	{

	}

	Rectangle(int l, int b)
	{
		this.l = l;
		this.b = b;
	}

	public double area(){
		double area;
		area = l*b;
		return area;
	}

	public double perimeter()
	{
		double perimeter;
		perimeter = 2*(l+b);
		return perimeter;
	}

}

class RightTriangle extends Triangle{
	int height, base, hypotenuse;

	RightTriangle(int height, int base, int hypotenuse)
	{
		super();
		this.height = height;
		this.base = base;
		this.hypotenuse = hypotenuse;
	}

	public double area()
	{
		double area;
		area = 0.5*base*height;
		return area;
	}

	public double perimeter()
	{
		double perimeter;
		perimeter = height+base+hypotenuse;
		return perimeter;
	}
}

class Square extends Rectangle{
	int length;

	Square(int length)
	{
		super();
		this.length = length;
	}

	public double area()
	{
		double area;
		area = length*length;
		return area;
	}

	public double perimeter()
	{
		double perimeter;
		perimeter = 4*length;
		return perimeter;
	}

}

public class shape_question{
	public static void main(String args[])
	{
		Scanner num = new Scanner(System.in);
		int choice, side1=0,side2=0, side3=0, length, breadth;
		CheckTriangleSides err = new CheckTriangleSides();

		do
		{
			System.out.println("****Enter your choice****\n1. Area of Triangle\n2. Area of Rectangle");
			System.out.print("Enter choice: ");
			choice = CheckSide.chkSide();
			if (choice == 1)
			{
				try
				{
					System.out.println("Enter sides:");
					System.out.print("Enter side1: ");
					side1 = CheckSide.chkSide();
					System.out.print("Enter side2: ");
					side2 = CheckSide.chkSide();
					System.out.print("Enter side3: ");
					side3 = CheckSide.chkSide();

					err.trianglePosiblity(side1, side2, side3);
				}
				catch(TriangleSidesException e)
				{
					System.out.println("Exception: "+ e);
				}


				if(side1*side1 + side2*side2 == side3*side3)
				{
					RightTriangle t = new RightTriangle(side1, side2, side3);
					System.out.println("area = "+t.area());
					System.out.println("perimeter = "+t.perimeter());
				}
				else if(side2*side2 + side3*side3 == side1*side1)
				{
					RightTriangle t = new RightTriangle(side2, side3, side1);
					System.out.println("area = "+t.area());
					System.out.println("perimeter = "+t.perimeter());
				}
				else if(side3*side3 + side1*side1 == side2*side2)
				{
					RightTriangle t = new RightTriangle(side3, side1, side2);
					System.out.println("area = "+t.area());
					System.out.println("perimeter = "+t.perimeter());
				}
				else
				{
					Triangle t = new Triangle(side1, side2, side3);
					System.out.println("area = "+t.area());
					System.out.println("perimeter = "+t.perimeter());
				}
				
			}
			else if(choice == 2)
			{
				System.out.println("Enter sides:");
				System.out.print("Enter length: ");
				length = CheckSide.chkSide();
				System.out.print("Enter breadth: ");
				breadth = CheckSide.chkSide();
				
				if(length == breadth)
				{
					Square s = new Square(length);
					System.out.println("area = "+s.area());
					System.out.println("perimeter = "+s.perimeter());
				}
				else
				{
					Rectangle s = new Rectangle(length, breadth);
					System.out.println("area = "+s.area());
					System.out.println("perimeter = "+s.perimeter());
				}
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




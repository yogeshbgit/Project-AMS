package JavaTest;

public class java {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String nm = "Christmas Day .... Sunday - 25-Dec-2022";
		String nme = nm.split(" -")[0].split("[.] ")[1];
		System.out.println(nme);
		//Christmas Eve .... Saturday - 24-Dec-2022
		//Christmas Day .... Sunday - 25-Dec-2022
		//New Year Eve .... Saturday - 31-Dec-2022
	}

}

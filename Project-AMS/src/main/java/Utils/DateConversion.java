package Utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.testng.Assert;

public class DateConversion {

public int date;	
public int month;	
public int year;	
	public List<Integer> dateFormatConversion(String  schedule)
	{

		List<Integer> alldata = new ArrayList<>();
	 date = Integer.parseInt(schedule.split("-")[0]);
	 year = Integer.parseInt(schedule.split("-")[2]);
	String Month = schedule.split("-")[1];

	HashMap<String,Integer> hm = new HashMap<String,Integer>();
	hm.put("Jan", 1);
	hm.put("Feb", 2);
	hm.put("Mar", 3);
	hm.put("Apr", 4);
	hm.put("May", 5);
	hm.put("Jun", 6);
	hm.put("Jul", 7);
	hm.put("Aug", 8);
	hm.put("Sep", 9);
	hm.put("Oct", 10);
	hm.put("Nov", 11);
	hm.put("Dec", 12);

	 month = hm.get(Month);
	alldata.add(date);
	alldata.add(month);
	alldata.add(year);

		
	return alldata;
	
	}
	
	public List<Integer> dateFormatConversion1(String  schedule)
	{
		
		List<Integer> alldata = new ArrayList<>();
	int date = Integer.parseInt(schedule.split(" ")[0]);
	int year = Integer.parseInt(schedule.split(" ")[2]);
	String Month = schedule.split(" ")[1];

	HashMap<String,Integer> hm = new HashMap<String,Integer>();
	hm.put("Jan", 1);
	hm.put("Feb", 2);
	hm.put("Mar", 3);
	hm.put("Apr", 4);
	hm.put("May", 5);
	hm.put("Jun", 6);
	hm.put("Jul", 7);
	hm.put("Aug", 8);
	hm.put("Sep", 9);
	hm.put("Oct", 10);
	hm.put("Nov", 11);
	hm.put("Dec", 12);

	int month = hm.get(Month);
	alldata.add(date);
	alldata.add(month);
	alldata.add(year);
	return alldata;
	}

}

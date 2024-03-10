import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class validateData {

	public static boolean validateInputData(String input) throws ParseException {
		boolean isDataValid=false;
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("MMM YYYY");
		String store = sdf.format(cal.getTime());


		String inputMonth = input.split(" ")[0];
		String year = store.split(" ")[1];
		String month = store.split(" ")[0];
		Integer.parseInt(year);
		if (Integer.parseInt(input.split(" ")[1]) > Integer.parseInt(year)) {

			isDataValid=true;

		} else if (Integer.parseInt(input.split(" ")[1]) == Integer.parseInt(year)) {
			int inputMonthNum = getNumberFromMonthName(inputMonth);
			int expectedMonthNum = getNumberFromMonthName(month);

			if(inputMonthNum>=expectedMonthNum) {
				isDataValid=true;
			}
			

		}
		return isDataValid;
	}

	public static int getNumberFromMonthName(String monthName) throws ParseException {

		Date date = new SimpleDateFormat("MMM").parse(monthName);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MONTH);
	}
}

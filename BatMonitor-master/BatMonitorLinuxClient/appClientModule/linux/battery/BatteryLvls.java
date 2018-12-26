package linux.battery;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BatteryLvls {
	
	Map<Long, Integer> batteryLvls = new HashMap<Long,Integer>();
	
	public File getFileToRead() {
		return new File("/sys/class/power_supply/BAT0/capacity");
	}
	
	public int getBatteryLvl() throws FileNotFoundException {
            
            File file = getFileToRead();

            Scanner input = new Scanner(file);

            String line = input.nextLine();
            
            input.close();
            
            return Integer.parseInt(line);
	}
	
	public void addBatteryLvl() throws FileNotFoundException {
		batteryLvls.put(new Date().getTime(), getBatteryLvl());
	}
	
	public Map<Long, Integer> getBatteryLvls() {
		return batteryLvls;
	}
	
}

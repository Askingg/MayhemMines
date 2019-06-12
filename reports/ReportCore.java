package me.askingg.mayhem.reports;

import me.askingg.mayhem.utils.Files;

public class ReportCore {

	public static void createReport(String reporter, String reported, String reason) {
		int x = nextIndex();
		Files.reports.set("Reports.Menu." + x + ".Reporter", reporter);
		Files.reports.set("Reports.Menu." + x + ".Reported", reported);
		Files.reports.set("Reports.Menu." + x + ".Reason", reason);
		Files.reports.set("index", x++);
		try {
			Files.reports.save(Files.reportsFile);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void deleteReport(int x) {
		Files.reports.set("Reports.Menu." + x, null);
		try {
			Files.reports.save(Files.reportsFile);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static Integer nextIndex() {
		return Files.reports.getInt("index");
	}
}

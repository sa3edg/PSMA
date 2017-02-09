package com.benchmark.psma.reports;

public abstract class ReportStatementBase {
	
	public static String REPORT_SQL = 
			"select usher_type, first_name, last_name from ushers, projects where ";
	
	public abstract String getReportSql();
}

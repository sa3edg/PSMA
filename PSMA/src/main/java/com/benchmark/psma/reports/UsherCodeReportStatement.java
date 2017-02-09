package com.benchmark.psma.reports;

public class UsherCodeReportStatement extends ReportStatementBase {

	public static final String itemName = "usher_code";
	
	private String reportSql;
	
	public UsherCodeReportStatement(String reportSql, String itemValue) {
		this.reportSql = reportSql + " and " + itemName + itemValue;
	}
	
	public UsherCodeReportStatement(String itemValue) {
		this.reportSql = REPORT_SQL + itemName + itemValue;
	}
	
	@Override
	public String getReportSql() {
		return reportSql;
	}

}

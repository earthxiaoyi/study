package leaderus.study3;

public final class TestLink {
	public static void main(String[] args) {
		SQLLinkList link = new SQLLinkList();
		link.add("1", "10");
		link.add("2", "20");
		System.out.println(link);
		//String runSQLBySameSQL = link.getRunSQLBySameSQL("1");
		//System.out.println(runSQLBySameSQL);
		String runSQLBySameSQL2 = link.getRunSQLBySameSQL("2");
		link.add("3", "30");
		link.add("4", "40");
		//System.out.println(runSQLBySameSQL2);

	}
}

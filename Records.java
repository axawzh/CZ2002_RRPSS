import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;


public class Records implements Serializable {
	
	ArrayList<Entry> entries = null;
	
	public Records() {
		entries = new ArrayList<Entry>();
	}
	
	public void add(Entry e) {
		entries.add(e);
	}
	
	public void printRevenueReport(DateTime d1, DateTime d2) {
		ArrayList<Entry> report = new ArrayList<Entry>();
		HashSet<String> reportScope = new HashSet<String>();
		
		for (int index = 0; index < entries.size(); index++) {
			if (entries.get(index).getDateTime().after(d1) && !entries.get(index).getDateTime().after(d2)) {
				Entry e = entries.get(index);
				if (reportScope.contains(e.getName())) {
					for (int i = 0; i < report.size(); i++)
						if (report.get(i).getName().equals(e.getName())) {
							report.get(i).addQuantity(e.getQuantity());
							report.get(i).addValue(e.getPrice());
							break;
						}
				}
				else {
					report.add(new Entry(e.getName(), e.getDateTime(), e.getQuantity(), e.getPrice()));
					reportScope.add(e.getName());
				}		
			}
		}
		
		StringBuilder sb = new StringBuilder();
		double total = 0;
		
		sb.append("Sale Revenue Report\n");
		sb.append("Fulo Restaurant");
		sb.append("For the period from : " + d1 + "\n");
		sb.append("                 To : " + d2 + "\n");
		sb.append("-------------------------------------------\n");
		sb.append(" S/N          Items         Quantity Revenue\n");
		
		for (int i = 0; i < report.size(); i++) {
			Entry eTemp = report.get(i);
			total += eTemp.getPrice();
			sb.append(String.format("%3d  %-23s %4d   $%6.2f\n", i+1, eTemp.getName(), eTemp.getQuantity(), eTemp.getPrice()));
		}
		
		sb.append("-------------------------------------------\n");
		sb.append(String.format("                      Total Revenue: $%6.2f\n", total));
		
		System.out.println(sb.toString());
		
	}
}

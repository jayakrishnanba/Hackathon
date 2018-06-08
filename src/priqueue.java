

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

public class priqueue{	
	
	public static boolean contains(PriorityQueue<StrInfo> q, StrInfo o)
	{
		Iterator<StrInfo> iter = q.iterator();
		while(iter.hasNext())
		{
			StrInfo i = iter.next();
			if(i.str.equals(o.str) && i.Freq == o.Freq)
				return true;
		}
		return false;
	}




	public static void addToQueue(PriorityQueue<StrInfo> queue, StrInfo strInfo)
	{
		if(!contains(queue,strInfo))
			queue.add(strInfo);
	}	
}
final class StrInfo {
	StrInfo(String t, double df) {
		str = t;
		Freq = df;
	}
	double Freq;
	String str;
}
final class StringLengthComparator implements Comparator<StrInfo>
{
	@Override
		public final int compare(StrInfo x, StrInfo y){
			StrInfo strInfoA = (StrInfo) x;
			StrInfo strInfoB = (StrInfo) y;           
			if (strInfoA.Freq < strInfoB.Freq)
			{
				return -1;
			}
			if (strInfoA.Freq > strInfoB.Freq)
			{

				return 1;
			}
			return 0;
		}
}

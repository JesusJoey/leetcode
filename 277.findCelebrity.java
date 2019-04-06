//boolean knows(int a,int b)

/**
1.find the candidate 
2. check if he is celebrity
*/
public class Solution extends Relation{
	public int findCelebrity(int n){
		int candidate=0;
		for (int i=0;i<n;i++){
			if knows(candidate,i){
				candidate=i;
			}
		}
		for (int i=0;i<n;i){
			if (i==candidate)
				continue;
			if (!knows(i,candidate)||knows(candidate,i))
				return -1; //no celebrity
		}
		return candidate;
	}
}
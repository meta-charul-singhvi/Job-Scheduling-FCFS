package fcfs;
import java.util.Scanner;

public class JobSchedulingFCFS {
	
	static int[] calCompTime(int[][] process, int n){
		int[] compTime = new int[n];
		
		compTime[0] = process[0][1]+process[0][0];
		for(int i=1; i<n; i++){
//			checking if the completion time of earlier process is less than arrival 
//			time of the current
			if(process[i][0] > compTime[i-1]){
				compTime[i] = (process[i][0] - compTime[i-1]);
			}
			compTime[i] += process[i][1] + compTime[i-1];
		}
		return compTime;
	}
	
	static int[] calWaitTime(int[][] process, int[] compTime, int n){
		int[] waitingTime = new int[n];

		for(int i=1; i<n; i++){
			if(process[i][0]<compTime[i-1])
				waitingTime[i] =  compTime[i-1] - process[i][0];
		}
		return waitingTime;
	}
	
	static int[] calTurnAroundTime(int[] compTime, int[] waitTime, int n){
		int[] turnArdTime = new int[n];
		for(int i=1; i<n; i++){
			turnArdTime[i] =  compTime[i] - waitTime[i];
		}
		return turnArdTime;
	}
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter the number of processes : ");
		int n = scan.nextInt();
		int[][] process = new int[n][2];
		for(int i=0; i<n; i++){
			System.out.print("Enter arrival time for process "+i+": ");
			process[i][0] = scan.nextInt();
			System.out.print("Enter burst time for process "+i+": ");
			process[i][1] = scan.nextInt();
		}
		
		int[] compTime = calCompTime(process,n);
		int[] waitTime = calWaitTime(process,compTime,n);
		int[] turnAroundTime = calTurnAroundTime(compTime,waitTime,n);
		int maxWaitTime = 0;
		int maxTimeProcess = 0;
		int sumWait=0;
		
		System.out.println("Process\tArrival Time\tBurst Time\tCompletion Time\tWaiting Time\tTurn Around Time");
		for(int i=0; i<n; i++){
			System.out.println("P"+i+"\t"+process[i][0]+"\t\t"+process[i][1]+"\t\t"
					+compTime[i]+"\t\t"+waitTime[i]+"\t\t"+turnAroundTime[i]);
			sumWait += waitTime[i];
			if(maxWaitTime < waitTime[i]){
				maxWaitTime = waitTime[i];
				maxTimeProcess = i;
			}
		}
		
		double avgWaitTime = sumWait/n;
		System.out.println("Average Waiting Time : "+ avgWaitTime);
		System.out.println("Maximum Waiting Time is "+ maxWaitTime+ " for process P"+maxTimeProcess);
		
		scan.close();

	}

}

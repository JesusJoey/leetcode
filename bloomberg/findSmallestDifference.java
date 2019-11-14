第一问 问了下two vector how to find the minimum difference between them
[5,6,7,3]

[1,2,4,5]
这个例子minimum diffrence is 0

   static int findSmallestDifference(int A[], int B[], 
                                      int m, int n) 
    { 
        Arrays.sort(A); 
        Arrays.sort(B); 
      
        int a = 0, b = 0; 
      
        // Initialize result as max value 
        int result = Integer.MAX_VALUE; 
      
        // Scan Both Arrays upto  
        // sizeof of the Arrays 
        while (a < m && b < n) 
        { 
            if (Math.abs(A[a] - B[b]) < result) 
                result = Math.abs(A[a] - B[b]); 
      
            // Move Smaller Value 
            if (A[a] < B[b]) 
                a++; 
      
            else
                b++; 
        } 
          
        // return final sma result 
        return result;  
    } 
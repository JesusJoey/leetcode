    public int findLongestChain(int[][] pairs) {
        if(pairs == null || pairs.length == 0 || pairs[0].length == 0){
            return 0;
        }
        Arrays.sort(pairs, new Comparator<int[]>(){
            @Override
            public int compare(int[] p1, int[] p2){
                return p1[1] - p2[1];
            }
        });
        int count = 1;
        int preEnd = pairs[0][1];
        for(int i = 1; i < pairs.length; i++){
            int[] curr = pairs[i];
            if(preEnd < curr[0]){
                count++;
                preEnd = pairs[i][1];
            }else{
                continue;
            }
        }
        return count;
    }

    public int findLongestChain(int[][] pairs)
    {
        Arrays.sort(pairs, (p1,p2)->p1[1]-p2[1] );

        int count = 0, end = Integer.MIN_VALUE;
        for (int[] pair : pairs)
        {
            if (pair[0] > end)
            {
                count++;
                end = pair[1];
            }
        }
        return count;
    }

    public int findLongestChain(int[][] pairs) {
        if (pairs == null || pairs.length == 0) return 0;
        Arrays.sort(pairs, (a, b) -> (a[0] - b[0]));
        int[] dp = new int[pairs.length];
        Arrays.fill(dp, 1);
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] = Math.max(dp[i], pairs[i][0] > pairs[j][1]? dp[j] + 1 : dp[j]);
            }
        }
        return dp[pairs.length - 1];
    }


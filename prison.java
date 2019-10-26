 	public int[] prisonAfterNDays(int[] cells, int N) {
        while (N > 0) {
            N--;
            int[] cells2 = new int[8];
            for (int i = 1; i < 7; ++i)
                cells2[i] = cells[i - 1] == cells[i + 1] ? 1 : 0;
            cells = cells2;
        }
        return cells;
    }

    Solution 1
We record all seen states.
Be careful,
we need transform array to string as the key,
otherwise it use the reference.

    public int[] prisonAfterNDays(int[] cells, int N) {
        Map<String, Integer> seen = new HashMap<>();
        while (N > 0) {
            int[] cells2 = new int[8];
            seen.put(Arrays.toString(cells), N--);
            for (int i = 1; i < 7; ++i)
                cells2[i] = cells[i - 1] == cells[i + 1] ? 1 : 0;
            cells = cells2;
            if (seen.containsKey(Arrays.toString(cells))) {
                N %= seen.get(Arrays.toString(cells)) - N;
            }
        }
        return cells;
    }

    public int[] prisonAfterNDays(int[] cells, int N) {

        int[] firstSimulation = new int[8];
        for (int i=1; i<7; i++) firstSimulation[i] = (cells[i-1] == cells[i+1] ? 1 : 0);
        cells = firstSimulation.clone();
        N -= 1;
        int cycle = 1;
        while (N-- > 0) {
            int[] nextSimulation = new int[8];
            for (int i=1; i<7; i++) nextSimulation[i] = (cells[i-1] == cells[i+1] ? 1 : 0);
            if (Arrays.equals(firstSimulation, nextSimulation)) N %= cycle;
            cells = nextSimulation.clone();
            cycle++;
        }
        return cells;
    }
/*
443. String Compression
*/

	public int compress(char[] chars) {
        int indexAns = 0, index = 0;
        while(index < chars.length){
            char currentChar = chars[index];
            int count = 0;
            while(index < chars.length && chars[index] == currentChar){
                index++;
                count++;
            }
            chars[indexAns++] = currentChar;
            if(count != 1)
                for(char c : Integer.toString(count).toCharArray()) 
                    chars[indexAns++] = c;
        }
        return indexAns;
    }

    public String decode(String s) {
    	char[] compressed = s.toCharArray();
    	StringBuilder sb = new StringBuilder();
    	for (int i = 0; i < compressed.length; i++) {
    		int count = 1;
    		if (Character.isDigit(compressed[i])) {
    			count = Character.getNumericValue(compressed[i]);
    		} else {
    			sb.append(compressed[i]);
    		}
    		while (count > 1) {
    			sb.append(compressed[i - 1]);
    			count--;
    		}
    	}
    	return sb.toString();
    }
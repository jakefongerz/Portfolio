reading the file and adding to hashtable
    
read file into a long single array
iterate threw each word
    check if it has punctuation
        if so remove it and set it as the follow word
        look at the punctuation as the next word
    check if word is in dictionary
        if not add word 
            create word info obj
            insert key, value
        if word is there
            get current wordinfo obj
            del current key from dict
            add follow to wordinfo obj
            insert key and value to dict


Add a public String getFollowWord(int count) method to WordFreqInfo that obtains the next word, based on the value of count.  
 The value of count is randomly generated during the poem generation,
 it is a value from 0 to n-1, where n is the number of times a word follows the key represented by the WordFreqInfo instance.
 This method selects the correct follow word, based on the value of n.  
 The following example may help you understand how this works better.
The word "sam" occurs 8 times, 2 of those times it is followed by a "." (a period),
 6 of those times it is followed by "I".
If count is 0, then the follow word is "."
If count is 1, then the follow word is "."
If count is 2, then the follow word is "I"
For any value of count from 2 to 7, the follow word is "I"

            
            
    
        
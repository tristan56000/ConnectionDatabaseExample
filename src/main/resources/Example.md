### Unsuccessful idea

If we take an entry and concatenate all its values in order to hash them, several
entries that are different might have the same hash at the end, due to the fact a value can be null.

+----------+--------------+---------+<br/>
| col1     | col2         | col3    |<br/>
+----------+--------------+---------+<br/>
| "aaa"    | "bbb"        |  NULL   |<br/>
| "aaa"    |  NULL        | "bbb"   |<br/>
|  NULL    | "aaa"        | "bbb"   |<br/>
+----------+--------------+---------+<br/>

For each concatenation entry we will have:
 * aaabbb   -----SHA2--->   sdjfhskdjh
 * aaabbb   -----SHA2--->   sdjfhskdjh
 * aaabbb   -----SHA2--->   sdjfhskdjh
 
Then with this idea different entries have finally the same hash



### Successful idea

If we take an entry and concatenate all its values and their column's name in order to hash them,
then here all different entries will lead to a different hash due to the fact that column's name are
necessarily different one from each other.


+----------+--------------+---------+<br/>
| col1     | col2         | col3    |<br/>
+----------+--------------+---------+<br/>
| "aaa"    | "bbb"        |  NULL   |<br/>
| "aaa"    |  NULL        | "bbb"   |<br/>
|  NULL    | "aaa"        | "bbb"   |<br/>
+----------+--------------+---------+<br/>

For each concatenation entry we will have:
 * col1aaacol2bbbcol3   -----SHA2--->   ifucefjfes
 * col1aaacol2col3bbb   -----SHA2--->   ofnsjkkjdq
 * col1col2aaacol3bbb   -----SHA2--->   asosdjcjds
 
Then with this idea two different entries will lead to two different hashes

# Algorithms - compute 2k length strings of a DNA sequence given a k-length string

Folder contains a war story implementation from the text  The Algorithm Design Manual by Steven Skiena using various data structures- Arrays, Hashing/Rollover Hashing. 

This implementation contains the following classes- 
1) WarWithArray
2) WarWithBST
3) WarWithHash
4) WarWithRollHash

An unknown DNA sequence U is provided about which you would like to find some information. You are given a set S of n strings, and each string has length exactly k such that S is the set of all 2 k-length substrings of U. Now the goal is to construct a set T such that T is the set of all 2k-length substrings of U. Input is S: A set of k-length substrings of U and output is T|set of all 2k-length substrings of U.

Classes:

WarWithArray

This class will contain a method to return T. This class uses array to store S. This class will have following constructor and method.
WarWithArray(String[] s, int k ). s is the set of all k-length substrings of U. compute2k() in the method which returns an arraylist of 2k-length strings which is the set of all 2k-length substrings of U. Return type of this method must be ArrayList<String>.
  
 WarWithHash
  
This class will contain a method to return T. This class uses hash sets/hash tables to store S.This class will have following constructor and method - WarWithHash(String[] s, int k ). s is the set of all k-length substrings of U. In the constructor, you must store s in a hash table/hash set so that it can be used in compute2k().
compute2k() - This method returns an arraylist of 2k-length strings which is the set of all 2k-length substrings of U. Return type of this method must be ArrayList<String>.
  
WarWithRollHash

This class will also contain a method to compute T.This class must have following constructor and method - WarWithBST(String[] s, int k ). s is the set of all k-length substrings of U.
compute2k() - This method returns an arraylist of 2k-length strings which is the set of all 2k-length substrings of U. Return type of this method must be ArrayList<String> .
  
A brief report that includes the following.
Pseudo code of method compute2k() from - 
1. WarWithArray
2. WarWithBST
3. WarWithHash
4. WarWithRollHash

In addition, we analyze the asymptotic run times of the algorithms. Express the run time as a function of k and n, where n is the size of the set S.

All the designs are included in this folder. 

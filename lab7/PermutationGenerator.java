package lab7;

import java.util.*;

public class PermutationGenerator {
    
    // Recursive function to generate permutations
    public List<String> generatePermutations(String str, boolean allowDuplicates) {
        List<String> result = new ArrayList<>();
        if (str == null || str.isEmpty()) return result;
        
        // For duplicate handling, use a set to ensure unique entries
        Set<String> uniquePermutations = new HashSet<>();
        permuteRecursive(str.toCharArray(), 0, result, allowDuplicates ? null : uniquePermutations);
        
        return result;
    }
    
    private void permuteRecursive(char[] chars, int index, List<String> result, Set<String> uniquePermutations) {
        if (index == chars.length - 1) {
            String permutation = new String(chars);
            if (uniquePermutations == null || uniquePermutations.add(permutation)) {
                result.add(permutation);
            }
        } else {
            for (int i = index; i < chars.length; i++) {
                swap(chars, index, i);
                permuteRecursive(chars, index + 1, result, uniquePermutations);
                swap(chars, index, i); // backtrack
            }
        }
    }
    
    private void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }
    
    // Non-recursive permutation generation
    public List<String> generatePermutationsIterative(String str) {
        List<String> result = new ArrayList<>();
        if (str == null || str.isEmpty()) return result;

        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        do {
            result.add(new String(chars));
        } while (nextPermutation(chars));
        
        return result;
    }
    
    // Helper function to find the next lexicographical permutation
    private boolean nextPermutation(char[] chars) {
        int i = chars.length - 2;
        while (i >= 0 && chars[i] >= chars[i + 1]) i--;
        if (i < 0) return false;
        
        int j = chars.length - 1;
        while (chars[j] <= chars[i]) j--;
        swap(chars, i, j);
        
        reverse(chars, i + 1);
        return true;
    }
    
    private void reverse(char[] chars, int start) {
        int end = chars.length - 1;
        while (start < end) swap(chars, start++, end--);
    }
    
    public static void main(String[] args) {
        PermutationGenerator pg = new PermutationGenerator();
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();
        
        System.out.print("Allow duplicate permutations? (yes/no): ");
        boolean allowDuplicates = scanner.nextLine().equalsIgnoreCase("yes");
        
        long start = System.nanoTime();
        List<String> recursivePerms = pg.generatePermutations(input, allowDuplicates);
        long recursiveTime = System.nanoTime() - start;
        
        System.out.println("Recursive permutations: " + recursivePerms);
        System.out.println("Number of permutations (recursive): " + recursivePerms.size());
        System.out.println("Time taken (recursive): " + recursiveTime + " ns");
        
        start = System.nanoTime();
        List<String> iterativePerms = pg.generatePermutationsIterative(input);
        long iterativeTime = System.nanoTime() - start;
        
        System.out.println("Iterative permutations: " + iterativePerms);
        System.out.println("Number of permutations (iterative): " + iterativePerms.size());
        System.out.println("Time taken (iterative): " + iterativeTime + " ns");
        
        scanner.close();
    }
}

package h3;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

public class H3_main {
    public static void main(String[] args) {
        for(int i = 0; i < 10; i++) System.out.println(Arrays.toString(mergeSort(Stream.generate(Random::new).limit(30).mapToInt(rand -> rand.nextInt(100)).toArray())));
    }

    public static int[] mergeSort(int[] src) {
        if(src.length <= 1) return src;
        int leftLength = src.length / 2;
        int rightLength = src.length - leftLength;
        int[] left = mergeSort(Arrays.copyOfRange(src, 0, leftLength));
        int[] right = mergeSort(Arrays.copyOfRange(src, leftLength, src.length));
        int[] dest = new int[src.length];
        int leftI = 0;
        int rightI = 0;
        for(int i = 0; i < dest.length; i++) {
            if(leftI >= leftLength) {
                dest[i] = right[rightI++];
                continue;
            }
            if(rightI >= rightLength) {
                dest[i] = left[leftI++];
                continue;
            }
            int leftE = left[leftI];
            int rightE = right[rightI];
            if(leftE <= rightE) {
                dest[i] = leftE;
                leftI++;
            } else {
                dest[i] = rightE;
                rightI++;
            }
        }
        return dest;
    }
}

package com.devtop.studentsorter.sorting;

import com.devtop.studentsorter.model.Student;

/**
 * java.util.Collections.sort also sorts in merge sort. Although its a bit modified version
 */
public class MergeSorter implements Sorter {

    public Student[] sort(Student[] arr){
        return sort(arr, 0, arr.length - 1);
    }

    public Student[] sort(Student[] arr, int startIndex, int endIndex) {
        if (startIndex < endIndex) {
            int middle = (startIndex + endIndex) / 2;
            sort(arr, startIndex, middle);
            sort(arr, middle + 1, endIndex);
            merge(arr, startIndex, middle, endIndex);
        }
        return arr;
    }

    private void merge(Student[] arr, int startIndex, int middle, int endIndex) {
        int n1 = middle - startIndex + 1;
        int n2 = endIndex - middle;
        Student[] L = new Student[n1];
        Student[] R = new Student[n2];
        for (int i = 0; i < n1; ++i)
            L[i] = arr[startIndex + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[middle + 1 + j];

        int i = 0, j = 0;
        int k = startIndex;
        while (i < n1 && j < n2) {
            if (L[i].getMark() <= R[j].getMark()) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
}

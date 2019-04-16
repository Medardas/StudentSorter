package com.devtop.studentsorter.sorting;

import com.devtop.studentsorter.model.Student;

public class HeapSorter implements Sorter {
    public Student[] sort(Student[] arr) {
        int length = arr.length;
        for (int i = length / 2 - 1; i >= 0; i--) {
            heapify(arr, length, i);
        }
        for (int i = length - 1; i >= 0; i--) {
            Student temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }

        return arr;
    }

    private void heapify(Student[] arr, int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && arr[l].getMark() > arr[largest].getMark()) {
            largest = l;
        }

        if (r < n && arr[r].getMark() > arr[largest].getMark()) {
            largest = r;
        }

        if (largest != i) {
            Student swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            heapify(arr, n, largest);
        }
    }
}

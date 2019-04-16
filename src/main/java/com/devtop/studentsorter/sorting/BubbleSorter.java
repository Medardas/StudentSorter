package com.devtop.studentsorter.sorting;

import com.devtop.studentsorter.model.Student;

public class BubbleSorter implements Sorter{

    public Student[] sort(Student[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (arr[j].getMark() > arr[j + 1].getMark()) {
                    Student temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
        return arr;
    }

}

package com.devtop.studentsorter.view;

import com.devtop.studentsorter.model.Student;
import com.devtop.studentsorter.service.StudentFactory;
import com.devtop.studentsorter.sorting.BubbleSorter;
import com.devtop.studentsorter.sorting.HeapSorter;
import com.devtop.studentsorter.sorting.MergeSorter;
import com.devtop.studentsorter.sorting.Sorter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SorterWindow extends JPanel implements ActionListener {
    static private final String newline = "\n";
    JButton saveButton;
    private Student[] model;

    JButton openButton;
    JButton bubbleSortButton;
    JButton heapSortButton;
    JButton mergeSortButton;
    JPanel resultPanel;
    private int runCount = 0;
    JTextArea textArea;
    JFileChooser fc;

    public SorterWindow() {
        super(new BorderLayout());

        textArea = new JTextArea(5, 20);
        textArea.setMargin(new Insets(5, 5, 5, 5));
        textArea.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(textArea);

        fc = new JFileChooser();

        openButton = new JButton("Open a File...");
        openButton.addActionListener(this);
        bubbleSortButton = new JButton("Bubble sort");
        bubbleSortButton.addActionListener(this);
        heapSortButton = new JButton("Heap sort");
        heapSortButton.addActionListener(this);
        mergeSortButton = new JButton("Merge sort");
        mergeSortButton.addActionListener(this);

        saveButton = new JButton("Save a File...");
        saveButton.addActionListener(this);

        JPanel topButtonPanel = new JPanel();
        topButtonPanel.add(openButton);
        topButtonPanel.add(saveButton);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        JPanel sortingButtonPanel = new JPanel();
        sortingButtonPanel.add(bubbleSortButton);
        sortingButtonPanel.add(heapSortButton);
        sortingButtonPanel.add(mergeSortButton);
        bottomPanel.add(sortingButtonPanel, BorderLayout.PAGE_START);

        resultPanel = new JPanel();
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
        bottomPanel.add(resultPanel, BorderLayout.PAGE_END);

        add(topButtonPanel, BorderLayout.PAGE_START);
        add(logScrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.PAGE_END);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openButton) {
            openFile();
        } else if (e.getSource() == bubbleSortButton && model != null) {
            printSortedStudents(new BubbleSorter());
        } else if (e.getSource() == heapSortButton && model != null) {
            printSortedStudents(new HeapSorter());
        } else if (e.getSource() == mergeSortButton && model != null) {
            printSortedStudents(new MergeSorter());
        } else if (e.getSource() == saveButton && !textArea.getText().trim().isEmpty()) {
            saveToFile();
        }
    }

    private void openFile() {
        int returnVal = fc.showOpenDialog(SorterWindow.this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            printFileAndCreateStudentModel(file);
        }
    }

    private void saveToFile() {
        int returnVal = fc.showSaveDialog(SorterWindow.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                FileWriter fileWriter = new FileWriter(fc.getSelectedFile().getAbsoluteFile(), true);
                fileWriter.append(textArea.getText());
                fileWriter.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    private void printSortedStudents(Sorter sorter) {
        textArea.setText("");
        long timeStart = System.currentTimeMillis();
        Student[] sortedStudents = sorter.sort(model); //to show algorithm work sorting is done only on initial input
        long timeEnd = System.currentTimeMillis() - timeStart;
        for (Student student : sortedStudents) {
            textArea.append(student.toString() + newline);
        }
        updateResult(timeEnd);
    }

    private void updateResult(long timeTook) {
        JLabel resultLabel = new JLabel("Run " + (++runCount) + ": " + model.length + " records sorted in " + timeTook + "ms");
        resultLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        resultPanel.add(resultLabel);
        updateUI();
    }

    private void printFileAndCreateStudentModel(File file) {
        textArea.setText("");
        StudentFactory factory = new StudentFactory();
        java.util.List<Student> studentList = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter("\r?\n");
            while (scanner.hasNext()) {
                String inputLine = scanner.next();
                studentList.add(factory.createStudent(inputLine));
                textArea.append(inputLine + newline);
            }
            model = studentList.toArray(new Student[]{});
        } catch (FileNotFoundException e) {
            System.err.println("Could not process file: " + file.getName());
            e.printStackTrace();
        }
    }

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("FileChooserDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(new SorterWindow());

        frame.pack();
        frame.setVisible(true);
    }
}

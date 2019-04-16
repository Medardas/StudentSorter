package com.devtop.studentsorter;

import com.devtop.studentsorter.view.SorterWindow;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                new SorterWindow().createAndShowGUI());

    }
}

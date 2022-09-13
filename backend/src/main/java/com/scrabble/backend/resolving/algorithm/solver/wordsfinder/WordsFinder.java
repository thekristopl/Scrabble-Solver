package com.scrabble.backend.resolving.algorithm.solver.wordsfinder;

import com.scrabble.backend.resolving.algorithm.Word;
import com.scrabble.backend.resolving.algorithm.settings.ScrabbleSettings;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.scrabble.backend.resolving.algorithm.Word.transposePoint;


public class WordsFinder {
    private static final int size = ScrabbleSettings.getBoardSize();

    public static ArrayList<Word> getVerticalAndHorizontal(char[][] board, String holder) {
        ArrayList<Word> allWords = new ArrayList<>();
        allWords.addAll(getVertical(board, holder));
        allWords.addAll(getHorizontal(board, holder));

        return allWords;
    }


    public static ArrayList<Word> getVertical(char[][] board, String holder) {
        List<Word> list = IntStream.range(0, board.length).parallel()
                .mapToObj(colNum -> WordsFinderForColumn.find(board, colNum, holder))
                .flatMap(List::stream)
                .toList();
        return new ArrayList<>(list);
    }


    private static ArrayList<Word> getHorizontal(char[][] board, String holder) {
        ArrayList<Word> verticalToRotate = getVertical(transpose(board), holder);
        return rotateVerticalToHorizontal(verticalToRotate);
    }


    private static char[][] transpose(char[][] board) {
        char[][] transposedBoard = new char[WordsFinder.size][WordsFinder.size];
        for (int x = 0; x < WordsFinder.size; x++) {
            for (int y = 0; y < WordsFinder.size; y++) {
                transposedBoard[x][y] = board[y][x];
            }
        }
        return transposedBoard;
    }


    private static ArrayList<Word> rotateVerticalToHorizontal(ArrayList<Word> verticalToRotate) {
        return verticalToRotate.stream()
                .map(word -> new Word(word.value, transposePoint(word.begin), Word.Direction.HORIZONTAL, transposePoint(word.entryBegin), word.entryLength))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}

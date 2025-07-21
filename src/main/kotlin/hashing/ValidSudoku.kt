package hashing

/**
 * You are given a 9x9 sudoku board.
 *
 * A Sudoku board is valid if the following rules are followed:
 *
 * - Each row must contain the digits 1-9 without duplicates.
 * - Each column must contain the digits 1-9 without duplicates.
 * - Each of the nine sub boxes within the grid must contain the digits 1-9 without duplicates.
 *
 * Return true if the sudoku board is valid, otherwise return false. A board does not need to be full or be
 * solvable to be considered valid.
 */
class ValidSudoku {
    /**
     * This solution uses a two hashsets to track repeated values of a row and column and an array of hashsets to
     * track duplicated for each sub box.
     *
     * The algorithm iterates over each value of the board and adds the value to the row, column, and target square
     * hashset. The index of the square hashset is determined via integer division of the row and column indices.
     *
     * If any value is found to be a number and also already within at least one hashset, the board is invalid and
     * a value of false will be returned. Otherwise, the board is valid and true will be returned.
     */
    fun isValidSudoku(board: Array<CharArray>): Boolean {
        val squares = Array(9) { hashSetOf<Char>() }

        for (i in board.indices) {
            val row = hashSetOf<Char>()
            val column = hashSetOf<Char>()

            for (j in board.indices) {
                if (board[i][j] != '.') {
                    if (board[i][j] in row) return false

                    val index = (i / 3) * 3 + (j / 3)
                    if (board[i][j] in squares[index]) return false

                    row.add(board[i][j])
                    squares[index].add(board[i][j])
                }

                if (board[j][i] != '.') {
                    if (board[j][i] in column) return false

                    column.add(board[j][i])
                }
            }
        }

        return true
    }
}

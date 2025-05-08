package chess;

import java.util.ArrayList;
import java.util.Collection;

public class KnightMovesCalc implements PieceMovesCalc {
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        Collection<ChessMove> knightMoves = new ArrayList<>();

        int row = position.getRow();
        int col = position.getColumn();
        int[][] knightDirections = {{-2, 1}, {-2, -1}, {1, -2}, {1, 2}, {2, 1}, {2, -1}, {-1, 2}, {-1, -2}};

        for (int[] dir : knightDirections) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            ChessPosition newPosition = new ChessPosition(newRow, newCol);

            if (isWithinBounds(newPosition)) {
                if (board.getPiece(newPosition) == null || isOpponentTeam(board, position, newPosition) ) {
                    knightMoves.add(new ChessMove(position, newPosition, null));
                }
            }
        }
        return knightMoves;
    }
}

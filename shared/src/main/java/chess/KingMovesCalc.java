package chess;

import java.util.ArrayList;
import java.util.Collection;

public class KingMovesCalc implements PieceMovesCalc {
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        Collection<ChessMove> kingMoves = new ArrayList<>();

        int row = position.getRow();
        int col = position.getColumn();
        int[][] kingDirections = {{-1, 1}, {1, 1}, {1, -1}, {-1, -1}, {-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        for (int[] dir : kingDirections) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            ChessPosition newPosition = new ChessPosition(newRow, newCol);

            if (isWithinBounds(newPosition)) {
                if (board.getPiece(newPosition) == null || isOpponentTeam(board, position, newPosition) ) {
                    kingMoves.add(new ChessMove(position, newPosition, null));
                }
            }
        }

        return kingMoves;
    }
}

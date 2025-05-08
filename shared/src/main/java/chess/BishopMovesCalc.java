package chess;

import java.util.ArrayList;
import java.util.Collection;

public class BishopMovesCalc implements PieceMovesCalc {
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        Collection<ChessMove> bishopMoves = new ArrayList<>();

        int row = position.getRow();
        int col = position.getColumn();
        int[][] bishopDirections = {{-1, 1}, {1, 1}, {1, -1}, {-1, -1}};

        for (int[] dir : bishopDirections) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            ChessPosition newPosition = new ChessPosition(newRow, newCol);

            while (isWithinBounds(newPosition)) {
                if (board.getPiece(newPosition) == null) {
                    bishopMoves.add(new ChessMove(position, newPosition, null));
                } else {
                    if(isOpponentTeam(board, position, newPosition)) {
                        bishopMoves.add(new ChessMove(position, newPosition, null));
                    }
                    break;
                }
                newRow += dir[0];
                newCol += dir[1];
                newPosition = new ChessPosition(newRow, newCol);
            }

        }

        return bishopMoves;
    }
}

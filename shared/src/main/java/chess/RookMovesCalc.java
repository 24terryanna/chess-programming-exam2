package chess;

import java.util.ArrayList;
import java.util.Collection;

public class RookMovesCalc implements PieceMovesCalc {
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        Collection<ChessMove> rookMoves = new ArrayList<>();

        int row = position.getRow();
        int col = position.getColumn();
        int[][] rookDirections = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        for (int[] dir : rookDirections) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            ChessPosition newPosition = new ChessPosition(newRow, newCol);

            while (isWithinBounds(newPosition)) {
                if (board.getPiece(newPosition) == null) {
                    rookMoves.add(new ChessMove(position, newPosition, null));
                } else {
                    if(isOpponentTeam(board, position, newPosition)) {
                        rookMoves.add(new ChessMove(position, newPosition, null));
                    }
                    break;
                }
                newRow += dir[0];
                newCol += dir[1];
                newPosition = new ChessPosition(newRow, newCol);
            }

        }

        return rookMoves;
    }


}

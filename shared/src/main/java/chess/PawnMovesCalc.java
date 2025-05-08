package chess;

import java.util.ArrayList;
import java.util.Collection;

public class PawnMovesCalc implements PieceMovesCalc {
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        Collection<ChessMove> pawnMoves = new ArrayList<>();

        int row = position.getRow();
        int col = position.getColumn();
        ChessPosition pawnPosition = new ChessPosition(row, col);

        ChessPiece currentPawn = board.getPiece(position);
        boolean isWhite = currentPawn.getTeamColor() == ChessGame.TeamColor.WHITE;

        if (isWhite) {
            return whitePawnMoves(board, pawnPosition, pawnMoves);
        } else {
            return blackPawnMoves(board, pawnPosition, pawnMoves);
        }
    }

    //private void promotion piece
    private void promotionPiece(Collection<ChessMove> pawnMoves, ChessPosition startPosition, ChessPosition endPosition) {
        pawnMoves.add(new ChessMove(startPosition, endPosition, ChessPiece.PieceType.BISHOP));
        pawnMoves.add(new ChessMove(startPosition, endPosition, ChessPiece.PieceType.ROOK));
        pawnMoves.add(new ChessMove(startPosition, endPosition, ChessPiece.PieceType.KNIGHT));
        pawnMoves.add(new ChessMove(startPosition, endPosition, ChessPiece.PieceType.QUEEN));
    }

    //white helper
    private Collection<ChessMove> whitePawnMoves(ChessBoard board, ChessPosition position, Collection<ChessMove> pawnMoves) {
        int pawnRow = position.getRow();
        int pawnCol = position.getColumn();

        ChessPosition pawnForwardOne = new ChessPosition(pawnRow + 1, pawnCol);
        if (pawnRow + 1 == 8) {
            promotionPiece(pawnMoves, position, pawnForwardOne);
        } else {
            if (isWithinBounds(pawnForwardOne) && board.getPiece(pawnForwardOne) == null) {
                pawnMoves.add(new ChessMove(position, pawnForwardOne, null));
            }
        }

        //forward 2
        ChessPosition pawnForwardTwo = new ChessPosition(pawnRow + 2, pawnCol);
        if (pawnRow == 2) {
            if (isWithinBounds(pawnForwardTwo) && board.getPiece(pawnForwardOne) == null && board.getPiece(pawnForwardTwo) == null) {
                pawnMoves.add(new ChessMove(position, pawnForwardTwo, null));
            }
        }


        //capture left
        ChessPosition captureLeft = new ChessPosition(pawnRow + 1, pawnCol - 1);
        if (isWithinBounds(captureLeft)) {
            if (pawnRow + 1 == 8 && board.getPiece(captureLeft) != null && isOpponentTeam(board, position, captureLeft)) {
                promotionPiece(pawnMoves, position, captureLeft);
            } else {
                if (isWithinBounds(captureLeft) && board.getPiece(captureLeft) != null && isOpponentTeam(board, position, captureLeft)) {
                    pawnMoves.add(new ChessMove(position, captureLeft, null));
                }
            }
        }


        //capture right
        ChessPosition captureRight = new ChessPosition(pawnRow + 1, pawnCol + 1);
        if (isWithinBounds(captureRight)) {
            if (pawnRow + 1 == 8 && board.getPiece(captureRight) != null && isOpponentTeam(board, position, captureRight)) {
                promotionPiece(pawnMoves, position, captureRight);
            } else {
                if (board.getPiece(captureRight) != null && isOpponentTeam(board, position, captureRight)) {
                    pawnMoves.add(new ChessMove(position, captureRight, null));
                }
            }
        }

        return pawnMoves;

    }



    //private black helper
    private Collection<ChessMove> blackPawnMoves (ChessBoard board, ChessPosition
    position, Collection < ChessMove > pawnMoves){
        int pawnRow = position.getRow();
        int pawnCol = position.getColumn();

        ChessPosition pawnForwardOne = new ChessPosition(pawnRow - 1, pawnCol);
        if (pawnRow - 1 == 1) {
            promotionPiece(pawnMoves, position, pawnForwardOne);
        } else {
            if (isWithinBounds(pawnForwardOne) && board.getPiece(pawnForwardOne) == null) {
                pawnMoves.add(new ChessMove(position, pawnForwardOne, null));
            }
        }

        //forward 2
        ChessPosition pawnForwardTwo = new ChessPosition(pawnRow - 2, pawnCol);
        if (pawnRow == 7) {
            if (isWithinBounds(pawnForwardTwo) && board.getPiece(pawnForwardOne) == null && board.getPiece(pawnForwardTwo) == null) {
                pawnMoves.add(new ChessMove(position, pawnForwardTwo, null));
            }
        }


        //capture left
        ChessPosition captureLeft = new ChessPosition(pawnRow - 1, pawnCol - 1);
        if (isWithinBounds(captureLeft)) {
            if (pawnRow - 1 == 1 && board.getPiece(captureLeft) != null && isOpponentTeam(board, position, captureLeft)) {
                promotionPiece(pawnMoves, position, captureLeft);
            } else {
                if (board.getPiece(captureLeft) != null && isOpponentTeam(board, position, captureLeft)) {
                    pawnMoves.add(new ChessMove(position, captureLeft, null));
                }
            }
        }


        //capture right
        ChessPosition captureRight = new ChessPosition(pawnRow - 1, pawnCol + 1);
        if (isWithinBounds(captureRight)) {
            if (pawnRow - 1 == 1 && board.getPiece(captureRight) != null && isOpponentTeam(board, position, captureRight)) {
                promotionPiece(pawnMoves, position, captureRight);
            } else {
                if (board.getPiece(captureRight) != null && isOpponentTeam(board, position, captureRight)) {
                    pawnMoves.add(new ChessMove(position, captureRight, null));
                }
            }
        }

        return pawnMoves;
    }
}

